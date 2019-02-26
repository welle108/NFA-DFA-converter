import java.util.HashMap;
import java.util.HashSet;


public class NFA {
    private HashMap<String, State> input_states;
    private HashSet<HashSet<String>> dfa_state_list;
    private HashSet<State> dfa_output_states;
    private HashSet<String> language;
    private State start_state;

    public NFA(HashMap<String, State> input_states, HashSet<String> language, String nfa_start_state, HashSet<String> nfa_accept_states)
    {
        dfa_output_states = new HashSet<>();
        dfa_state_list = new HashSet<>();
        this.input_states = new HashMap<String, State>(input_states);
        this.language = new HashSet<>(language);
        HashSet<String> start_state_name = new HashSet<>(epsilonClosure(input_states.get(nfa_start_state)));
        start_state = new State(start_state_name,language,true,false);
        dfa_output_states.add(start_state);
        dfa_state_list.add(start_state_name);

    }

    public HashSet<String> epsilonClosure(State inputState) {
        HashSet<String> outputClosure = new HashSet<String>(inputState.getName());
        HashSet<String> epsTransitions = new HashSet<String>(inputState.getTransitions("EPS"));

        while(!outputClosure.equals(epsTransitions))
        {

            for(String i : epsTransitions)
            {
               epsTransitions.addAll(input_states.get(i).getEPSvalues());
            }
            outputClosure.addAll(epsTransitions);
        }

        return outputClosure;
    }

    public boolean convertToDFA()
    {
        while(true)
        {
            State tempState;
            HashSet<String> temp_state_name = new HashSet<>();
            for(State s : dfa_output_states)
            {
                for(String i : s.getName())
                {
                    for(String j : language)
                    {
                        for(String k : input_states.get(i).getTransitions(j))
                        {
                            if(!input_states.get(k).getTransitions(j).contains("EM"))
                            {
                                temp_state_name.addAll(input_states.get(k).getTransitions("EPS"));
                            }
                        }

                    }
                }
            }
            break;
        }
        return true;
    }















    /*
    public DFA toDFA(){
        Hashtable<HashSet<String>, Hashtable<String, HashSet<String>>> finalStates = new Hashtable<>();
        DFA outputDFA = new DFA();

        HashSet<String> tempName;
        HashSet<String> tempTransitions;
        Hashtable<String, HashSet<String>> tempTransitionFunction = new Hashtable<>();
        for(String i : input_symbols )
        {
            tempTransitionFunction.put(i,null);
        }

        tempName = new HashSet<>(epsilonClosure(input_states.get(input_start_state)));

        // For each state in start state
        for(String i : tempName)
        {
            // For each symbol in the language
            for(String j : input_symbols)
            {
                //Reinitialize temporary transition list
                tempTransitions = new HashSet<>();
                // For each transition from that symbol
                for(String k : input_states.get(i).getTransitions(j))
                {
                    tempTransitions.addAll(epsilonClosure(input_states.get(k)));
                }
                tempTransitionFunction.put(j,tempTransitions);


            }
        }

        finalStates.put(tempName,tempTransitionFunction);




        return outputDFA;
    }
*/
}

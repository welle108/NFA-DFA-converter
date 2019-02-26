import java.util.HashMap;
import java.util.HashSet;


public class NFA {
    private HashMap<String, State> input_states;
    private HashSet<HashSet<String>> dfa_state_list;
    private HashSet<String> nfa_accept_states;
    private HashSet<HashSet<String>> dfa_accept_states;
    private HashSet<State> dfa_output_states;
    private HashSet<String> language;
    private State start_state;

    public NFA(HashMap<String, State> input_states, HashSet<String> language, String nfa_start_state, HashSet<String> nfa_accept_states)
    {
        dfa_output_states = new HashSet<>();
        dfa_state_list = new HashSet<>();
        this.input_states = new HashMap<String, State>(input_states);
        this.language = new HashSet<>(language);
        start_state = new State(input_states.get(nfa_start_state).getEspilonClosure(),language,true,false);
        System.out.println("Start State for DFA: ");
        start_state.printTransitions();
        /*
        HashSet<String> start_state_name = new HashSet<>(epsilonClosure(input_states.get(nfa_start_state)));
        start_state = new State(start_state_name,language,true,false);
        dfa_output_states.add(start_state);
        dfa_state_list.add(start_state_name);
        */

    }


    public boolean convertToDFA() {
        boolean running = true;
        System.out.println("Entered DFA conversion method");
        while(running) {
            State temp_state;
            for(State s : dfa_output_states) {
                System.out.println("Working on state: "+s.toString());

                for(String i : s.getName()) {

                    for(String j : language) {

                        HashSet<String> temp_transition_function = new HashSet<>();
                        System.out.println("Input is: "+j);

                        if(!input_states.get(i).getTransitions(j).contains("EM")) {

                             for(String k : input_states.get(i).getTransitions(j)){
                                temp_transition_function.addAll(input_states.get(k).getEspilonClosure());
                             }
                        }
                        for(String temp_func : temp_transition_function) {
                            s.addTransition(j,temp_func);
                        }
                        if(!dfa_state_list.contains(s.getTransitions(j)))
                        {
                            temp_state = new State(s.getTransitions(j),language,false,false);
                            dfa_output_states.add(temp_state);
                        }
                    }
                }
                s.printTransitions();
            }


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

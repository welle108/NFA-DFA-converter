import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

public class NFA {
    private HashSet<State> input_states;

    public NFA(HashSet<State> inputStates) {

    }


    public HashSet<String> epsilonClosure(State inputState) {
        HashSet<String> outputClosure = new HashSet<String>(inputState.getName());
        HashSet<String> epsTransitions = new HashSet<String>(inputState.getEPSvalues());

        while(!outputClosure.equals(epsTransitions)){
            for(String i : epsTransitions){
               // epsTransitions.addAll(input_states.get(i).getEPSvalues());
            }
            outputClosure.addAll(epsTransitions);
        }

        return outputClosure;

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

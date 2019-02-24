import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

public class NFA {
    private Hashtable<String, State> input_states;
    private HashSet<String> input_symbols;
    private String input_start_state;
    private HashSet<String> input_accept_states;
    private HashSet<String> input_trans_function;

    public NFA(Hashtable<String, State> states, HashSet<String> symbols, String start_State, HashSet<String> accept_States, HashSet<String> trans_Function) {
        this.input_states = new Hashtable(states);
        this.input_symbols = new HashSet<>(symbols);
        this.input_start_state = start_State;
        this.input_accept_states = new HashSet<>(accept_States);
        this.input_trans_function = new HashSet<>(trans_Function);
    }


    public HashSet<String> epsilonClosure(State inputState) {
        HashSet<String> outputClosure = new HashSet<String>(inputState.getName());
        HashSet<String> epsTransitions = new HashSet<String>(inputState.getEPSvalues());

        while(!outputClosure.equals(epsTransitions)){
            for(String i : epsTransitions){
                epsTransitions.addAll(input_states.get(i).getEPSvalues());
            }
            outputClosure.addAll(epsTransitions);
        }

        return outputClosure;

    }

}
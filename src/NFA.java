import java.util.ArrayList;

public class NFA {
    private ArrayList<String> input_states;
    private ArrayList<String> input_symbols;
    private String input_start_state;
    private ArrayList<String> input_accept_states;
    private ArrayList<String> input_trans_function;

    public NFA(ArrayList<String> states, ArrayList<String> symbols, String start_State, ArrayList<String> accept_States, ArrayList<String> trans_Function) {
        this.input_states = new ArrayList<>(states);
        this.input_symbols = new ArrayList<>(symbols);
        this.input_start_state = start_State;
        this.input_accept_states = new ArrayList<>(accept_States);
        this.input_trans_function = new ArrayList<>(trans_Function);
    }
}


/*
    TODO: Insert .dfa output code

    File dfaSchema = new File("output.dfa");
            if(! dfaSchema.exists()) {
                dfaSchema.createNewFile();
            }
            PrintWriter pw = new PrintWriter(dfaSchema);

            pw.close();
 */
import java.util.ArrayList;

// Public class which constructs FA states from Nfa_Scanner object and converts to DFA
public class Converter {
    ArrayList<String> input_states;
    ArrayList<String> input_symbols;
    String input_start_state;
    ArrayList<String> input_accept_states;
    ArrayList<String> input_trans_function;

    public Converter(ArrayList<String> states, ArrayList<String> symbols, String start_State, ArrayList<String> accept_States, ArrayList<String> trans_Function){
        this.input_states = new ArrayList<>(states);
        this.input_symbols = new ArrayList<>(symbols);
        this.input_start_state = start_State;
        this.input_accept_states = new ArrayList<>(accept_States);
        this.input_trans_function = new ArrayList<>(trans_Function);

        System.out.println("Converter created. Displaying data:");
        System.out.println("-----------------------------------------");
        System.out.println("NFA states:");
        for( String i : input_states){
            System.out.print(i);
        }
        System.out.print("\n");
        System.out.print("\n");
        System.out.println("Input Symbols");
        for( String i : input_symbols){
            System.out.print(i);
        }
        System.out.print("\n");
        System.out.print("\n");
        System.out.println("Start State");
        System.out.println(input_start_state);
        System.out.print("\n");
        System.out.println("Accept States: ");
        for( String i : input_accept_states){
            System.out.print(i);
        }
        System.out.print("\n");
        System.out.print("\n");
        System.out.println("Trans Function");
        for( String i : input_trans_function){
            System.out.println(i);
        }




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
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

    /*
    Public class for Scanning .nfa files and delivering the NFA schema to
    an Nfa_Converter object in the form of ArrayList<String>'s and Strings.
     */

public class Nfa_Scanner {
    private String filename;
    private HashSet<String> input_states;
    private HashSet<String> symbols;
    private String start_state;
    private HashSet<String> accept_states;
    private HashSet<String> trans_strings;
    private HashMap<String,State> output_state_table;



    //Initializes object variables
    public Nfa_Scanner(String filename)
    {
            input_states = new HashSet<>();
            symbols = new HashSet<>();
            accept_states = new HashSet<>();
            trans_strings = new HashSet<>();
            start_state = null;
            output_state_table = new HashMap<>();
            this.filename = filename;
    }

    /*
    Reads .nfa file and parses data. Pattern p uses regex to search for data group between curly braces to extract
    data from lines where necessary. Data is then passed to Converter object constructor where it is assembled into
    State objects
     */

    public void Scan()
    {
        try {
            File nfaSchema = new File(filename);
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(nfaSchema)));
            String line;
            Pattern p = Pattern.compile("\\{([^}]*)\\}");
            Matcher m;

            //Get states
            line = in.readLine();
            m = p.matcher(line);
            while (m.find()) {
                input_states.add(m.group(1));
            }

            // Create language
            line = in.readLine();
            symbols = new HashSet<String>(Arrays.asList(line.split("\\s+")));


            // Get start state
            line = in.readLine();
            m = p.matcher(line);
            if(m.find())
            {
                start_state = m.group(1);
                start_state = start_state.replaceAll("\\s","");
            }

            //Get accept states
            line = in.readLine();
            m = p.matcher(line);
            while (m.find()) {
                accept_states.add(m.group(1));
            }

            // Read transition function
            while((line = in.readLine()) != null)
            {
                trans_strings.add(line);
                //pw.println(line);

            }
            in.close();

        }catch (IOException e){
            System.out.println("Error opening file");
            e.printStackTrace();
            System.exit(1);
        }

    }

    public boolean generateStates()
    {
        State tempState;
        Pattern p = Pattern.compile("\\{([^}]*)\\}");
        Pattern q = Pattern.compile(",\\s(.*)=");

        for (String i : input_states)
        {
            HashSet<String> tempName = new HashSet<>();
            tempName.add(i);
            if(i.equals(start_state))
            {
                tempState = new State(tempName,symbols,true,true);
            }
            else
            {
                tempState = new State(tempName,symbols,false,true);
            }
            output_state_table.put(tempState.toString(),tempState);
        }

        for (String j : trans_strings)
        {
            String state = new String();
            String input = new String();
            String dest = new String();
            Matcher m = p.matcher(j);
            Matcher n = q.matcher(j);
            if (m.find())
            {
                state = m.group(1);
            }
            if (n.find())
            {
                input = n.group(1);
            }
            if (m.find())
            {
                dest = m.group(1);
            }
            output_state_table.get(state).addTransition(input,dest);
        }
        return true;
    }

    public HashSet<String> getInputStates(){
        return input_states;
    }

    public HashSet<String> getAcceptStates() {
        return accept_states;
    }

    public HashSet<String> getSymbols(boolean remove_eps) {
        if(remove_eps && symbols.contains("EPS"))
        {
            symbols.remove("EPS");
        }
        return symbols;
    }

    public HashSet<String> getTransFunction() {
        return trans_strings;
    }

    public String getStartState() {
        return start_state;
    }

    public HashMap<String, State> getOutputStates()
    {
        return output_state_table;
    }


    public void printNFASchema()
    {
        System.out.println("NFA schema: ");
        System.out.println("---------------------------------------");
        System.out.println("States: ");
        for(String i : input_states)
        {
            System.out.print(i);
        }
        System.out.print("\n");
        System.out.println("Symbols: ");
        for(String i : symbols)
        {
            System.out.print(i);
        }
        System.out.print("\n");
        System.out.println("Start State: ");
        System.out.println(start_state);
        System.out.println("Accept States: ");
        for(String i : accept_states)
        {
            System.out.print(i);
        }
        System.out.print("\n");

        System.out.println("Transition Function: ");
        for(String i : trans_strings)
        {
            System.out.println(i);
        }
        System.out.print("\n");
        System.out.println("---------------------------------------");

    }

    public void printOutputStates()
    {
        Set<String> keys = output_state_table.keySet();
        System.out.println("--------------------------------------");
        for(String k : keys)
        {
            output_state_table.get(k).printTransitions();
            System.out.println("--------------------------------------");
        }

    }
}


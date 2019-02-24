import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

    /*
    Public class for Scanning .nfa files and delivering the NFA schema to
    an Nfa_Converter object in the form of ArrayList<String>'s and Strings.
     */

public class Nfa_Scanner {
    private String filename;
    private HashSet<String> states;
    private HashSet<String> symbols;
    private String start_state;
    private HashSet<String> accept_states;
    private HashSet<String> trans_function;
    private Hashtable<String, State> nfaStateTable;


    //Initializes object variables
    public Nfa_Scanner(String filename)
    {
            this.nfaStateTable = new Hashtable<>();
            this.states = new HashSet<String>();
            this.symbols = new HashSet<>();
            this.accept_states = new HashSet<>();
            this.trans_function = new HashSet<>();
            this.start_state = null;
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
                states.add(m.group(1));
            }

            // Get symbols
            line = in.readLine();
            symbols = new HashSet<String>(Arrays.asList(line.split("\\s+")));

            // Get start state
            line = in.readLine();
            m = p.matcher(line);
            if(m.find())
            {
                start_state = m.group(1);
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
                trans_function.add(line);
                //pw.println(line);

            }
            in.close();

        }catch (IOException e){
            System.out.println("Error opening file");
            e.printStackTrace();
            System.exit(1);
        }

    }

    public void printAll()
    {
        System.out.println("NFA schema: ");
        System.out.println("---------------------------------------");
        System.out.println("States: ");
        for(String i : states)
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
        for(String i : trans_function)
        {
            System.out.println(i);
        }
        System.out.print("\n");
        System.out.println("---------------------------------------");

    }

    public HashSet<String> getStates(){
        return states;
    }

    public HashSet<String> getAccept_states() {
        return accept_states;
    }

    public HashSet<String> getSymbols() {
        return symbols;
    }

    public HashSet<String> getTrans_function() {
        return trans_function;
    }

    public String getStart_state() {
        return start_state;
    }
}

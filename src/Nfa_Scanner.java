import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

    /*
    Public class for Scanning .nfa files and delivering the NFA schema to
    an Nfa_Converter object in the form of ArrayList<String>'s and Strings.
     */

public class Nfa_Scanner {
    private String filename;
    private ArrayList<String> states;
    private ArrayList<String> symbols;
    private String start_state;
    private ArrayList<String> accept_states;
    private ArrayList<String> trans_function;

    //Initializes object variables
    public Nfa_Scanner(String filename)
    {
            this.states = new ArrayList<String>();
            this.symbols = null;
            this.accept_states = new ArrayList<String>();
            this.trans_function = new ArrayList<String>();
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
            symbols = new ArrayList<String>(Arrays.asList(line.split("\\s+")));

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

    public ArrayList<String> getStates(){
        return states;
    }

    public ArrayList<String> getAccept_states() {
        return accept_states;
    }

    public ArrayList<String> getSymbols() {
        return symbols;
    }

    public ArrayList<String> getTrans_function() {
        return trans_function;
    }

    public String getStart_state() {
        return start_state;
    }
}

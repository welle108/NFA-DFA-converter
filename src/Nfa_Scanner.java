import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Public class for Scanning .nfa files and delivering the NFA schema to
an Nfa_Converter object in the form of ArrayList<String>'s and Strings.
 */

public class Nfa_Scanner {
    String filename;
    ArrayList<String> states;
    ArrayList<String> symbols;
    String start_state;
    ArrayList<String> accept_states;
    ArrayList<String> trans_function;

    //Initializes object variables
    public Nfa_Scanner(String filename)
    {
            this.states = new ArrayList<String>();
            this.symbols = new ArrayList<String>();
            this.accept_states = new ArrayList<String>();
            this.trans_function = new ArrayList<String>();
            this.start_state = null;
            this.filename = filename;
    }

    //Scans file and properly formats data
    public void Scan()
    {
        try {
            File nfaSchema = new File(filename);
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(nfaSchema)));


            /*File dfaSchema = new File("output.dfa");
            if(! dfaSchema.exists()) {
                dfaSchema.createNewFile();
            }
            PrintWriter pw = new PrintWriter(dfaSchema);
            */

            String line;
            Pattern p = Pattern.compile("\\{([^}]*)\\}");

            //Get states
            line = in.readLine();
            Matcher m = p.matcher(line);
            while (m.find()) {
                states.add(m.group(1));
            }

            //Get symbols
            line = in.readLine();

            // Start state

            //Get accept states

            // Read transition function
            while((line = in.readLine()) != null)
            {


                //pw.println(line);

            }
            //pw.close();
            in.close();


        }catch (IOException e){
            System.out.println("Error opening file");
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("File closed. Printing contents of 'states: '");
        for( String i : states){
            System.out.println(i);
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

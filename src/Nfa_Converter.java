import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


    /*
    Public main class which performs macro instructions using class objects
    NFA is input through Nfa_Scanner object which parses and sends data to Converter object
    which facilitates NFA-DFA conversion
    */
public class Nfa_Converter {


    public static void main(String[] args) {

        /*if(args.length > 0) {
            Nfa_Scanner mScan = new Nfa_Scanner(args[0]);
            mScan.Scan();
            mScan.printAll();



        }
        else
        {
            System.err.println("Invalid arg count: "+args.length);
            System.exit(1);
        }
        */
        HashSet<String> name = new HashSet<>();
        HashSet<String> language = new HashSet<>();
        name.add("1");
        language.add("a");
        language.add("b");
        State mState = new State(name, language);
        mState.printTransitions();

    }
}

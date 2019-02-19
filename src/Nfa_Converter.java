import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/*  Public main class which performs macro instructions using class objects
    NFA is input through Nfa_Scanner object which parses and sends data to Converter object
    which facilitates NFA-DFA conversion
    */
public class Nfa_Converter {


    public static void main(String[] args) {
        if(args.length > 0) {
            Nfa_Scanner mScan = new Nfa_Scanner(args[0]);
            mScan.Scan();
            Converter converter = new Converter(mScan.getStates(),mScan.getSymbols(),mScan.getStart_state(),mScan.getAccept_states(),mScan.getTrans_function());

        }
        else
        {
            System.err.println("Invalid arg count: "+args.length);
            System.exit(1);
        }


    }
}

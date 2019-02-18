import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Nfa_Converter {


    public static void main(String[] args) {
        if(args.length > 0) {
            Nfa_Scanner myScan = new Nfa_Scanner(args[0]);
            myScan.Scan();
        }
        else
        {
            System.err.println("Invalid arg count: "+args.length);
            System.exit(1);
        }

    }
}

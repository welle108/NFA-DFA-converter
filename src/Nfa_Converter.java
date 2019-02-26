 /*
    Public main class which performs macro instructions using class objects
    NFA is input through Nfa_Scanner object which parses and sends data to Converter object
    which facilitates NFA-DFA conversion
*/

public class Nfa_Converter {

    public static void main(String[] args) {

        if(args.length > 0) {
            NFA inputNFA;
            Nfa_Scanner mScan = new Nfa_Scanner(args[0]);
            mScan.Scan();
            mScan.generateStates();
            inputNFA = new NFA(mScan.getOutputStates(),mScan.getSymbols(true),mScan.getStartState(),mScan.getAcceptStates());
            //inputNFA.convertToDFA();
        }
        else
        {
            System.err.println("Invalid arg count: "+args.length);
            System.exit(1);
        }





    }
}

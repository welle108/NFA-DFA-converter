import java.util.ArrayList;
import java.util.Hashtable;

public class State {

    private ArrayList<String> name;
    private Hashtable<String, String> transition_function;

    public State(ArrayList<String> name){
        this.name = name;
        this.transition_function = new Hashtable<String, String>();
    }

    public void addTransition(String input, String nextState)
    {
        transition_function.put(input, nextState);
    }


}

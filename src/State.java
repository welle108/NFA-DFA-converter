import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

public class State {

    private HashSet<String> name;
    private Hashtable<String, HashSet<String>> transition_function;

    public State(HashSet<String> name){
        this.name = new HashSet<String>(name);
        this.transition_function = new Hashtable<String, HashSet<String>>();
    }

    public void addTransition(String input, HashSet<String> nextState)
    {
        transition_function.put(input, nextState);
    }

    public HashSet<String> getName() {
        return name;
    }

    public HashSet<String> getEPSvalues()
    {
        return transition_function.get("EPS");
    }
}

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

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

    public String stringOfName(){
        String nameString = "{" + name.toString().substring(1, name.size()-1) + "}";
        return nameString;
    }

    public String stringOfTransition(){
        Set<String> keys = transition_function.keySet();
        StringBuffer s = new StringBuffer();


        for(String key : keys){
            s.append(this.stringOfName());
            s.append(", " + key + " = ");
            HashSet<String> nextState = transition_function.get(key);
            s.append('{'+ nextState.toString().substring(1, nextState.size()-1)
                    + "}\n");
        }

        return s.toString();
    }
}

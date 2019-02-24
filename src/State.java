import javax.lang.model.type.NullType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class State {

    private HashSet<String> name;
    private Hashtable<String, HashSet<String>> transition_function;

    public State(HashSet<String> name, HashSet<String> language){
        this.name = new HashSet<String>(name);
        this.transition_function = new Hashtable<String, HashSet<String>>();
        for(String i : language)
        {
            transition_function.put(i, new HashSet<>());
            transition_function.get(i).add("EM");
        }
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

    public HashSet<String> getTransitions(String input)
    {
        return transition_function.get(input);
    }

    public void printTransitions()
    {
        System.out.println("State: "+toString()+" transitions");
        Set<String> tranSet = transition_function.keySet();
        for(String i : tranSet)
        {
            System.out.println("Input: "+i);
            for(String j : transition_function.get(i))
            {
                System.out.println(j);
            }
        }
    }


    public String toString(){
        String name = new String();
        for(String i : this.name)
        {
            name+=i;
        }
        return name;
    }
}

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;

public class State {

    private HashSet<String> name;
    private ArrayList<Transition> transitionFunction;

    public State(HashSet<String> name){
        this.name = name;
        this.transitionFunction = new ArrayList<Transition>();
    }

    //returns true if successful;
    public void addTransition(String input, HashSet<String> nextState)
    {
        Transition t = new Transition(input, nextState);
        transitionFunction.add(t);
    }

    public void setName(HashSet<String> name) {
        this.name = name;
    }

    public HashSet<String> getName() {
        return name;
    }

    public ArrayList<Transition> getTransitionFunction() {
        return transitionFunction;
    }
}

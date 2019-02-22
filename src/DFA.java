import java.util.ArrayList;
import java.util.HashSet;

public class DFA {
    private State startState;
    private HashSet<State> acceptStates;
    private HashSet<String> language;
    private HashSet<State> states;

    public DFA(State startState, HashSet<String> language){
        this.startState = startState;
        this.language = language;
        this.acceptStates = new HashSet<State>();
        this.states = new HashSet<State>();
    }

    public void setStartState(State startState) {
        this.startState = startState;
    }

    public State getStartState() {
        return startState;
    }

    public void setAcceptStates(HashSet<State> acceptStates) {
        this.acceptStates = acceptStates;
    }

    public HashSet<State> getAcceptStates() {
        return acceptStates;
    }


    public HashSet<String> getLanguage() {
        return language;
    }

    public void setLanguage(HashSet<String> language) {
        this.language = language;
    }

    public boolean addLanguage(String lang){
        boolean success = this.language.add(lang);
        return success;
    }

    public void setStates(HashSet<State> states) {
        this.states = states;
    }

    public HashSet<State> getStates() {
        return states;
    }

    public boolean addStates(State newState){
        boolean success = this.states.add(newState);
        return success;
    }

    public boolean addStates(State newState, boolean accept){
        boolean success = this.states.add(newState);
        boolean success2 = true;
        if(accept == true){
             success2 = this.acceptStates.add(newState);
        }
        return (success && success2);
    }

    public void printDFA(){

    }
}

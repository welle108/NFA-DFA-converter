import java.awt.desktop.SystemSleepEvent;
import java.util.HashSet;
import java.util.Set;
import java.util.*;

public class State {
    private String string_name;
    private boolean is_start_state;
    private boolean is_accept_state;
    private HashSet<String> name;
    private HashSet<String> language;
    private HashSet<String> epsilon_closure;
    private HashMap<String, HashSet<String>> transition_function;

    public State() {
        is_start_state=false;
        name = null;
        language = null;
        epsilon_closure = null;
        transition_function = null;
    }


    public State(HashSet<String> name, HashSet<String> symbols, boolean isStart, boolean isNFA){
        this.language = new HashSet<>(symbols);
        this.name = new HashSet<>(name);
        string_name = new String(this.toString());
        if(isNFA)
        {
            language.add("EPS");
        }
        this.transition_function = new HashMap<>();
        for(String i : language) {
            transition_function.put(i, new HashSet<String>());
            if(i.equals("EPS")) {
                transition_function.get(i).add(string_name);
            }
            else{
                transition_function.get(i).add("EM");
            }
        }
        this.is_start_state = isStart;
        epsilon_closure = new HashSet<>();
    }


    public void addTransition(String input, String nextState) {
        input = input.replaceAll("\\s","");
    if(transition_function.containsKey(input)) {
            try {
                if (transition_function.get(input).contains("EM")) ;{
                    transition_function.get(input).remove("EM");
                }
                transition_function.get(input).add(nextState);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void addAllTransitions(String input, HashSet<String> state_values)
    {
        input = input.replaceAll("\\s","");
        if(transition_function.containsKey(input)) {
            try {
                if (transition_function.get(input).contains("EM")) ;{
                    transition_function.get(input).remove("EM");
                }
                transition_function.get(input).addAll(state_values);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public boolean setName(HashSet<String> name) {
        this.name = new HashSet<>(name);
        return true;
    }

    public boolean setLanguage(HashSet<String> language) {
        this.language = new HashSet<>(language);
        return true;
    }

    public boolean setIsStartState(boolean isStart) {
        this.is_start_state = isStart;
        return true;
    }

    public boolean setIsNFA(boolean isNFA)
    {

        return true;
    }

    public boolean setIsAcceptState(boolean isAccept) {
        this.is_accept_state = isAccept;
        return true;
    }

    public boolean setEpsilonClosure(HashSet<String> inputClosure){
        epsilon_closure = new HashSet<>(inputClosure);
        return true;
    }

    public HashSet<String> getName() {
        return name;
    }

    public boolean getIsStartState(){return is_start_state;}

    public boolean getIsAcceptState(){return is_accept_state;}

    public HashSet<String> getTransitions(String input)
    {
        return transition_function.get(input);
    }

    public HashSet<String> getEpsilonClosure(){ return epsilon_closure; }


    public String toString(){
        String name = new String();
        for(String i : this.name)
        {
            name+=i;
        }
        return name;
    }

    public void printTransitions()
    {
        System.out.println("State: " + toString());
        System.out.println("--------------------------------------");
        System.out.println("Transitions");
        System.out.println("-------------");
        Set<String> tranSet = transition_function.keySet();
        for (String i : tranSet) {
            System.out.println("Input: " + i);
            for (String j : transition_function.get(i)) {
                System.out.println(j);
            }
        }
        System.out.println("Epsilon Closure");
        for(String j : epsilon_closure)
        {
            System.out.print(j);
        }
            System.out.print("\n");
        if (is_start_state) {
            System.out.println("Is Start State");
        }
        if(is_accept_state){
            System.out.println("Is Accept State");
        }
    }

    public String stringOfName(){
        String nameString = "{" + name.toString().substring(0, name.size()-1) + "}";
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

    public String getStateStringName()
    {
        String state_string_name = new String();
        state_string_name+="{";
        for(String s : name)
        {
            state_string_name+=s;
            state_string_name+=",";
        }
        state_string_name = state_string_name.substring(0, state_string_name.length()-1);
        state_string_name+="}";
        return state_string_name;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof State)
        {
            return toString().equals(obj.toString());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

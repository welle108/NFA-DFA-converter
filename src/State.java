import java.util.HashSet;
import java.util.Set;
import java.util.*;

public class State {
    private boolean is_start_state;
    private HashSet<String> name;
    private HashSet<String> language;
    private HashMap<String, HashSet<String>> transition_function;

    public State()
    {
        this.is_start_state=false;
        name = null;
        language = null;
        transition_function = null;
    }


    public State(HashSet<String> name, HashSet<String> symbols, boolean isStart, boolean isNFA){
        this.name = new HashSet<>(name);
        this.transition_function = new HashMap<>();
        if(isNFA)
        {
            symbols.add("EPS");
        }
        for(String i : symbols)
        {
            transition_function.put(i, new HashSet<>());

            if(i.equals("EPS"))
            {
                transition_function.get(i).add(this.toString());
            }
            else
            {
                transition_function.get(i).add("EM");
            }
        }
        this.is_start_state = isStart;
    }


    public void addTransition(String input, String nextState)
    {
        input = input.replaceAll("\\s","");
    if(transition_function.containsKey(input))
        {
            try {
                if (transition_function.get(input).contains("EM")) ;
                {
                    transition_function.get(input).remove("EM");
                }
                transition_function.get(input).add(nextState);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean setName(HashSet<String> name)
    {
        this.name = new HashSet<>(name);
        return true;
    }

    public boolean setLanguage(HashSet<String> language)
    {
        this.language = new HashSet<>(language);
        return true;
    }

    public boolean setIsStartState(boolean isStart)
    {
        this.is_start_state = isStart;
        return true;
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
        if (is_start_state) {
            System.out.println("Is Start State");
        }

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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class NFA {
    private HashMap<String, State> input_states;
    private HashSet<HashSet<String>> dfa_check_list;
    private HashSet<HashSet<String>> dfa_state_list;
    private HashSet<String> nfa_accept_states;
    private HashSet<State> dfa_accept_states;
    private HashSet<State> dfa_output_states;
    private HashSet<String> language;
    private State start_state;

    public NFA(HashMap<String, State> input_states, HashSet<String> language, String nfa_start_state, HashSet<String> nfa_accept_states)
    {
        dfa_accept_states = new HashSet<>();
        dfa_output_states = new HashSet<>();
        dfa_state_list = new HashSet<>();
        this.input_states = new HashMap<String, State>(input_states);
        this.language = new HashSet<>(language);
        this.nfa_accept_states = new HashSet<>(nfa_accept_states);
        start_state = new State(input_states.get(nfa_start_state).getEpsilonClosure(), language, true, false);


        HashSet<String> temp_set = new HashSet<>();
        for(String state : start_state.getName())
        {
            temp_set.add(state);
        }
        dfa_output_states.add(start_state);
        dfa_state_list.add(start_state.getName());
        for(String s : start_state.getName())
        {
            for(String l : language)
            {
                HashSet<String> temp_trans = new HashSet<>(input_states.get(s).getTransitions(l));

                if(!temp_trans.contains("EM"))
                {
                    start_state.addAllTransitions(l, temp_trans);
                }
                for(String accept_states: nfa_accept_states)
                {
                    if(start_state.getName().contains(accept_states))
                    {
                        start_state.setIsAcceptState(true);
                    }
                }
            }
        }

    start_state.printTransitions();
    }

    public boolean convertToDFA()
    {
        HashSet<State> temp_state_set = new HashSet<>();
        HashSet<HashSet<String>> temp_star_trans = new HashSet<>();
        boolean running = true;
        while(running)
        {
            dfa_check_list = new HashSet<>(dfa_state_list);
            State temp_state;

            for(State s : dfa_output_states) {
                for (String i : s.getName()) {
                    for(String l : language)
                    {
                        HashSet<String> temp_trans = new HashSet<>(input_states.get(i).getTransitions(l));

                        if(temp_trans.contains("EM"))
                        {
                            continue;
                        }
                        for(String string : input_states.get(i).getTransitions("EPS"))
                        {
                            temp_trans.add(string);
                        }
                        if(!dfa_state_list.contains(temp_trans))
                        {
                            dfa_check_list.add(temp_trans);
                            temp_state = new State(temp_trans,language,false,false);
                            temp_state_set.add(temp_state);
                            for(String accept_states: nfa_accept_states)
                            {
                                if(temp_state.getName().contains(accept_states))
                                {
                                    temp_state.setIsAcceptState(true);
                                }
                            }
                        }
                        s.addAllTransitions(l, temp_trans);

                    }
                }

            }

            if(dfa_state_list.equals(dfa_check_list))
            {
                break;
            }


            dfa_state_list.addAll(dfa_check_list);
            dfa_output_states.addAll(temp_state_set);

                setAcceptStates();
                printAllDFAStates();

        }
        return true;
    }

    private void setAcceptStates()
    {
        for(State s : dfa_output_states)
        {
            for(String i : nfa_accept_states)
            {
                if(s.getName().contains(i))
                {
                    s.setIsAcceptState(true);
                    dfa_accept_states.add(s);
                }
            }

        }
    }

    public HashSet<State> getDFAAcceptStates()
    {
        return dfa_accept_states;
    }

    public HashSet<State> getAllDFAStates()
    {
        return dfa_output_states;
    }

    public State getStartState()
    {
        return start_state;
    }

    public HashSet<String> getLanguage()
    {
        return language;
    }

    public boolean exportResults()
    {
        File outputFile = new File("output.dfa");
        try {
            outputFile.createNewFile();
            FileWriter fw = new FileWriter((outputFile.getAbsoluteFile()));
            BufferedWriter bw = new BufferedWriter(fw);
            String line = new String();
            //Write States
            for(State s : dfa_output_states)
            {
                line+=s.getStateStringName();
                line+="\t";

            }
            System.out.println(line);
            bw.write(line);
            bw.write("\n");

            //Write Language
            line = new String();
            for(String i : language)
            {
                line += i;
                line += "\t";
            }
            System.out.println(line);
            bw.write(line);
            bw.write("\n");
            //Start State
            line = new String(start_state.getStateStringName());
            bw.write(line);
            bw.write("\n");
            System.out.println(line);

            //Accept State
            line = new String();
            for(State s : dfa_output_states)
            {
                if(s.getIsAcceptState())
                {
                    line+=s.getStateStringName();
                    line+="\t";
                }
            }
            System.out.println(line);
            bw.write(line);
            bw.write("\n");
            //Transition Function
            line = new String();
            for(State s : dfa_output_states)
            {
                for(String l : language)
                {
                    line = s.getStateStringName()+", "+l+" = ";
                    String dest = new String();
                    dest+="{";
                    for(String i : s.getTransitions(l))
                    {
                        dest+=i;
                        dest+=",";
                    }
                    dest = dest.substring(0,dest.length()-1);
                    dest+="}";
                    line+=dest;
                    System.out.println(line);
                    bw.write(line);
                    bw.write("\n");

                }
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;

    }

    public boolean printAllDFAStates()
    {
        for(State s : dfa_output_states)
        {
            s.printTransitions();
            System.out.println();
        }

        return true;
    }


}

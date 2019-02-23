import java.util.ArrayList;
import java.util.HashSet;

public class Transition {
    String letter;
    HashSet<String> nextState;

    Transition(String letter, HashSet<String> nextState){
        this.letter = letter;
        this.nextState = nextState;
    }
}

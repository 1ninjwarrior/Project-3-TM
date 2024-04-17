package tm;

import java.util.HashMap;
import java.util.Map;

class TuringMachine {
    private String tape;
    private int headPosition;
    private String currentState;
    private Map<String, String[]> transitionFunction;

    public TuringMachine(String initialTape, String initialState, Map<String, String[]> transitionFunction) {
        this.tape = initialTape;
        this.headPosition = 0;
        this.currentState = initialState;
        this.transitionFunction = transitionFunction;
    }

    public void run() {
        while (true) {
            String currentSymbol = String.valueOf(tape.charAt(headPosition));
            String key = currentState + "_" + currentSymbol;

            if (!transitionFunction.containsKey(key)) {
                break;
            }

            String[] transition = transitionFunction.get(key);
            String newSymbol = transition[0];
            String direction = transition[1];
            String newState = transition[2];

            tape = tape.substring(0, headPosition) + newSymbol + tape.substring(headPosition + 1);
            headPosition += direction.equals("R") ? 1 : -1;
            currentState = newState;
        }
    }

    public String getTape() {
        return tape;
    }
}

public class Main {
    public static void main(String[] args) {
        String initialTape = "0000000000";
        String initialState = "q0";

        Map<String, String[]> transitionFunction = new HashMap<>();
        transitionFunction.put("q0_0", new String[]{"1", "R", "q1"});
        transitionFunction.put("q1_0", new String[]{"0", "R", "q0"});

        TuringMachine turingMachine = new TuringMachine(initialTape, initialState, transitionFunction);
        turingMachine.run();

        System.out.println("Final tape: " + turingMachine.getTape());
    }
}
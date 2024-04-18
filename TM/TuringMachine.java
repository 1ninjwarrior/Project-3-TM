package tm;

import java.util.Map;
import java.util.HashMap;

public class TuringMachine {
    private Map<Integer, TMState> states;
    private int[] tape;
    private int currentState;
    private int headPosition;

    public TuringMachine(Map<Integer, TMState> states, String inputString) {
        this.states = states;
        this.tape = new int[inputString.length() + 2]; // +2 for left and right sentinels
        this.currentState = 0;
        this.headPosition = 1;

        // Initialize the tape with the input string
        for (int i = 0; i < inputString.length(); i++) {
            tape[i + 1] = inputString.charAt(i) - '0';
        }
    }

    public void simulate() {
        while (currentState != states.size() - 1) { // While not in the halting state
            int currentSymbol = tape[headPosition];
            TMState.Transition transition = states.get(currentState).getTransition(currentSymbol);

            // Update the tape
            tape[headPosition] = transition.getWriteSymbol();

            // Move the head position
            if (transition.getDirection() == 'L') {
                headPosition--;
            } else {
                headPosition++;
            }

            // Update the current state
            currentState = transition.getNextState();
        }
    }

    public void printTape() {
        StringBuilder sb = new StringBuilder();
        for (int symbol : tape) {
            if (symbol != 0) {
                sb.append(symbol);
            }
        }
        System.out.println(sb.toString());
    }
}
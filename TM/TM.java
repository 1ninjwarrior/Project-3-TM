package tm;

import java.util.Map;
import java.util.HashMap;

public class TM {
    private Map<Integer, TMState> states;
    private int[] tape;
    private int currentState;
    private int headPosition;

    public TM(Map<Integer, TMState> states, String inputString) {
        this.states = states;
        this.tape = new int[Math.max(inputString.length(), 1) + 2]; // +2 for left and right sentinels
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

            if (transition == null) {
                break; // If there is no valid transition, halt the machine
            }

            // Update the tape
            tape[headPosition] = transition.getWriteSymbol();

            // Move the head position
            if (transition.getDirection() == 'L') {
                headPosition = Math.max(headPosition - 1, 1);
            } else {
                headPosition = Math.min(headPosition + 1, tape.length - 2);
            }

            // Update the current state
            currentState = transition.getNextState();
        }
    }

    public void printTape() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < tape.length - 1; i++) {
            sb.append(tape[i]);
        }
        System.out.println(sb.toString());
    }
}
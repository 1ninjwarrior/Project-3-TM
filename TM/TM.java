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
        this.tape = new int[1000]; // Increase the tape size to accommodate larger inputs
        this.currentState = 0;
        this.headPosition = 500; // Start the head position in the middle of the tape

        // Initialize the tape with the input string
        for (int i = 0; i < inputString.length(); i++) {
            tape[headPosition + i] = inputString.charAt(i) - '0';
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
                headPosition--;
            } else {
                headPosition++;
            }

            // Update the current state
            currentState = transition.getNextState().getStateNumber();
        }
    }

    public void printTape() {
        StringBuilder sb = new StringBuilder();
        int startIndex = 0;
        int endIndex = tape.length - 1;
        
        // Find the start index of non-zero symbols
        while (startIndex < tape.length && tape[startIndex] == 0) {
            startIndex++;
        }
        
        // Find the end index of non-zero symbols
        while (endIndex >= 0 && tape[endIndex] == 0) {
            endIndex--;
        }
        
        // Print the tape content
        for (int i = startIndex; i <= endIndex; i++) {
            sb.append(tape[i]);
        }
        
        String tapeContent = sb.toString();
        System.out.println(tapeContent);
        System.out.println("output length: " + tapeContent.length());
        
        int sumOfSymbols = 0;
        for (char symbol : tapeContent.toCharArray()) {
            sumOfSymbols += symbol - '0';
        }
        System.out.println("sum of symbols: " + sumOfSymbols);
    }
}
package tm;

import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class TM {
    private Map<Integer, TMState> states;
    private List<Integer> tape;
    private int currentState;
    private int headPosition;

    public TM(Map<Integer, TMState> states, String inputString) {
        this.states = states;
        this.tape = new ArrayList<>(Collections.nCopies(1000, 0)); // Increase the tape size to accommodate larger inputs
        this.currentState = 0;
        this.headPosition = 0; // Start the head position in the middle of the tape

        // Initialize the tape with the input string
        for (int i = 0; i < inputString.length(); i++) {
            set(headPosition + i, inputString.charAt(i) - '0');
        }
    }


    public void simulate() {
        while (currentState != states.size() - 1) { // While not in the halting state
            int currentSymbol = tape.get(headPosition);
            TMState.Transition transition = states.get(currentState).getTransition(currentSymbol);

            if (transition == null) {
                break; // If there is no valid transition, halt the machine
            }

            // Update the tape
            set(headPosition, transition.getWriteSymbol());

            // Move the head position
            if (transition.getDirection() == 'L') {
                if (headPosition == 0) {
                    // If the head position is already at the start of the tape, expand the tape at the beginning
                    tape.add(0, 0);
                } else {
                    headPosition--;
                }
            } else {
                headPosition++;
                // Check if the head position is out of bounds of the current tape size
                if (headPosition >= tape.size()) {
                    // Expand the tape by adding a zero at the end
                    tape.add(0);
                }
            }

            // Update the current state
            currentState = transition.getNextState().getStateNumber();
        }
    }

    public void expandTape() {
        tape.addAll(Collections.nCopies(1000, 0)); // Add 1000 more zeros to the end of the tape
    }

    public void printTape() {
        StringBuilder sb = new StringBuilder();
        int startIndex = 0;
        int endIndex = tape.size() - 1;
        
        // Find the start index of non-zero symbols
        while (startIndex < tape.size() && tape.get(startIndex) == 0) {
            startIndex++;
        }
        
        // If the start index is not 0 and the previous symbol is 0, include it
        if (startIndex > 0 && tape.get(startIndex - 1) == 0) {
            startIndex--;
        }
        
        // Find the end index of non-zero symbols
        while (endIndex >= 0 && tape.get(endIndex) == 0) {
            endIndex--;
        }
        
        // Print the tape content
        for (int i = startIndex; i <= endIndex; i++) {
            sb.append(tape.get(i));
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

    public void set(int index, int value) {
        // Check if the index is out of bounds of the current tape size
        if (index >= tape.size()) {
            // Calculate how many more elements we need
            int additionalElementsNeeded = index - tape.size() + 1;
            // Expand the tape by the number of additional elements needed
            tape.addAll(Collections.nCopies(additionalElementsNeeded, 0));
        }
        // Set the value on the tape
        tape.set(index, value);
    }
}
package tm;

import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a Turing Machine object
 * 
 * @authors Jaden Dawdy, Xian Ma
 */
public class TM implements TMInterface {
    private Map<Integer, TMState> states;
    private List<Integer> tape;
    private int currentState;
    private int headPosition;

    /**
     * Constructs a Turing Machine with the given states and input string
     * @param states
     * @param inputString
     */
    public TM(Map<Integer, TMState> states, String inputString) {
        this.states = states;
        this.tape = new ArrayList<>(Collections.nCopies(1000, 0)); // initial tape size is 1000
        this.currentState = 0;
        this.headPosition = 0;
        
        // initialize the tape
        for (int i = 0; i < inputString.length(); i++) {
            // convert char to int
            set(headPosition + i, inputString.charAt(i) - '0');
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void simulate() {
        while (currentState != states.size() - 1) {
            int currentSymbol = tape.get(headPosition);
            Transition transition = states.get(currentState).getTransition(currentSymbol);
            
            if (transition == null) {
                break;
            }

            set(headPosition, transition.getWriteSymbol());

            // check where to add to tape
            if (transition.getDirection() == 'L') {
                // at to beginning
                if (headPosition == 0) {
                    tape.add(0, 0);
                } else {
                    headPosition--;
                }
            } else {
                // at to end
                if (++headPosition >= tape.size()) {
                    tape.add(0);
                }
            }

            currentState = transition.getNextState().getStateNumber();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void expandTape(int index) {
        // Calculate how many more elements we need
        int additionalElementsNeeded = index - tape.size() + 1;

        tape.addAll(Collections.nCopies(additionalElementsNeeded, 0));
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
        
        // Append tape content
        for (int i = startIndex; i <= endIndex; i++) {
            sb.append(tape.get(i));
        }
        
        String tapeContent = sb.toString();
        System.out.println("output: " + tapeContent);
        System.out.println("output length: " + tapeContent.length());
        
        int sumOfSymbols = 0;
        for (char symbol : tapeContent.toCharArray()) {
            sumOfSymbols += symbol - '0';
        }
        System.out.println("sum of symbols: " + sumOfSymbols);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(int index, int value) {
        if (index >= tape.size()) {
            expandTape(index);
        }

        tape.set(index, value);
    }
}
package tm;

import java.util.Map;
import java.util.HashMap;

/**
 * Represents a Turing Machine State object
 * 
 * @authors Jaden Dawdy, Xian Ma
 */
public class TMState implements TMStateInterface {
    private int stateNumber;
    private Map<Integer, Transition> transitions;

    /**
     * Constructs a TMState with the given state number
     * 
     * @param stateNumber
     */
    public TMState(int stateNumber) {
        this.stateNumber = stateNumber;
        this.transitions = new HashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    public void addTransition(int symbol, TMState nextState, int writeSymbol, char direction) {
        transitions.put(symbol, new Transition(nextState, writeSymbol, direction));
    }

    /**
     * {@inheritDoc}
     */
    public Transition getTransition(int symbol) {
        return transitions.get(symbol);
    }

    /**
     * {@inheritDoc}
     */
    public int getStateNumber() {
        return stateNumber;
    }  
}
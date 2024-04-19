package tm;

/**
 * Represents a Turing Machine State object
 * 
 * @authors Jaden Dawdy, Xian Ma
 */
public interface TMStateInterface {
    
    /**
     * Adds a transition to the state
     * 
     * @param symbol - the symbol to transition on
     * @param nextState - the next state
     * @param writeSymbol - the symbol to write
     * @param direction - the direction to move the head
     */
    public void addTransition(int symbol, TMState nextState, int writeSymbol, char direction);

    /**
     * Gets the transition for the given symbol
     * 
     * @param symbol - the symbol to transition on
     * @return the transition
     */
    public Transition getTransition(int symbol);

    /**
     * Gets the state number
     * 
     * @return the state number
     */
    public int getStateNumber();
}

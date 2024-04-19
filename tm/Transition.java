package tm;

/**
 * Represents a Transition object
 * 
 * @authors Jaden Dawdy, Xian Ma
 */
public class Transition {
    private TMState nextState;
    private int writeSymbol;
    private char direction;

    /**
     * Constructs a Transition object with the given next state, write symbol, and direction
     * 
     * @param nextState - the next state
     * @param writeSymbol - the symbol to write
     * @param direction - the direction to move the head
     */
    public Transition(TMState nextState, int writeSymbol, char direction) {
        this.nextState = nextState;
        this.writeSymbol = writeSymbol;
        this.direction = direction;
    }

    /**
     * Gets the next state
     * 
     * @return the next state
     */
    public TMState getNextState() {
        return nextState;
    }

    /**
     * Gets the write symbol
     * 
     * @return the write symbol
     */
    public int getWriteSymbol() {
        return writeSymbol;
    }

    /**
     * Gets the direction
     * 
     * @return the direction
     */
    public char getDirection() {
        return direction;
    }
}

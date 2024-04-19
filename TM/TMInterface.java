package tm;

/**
 * Represents a Turing Machine object interface
 * 
 * @authors Jaden Dawdy, Xian Ma
 */
public interface TMInterface {

    /**
     * Simulates the Turing Machine on the input string.
     */
    void simulate();

    /**
     * Expands the tape by adding zeros the end of the tape
     */
    void expandTape(int index);

    /**
     * Prints the content of the tape
     */
    void printTape();

    /**
     * Sets the value at the specified index on the tape.
     * @param index - the index to set the value
     * @param value - the value to set
     */
    void set(int index, int value);
}
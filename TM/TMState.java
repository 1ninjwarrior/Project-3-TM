package tm;

import java.util.Map;
import java.util.HashMap;

public class TMState {
    private int stateNumber;
    private Map<Integer, Transition> transitions;

    public TMState(int stateNumber) {
        this.stateNumber = stateNumber;
        this.transitions = new HashMap<>();
    }

    public void addTransition(int symbol, TMState nextState, int writeSymbol, char direction) {
        transitions.put(symbol, new Transition(nextState, writeSymbol, direction));
    }

    public Transition getTransition(int symbol) {
        return transitions.get(symbol);
    }

    public int getStateNumber() {
        return stateNumber;
    }  

    // Inner class representing a transition
    public static class Transition {
        private TMState nextState;
        private int writeSymbol;
        private char direction;

        public Transition(TMState nextState, int writeSymbol, char direction) {
            this.nextState = nextState;
            this.writeSymbol = writeSymbol;
            this.direction = direction;
        }

        public TMState getNextState() {
            return nextState;
        }

        public int getWriteSymbol() {
            return writeSymbol;
        }

        public char getDirection() {
            return direction;
        }
    }
}
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

    public void addTransition(int symbol, int nextState, int writeSymbol, char direction) {
        transitions.put(symbol, new Transition(nextState, writeSymbol, direction));
    }

    public Transition getTransition(int symbol) {
        return transitions.get(symbol);
    }

    // Inner class representing a transition
    public static class Transition {
        private int nextState;
        private int writeSymbol;
        private char direction;

        public Transition(int nextState, int writeSymbol, char direction) {
            this.nextState = nextState;
            this.writeSymbol = writeSymbol;
            this.direction = direction;
        }

        public int getNextState() {
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
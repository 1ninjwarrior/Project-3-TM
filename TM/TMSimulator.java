// TMSimulator.java
package tm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

public class TMSimulator {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java tm.TMSimulator <input_file>");
            return;
        }

        String inputFile = args[0];
        Map<Integer, TMState> states = new HashMap<>();
        String inputString = "";

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            int numStates = Integer.parseInt(br.readLine());
            int numSymbols = Integer.parseInt(br.readLine());

            // Create TMState objects for each state
            for (int i = 0; i < numStates; i++) {
                states.put(i, new TMState(i));
            }

            // Parse the transition function
            for (int i = 0; i < (numSymbols + 1) * (numStates - 1); i++) {
                String[] transition = br.readLine().split(",");
                int fromState = i / (numSymbols + 1);
                int symbol = i % (numSymbols + 1);
                int nextState = Integer.parseInt(transition[0]);
                int writeSymbol = Integer.parseInt(transition[1]);
                char direction = transition[2].charAt(0);

                states.get(fromState).addTransition(symbol, nextState, writeSymbol, direction);
            }

            // Read the input string
            inputString = br.readLine();
            if (inputString == null) {
                inputString = "";
            }
        } catch (IOException e) {
            System.out.println("Error reading the input file: " + e.getMessage());
            return;
        }

        // Create the Turing Machine and simulate
        TM tm = new TM(states, inputString);
        tm.simulate();

        // Print the final tape content
        tm.printTape();
    }
}
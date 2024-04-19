package tm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

/**
 * A Turing Machine Simulator
 * Usage: java tm.TMSimulator <input_file>
 * 
 * @authors Jaden Dawdy, Xian Ma
 */
public class TMSimulator {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java tm.TMSimulator <input_file>");
            return;
        }

        String inputFile = args[0];
        Map<Integer, TMState> states = new HashMap<>();
        String inputString = "";

        // parse the input file
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            int numStates = Integer.parseInt(br.readLine());
            int numSymbols = Integer.parseInt(br.readLine());

            // Create TMState objects for each state
            for (int i = 0; i < numStates; i++) {
                states.put(i, new TMState(i));
            }

            // Parse the transition function
            for (int i = 0; i < numStates - 1; i++) {
                for (int j = 0; j <= numSymbols; j++) {
                    // split each line into the next state, write symbol, and direction
                    String[] transition = br.readLine().split(",");

                    int nextState = Integer.parseInt(transition[0]);
                    int writeSymbol = Integer.parseInt(transition[1]);
                    char direction = transition[2].charAt(0);
                    
                    states.get(i).addTransition(j, states.get(nextState), writeSymbol, direction);
                }
            }

            // Read the input string
            inputString = br.readLine();
            if (inputString == null) {
                inputString = "";
            } else {
                inputString = inputString.trim();
            }
        } catch (IOException e) {
            System.out.println("Error reading the input file: " + e.getMessage());
            return;
        }

        TM tm = new TM(states, inputString);
        tm.simulate();

        tm.printTape();
    }
}
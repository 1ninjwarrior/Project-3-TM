# Project 3: Turing Machine

* Author: Jaden Dawdy, Xian Ma
* Class: CS361 Section #002
* Semester: Spring 2024

## Overview

This is a Java program that simulates running a turing machine. It takes a file input where the first line contains the number of states, second contains number of inputs. The rest of the lines contain the transitions which are in order of next_state, write_string, direction. There is an optional last line that contains an input tape. The simulator then simulates what the turing machines would do and outputs the final tape. It also displays the length of the output and the sum of symbols in the output.


## Reflection

This project went very well escpecially compared to other projects. The simulator was very simple to write because it really just read from the file then called the TM.java. This is the same for TMState because it is just a state like we used with other projects.

The main difficutly for us was getting the correct number to output. This wasn't because of the turing machine simulating incorrectly, but because of the printTape. Originally we wrote the code to remove any 0's at the beginnig, but that caused issues with file2.txt because it needed a zero. Then through trial and error, we eventually got it working correctly. 

## Compiling and Using

To compile

```
$ javac tm/*.java
```

To run

```
$ java tm.TMSimulator <fileName>
```

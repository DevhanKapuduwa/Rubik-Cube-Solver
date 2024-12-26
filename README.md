# Rubik Cube Solver

## Description
A Java-based console application which simulates a 3x3x3 Rubik's Cube. This program allows users to shuffle the cube, perform rotations based on commands and check if the cube is solved. It is designed to enhance familiarity with Java development and OOP concepts.

## Features
- Simulates a 3x3x3 Rubik's Cube with accurate color assignments.
- Allows for user interaction via console commands to rotate cube layers.
- Displays the cube's current state after each command.
- Checks and indicates whether the cube is solved.
- Reshuffles the cube with the `RS` command.
- Exits the program with the `EX` command.

## Commands
| Command | Description                           |
|---------|---------------------------------------|
| `U+`    | Rotate the top layer clockwise.       |
| `U-`    | Rotate the top layer anti-clockwise.  |
| `B+`    | Rotate the bottom layer clockwise.    |
| `B-`    | Rotate the bottom layer anti-clockwise.|
| `R+`    | Rotate the right-hand side layer clockwise.|
| `R-`    | Rotate the right-hand side layer anti-clockwise.|
| `L+`    | Rotate the left-hand side layer clockwise.|
| `L-`    | Rotate the left-hand side layer anti-clockwise.|
| `F+`    | Rotate the front face clockwise.      |
| `F-`    | Rotate the front face anti-clockwise. |
| `RS`    | Reshuffle the cube.                   |
| `EX`    | Exit the program.                     |

## Technologies Used
- Java
- OOP

## Setup and Usage (Both java files should be placed in the same directory)
1. Clone the repository:
   ```bash
   git clone https://github.com/username/RubikCubeSolver.git

2. Navigate to the project directory (RubikCubeSolver):
    ```bash
    cd RubikCubeSolver
    
3. Compile the code:
    ```bash
    javac RubikCube.java
    
4. Run the program:
    ```bash
    java RubikCube


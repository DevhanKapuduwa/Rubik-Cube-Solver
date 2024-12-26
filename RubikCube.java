/*
    *Please refer to the attached PDF before referring to the code
*/


import java.util.Scanner;                               //input handling

public class RubikCube
{
    private CubePiece[][][] cube;

    public RubikCube() 
    {
        this.cube = new CubePiece[6][3][3];             //defining the size of cube and the size of a face
        initializeCube();
    }

    private CubePiece[][] createSideWithColor(char color)   //sides with colors
    {
        CubePiece[][] side = new CubePiece[3][3];           //algorithm to color all 9 pieces in one side
        for (int i = 0; i < 3; i++) 
        {
            for (int j = 0; j < 3; j++) 
            {
                side[i][j] = new CubePiece(color);
            }
        }
        return side;
    }

    private void initializeCube()                           //specific colors of sides
    {
        cube[0] = createSideWithColor('B');
        cube[1] = createSideWithColor('R');
        cube[2] = createSideWithColor('G');
        cube[3] = createSideWithColor('Y');
        cube[4] = createSideWithColor('O');
        cube[5] = createSideWithColor('W');
    }

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        RubikCube cube = new RubikCube();                   //object creation

        cube.shuffle();                                 //initial state of the cube
        cube.display();
        System.out.println();
        System.out.println("This is the initial shuffled state");

        while (true) 
        {
            System.out.println();
            System.out.print("Give the Command:\n U+ : Toplayer is rotated in clockwise direction\n" + //
                                " U- : Toplayer is rotated in anti-clockwise direction\n" + //
                                " B+ : Bottomlayer is rotated in clockwise direction\n" + //
                                " B- : Bottomlayer is rotated in anti-clockwise direction\n" + //
                                " R+ : Right hand side layer is rotated clockwise direction\n" + //
                                " R- : Right hand side layer is rotated anti-clockwise direction\n" + //
                                " L+ : Right hand side layer is rotated clockwise direction\n" + //
                                " L- : Right hand side layer is rotated anti-clockwise direction\n" + //
                                " F+ : Front face is rotated clockwise direction\n" + //
                                " F- : Front face is rotated anti-clockwise direction\n*EX: To exit\n*RS: To reset\n ");//taking user inputs
            String command = scanner.nextLine().toUpperCase();
    
            if (command.equals("EX")) 
            {
                System.out.println("Exiting...");           //exit function
                break;
            } 
            else if (command.equals("RS"))                  //reset function
            {
                cube.shuffle();
                cube.display();
            } 
            else if (command.matches("[ULRFB][+-]?"))       //checking th evalidity of inputs
            {
                cube.handleRotation(command);
                // Add the necessary logic for cube solving check here
            } 
            else 
            {
                System.out.println("Invalid command");
            }
        }
    
        scanner.close();
    }

    public void handleRotation(String command) 
    {
        char sideChar = command.charAt(0);
        int turns = 1;
    
        switch (sideChar)               //side checking to implement functions
        {
            case 'U':
                rotateSide(0, turns, command);
                break;
            case 'L':
                rotateSide(1, turns, command);
                break;
            case 'R':
                rotateSide(3, turns, command);
                break;
            case 'F':
                rotateSide(2, turns, command);
                break;
            case 'B':
                rotateSide(5, turns, command);
                break;
            default:
                System.out.println("Invalid side input");
                break;
        }
    }
    
    public void rotateSide(int side, int turns, String command)             //clockwise and anticlockwise rotation
    {
        if (command.length() == 2) {
            if (command.charAt(1) == '+') {
                turns = 1;
            } else if (command.charAt(1) == '-') {
                turns = 3; // anticlockwise is equivalent to three clockwise turns
            }
        }
        rotate(side, turns);
        display();
        if (isSolved())                             //solving state checker
        {
            System.out.println("Solved the Rubik Cube");
        } else {
            System.out.println("Cube is not fully solved");
        }
    }
    


    public void display()               //displaying function by side by side
    {
        System.out.println("Top face:");

        for (int i = 0; i < 3; i++) 
        {
            for (int j = 0; j < 3; j++)
            {
                System.out.print(cube[0][i][j].getColor() + " ");
            }
            System.out.println();
        }

        System.out.println();

        System.out.println("Middle faces: (cube[1],cube[2],cube[3],cube[4] in order)");
        for (int i = 0; i < 3; i++)
        {
            for (int j = 1; j < 5; j++)
            {
                for (int k = 0; k < 3; k++)
                {
                    
                    System.out.print(cube[j][i][k].getColor() + " ");

                    if (k==2)
                    {
                        System.out.print("\t");
                    }
        
                }

            }
            System.out.println();
        }

        System.out.println();

        System.out.println("Bottom face:");
        for (int i = 0; i < 3; i++)
        {
           // System.out.print("      ");
            for (int j = 0; j < 3; j++)
            {
                System.out.print(cube[5][i][j].getColor() + " ");
            }
            System.out.println();
        }
    }

    public boolean isSolved()                       //condition for a solved cube
    {
        for (int i = 0; i < 6; i++) 
        {
            char c = cube[i][1][1].getColor(); // Get the center color of the face
    
            for (int j = 0; j < 3; j++) 
            {
                for (int k = 0; k < 3; k++) 
                {
                    if (cube[i][j][k].getColor() != c) 
                    {
                        return false; // If any piece doesn't match the center color, cube is not solved
                    }
                }
            }
        }
        return true; // All faces have the same color in all pieces, cube is solved
    }
    

    public void shuffle()           //shuffle function
    {
        for (int i = 0; i < 20; i++)
        {
            int side = (int) (Math.random() * 6);
            int turns = (int) (Math.random() * 3) + 1;
            rotate(side, turns);
        }
    }

    public void rotate(int side, int turns)
    {
        while (turns-- > 0)
        {
            //storing tha values of the rotated side
            CubePiece[][] temp = new CubePiece[3][3];
            //iteration throughout the rotation face and repositioning
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    //new postions of the rotating face
                    temp[i][j] = cube[side][2 - j][i];
                }
            }
            //update of original array with temp array values
            cube[side] = temp;
            
            //storing the values as a temp row
            CubePiece[] tempRow = new CubePiece[3];
            
            //when the cube is rotated clockwise, the rearrangement of rows is given here
            if (side == 0)
            {
                tempRow = cube[1][0];
                cube[1][0] = cube[2][0];
                cube[2][0] = cube[3][0];
                cube[3][0] = cube[4][0];
                cube[4][0] = tempRow;
            }

            else if (side == 1)
            {
                CubePiece tempPiece1 = cube [0][0][0]; CubePiece tempPiece2 = cube [0][1][0]; CubePiece tempPiece3 = cube [0][2][0];

                cube [0][0][0] = cube [4][2][2]; cube [0][1][0] = cube [4][1][2]; cube [0][2][0] = cube [4][0][2];

                cube [4][2][2] = cube [5][0][0]; cube [4][1][2] = cube [5][1][0]; cube [4][0][2] = cube [5][2][0];

                cube [5][0][0] = cube [2][0][0]; cube [5][1][0] = cube [2][1][0]; cube [5][2][0] = cube [2][2][0];

                cube [2][0][0] = tempPiece1; cube [2][1][0] = tempPiece2; cube[2][2][0] = tempPiece3;

            }

            else if (side == 2)
            {
                CubePiece tempPiece1 = cube [0][2][0]; CubePiece tempPiece2 = cube [0][2][1]; CubePiece tempPiece3 = cube [0][2][2];

                cube [0][2][0] = cube [1][2][2]; cube [0][2][1] = cube [1][1][2]; cube [0][2][2] = cube [1][0][2];

                cube [1][2][2] = cube [5][0][2]; cube [1][1][2] = cube [5][0][1]; cube [1][0][2] = cube [5][0][0];

                cube [5][0][2] = cube [3][0][0]; cube [5][0][1] = cube [3][1][0]; cube [5][0][0] = cube [3][2][0];

                cube [3][0][0] = tempPiece1; cube [3][1][0] = tempPiece2; cube [3][2][0] = tempPiece3;
            }
        

            else if (side == 3)
            {
                CubePiece tempPiece1 = cube [0][0][2]; CubePiece tempPiece2 = cube [0][1][2]; CubePiece tempPiece3 = cube [0][2][2];

                cube [0][0][2] = cube [2][0][2]; cube [0][1][2] = cube [2][1][2]; cube [0][2][2] = cube [2][2][2];

                cube [2][0][2] = cube [5][0][2]; cube [2][1][2] = cube [5][1][2]; cube [2][2][2] = cube [5][2][2];

                cube [5][0][2] = cube [4][2][0]; cube [5][1][2] = cube [4][1][0]; cube [5][2][2] = cube [4][0][0];

                cube [4][2][0] = tempPiece1; cube [4][1][0] = tempPiece2; cube [4][0][0] = tempPiece3;

            }
            else if (side == 5)
            {
                tempRow = cube[1][2];
                cube[1][2] = cube[4][2];
                cube[4][2] = cube[3][2];
                cube[3][2] = cube[2][2];
                cube[2][2] = tempRow;
            }
        }
    }
}







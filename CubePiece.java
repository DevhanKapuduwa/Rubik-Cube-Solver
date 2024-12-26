/* 
 This is the sub class. main class is in other file
*/

public class CubePiece      //definingt he properties of a side of a segment in the rubik cube
{
    private char color;     //only existing property for the class is it's color

    public CubePiece(char color) 
    {
        this.color = color;
    }

    public char getColor()
    {
        return this.color;  //generate the class for the main class
}
}


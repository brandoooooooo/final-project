public class Piece {
    private int x; // x position of piece
    private int y; // y position of piece
    private String image; // image of piece

    // constructor for piece data type
    public Piece(String image) {
        // initialize instance variables - default for unplaced piece is (-1, -1)
        x = -1;
        y = -1;
        this.image = image;
    }

    // places the piece at (x, y) on the board
    public void place(int x, int y) {
        StdDraw.picture(x - 0.5, y - 0.5, image);
        this.x = x;
        this.y = y;
    }

    // moves the piece from its position to (x, y)
    public void move(int x, int y) {
        // if the piece is unplaced, throw an exception
        if (this.x == -1 || this.y == -1)
            throw new IllegalArgumentException("Piece has not been placed on the board");

        // place a square to hide the original piece and place again at new position
        Board.square(this.x, this.y);
        place(x, y);
    }

    // removes the piece from the board
    public void remove() {
        // if the piece is unplaced, throw an exception
        if (this.x == -1 || this.y == -1)
            throw new IllegalArgumentException("Piece has not been placed on the board");

        move(-1, -1);
    }

    // returns the x position of the piece
    public int x() {
        return x;
    }

    // returns the y position of the piece
    public int y() {
        return y;
    }

    // translates from chess notation (a-h for file or 1-8 for rank) to an x, y coordinate
    public static int translate(char square) {
        // since each character position is known in a move, calculate coordinates based off of that and the letter
        if (square >= 'a' && square <= 'h') {
            // a -> 1 and so on
            return square - 'a' + 1;
        } else if (square >= '1' && square <= '8') {
            return square - '0';
        } else
            throw new IllegalArgumentException("translate argument must be between 1 and 8 or a and h");
    }

    // set the image for the piece
    public void setImage(String image) {
        this.image = image;
    }

    // returns the position of the piece
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // a test client to make sure all the functions are working
    public static void main(String[] args) {
        String file = "White_Pawn.png";

        Piece testPiece = new Piece(file);
    }
}

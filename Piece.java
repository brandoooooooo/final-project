// piece data type that simulates the positions on a chess piece and visualizes the piece on a board
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

    // returns the position of the piece (for debugging)
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // a test client to make sure all the functions are working
    public static void main(String[] args) {
        // change canvas scale to be accurate of a chess board
        StdDraw.setScale(0, 8);

        String file = "White_Pawn.png";

        Piece testPiece1 = new Piece(file);
        Piece testPiece2 = new Piece(file);

        // translate a1 to (1, 1)
        int x1 = translate('a');
        int y1 = translate('1');

        StdOut.println("a1 translated to x-y: (" + x1 + ", " + y1 + ")"); // should return (1, 1)

        // translate g6 to (7, 3)
        int x2 = translate('g');
        int y2 = translate('3');

        StdOut.println("g6 translated to x-y: (" + x2 + ", " + y2 + ")"); // should return (7, 3)

        testPiece1.place(x1, y1);

        StdOut.println("Piece initial position (a1): " + testPiece1); // should return (1, 1)

        testPiece1.setImage("White_Queen.png");

        testPiece1.move(2, 2);

        StdOut.println("Piece position after moving to b2: " + testPiece1); // should return (2, 2)

        testPiece1.remove();

        // final result on StdDraw should be a blank board except 2 dark squares where pieces were
        // with piece at (3, 3) as a queen, and a piece at (1, 1) as a pawn
        testPiece1.place(3, 3);
        testPiece2.place(2, 2);
        testPiece2.move(1, 1);

        StdOut.println("Piece 1 final position: " + testPiece1); // should return (3, 3)
        StdOut.println("Piece 2 final position: " + testPiece2); // should return (1, 1)
    }
}

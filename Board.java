import java.awt.*;

public class Board {
    private final Piece[] pieces; // stores pieces on the board

    // constructor for the board
    public Board(Piece[] pieces) {
        this.pieces = pieces;
    }

    // initializes the board on StdDraw
    public static void initBoard(int rows) {
        // change canvas size and scale
        StdDraw.setCanvasSize(768, 512);
        StdDraw.setScale(0, 8);
        StdDraw.setXscale(0, 12);

        // colors for squares of board
        // colors from https://www.color-hex.com/color-palette/8548
        Color LIGHT = new Color(238, 238, 210);
        Color DARK = new Color(118, 150, 86);

        // draw the checkerboard
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < rows; y++) {
                if (x % 2 == 0 && y % 2 == 0) {
                    StdDraw.setPenColor(DARK);
                    StdDraw.filledSquare(x + 0.5, y + 0.5, 0.5);
                } else if (x % 2 != 0 && y % 2 != 0) {
                    StdDraw.setPenColor(DARK);
                    StdDraw.filledSquare(x + 0.5, y + 0.5, 0.5);
                } else {
                    StdDraw.setPenColor(LIGHT);
                    StdDraw.filledSquare(x + 0.5, y + 0.5, 0.5);
                }
            }
        }

        // draw lines to separate board and captured pieces display
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.0075);
        StdDraw.line(8, 0, 8, 8);
        StdDraw.line(10, 0, 10, 8);
    }

    // places a square at (x, y)
    public static void square(int x, int y) {
        // colors of squares of board
        // colors from https://www.color-hex.com/color-palette/8548
        Color LIGHT = new Color(238, 238, 210);
        Color DARK = new Color(118, 150, 86);

        // fill in square based on position
        if (x % 2 == 0 && y % 2 == 0) {
            StdDraw.setPenColor(DARK);
            StdDraw.filledSquare(x - 0.5, y - 0.5, 0.5);
        } else if (x % 2 != 0 && y % 2 != 0) {
            StdDraw.setPenColor(DARK);
            StdDraw.filledSquare(x - 0.5, y - 0.5, 0.5);
        } else {
            StdDraw.setPenColor(LIGHT);
            StdDraw.filledSquare(x - 0.5, y - 0.5, 0.5);
        }
    }

    // returns the piece at (x, y), if any, returns null otherwise
    public Piece pieceAt(int x, int y) {
        // traverses piece array and checks if x and y coordinates match
        for (Piece piece : pieces) {
            if (piece.x() == x && piece.y() == y)
                return piece;
        }

        return null;
    }

    // returns true if there is a piece at (x, y), false otherwise
    public boolean isPiece(int x, int y) {
        // traverses piece array and checks if x and y coordinates match
        for (Piece piece : pieces) {
            if (piece.x() == x && piece.y() == y)
                return true;
        }

        // if no such piece found, return false
        return false;
    }

    // string representation of board, printing number of pieces and position of each piece
    public String toString() {
        StringBuilder output = new StringBuilder();

        // gets number of pieces
        output.append("number of pieces: ");
        output.append(pieces.length);
        output.append("\n");

        // traverses piece array and appends position of piece
        for (Piece piece : pieces) {
            char file = (char) (piece.x() + 'a' - 1);
            char rank = (char) (piece.y() + '0');

            output.append("(");
            output.append(file);
            output.append(", ");
            output.append(rank);
            output.append(")\n");
        }

        return output.toString();
    }

    // test client to make sure each method works
    public static void main(String[] args) {
        int ROWS = 8;

        String file1 = "White_Pawn.png";
        String file2 = "Black_Pawn.png";
        String file3 = "Black_Queen.png";

        Piece testPiece1 = new Piece(file1);
        Piece testPiece2 = new Piece(file2);
        Piece testPiece3 = new Piece(file3);

        Piece[] pieces = {testPiece1, testPiece2, testPiece3};

        Board board = new Board(pieces);

        initBoard(ROWS);

        testPiece1.place(3, 3);
        testPiece2.place(2, 2);
        testPiece3.place(4, 8);

        // should hide the white pawn
        // final result should be a black pawn at b2 and a black queen at d8 with the white pawn at c3 hidden
        square(3, 3);

        StdOut.println("Piece 1 position: " + testPiece1); // should return (3, 3)
        StdOut.println("Piece 2 position: " + testPiece2); // should return (2, 2)
        StdOut.println("Piece 3 position: " + testPiece3); // should return (4, 8)
        StdOut.println(board); // should return 3 pieces, c3, b2, and d8
        StdOut.println(board.isPiece(4, 8)); // should return true
    }
}

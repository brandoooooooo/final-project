import java.awt.*;

public class Board {
    private final Piece[] pieces;

    public Board(Piece[] pieces) {
        this.pieces = pieces;
    }

    public static void initBoard(int rows) {
        StdDraw.setCanvasSize(768, 512);
        StdDraw.setScale(0, 8);
        StdDraw.setXscale(0, 12);

        Color LIGHT = new Color(238, 238, 210);
        Color DARK = new Color(118, 150, 86);

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.015);
        StdDraw.line(8, 0, 8, 8);

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
    }

    // places a square at (x, y)
    public static void square(int x, int y) {
        Color LIGHT = new Color(238, 238, 210);
        Color DARK = new Color(118, 150, 86);

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

    public Piece pieceAt(int x, int y) {
        for (Piece piece : pieces) {
            if (piece.x() == x && piece.y() == y)
                return piece;
        }

        return null;
    }

    public boolean isPiece(int x, int y) {
        for (Piece piece : pieces) {
            if (piece.x() == x && piece.y() == y)
                return true;
        }

        return false;
    }

    public String toString() {
        StringBuilder output = new StringBuilder();

        output.append("number of pieces: ");
        output.append(pieces.length);
        output.append("\n");

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

    public static void main(String[] args) {

    }
}

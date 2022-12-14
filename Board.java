public class Board {
    private final Piece[] pieces;

    public Board(Piece[] pieces) {
        this.pieces = pieces;
    }

    public static void initBoard(int x) {
        StdDraw.setScale(0, x);

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < x; j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        StdDraw.setPenColor(118, 150, 86);
                        StdDraw.filledSquare(i + 0.5, j + 0.5, 0.5);
                    } else {
                        StdDraw.setPenColor(238, 238, 210);
                        StdDraw.filledSquare(i + 0.5, j + 0.5, 0.5);
                    }
                } else {
                    if (j % 2 == 0) {
                        StdDraw.setPenColor(238, 238, 210);
                        StdDraw.filledSquare(i + 0.5, j + 0.5, 0.5);
                    } else {
                        StdDraw.setPenColor(118, 150, 86);
                        StdDraw.filledSquare(i + 0.5, j + 0.5, 0.5);
                    }
                }
            }
        }
    }

    // places a square at (x, y)
    public static void square(int x, int y) {
        if (x % 2 == 0 && y % 2 == 0) {
            StdDraw.setPenColor(118, 150, 86);
            StdDraw.filledSquare(x - 0.5, y - 0.5, 0.5);
        } else if (x % 2 != 0 && y % 2 != 0) {
            StdDraw.setPenColor(118, 150, 86);
            StdDraw.filledSquare(x - 0.5, y - 0.5, 0.5);
        } else {
            StdDraw.setPenColor(238, 238, 210);
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

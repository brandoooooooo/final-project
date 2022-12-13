public class Board {
    private final Piece[] pieces;

    public Board(int x, Piece[] pieces) {
        this.pieces = pieces;

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < x; j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        StdDraw.setPenColor(StdDraw.BLACK);
                        StdDraw.filledSquare(i + 0.5, j + 0.5, 0.5);
                    } else {
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledSquare(i + 0.5, j + 0.5, 0.5);
                    }
                } else {
                    if (j % 2 == 0) {
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledSquare(i + 0.5, j + 0.5, 0.5);
                    } else {
                        StdDraw.setPenColor(StdDraw.BLACK);
                        StdDraw.filledSquare(i + 0.5, j + 0.5, 0.5);
                    }
                }
            }
        }
    }

    // places a square at (x, y)
    public static void square(int x, int y) {
        if (x % 2 == 0 && y % 2 == 0) {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledSquare(x - 0.5, y - 0.5, 0.5);
        } else {
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.filledSquare(x - 0.5, y - 0.5, 0.5);
        }
    }

    public Piece pieceAt(int x, int y) {
        for (Piece piece : pieces) {
            if (piece.col() == x && piece.row() == y)
                return piece;
        }

        return null;
    }

    public static void main(String[] args) {

    }
}

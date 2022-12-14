import java.util.Arrays;

public class Game {

    private static Piece[] readPieces(String file) {
        In files = new In(file);
        Piece[] pieces = new Piece[files.readInt()];

        for (int i = 0; !files.isEmpty(); i++) {
            Piece piece = new Piece(files.readString());
            pieces[i] = piece;
        }

        return pieces;
    }

    public static void main(String[] args) {
        int ROWS = 8;

        String piecesFile = args[0];
        String game = args[1];

        Piece[] pieces = readPieces(piecesFile);

        // Board board = new Board(ROWS, pieces);

        In in = new In(game);

        int numMoves = in.readInt();
        String[][] moves = new String[numMoves][2];

        in.readLine();

        for (int i = 0; i < moves.length; i++) {
            if (!in.isEmpty()) {
                String line = in.readLine();
                if (line != null) {
                    String[] moveArray = line.split(" ");
                    StdOut.println(Arrays.toString(moveArray));

                    moves[i] = moveArray;
                }
            }
        }

        for (int i = 0; i < 1; i++) {


            String move = in.readString();

            char[] letters = move.toCharArray();

            // since each character position is known in a move, calculate coordinates based off of that
            int x1 = letters[1] - 'a' + 1;
            int y1 = letters[2] - '0';
            int x2 = letters[4] - 'a' + 1;
            int y2 = letters[5] - '0';

            // king-side castle
            if (move.contains("O-O")) {

            }
            // queen-side castle
            else if (move.contains("O-O-O")) {

            }
        }
    }

}

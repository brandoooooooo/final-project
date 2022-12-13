public class Game {

    private static Piece[] readPieces(String file) {
        java.util.Scanner files = new java.util.Scanner(file);
        Piece[] pieces = new Piece[files.nextInt()];

        for (int i = 0; !files.hasNext(); i++) {
            Piece piece = new Piece(files.next());
            pieces[i] = piece;
        }

        return pieces;
    }

    public static void main(String[] args) {
        int ROWS = 8;

        String piecesFile = args[0];

        Piece[] pieces = readPieces(piecesFile);

        Board board = new Board(ROWS, pieces);

        String game = args[0];
        java.util.Scanner in = new java.util.Scanner(game);
        boolean side = true; // boolean for which side moves - true is white, false is black
        int numMoves = in.nextInt();
        String[][] moves = new String[2][numMoves];

        StdOut.println(moves.length);
        StdOut.println(moves[1].length);


        while (!in.hasNext()) {
            for (int i = 0; i < moves[0].length; i++) {
                for (int j = 0; j < moves.length; j++) {
                    
                    String move = in.next();
                    StdOut.println(move);
                    moves[j][i] = move;
                }
            }
        }

        // System.out.println(Arrays.deepToString(moves));
        while (!StdIn.isEmpty()) {


            String move = in.next();

            char[] letters = move.toCharArray();

            // since each character position is known in a move, calculate coordinates based off of that
            int x1 = letters[1] - 'a' + 1;
            int y1 = letters[2] - '0';
            int x2 = letters[4] - 'a' + 1;
            int y2 = letters[5] - '0';

            // king-side castle
            if (move.contains("O-O")) {
                if (side) {

                } else {

                }
            }
            // queen-side castle
            else if (move.contains("O-O-O")) {

            }

            side = !side;
        }
    }

}

public class Game {
    public static void main(String[] args) {
        // rows/columns of board
        int ROWS = 8;

        // read command line arguments
        String piecesFile = args[0];
        String gameFile = args[1];

        In setup = new In(piecesFile);
        Piece[] pieces = new Piece[setup.readInt()];

        Board.initBoard(ROWS);

        for (int i = 0; !setup.isEmpty(); i++) {
            char[] square = setup.readString().toCharArray();
            Piece piece = new Piece(setup.readString());
            pieces[i] = piece;
            pieces[i].place(Piece.translate(square[0]), Piece.translate(square[1]));
        }

        Board board = new Board(pieces);

        In game = new In(gameFile);

        int numMoves = game.readInt();
        String[][] moves = new String[numMoves][2];

        game.readLine();

        for (int i = 0; i < moves.length; i++) {
            if (!game.isEmpty()) {
                String line = game.readLine();
                String[] moveArray = line.split(" ");
                moves[i] = moveArray;
            }
        }

        for (String[] moveArray : moves) {
            // boolean for side that moves - true for white, false for black
            boolean side = true;
            for (String move : moveArray) {
                // pause for 1 second every move
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                char[] letters = move.toCharArray();

                // king-side castle
                if (move.equals("O-O")) {
                    if (side) {
                        board.pieceAt(5, 1).move(7, 1);
                        board.pieceAt(8, 1).move(6, 1);
                    } else {
                        board.pieceAt(5, 8).move(7, 8);
                        board.pieceAt(8, 8).move(6, 8);
                    }

                    continue;
                }
                // queen-side castle
                else if (move.equals("O-O-O")) {
                    if (side) {
                        board.pieceAt(5, 1).move(3, 1);
                        board.pieceAt(1, 1).move(4, 1);
                    } else {
                        board.pieceAt(5, 8).move(3, 8);
                        board.pieceAt(1, 8).move(4, 8);
                    }

                    continue;
                }

                // since each character position is known in a move, calculate coordinates based off of that
                int x1 = letters[1] - 'a' + 1;
                int y1 = letters[2] - '0';
                int x2 = letters[4] - 'a' + 1;
                int y2 = letters[5] - '0';

                // pawn promotion
                if (move.contains("=Q") && move.contains("P")) {
                    if (side)
                        board.pieceAt(x1, y1).setImage("White_Queen.png");
                    else
                        board.pieceAt(x1, y1).setImage("Black_Queen.png");
                }

                if (board.isPiece(x2, y2)) {
                    board.pieceAt(x2, y2).remove();
                    board.pieceAt(x1, y1).move(x2, y2);
                } else
                    board.pieceAt(x1, y1).move(x2, y2);

                side = !side;
            }
        }
    }

}

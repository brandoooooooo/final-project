import java.awt.*;

public class Game {
    // helper class that calculates the sidebar coordinates to move a captured piece to
    private static int[] capture(Board board, boolean side) {
        // set initial position
        int y = 8;
        int row = 11;

        // if a column is filled with captured pieces, place pieces in the next column
        if (board.isPiece(row, 1)) {
            row = 12;
        }

        // if black captures white, display 2 columns to left
        if (!side) {
            row -= 2;
        }

        // traverse down the column until an empty spot is found to place
        while (board.isPiece(row, y)) {
            y--;
        }

        // return coordinates in an array
        return new int[]{row, y};
    }

    public static void main(String[] args) {
        // rows/columns of board
        int ROWS = 8;

        // read command line arguments
        String piecesFile = args[0];
        String gameFile = args[1];

        // create piece array and StdIn from piece file
        In setup = new In(piecesFile);
        Piece[] pieces = new Piece[setup.readInt()];

        // initialize board on StdDraw with x rows
        Board.initBoard(ROWS);

        // read pieces from file
        for (int i = 0; !setup.isEmpty(); i++) {
            // read initial position for piece
            char[] square = setup.readString().toCharArray();

            // create piece using image read from file and place in array
            Piece piece = new Piece(setup.readString());
            pieces[i] = piece;

            // place piece at initial position on board
            pieces[i].place(Piece.translate(square[0]), Piece.translate(square[1]));
        }

        // create new board variable
        Board board = new Board(pieces);

        In game = new In(gameFile);

        // create 2D move array
        int numMoves = game.readInt();
        String[][] moves = new String[numMoves][2];

        // first line after reading int is null for some reason so skip a line to bypass that
        game.readLine();

        // read in moves to array
        for (int i = 0; i < moves.length; i++) {
            if (!game.isEmpty()) {
                // read a pair of moves, split into array, then place into 2D array
                String line = game.readLine();
                String[] moveArray = line.split(" ");
                moves[i] = moveArray;
            }
        }

        // main loop that plays out the game
        for (String[] moveArray : moves) {
            // boolean for side that moves - true for white, false for black
            boolean side = true;

            for (String move : moveArray) {
                // pause for 1 second every move
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }

                // switch statement to compare move to certain strings
                switch (move) {
                    // terminate game and display text if white wins
                    case "1-0":
                        Font font = new Font("COURIER", Font.BOLD, 72);
                        StdDraw.setFont(font);
                        StdDraw.setPenColor(StdDraw.BLACK);
                        StdDraw.text(6, 4, "White wins!");

                        break;
                    // terminate game and display text if black wins
                    case "0-1":
                        font = new Font("COURIER", Font.BOLD, 72);
                        StdDraw.setFont(font);
                        StdDraw.setPenColor(StdDraw.BLACK);
                        StdDraw.text(6, 4, "Black wins!");

                        break;
                    // king side castle
                    case "O-O":
                        // move the king and rook to the correct squares needed for castling
                        if (side) {
                            board.pieceAt(5, 1).move(7, 1);
                            board.pieceAt(8, 1).move(6, 1);
                        } else {
                            board.pieceAt(5, 8).move(7, 8);
                            board.pieceAt(8, 8).move(6, 8);
                        }

                        break;
                    // queen-side castle
                    case "O-O-O":
                        // move the king and rook to the correct squares needed for castling
                        if (side) {
                            board.pieceAt(5, 1).move(3, 1);
                            board.pieceAt(1, 1).move(4, 1);
                        } else {
                            board.pieceAt(5, 8).move(3, 8);
                            board.pieceAt(1, 8).move(4, 8);
                        }

                        break;
                    // default case if move does not math those keywords
                    default:
                        // convert move to array of chars
                        char[] letters = move.toCharArray();

                        // translate chess notation coordinates to x and y coordinates
                        int x1 = Piece.translate(letters[1]);
                        int y1 = Piece.translate(letters[2]);
                        int x2 = Piece.translate(letters[4]);
                        int y2 = Piece.translate(letters[5]);

                        // pawn promotion by changing piece image from pawn to queen
                        if (move.contains("=Q") && move.contains("P")) {
                            if (side)
                                board.pieceAt(x1, y1).setImage("White_Queen.png");
                            else
                                board.pieceAt(x1, y1).setImage("Black_Queen.png");
                        }

                        // capture piece and display on sidebar if necessary
                        if (board.isPiece(x2, y2)) {
                            // calculate sidebar coordinates to move captured piece to
                            int[] coordinates = capture(board, side);

                            // move piece being moved and capture piece being captured
                            board.pieceAt(x2, y2).move(coordinates[0], coordinates[1]);
                            board.pieceAt(x1, y1).move(x2, y2);
                        }
                        // special case for en passant captures
                        else if (move.contains("P") && move.contains("x") && !board.isPiece(x2, y2)) {
                            // check if the pawn being captured is at the correct square (depends on side)
                            if ((board.isPiece(x2, y2 - 1) && side) || (board.isPiece(x2, y2 + 1) && !side)) {
                                // calculate sidebar coordinates to move captured piece to
                                int[] coordinates = capture(board, side);

                                // move piece being moved and capture piece being captured
                                // since it is en passant, the pawn captured is actually one square under the
                                if (side) {
                                    board.pieceAt(x2, y2 - 1).move(coordinates[0], coordinates[1]);
                                    board.pieceAt(x1, y1).move(x2, y2);
                                } else {
                                    board.pieceAt(x2, y2 + 1).move(coordinates[0], coordinates[1]);
                                    board.pieceAt(x1, y1).move(x2, y2);
                                }
                            }
                        }
                        // if no capture, move piece to square
                        else
                            board.pieceAt(x1, y1).move(x2, y2);
                }

                // if mouse is pressed, pause the playback
                if (StdDraw.isMousePressed()) {
                    // pause for a second before space can be pressed again to resume
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }

                    // listens for key press and resumes
                    while (true) {
                        if (StdDraw.isMousePressed()) {
                            break;
                        }
                    }
                }

                // switch sides
                side = !side;
            }
        }
    }
}

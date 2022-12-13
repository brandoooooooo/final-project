public class Piece {
    private int x;
    private int y;
    private final String image;

    public Piece(String image) {
        x = -1;
        y = -1;
        this.image = image;
    }

    public void place(int x, int y) {
        if (x < 1 || x > 8)
            throw new IllegalArgumentException("File must between a and h");
        if (y < 1 || y > 8)
            throw new IllegalArgumentException("Rank must between 1 and 8");

        StdDraw.picture(x - 0.5, y - 0.5, image);
        this.x = x;
        this.y = y;
    }

    public void move(int x, int y) {
        if (x == -1 || y == -1)
            throw new IllegalArgumentException("Piece has not been placed on the board");

        Board.square(this.x, this.y);
        place(x, y);
    }

    public int row() {
        return y;
    }

    public int col() {
        return x;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }


    public static void main(String[] args) {

    }
}

package chess;

/**
 *
 * @author heiseed
 */
public class Board {

    private Square[][] board;

    public Board() {
        board = new Square[8][8];
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                board[x][y] = new Square(x, y);
            }
        }
    }
}

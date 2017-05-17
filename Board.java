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
    public void printBoard() {
        System.out.println();
        for (int i = 0; i<8; i++) {
            System.out.print(i+1);
            for (int j = 0; j<8; j++) {
                 if (board[i][j].getPiece() == null) {
                  System.out.print(" _");
                 } else {
                     System.out.print(" " + board[i][j].getPiece().getSymbol());                          
                 } 
            }    
            System.out.println();
        }
        System.out.println("\n a b c d e f g h");
    }
    
}

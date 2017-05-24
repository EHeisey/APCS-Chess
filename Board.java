package chess;

/**
 *
 * @author heiseed
 */
public class Board {

    private Square[][] board;

    // needs to set up all pieces
    public Board() {
        board = new Square[8][8];
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                board[x][y] = new Square(x, y);
            }
        }

    }

    // needs to be modified to take a Color as a parameter and print the board from either perspective
    public void print() {
        System.out.println("\n  a b c d e f g h");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < 8; j++) {
                if (board[i][j].isEmpty()) {
                    System.out.print(" _");
                } else {
                    System.out.print(" " + board[i][j].getPiece().getID());
                }
            }
            System.out.println();
        }
    }

    public boolean isValidSquare(Square s) {
        return s.getX() > -1 && s.getX() < 8 && s.getY() > -1 && s.getY() < 8;
    }

    public Square getSquare(int x, int y) {
        return board[x][y];
    }
    
    // needs serious work
    public boolean inCheck(Color c){
        King k;
        for(Square[] row : board){
            for(Square s : row){
                if(s.getPiece() instanceof King && s.getPiece().getColor()==c) k = (King)s.getPiece();
            }
        }
        return false;
    }
}

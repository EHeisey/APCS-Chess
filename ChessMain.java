package chess;

/**
 *
 * @author heiseed
 */
public class ChessMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Board b = new Board();
        //b.getSquare(0, 4).setPiece(new King(Color.BLACK));
        //b.getSquare(0, 1).setPiece(new King(Color.WHITE));
        //b.print();
        ChessGame g = new ChessGame();
        g.start();
    }
}

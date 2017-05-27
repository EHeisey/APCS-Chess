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
        ChessGame.printInstructions();
        new ChessGame().start();
    }
}

package chess;

/**
 *
 * @author heiseed
 */
public abstract class Piece {
    
    private Board board;
    
    public Piece(Board b){
        board = b;
    }
    
    public Board getBoard(){
        return board;
    }
    
    
    
}

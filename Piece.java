package chess;

/**
 *
 * @author heiseed
 */
public abstract class Piece {
    
    private Board board;
    private Color color;
    
    public Piece(Board b, Color c){
        board = b;
        color = c;
    }
    
    public Board getBoard(){
        return board;
    }
    
    public Color getColor(){
        return color;
    }
    
}

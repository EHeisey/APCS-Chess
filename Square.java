package chess;

/**
 *
 * @author heiseed
 */
public class Square {
    
    private int x, y;
    private Piece piece;
    
    public Square(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public Piece getPiece(){
        return piece;
    }
    
}

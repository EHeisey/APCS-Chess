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
    
    public Piece getPiece() {
        return piece;
    }
    
     public int getX() {
         return x;
     }
     
     public int getY() {
         return y;
     }
     
    public void setPiece(Piece p) {
        piece = p;                
    }    
    public void removePiece() {
        piece = null;
    }    
    public boolean isEmpty() {        
        return piece==null;
    }    
    
}

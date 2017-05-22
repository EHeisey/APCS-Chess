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
    
    public void setPiece(Piece p){
        piece = p;
    }
    
    public boolean isEmpty(){
        return piece==null;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
}

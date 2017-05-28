package chess;

/**
 *
 * @author heiseed, wyliebl
 */
public class Square {
    
    private int x, y;
    private Piece piece;
    /**
    constructor Square(int x,int  y) - make a square with coordinates x and y
    @param x - vertical/row
    @param y - horizontal/column
    */
    public Square(int x, int y){
        this.x = x;
        this.y = y;
    }
   /** 
    method getPiece() - return piece on this square
    @param none
    @return Piece p - the piece
    */
    public Piece getPiece() {
        return piece;
    }
    /**
    method getX() - get the x value of this square
    @param none
    @return int x - the x value
    */
     public int getX() {
         return x;
     }
    /**
    method getY() - get the y value of this square
    @param none
    @return int y - the y value
    */
     public int getY() {
         return y;
     }
    /**
     method setPiece(Piece p) - set this square's piece to p
     @param p - the piece to put in the square
     @return null
     */
    public void setPiece(Piece p) {
        piece = p;                
    }    
    /**
    method removePiece() - remove the piece from this square
    @param none
    @return null
    */
    public void removePiece() {
        piece = null;
    }    
    /**
    method isEmpty() - return whether the piece is empty
    @param none
    @return true- if there is no piece there
            false - if there is a piece
    */
    public boolean isEmpty() {        
        return piece==null;
    }    
    
    
}

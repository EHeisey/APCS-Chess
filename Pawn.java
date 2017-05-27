/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

/**
 *
 * @author heiseed, wyliebl
 */
import java.util.ArrayList;
public class Pawn extends Piece {

    public Pawn(Color c) {
        super(c, "P");
    }

    @Override
    public ArrayList<Square> getPossibleMoves(){
        ArrayList<Square> squares = new ArrayList<>();
        Square start = this.currentSquare();
        int x = start.getX();
        int y = start.getY();
        
        if (getColor().equals(Color.WHITE)) {
            if(getBoard().isValidSquare(x+1,y)) {
                Square forward = getBoard().getSquare(x+1,y);
                if (forward.isEmpty()) {
                    squares.add(forward);
                }    
            };    
            
            ArrayList<Square> enemies = getBoard().enemies(Color.WHITE);  
            for (Square s: enemies) {
                if(getBoard().isValidSquare(x+1,y+1)) {
                    if(s == getBoard().getSquare(x+1, y+1)) {
                        squares.add(s);
                    }
                }
                if(getBoard().isValidSquare(x+1,y-1)) {
                    if(s == getBoard().getSquare(x+1, y-1)) {
                        squares.add(s);
                    }
                }
            }    
        }else{    
            if(getBoard().isValidSquare(x-1,y)) {
                Square forward = getBoard().getSquare(x-1,y);
                if (forward.isEmpty()) {
                    squares.add(forward);
                }    
            };   
            ArrayList<Square> enemies = getBoard().enemies(Color.BLACK);  
            for (Square s: enemies) {
                if(getBoard().isValidSquare(x-1,y+1)) {
                    if(s == getBoard().getSquare(x-1, y+1)) {
                        squares.add(s);
                    }
                }
                if(getBoard().isValidSquare(x-1,y-1)) {
                    if(s == getBoard().getSquare(x-1, y-1)) {
                        squares.add(s);
                    }
                }
            } 
        }
        return squares;
    }
    
}

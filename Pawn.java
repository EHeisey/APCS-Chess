/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

/**
 *
 * @author wyliebl
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
            squares.add(getBoard().getSquare(x+1,y));
            Square[] spot = {getBoard().getSquare(x+1, y+1), getBoard().getSquare(x+1, y-1)};
            ArrayList<Square> enemies = getBoard().enemies(Color.WHITE);  
            for (Square s: enemies) {
                if (s.equals(spot[0]) || s.equals(spot[1])) {
                    squares.add(s);
                }
            }    
        }else{    
            squares.add(getBoard().getSquare(x-1,y));
            Square[] spot = {getBoard().getSquare(x-1, y+1), getBoard().getSquare(x-1, y-1)};
            ArrayList<Square> enemies = getBoard().enemies(Color.BLACK);
            for (Square s: enemies) {
                if (s.equals(spot[0]) || s.equals(spot[1])) {
                    squares.add(s);
                }
            } 
        }
        squares = okaySpots(squares);
        return squares;
    }
    private ArrayList<Square> okaySpots(ArrayList<Square> yo) {
        for (int i = yo.size()-1; i>=0; i--) {
            if (yo.get(i) == null) {
                yo.remove(i);
            } else{
                Square s = yo.get(i);
                
                if((!s.isEmpty() && getColor().equals(this.getColor())) || !Pawn.getBoard().isValidSquare(s)) {
                    yo.remove(s);
                }
            }
        }        
        return yo;
    } 
    
    
}

package chess;
import java.util.ArrayList;
/**
 *
 * @author wyliebl
 */
public class Knight extends Piece {
    
    public Knight(Color c) {
        super(c, "N");
    }

    @Override
    public ArrayList<Square> getPossibleMoves(){
        ArrayList<Square> squares = new ArrayList<>();
        Square start = this.currentSquare();
        int x = start.getX();
        int y = start.getY();
        
        squares.add(getBoard().getSquare(x+2, y+1));
        squares.add(getBoard().getSquare(x+2, y-1));
        squares.add(getBoard().getSquare(x+1, y+2));
        squares.add(getBoard().getSquare(x+1, y-2));
        squares.add(getBoard().getSquare(x-1, y+2));
        squares.add(getBoard().getSquare(x-1, y-2));
        squares.add(getBoard().getSquare(x-2, y+1));
        squares.add(getBoard().getSquare(x-2, y-1));
        
        squares = okaySpots(squares);
        return squares;
    }
    private ArrayList<Square> okaySpots(ArrayList<Square> yo) {
        for (int i = yo.size()-1; i>=0; i--) {
            if (yo.get(i) == null) {
                yo.remove(i);
            } else{
                Square s = yo.get(i);
                
                if((!s.isEmpty() && s.getPiece().getColor().equals(this.getColor())) || !Knight.getBoard().isValidSquare(s)) {
                    yo.remove(s);
                }
            }
        }        
        return yo;
    }  
}

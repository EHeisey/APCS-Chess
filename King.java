/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;
import java.util.ArrayList;
/**
 *
 * @author heiseed, wyliebl
 */
public class King extends Piece {
    /*
    * constructor King(Color c) - creates a piece of that color and gives it an identifying string
    *@param c - color of piece
    */
    public King(Color c) {
        super(c, "K");        
    }
    /*
    method getPossibleMoves() - get possible moves according to King rules
    @return ArrayList<Square> squares - all possible moves for King
    */
    @Override
    public ArrayList<Square> getPossibleMoves(){
        ArrayList<Square> squares = new ArrayList<Square>();
        Square start = this.currentSquare();
        int x = start.getX();
        int y = start.getY();
        
        squares.add(getBoard().getSquare(x+1, y+1));
        squares.add(getBoard().getSquare(x, y+1));
        squares.add(getBoard().getSquare(x-1, y+1));
        squares.add(getBoard().getSquare(x-1, y));
        squares.add(getBoard().getSquare(x+1, y));
        squares.add(getBoard().getSquare(x, y-1));
        squares.add(getBoard().getSquare(x+1, y-1));
        squares.add(getBoard().getSquare(x-1, y-1));
        
        squares = okaySpots(squares);
        
        return squares;
    }
    /**
    method okaySpots(ArrayList<Square> yo) - check to see if all squares are alright for the king to move to (exist and are not empty)
    @param yo  - the ArrayList to check
    @return ArrayList<Square>  - revised array list
    */
    private ArrayList<Square> okaySpots(ArrayList<Square> yo) {
        for (int i = yo.size()-1; i>=0; i--) {
            
            if (yo.get(i) == null) {
                yo.remove(i);
            } else{
                
                Square s = yo.get(i);

                if((!s.isEmpty() && s.getPiece().getColor().equals(this.getColor())) || !King.getBoard().isValidSquare(s)) {
                    yo.remove(s);
                }
                if (inCheck(s)){
                    yo.remove(s);
                }
            }
        }        
        return yo;
    }   
    /**
    method inCheck(Square newKing) - see if the king will be in check at the specified square
    @param newKing - the square the king will move to
    @return true if the king will be in check in that spot(i.e. an enemy piece could take the king at that spot), false if he will not
    */
    public boolean inCheck(Square newKing) {
        Board b = getBoard();
        Square current = currentSquare();
        King k = (King)current.getPiece();
        current.removePiece();
        for (int i = 0; i<8; i++) {
            for (int j = 0; j<8; j++) {  
                if (!b.getSquare(i,j).isEmpty()) {
                    Piece p = b.getSquare(i,j).getPiece();
                    
                    if (!(p.getColor().equals(k.getColor())) && !p.getID().toLowerCase().equals("k")) {
                        if (p.getPossibleMoves().size() > 0) {
                            for (Square s : p.getPossibleMoves()) {
                                if (s == newKing) { 
                               
                                    current.setPiece(k);
                                    return true;
                                    
                                }    
                            }
                        }
                    }
                }
            }
        }    
        current.setPiece(k);
        return false;
    }
}
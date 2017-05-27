
package chess;
import java.util.ArrayList;
/**
 *
 * @author heiseed, wyliebl
 */
public class Rook extends Piece {

    public Rook(Color c) {
        super(c, "R");
    }
    public ArrayList<Square> getPossibleMoves(){
        ArrayList<Square> squares = new ArrayList<Square>();
        Square start = this.currentSquare();
        int x = start.getX();
        int y = start.getY();
        //moves right
        boolean unblocked = true;   
        int i = 1;
        while(unblocked && i<8) {
           if (!(getBoard().isValidSquare(x, y+i))){
               unblocked = false;
           } else {
               
               Square s = getBoard().getSquare(x, y+i);
               if (s.isEmpty()) {
                   squares.add(s);
                   i++;
               } else if (s.getPiece().isEnemy(this)) {
                   squares.add(s);
                   unblocked = false;
               } else {
                   unblocked = false;
               } 
           }   
        
        }    
        //left
        unblocked = true;   
        i = 1;
        while(unblocked && i<8) {
           if (!(getBoard().isValidSquare(x, y-i))){
               unblocked = false;
           } else {
               
               Square s = getBoard().getSquare(x, y-i);
               if (s.isEmpty()) {
                   squares.add(s);
                   i++;
               } else if (s.getPiece().isEnemy(this)) {
                   squares.add(s);
                   unblocked = false;
               } else {
                   unblocked = false;
               } 
           }   
        
        } 
        //up
        unblocked = true;   
        i = 1;
        while(unblocked && i<8) {
           if (!(getBoard().isValidSquare(x-i, y))){
               unblocked = false;
           } else {
               
               Square s = getBoard().getSquare(x-i, y);
               if (s.isEmpty()) {
                   squares.add(s);
                   i++;
               } else if (s.getPiece().isEnemy(this)) {
                   squares.add(s);
                   unblocked = false;
               } else {
                   unblocked = false;
               } 
           }   
        
        } 
        //down
        unblocked = true;   
        i = 1;
        while(unblocked && i<8) {
           if (!(getBoard().isValidSquare(x+i, y))){
               unblocked = false;
           } else {
               Square s = getBoard().getSquare(x+i, y);
               if (s.isEmpty()) {
                   squares.add(s);
                   i++;
               } else if (s.getPiece().isEnemy(this)) {
                   squares.add(s);
                   unblocked = false;
               } else {
                   unblocked = false;
               } 
           }   
        }
        return squares;
    }
}

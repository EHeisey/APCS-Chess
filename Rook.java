
package chess;
import java.util.ArrayList;
/**
 *
 * @author wyliebl
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
        //moves up
        boolean unblocked = true;   
        int i = 1;
        while(unblocked && i<8) {
           if (!(getBoard().isValidSquare(x, y+i))){
               System.out.println("Not valid square");
               unblocked = false;
           } else {
               
               Square s = getBoard().getSquare(x, y+i);
               System.out.print("Ok Square" + s.getX() + s.getY());
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
           if (!(getBoard().isValidSquare(x, y-i))){
               System.out.println("Not valid square");
               unblocked = false;
           } else {
               
               Square s = getBoard().getSquare(x, y-i);
               System.out.print("Ok Square" + s.getX() + s.getY());
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
           if (!(getBoard().isValidSquare(x-i, y))){
               System.out.println("Not valid square");
               unblocked = false;
           } else {
               
               Square s = getBoard().getSquare(x-i, y);
               System.out.print("Ok Square" + s.getX() + s.getY());
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
        //right
        unblocked = true;   
        i = 0;
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

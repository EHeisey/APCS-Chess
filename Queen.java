package chess;
import java.util.ArrayList;
/**
 *
 * @author heiseed, wyliebl
 */
public class Queen extends Piece {
    /*
    * constructor Queen
    */
    public Queen(Color c) {
        super(c, "Q");
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
        
        //down
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
        //left
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
        //right
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
        //up right
        unblocked = true;   
        i = 1;
        while(unblocked && i<8) {
           if (!(getBoard().isValidSquare(x+i, y+i))){
               unblocked = false;
           } else {
               Square s = getBoard().getSquare(x+i, y+i);
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
        //up left
        unblocked = true;   
        i = 1;
        while(unblocked && i<8) {
           if (!(getBoard().isValidSquare(x-i, y+i))){
               unblocked = false;
           } else {
               Square s = getBoard().getSquare(x-i, y+i);
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
        //down right
        unblocked = true;   
        i = 1;
        while(unblocked && i<8) {
           if (!(getBoard().isValidSquare(x+i, y-i))){
               unblocked = false;
           } else {
               Square s = getBoard().getSquare(x+i, y-i);
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
        //down left
        unblocked = true;   
        i = 1;
        while(unblocked && i<8) {
           if (!(getBoard().isValidSquare(x-i, y-i))){
               unblocked = false;
           } else {
               Square s = getBoard().getSquare(x-i, y-i);
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

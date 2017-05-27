package chess;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author wyliebl
 */
public class King extends Piece {
    
    public King(Color c){
        super(c, "K");
    }

    @Override
    public ArrayList<Point> getMoves(Piece[][] board, Point start){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ArrayList<Point> getIllegalMoves(Piece[][] board){
        ArrayList<Point> moves = new ArrayList<Point>();
        
        
        return moves;
    }
    
    public boolean inCheck(Piece[][] board, Point pos){
        
        
        return false;
    }
    
}

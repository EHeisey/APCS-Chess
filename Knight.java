package chess;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author wyliebl
 */
public class Knight extends Piece {
    
    public Knight(Color c){
        super(c, "N");
    }

    @Override
    public ArrayList<Point> getMoves(Piece[][] board, Point start){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

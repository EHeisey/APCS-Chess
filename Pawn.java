package chess;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author wyliebl
 */
public class Pawn extends Piece {
    
    public Pawn(Color c){
        super(c, "P");
    }

    @Override
    public ArrayList<Point> getMoves(Piece[][] board, Point start){
        ArrayList<Point> moves = new ArrayList<Point>();
        if(getColor()==Color.WHITE){
            
        }else{
            
        }
        return moves;
    }
    
}

package chess;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author wyliebl
 */
public class Bishop extends Piece {
    
    public Bishop(Color c){
        super(c, "B");
    }

    @Override
    public ArrayList<Point> getMoves(Piece[][] board, Point start){
        ArrayList<Point> moves = new ArrayList<Point>();
        for(int i=1; i<7; i++){
            if(start.x+i>7||start.y+i>7|(board[start.x+i][start.y+i]!=null&&board[start.x+i][start.y+i].getColor()==getColor())) break;
            moves.add(new Point(start.x+i, start.y+i));
        }
        for(int i=1; i<7; i++){
            if(start.x+i>7||start.y-i<0||(board[start.x+i][start.y-i]!=null&&board[start.x+i][start.y-i].getColor()==getColor())) break;
            moves.add(new Point(start.x+i, start.y-i));
        }
        for(int i=1; i<7; i++){
            if(start.x-i<0||start.y+i>7||(board[start.x-i][start.y+i]!=null&&board[start.x-i][start.y+i].getColor()==getColor())) break;
            moves.add(new Point(start.x+i, start.y+i));
        }
        for(int i=1; i<7; i++){
            if(start.x-i<0||start.y-i<0||(board[start.x-i][start.y-i]!=null&&board[start.x-i][start.y-i].getColor()==getColor())) break;
            moves.add(new Point(start.x+i, start.y+i));
        }
        return moves;
    }
    
}

package chess;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author wyliebl
 */
public class Rook extends Piece {
    
    public Rook(Color c){
        super(c, "R");
    }

    @Override
    public ArrayList<Point> getMoves(Piece[][] board, Point start){
        ArrayList<Point> moves = new ArrayList<Point>();
        for(int x=start.x+1; x<8; x++){
            if(board[x][start.y]!=null&&board[x][start.y].getColor()==getColor()) break;
            moves.add(new Point(x, start.y));
        }
        for(int x=start.x-1; x>-1; x--){
            if(board[x][start.y]!=null&&board[x][start.y].getColor()==getColor()) break;
            moves.add(new Point(x, start.y));
        }
        for(int y=start.y+1; y<8; y++){
            if(board[start.x][y]!=null&&board[start.x][y].getColor()==getColor()) break;
            moves.add(new Point(start.x, y));
        }
        for(int y=start.y-1; y>-1; y--){
            if(board[start.x][y]!=null&&board[start.x][y].getColor()==getColor()) break;
            moves.add(new Point(start.x, y));
        }
        return moves;
    }
    
}

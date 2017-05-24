package chess;

import java.util.ArrayList;

/**
 *
 * @author heiseed
 */
public abstract class Piece {
    
    private static Board board;
    
    private Color color;
    private String identifier;
    
    public Piece(Color c, String i){
        color = c;
        identifier = (color==Color.WHITE) ? i.toUpperCase() : i.toLowerCase();
    }
    
    public static void setBoard(Board b){
        board = b;
    }
    
    public static Board getBoard(){
        return board;
    }
    
    public Color getColor(){
        return color;
    }
    
    public String getID(){
        return identifier;
    }
    
    public abstract ArrayList<Square> getPossibleMoves(Square start);
    
}

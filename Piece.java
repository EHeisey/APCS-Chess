package chess;

import java.util.ArrayList;

/**
 *
 * @author heiseed
 */
public abstract class Piece {
    
    private Color color;
    private String identifier;
    
    public Piece(Color c, String i){
        color = c;
        identifier = i;
    }
    
    public Color getColor(){
        return color;
    }
    
    public String getID(){
        return identifier;
    }
    
    public abstract ArrayList<Square> getPossibleMoves(Square start);
    
}

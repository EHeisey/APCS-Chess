package chess;

import java.util.ArrayList;

/**
 *
 * @author heiseed, wyliebl
 */
public abstract class Piece {
    
    private static Board board;
    private Color color;
    private String identifier;
    
    public Piece(Color c, String i){
        color = c;
        identifier = color==Color.WHITE ? i.toUpperCase() : i.toLowerCase();
    }
    public static void setBoard(Board b) {
            board = b;
    }
    
    public static Board getBoard() {
        return board;
    }
    
    public Square currentSquare() { 
        Square current = null;
        for (int i=0;i<8; i++) {
            for (int j= 0; j<8; j++) {
                if (!board.getSquare(i, j).isEmpty()&& board.getSquare(i,j).getPiece().equals(this)) {
                    current = getBoard().getSquare(i,j);
                }   
                
            }
        }
        return current;
    } 
    public Color getColor(){
        return color;
    }
    
    public String getID(){
        return identifier;
    }
    public boolean isEnemy(Piece p2){
        return (!p2.getColor().equals(this.getColor()));   
    }
 
    public abstract ArrayList<Square> getPossibleMoves();
    
}
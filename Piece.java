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

    /**
     * constructor Piece(Color c, String i) - creates a piece of that color and
     * assigns its identifying string
     *
     * @param c - color of piece
     * @param i - identifying piece String
     */
    public Piece(Color c, String i){
        color = c;
        identifier = color == Color.WHITE ? i.toUpperCase() : i.toLowerCase();
    }

    /**
     * method setBoard(Board b) - sets the board for all pieces to Board b
     *
     * @param b - board of pieces
     */
    public static void setBoard(Board b){
        board = b;
    }

    /**
     * method getBoard() - returns the board the piece is on
     *
     * @param none
     * @return Board
     */
    public static Board getBoard(){
        return board;
    }

    /**
     * method currentSquare() - returns the current square of the piece, or null
     * if it does not exist
     *
     * @param none
     * @return square of piece
     */
    public Square currentSquare(){
        Square current = null;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(!board.getSquare(i, j).isEmpty() && board.getSquare(i, j).getPiece().equals(this)){
                    current = getBoard().getSquare(i, j);
                }

            }
        }
        return current;
    }

    /**
     * method getColor()- returns the color of the piece
     *
     * @param none
     * @return color of piece
     */
    public Color getColor(){
        return color;
    }

    /**
     * method getID()- returns a string containing the set ID of the piece
     *
     * @param none
     * @return identifier - id of piece
     */
    public String getID(){
        return identifier;
    }

    /**
     * method isEnemy(Piece p2) - see if p2 is an enemy of this piece
     *
     * @param p2 - the piece you would like to compare to the current piece
     * @return true if p2 is a different color, false if p2 is same color as
     *         this piecee
     */
    public boolean isEnemy(Piece p2){
        return (!p2.getColor().equals(this.getColor()));
    }

    /**
     * method getPossibleMoves() - get possible moves according to piece rules
     */
    public abstract ArrayList<Square> getPossibleMoves();

}

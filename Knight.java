package chess;

import java.util.ArrayList;

/**
 *
 * @author heiseed, wyliebl
 */
public class Knight extends Piece {

    /**
     * constructor Knight(Color c) - creates a Knight of that color and gives it
     * an identifying string
     *
     * @param c - color of piece
     */
    public Knight(Color c){
        super(c, "N");
    }

    /**
     * method getPossibleMoves() - get possible moves according to Knight rules
     *
     * @return ArrayList<Square> squares - all possible moves for Knight
     */
    @Override
    public ArrayList<Square> getPossibleMoves(){
        ArrayList<Square> squares = new ArrayList<>();
        Square start = this.currentSquare();
        int x = start.getX();
        int y = start.getY();

        squares.add(getBoard().getSquare(x + 2, y + 1));
        squares.add(getBoard().getSquare(x + 2, y - 1));
        squares.add(getBoard().getSquare(x + 1, y + 2));
        squares.add(getBoard().getSquare(x + 1, y - 2));
        squares.add(getBoard().getSquare(x - 1, y + 2));
        squares.add(getBoard().getSquare(x - 1, y - 2));
        squares.add(getBoard().getSquare(x - 2, y + 1));
        squares.add(getBoard().getSquare(x - 2, y - 1));

        squares = okaySpots(squares);
        return squares;
    }

    /**
     * method okaySpots(ArrayList<Square> yo) - check to see if all squares are
     * alright for the knight to move to (exist and are not empty)
     *
     * @param yo - the ArrayList to check
     * @return ArrayList<Square> - revised array list
     */
    private ArrayList<Square> okaySpots(ArrayList<Square> yo){
        for(int i = yo.size() - 1; i >= 0; i--){
            if(yo.get(i) == null){
                yo.remove(i);
            } else{
                Square s = yo.get(i);

                if((!s.isEmpty() && s.getPiece().getColor().equals(this.getColor())) || !Knight.getBoard().isValidSquare(s)){
                    yo.remove(s);
                }
            }
        }
        return yo;
    }
}

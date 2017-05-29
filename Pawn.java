package chess;

/**
 * A representation of a Pawn chess piece.
 *
 * @author heiseed, wyliebl
 */
import java.util.ArrayList;

public class Pawn extends Piece {

    private boolean hasMoved;

    /**
     * constructor Pawn(Color c) - creates a Pawn of that color and gives it an
     * identifying string
     *
     * @param c - color of piece
     */
    public Pawn(Color c){
        super(c, "P");
        hasMoved = false;
    }

    /**
     * method getPossibleMoves() - get possible moves according to Pawn rules
     *
     * @return a list of all possible moves for Pawn
     */
    @Override
    public ArrayList<Square> getPossibleMoves(){
        ArrayList<Square> squares = new ArrayList<Square>();
        Square start = this.currentSquare();
        int x = start.getX();
        int y = start.getY();

        if(getColor()==Color.WHITE){
            if(getBoard().isValidSquare(x + 1, y)){
                Square forward = getBoard().getSquare(x + 1, y);
                if(forward.isEmpty()){
                    squares.add(forward);
                    if(!hasMoved){
                        Square upTwo = getBoard().getSquare(x + 2, y);
                        if(upTwo.isEmpty()){
                            squares.add(upTwo);
                        }
                    }
                }
            }
            if(getBoard().isValidSquare(x+1, y+1)){
                Square s = getBoard().getSquare(x+1, y+1);
                if(!s.isEmpty() && isEnemy(s.getPiece())){
                    squares.add(s);
                }
            }
            if(getBoard().isValidSquare(x+1, y-1)){
                Square s = getBoard().getSquare(x+1, y-1);
                if(!s.isEmpty() && isEnemy(s.getPiece())){
                    squares.add(s);
                }
            }
        } else{
            if(getBoard().isValidSquare(x - 1, y)){
                Square forward = getBoard().getSquare(x - 1, y);
                if(forward.isEmpty()){
                    squares.add(forward);
                    if(!hasMoved){
                        Square upTwo = getBoard().getSquare(x - 2, y);
                        if(upTwo.isEmpty()){
                            squares.add(upTwo);
                        }
                    }
                }
            }
            if(getBoard().isValidSquare(x-1, y+1)){
                Square s = getBoard().getSquare(x-1, y+1);
                if(!s.isEmpty() && isEnemy(s.getPiece())){
                    squares.add(s);
                }
            }
            if(getBoard().isValidSquare(x-1, y-1)){
                Square s = getBoard().getSquare(x-1, y-1);
                if(!s.isEmpty() && isEnemy(s.getPiece())){
                    squares.add(s);
                }
            }
        }
        return squares;
    }

    /**
     * method pawnHasMoved() - call when the pawn has moved on the board
     */
    public void pawnHasMoved(){
        hasMoved = true;
    }

}

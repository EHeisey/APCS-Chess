package chess;

import java.util.ArrayList;

/**
 * A representation of a Bishop chess piece.
 *
 * @author heiseed, wyliebl
 */
public class Bishop extends Piece {

    /**
     * constructor Bishop(Color c) - creates a Bishop of that color and gives it
     * an identifying string
     *
     * @param c - color of piece
     */
    public Bishop(Color c){
        super(c, "B");
    }

    /**
     * method getPossibleMoves() - get possible moves according to Bishop rules
     *
     * @return all possible moves for Bishop
     */
    @Override
    public ArrayList<Square> getPossibleMoves(){
        ArrayList<Square> squares = new ArrayList<Square>();
        Square start = this.currentSquare();
        int x = start.getX();
        int y = start.getY();
        //up right
        int i;
        for(i=1; i<8; i++){
            if(!(getBoard().isValidSquare(x + i, y + i))) break;
            Square s = getBoard().getSquare(x + i, y + i);
            if(s.isEmpty()){
                squares.add(s);
            } else if(isEnemy(s.getPiece())){
                squares.add(s);
                break;
            } else{
                break;
            }
        }
        //up left
        for(i=1; i<8; i++){
            if(!(getBoard().isValidSquare(x - i, y + i))) break;
            Square s = getBoard().getSquare(x - i, y + i);
            if(s.isEmpty()){
                squares.add(s);
            } else if(isEnemy(s.getPiece())){
                squares.add(s);
                break;
            } else{
                break;
            }
        }
        //down right
        for(i=1; i<8; i++){
            if(!(getBoard().isValidSquare(x + i, y - i))) break;
            Square s = getBoard().getSquare(x + i, y - i);
            if(s.isEmpty()){
                squares.add(s);
            } else if(isEnemy(s.getPiece())){
                squares.add(s);
                break;
            } else{
                break;
            }
        }
        //down left
        for(i=1; i<8; i++){
            if(!(getBoard().isValidSquare(x - i, y - i))) break;
            Square s = getBoard().getSquare(x - i, y - i);
            if(s.isEmpty()){
                squares.add(s);
            } else if(isEnemy(s.getPiece())){
                squares.add(s);
                break;
            } else{
                break;
            }
        }
        return squares;
    }
}

package chess;

import java.util.ArrayList;

/**
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
     * @return ArrayList<Square> squares - all possible moves for Bishop
     */
    @Override
    public ArrayList<Square> getPossibleMoves(){
        ArrayList<Square> squares = new ArrayList<Square>();
        Square start = this.currentSquare();
        int x = start.getX();
        int y = start.getY();
        //up right
        boolean unblocked = true;
        int i = 1;
        while(unblocked && i < 8){
            if(!(getBoard().isValidSquare(x + i, y + i))){
                unblocked = false;
            } else{
                Square s = getBoard().getSquare(x + i, y + i);
                if(s.isEmpty()){
                    squares.add(s);
                    i++;
                } else if(s.getPiece().isEnemy(this)){
                    squares.add(s);
                    unblocked = false;
                } else{
                    unblocked = false;
                }
            }

        }
        //up left
        unblocked = true;
        i = 1;
        while(unblocked && i < 8){
            if(!(getBoard().isValidSquare(x - i, y + i))){
                unblocked = false;
            } else{
                Square s = getBoard().getSquare(x - i, y + i);
                if(s.isEmpty()){
                    squares.add(s);
                    i++;
                } else if(s.getPiece().isEnemy(this)){
                    squares.add(s);
                    unblocked = false;
                } else{
                    unblocked = false;
                }
            }

        }
        //down right
        unblocked = true;
        i = 1;
        while(unblocked && i < 8){
            if(!(getBoard().isValidSquare(x + i, y - i))){
                unblocked = false;
            } else{
                Square s = getBoard().getSquare(x + i, y - i);
                if(s.isEmpty()){
                    squares.add(s);
                    i++;
                } else if(s.getPiece().isEnemy(this)){
                    squares.add(s);
                    unblocked = false;
                } else{
                    unblocked = false;
                }
            }
        }
        //down left
        unblocked = true;
        i = 1;
        while(unblocked && i < 8){
            if(!(getBoard().isValidSquare(x - i, y - i))){
                unblocked = false;
            } else{
                Square s = getBoard().getSquare(x - i, y - i);
                if(s.isEmpty()){
                    squares.add(s);
                    i++;
                } else if(s.getPiece().isEnemy(this)){
                    squares.add(s);
                    unblocked = false;
                } else{
                    unblocked = false;
                }
            }
        }
        return squares;
    }
}

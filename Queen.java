package chess;

import java.util.ArrayList;

/**
 *
 * @author heiseed, wyliebl
 */
public class Queen extends Piece {

    /**
     * constructor Queen(Color c) - creates a Queen of that color and gives it
     * an identifying string
     *
     * @param c - color of piece
     */
    public Queen(Color c){
        super(c, "Q");
    }

    /**
     * method getPossibleMoves() - get possible moves according to Queen rules
     *
     * @return all possible moves for Queen
     */
    @Override
    public ArrayList<Square> getPossibleMoves(){
        ArrayList<Square> squares = new ArrayList<Square>();
        Square start = this.currentSquare();
        int x = start.getX();
        int y = start.getY();
        
        //moves up
        int i;
        for(i=1; i<8; i++){
            if(!(getBoard().isValidSquare(x, y + i))) break;
            Square s = getBoard().getSquare(x, y + i);
            if(s.isEmpty()){
                squares.add(s);
            } else if(isEnemy(s.getPiece())){
                squares.add(s);
                break;
            } else{
                break;
            }
        }
        //down
        for(i=1; i<8; i++){
            if(!(getBoard().isValidSquare(x, y - i))) break;
            Square s = getBoard().getSquare(x, y - i);
            if(s.isEmpty()){
                squares.add(s);
            } else if(isEnemy(s.getPiece())){
                squares.add(s);
                break;
            } else{
                break;
            }
        }
        //left
        for(i=1; i<8; i++){
            if(!(getBoard().isValidSquare(x - i, y))) break;
            Square s = getBoard().getSquare(x - i, y);
            if(s.isEmpty()){
                squares.add(s);
            } else if(isEnemy(s.getPiece())){
                squares.add(s);
                break;
            } else{
                break;
            }
        }
        //right
        for(i=1; i<8; i++){
            if(!(getBoard().isValidSquare(x + i, y))) break;
            Square s = getBoard().getSquare(x + i, y);
            if(s.isEmpty()){
                squares.add(s);
            } else if(isEnemy(s.getPiece())){
                squares.add(s);
                break;
            } else{
                break;
            }
        }
        //up right
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

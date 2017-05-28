package chess;

import java.util.ArrayList;

/**
 *
 * @author heiseed, wyliebl
 */
public class Board {

    private Square[][] board;

    /**
     * constructor Board() - create a new 8x8 board for the game to take place
     *
     */
    public Board(){
        board = new Square[8][8];
        for(int x = 0; x < 8; x++){
            for(int y = 0; y < 8; y++){
                board[x][y] = new Square(x, y);
            }
        }
    }

    /**
     * method initialize() - place pieces on board to start the game
     *
     */
    public void initialize(){
        // place white pieces
        Piece[][] white = {
            {
                new Rook(Color.WHITE),
                new Knight(Color.WHITE),
                new Bishop(Color.WHITE),
                new King(Color.WHITE),
                new Queen(Color.WHITE),
                new Bishop(Color.WHITE),
                new Knight(Color.WHITE),
                new Rook(Color.WHITE)},
            {
                new Pawn(Color.WHITE),
                new Pawn(Color.WHITE),
                new Pawn(Color.WHITE),
                new Pawn(Color.WHITE),
                new Pawn(Color.WHITE),
                new Pawn(Color.WHITE),
                new Pawn(Color.WHITE),
                new Pawn(Color.WHITE),}};

        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 8; j++){
                board[i][j].setPiece(white[i][j]);
            }
        }
        // place black pieces
        Piece[][] black = {
            {
                new Rook(Color.BLACK),
                new Knight(Color.BLACK),
                new Bishop(Color.BLACK),
                new King(Color.BLACK),
                new Queen(Color.BLACK),
                new Bishop(Color.BLACK),
                new Knight(Color.BLACK),
                new Rook(Color.BLACK)},
            {
                new Pawn(Color.BLACK),
                new Pawn(Color.BLACK),
                new Pawn(Color.BLACK),
                new Pawn(Color.BLACK),
                new Pawn(Color.BLACK),
                new Pawn(Color.BLACK),
                new Pawn(Color.BLACK),
                new Pawn(Color.BLACK),}};

        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 8; j++){
                board[7 - i][j].setPiece(black[i][j]);
            }
        }
    }

    /**
     * method print(Color c) - print the board from the perspective of a color
     *
     * @param c - color of player
     */
    public void print(Color c){
        if(c == Color.BLACK){
            System.out.println("\n  h g f e d c b a");
            for(int i = 0; i < 8; i++){
                System.out.print(i + 1);
                for(int j = 0; j < 8; j++){
                    if(board[i][j].isEmpty()){
                        System.out.print(" _");
                    } else{
                        System.out.print(" " + board[i][j].getPiece().getID());
                    }
                }
                if(i < 7){
                    System.out.println();
                }
            }
            System.out.println("\n  h g f e d c b a");

        } else{
            System.out.println("\n  a b c d e f g h");
            for(int i = 7; i > -1; i--){
                System.out.print(i + 1);
                for(int j = 7; j > -1; j--){
                    if(board[i][j].isEmpty()){
                        System.out.print(" _");
                    } else{
                        System.out.print(" " + board[i][j].getPiece().getID());
                    }
                }
                if(i > 0){
                    System.out.println();
                }
            }
            System.out.println("\n  a b c d e f g h\n");
        }
    }

    /**
     * method isValidSquare(Square s) - determine whether a square is on the
     * board
     *
     * @param s - the square to test
     * @return true if square exists on board, false otherwise
     */
    public boolean isValidSquare(Square s){
        return s.getX() > -1 && s.getX() < 8 && s.getY() > -1 && s.getY() < 8;
    }

    /**
     * method isValidSquare(int x, int y) - determine whether the coordinates
     * given will show a square on the board
     *
     * @param x - the x coordinate (from user input 1-8)
     * @param y - the y coordinate (from user input a-h)
     * @return true if square exists on board, false otherwise
     */
    public boolean isValidSquare(int x, int y){
        return x > -1 && x < 8 && y > -1 && y < 8;
    }

    /**
     * method getSquare(int x, int y) - get the Square at those x and y
     * coordinates
     *
     * @param x - the x coordinate (from user input 1-8)
     * @param y - the y coordinate (from user input a-h)
     * @return board[x][y] - the Square at those coordinates
     */
    public Square getSquare(int x, int y){
        if(x < 0 || x > 7 || y < 0 || y > 7){
            return null;
        }
        return board[x][y];
    }

    /**
     * method inCheck(Color current) - see if the current color is in check
     *
     * @param current - the color to check
     * @return true - if any piece of the opposite color can move to current
     *         color's king, false otherwise
     *
     */
    public boolean inCheck(Color current){
        for(Square enemy : enemies(current)){
            if(!enemy.isEmpty()){
                for(Square newEnemySpot : enemy.getPiece().getPossibleMoves()){
                    if(!newEnemySpot.isEmpty() && newEnemySpot.getPiece().getID().toLowerCase().equals("k")){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * method inCheckMate(Color c) - determine if the color c is in checkmate
     *
     * @param c - the color to test
     * @return true - if an enemy can take the king, the king cannot move out,
     *         and another piece cannot take that enemy or move in between the
     *         king and the enemy; false otherwise
     */
    public boolean inCheckMate(Color c){
        if(inCheck(c)){
            King k = findKing(c);
            if(k.getPossibleMoves().isEmpty()){
                if(enemies(enemies(c).get(0).getPiece().getColor()).isEmpty()){
                    return true;
                } else{
                    for(Square friend : enemies(enemies(c).get(0).getPiece().getColor())){
                        Piece p = friend.getPiece();
                        if(!p.getID().toLowerCase().equals("k")){
                            for(Square helpful : p.getPossibleMoves()){
                                Piece temp = helpful.getPiece();
                                Piece f = p;
                                friend.removePiece();
                                helpful.setPiece(f);
                                if(!inCheck(c)){
                                    friend.setPiece(f);
                                    helpful.setPiece(temp);
                                    return false;
                                } else{
                                    friend.setPiece(f);
                                    helpful.setPiece(temp);
                                }
                            }
                            return true;
                        }
                    }
                }
            } else{
                return false;
            }

        }
        return false;

    }

    /**
     * method findKing(Color c) - find the king of the color c (and thus its
     * location)
     *
     * @param c - the color of the king to find
     * @return King k - the king of color c
     */
    private King findKing(Color c){
        King k = null;
        for(Square[] r : board){
            for(Square s : r){
                if(!s.isEmpty() && s.getPiece().getID().toLowerCase().equals("k")){
                    if(s.getPiece().getColor().equals(c)){
                        k = (King)s.getPiece();
                    }
                }
            }
        }
        return k;
    }

    /**
     * method enemies(Color c) - find all the Squares of the enemies of a color
     * c
     *
     * @param c - the color to find enemies for
     * @return ArrayList<Square> enemies - all Squares of enemies of color c
     */
    public ArrayList<Square> enemies(Color c){
        ArrayList<Square> enemies = new ArrayList<>();
        for(Square[] r : board){
            for(Square s : r){
                if(!s.isEmpty() && !s.getPiece().getColor().equals(c)){
                    enemies.add(s);
                }
            }
        }
        return enemies;
    }
}

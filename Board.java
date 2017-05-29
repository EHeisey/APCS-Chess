package chess;

import java.util.ArrayList;

/**
 * Represents all the squares on a chess board and provides access to individual
 * squares. Also has methods for actions on the board as a whole (i.e. check, 
 * checkmate, findKing, isValidSquare).
 * 
 * @author heiseed, wyliebl
 */
public class Board {

    private Square[][] board;

    /**
     * constructor Board() - create a new 8x8 board for the game to take place.
     * Places all the chess pieces in their starting positions
     */
    public Board(){
        board = new Square[8][8];
        for(int x = 0; x < 8; x++){
            for(int y = 0; y < 8; y++){
                board[x][y] = new Square(x, y);
            }
        }
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
                new Pawn(Color.WHITE)}};

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
                new Pawn(Color.BLACK)}};

        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 8; j++){
                board[7 - i][j].setPiece(black[i][j]);
            }
        }
    }

    /**
     * method print(Color c) - print the board from the perspective of the specified color
     *
     * @param c - color of player
     */
    public void print(Color c){
        if(c == Color.BLACK){
            System.out.println("\n    h    g    f    e    d    c    b    a\n");
            for(int i = 0; i < 8; i++){
                System.out.print(i + 1);
                for(int j = 0; j < 8; j++){
                    if(board[i][j].isEmpty()){
                        System.out.print("   __");
                    } else{
                        System.out.print("   " + board[i][j].getPiece().getID());
                    }
                }
                System.out.println("   "+(i+1)+"\n");
            }
            System.out.println("    h    g    f    e    d    c    b    a\n");
        } else{
            System.out.println("\n    a    b    c    d    e    f    g    h\n");
            for(int i = 7; i > -1; i--){
                System.out.print(i + 1);
                for(int j = 7; j > -1; j--){
                    if(board[i][j].isEmpty()){
                        System.out.print("   __");
                    } else{
                        System.out.print("   " + board[i][j].getPiece().getID());
                    }
                }
                System.out.println("   "+(i+1)+"\n");
            }
            System.out.println("    a    b    c    d    e    f    g    h\n");
        }
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
     * @param x - the x coordinate
     * @param y - the y coordinate
     * @return the Square at the give coordinates
     */
    public Square getSquare(int x, int y){
        if(x < 0 || x > 7 || y < 0 || y > 7){
            return null;
        }
        return board[x][y];
    }

    /**
     * method inCheck(Color c) - see if the c color is in check
     *
     * @param c - the color to check
     * @return true if any piece of the opposite color can move to c
     * color's king, false otherwise
     */
    public boolean inCheck(Color c){
        // finds the king on the board
        Square king = null;
        for(Square[] row : board){
            for(Square s : row){
                if(!s.isEmpty() && s.getPiece() instanceof King && s.getPiece().getColor()==c) king = s;
            }
        }
        // checks if the king is in a possible move of an enemy piece
        for(Square enemy : enemies(c)){
            if(enemy.getPiece().getPossibleMoves().contains(king)) return true;
        }
        return false;
    }

    /**
     * method inCheckMate(Color c) - determine if the color c is in checkmate
     *
     * @param c - the color to test
     * @return true if an enemy can take the king, the king cannot move out,
     *         and another piece cannot take that enemy or move in between the
     *         king and the enemy; false otherwise
     */
    public boolean inCheckMate(Color c){
        // check if the king is currently in check
        if(!inCheck(c)) return false;
        King k = findKing(c);
        // check if the king itself has any possible moves
        if(!k.getPossibleMoves().isEmpty()) return false;
        // check if the king is the only piece left
        ArrayList<Square> friends = enemies(c.opponent());
        if(friends.size()==1) return true;
        // check if any other pieces can block the attack
        for(Square friend : friends){
            Piece p = friend.getPiece();
            if(p instanceof King) continue;
            for(Square helpful : p.getPossibleMoves()){
                Piece temp = helpful.getPiece();
                friend.removePiece();
                helpful.setPiece(p);
                if(!inCheck(c)){
                    friend.setPiece(p);
                    helpful.setPiece(temp);
                    return false;
                } else{
                    friend.setPiece(p);
                    helpful.setPiece(temp);
                }
            }
        }
        return true;
    }

    /**
     * method findKing(Color c) - find the king of the color c
     *
     * @param c - the color of the king to find
     * @return the king of color c
     */
    private King findKing(Color c){
        for(Square[] r : board){
            for(Square s : r){
                if(!s.isEmpty() && s.getPiece() instanceof King && s.getPiece().getColor()==c){
                    return (King)s.getPiece();
                }
            }
        }
        return null;
    }

    /**
     * method enemies(Color c) - find all the Squares of the enemies of a color
     * c
     *
     * @param c - the color to find enemies for
     * @return all Squares of enemies of color c
     */
    public ArrayList<Square> enemies(Color c){
        ArrayList<Square> enemies = new ArrayList<>();
        for(Square[] r : board){
            for(Square s : r){
                if(!s.isEmpty() && s.getPiece().getColor()!=c){
                    enemies.add(s);
                }
            }
        }
        return enemies;
    }
}

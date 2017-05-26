package chess;

import java.util.ArrayList;
/**
 *
 * @author heiseed
 */
public class Board {

    private Square[][] board;

    public Board() {
        board = new Square[8][8];
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                board[x][y] = new Square(x, y);
            }
        }
    }
    
        public void print(Color c){
        if (c==Color.BLACK) {
            System.out.println("\n  a b c d e f g h");
            for (int i = 0; i < 8; i++) {
                System.out.print(i + 1);
                for (int j = 0; j < 8; j++) {
                    if (board[i][j].isEmpty()) {
                        System.out.print(" _");
                    } else {
                        System.out.print(" " + board[i][j].getPiece().getID());
                    }
                }
                System.out.println();
            }
        } else {
            System.out.println("\n  h g f e d c b a");
            for (int i = 7; i > -1; i--) {
                System.out.print(i + 1);
                for (int j = 7; j > -1; j--) {
                    if (board[i][j].isEmpty()) {
                        System.out.print(" _");
                    } else {
                        System.out.print(" " + board[i][j].getPiece().getID());
                    }
                }
                System.out.println();
            }
        }
    } 
    
    public boolean isValidSquare(Square s) {
        return s.getX() > -1 && s.getX() < 8 && s.getY() > -1 && s.getY() < 8;
    }
    public boolean isValidSquare(int x, int y) {
    return x > -1 && x < 8 && y > -1 && y < 8;
    }
    public Square getSquare(int x, int y) {
        if (x < 0 || x>7 || y<0|| y>7){
            return null;
        }
        return board[x][y];
    }
    public boolean inCheck(Color current) {
        //must get square of king
        King k;
        for (Square[] r : board) {
            for (Square s : r) {
                if (!s.isEmpty()) {
                    if (s.getPiece().getID().toLowerCase().equals("K") && s.getPiece().getColor().equals(current)){
                        k = (King)s.getPiece();
                        return k.inCheck(s);
                    } 
                }    
            }
        }
        return false;
    }
    public ArrayList<Square> enemies(Color c) {
        ArrayList<Square> enemies = new ArrayList<>();
        for (Square[] r: board) {
            for (Square s: r) {
                if(!s.isEmpty() && !s.getPiece().getColor().equals(c)) {
                    enemies.add(s);
                }    
            }
        }    
        return enemies;
    }    
    /*public Square findPiece(Piece p, Color c) {
        for (Square[] r : board) {
            for (Square s : r) {
               if (!s.isEmpty())  {
                 if (s.getPiece().getID().equals(p.getID()) && s.getPiece().getColor().equals(c)) {
                     return s;
                 }
               }
            }
        }
        return null;
    }*/ //not working sadly   
    /*
    * method getSquare(int x, int y) - find square at x and y coordinates
     int x - row
     int y- column
     return Square at coordinates or null if Square does not exist
    */
    
   
}
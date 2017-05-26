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
        for (Square enemy : enemies(current)) {
            for (Square newEnemySpot : enemy.getPiece().getPossibleMoves()) {
                if (!newEnemySpot.isEmpty() && newEnemySpot.getPiece().getID().toLowerCase().equals("k")) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean inCheckMate(Color c) {
        if (inCheck(c)) {
            King k = findKing(c);
            return k.getPossibleMoves().size() <= 0;
        }
        return false;
    }
    
    private King findKing(Color c) {
        King k = null;
        for (Square[] r : board) {
            for (Square s : r) {
                if (!s.isEmpty() && s.getPiece().getID().toLowerCase().equals("k")) {
                    if (s.getPiece().getColor().equals(c)) {
                        k = (King) s.getPiece();
                    }
                }
            }
        }
        return k;
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
}

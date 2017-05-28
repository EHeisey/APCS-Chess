package chess;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author wyliebl
 */
public class Pawn extends Piece {

    public boolean hasMoved = false;

    public Pawn(Color c) {
        super(c, "P");
    }

    @Override
    public ArrayList<Point> getMoves(Piece[][] board, Point start) {
        ArrayList<Point> moves = new ArrayList<>();

        int x = start.x;
        int y = start.y;

        if (getColor().equals(Color.WHITE)) {
            if (isValidLocation(x + 1, y)) {
                Point forward = new Point(x + 1, y);
                if (board[x + 1][y].getID().length() == 0) {
                    moves.add(forward);
                }
                if (!hasMoved) {
                    Point upTwo = new Point(x + 2, y);
                    if (isValidLocation(x + 2, y) && board[x + 2][y].getID().length() == 0) {
                        moves.add(upTwo);
                    }
                }
            };

            if (isValidLocation(x + 1, y + 1)) {
                if (board[x + 1][y + 1].getID().length() > 0 && board[x + 1][y + 1].getColor().equals(Color.BLACK)) {
                    moves.add(new Point(x + 1, y + 1));
                }
            }
            if (isValidLocation(x + 1, y - 1)) {
                if (board[x + 1][y - 1].getID().length() > 0 && board[x + 1][y - 1].getColor().equals(Color.BLACK)) {
                    moves.add(new Point(x + 1, y - 1));
                }
            }
        } else {
            if (isValidLocation(x - 1, y)) {
                Point forward = new Point(x - 1, y);
                if (board[x - 1][y].getID().length() == 0) {
                    moves.add(forward);
                }
                if (!hasMoved) {
                    Point upTwo = new Point(x - 2, y);
                    if (isValidLocation(x - 2, y) && board[x - 2][y].getID().length() == 0) {
                        moves.add(upTwo);
                    }
                }
            };
            if (isValidLocation(x - 1, y + 1)) {
                if (board[x - 1][y + 1].getID().length() > 0 && board[x - 1][y + 1].getColor().equals(Color.BLACK)) {
                    moves.add(new Point(x + 1, y + 1));
                }
            }
            if (isValidLocation(x - 1, y - 1)) {
                if (board[x - 1][y - 1].getID().length() > 0 && board[x - 1][y - 1].getColor().equals(Color.BLACK)) {
                    moves.add(new Point(x + 1, y - 1));
                }
            }
        }
        return moves;
    }
    public void pawnHasMoved() {
        hasMoved = true;
    }    

}

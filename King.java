package chess;

import java.util.ArrayList;

/**
 *
 * @author wyliebl
 */
public class King extends Piece {

    public King(Color c) {
        super(c, "K");
    }

    @Override
    public ArrayList<Square> getPossibleMoves(Square start) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

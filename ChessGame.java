package chess;

import java.util.Scanner;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * A command-line version of the classic game of chess.
 *
 * @author heiseed, wyliebl
 */
public class ChessGame {

    private static Scanner console = new Scanner(System.in);
    
    private final Board board;
    private final Player white, black;
    private int turn;
    private Player current;

    /**
     * constructor ChessGame() - set up a chess game with two players and find
     * out the names of those players
     */
    public ChessGame(){
        board = new Board();
        Piece.setBoard(board);
        System.out.print("Player 1 (white):");
        String w = console.nextLine();
        white = new Player(Color.WHITE, w);
        System.out.print("Player 2 (black):");
        String b = console.nextLine();
        black = new Player(Color.BLACK, b);
    }

    /**
     * method start() - start the chess game and call the turn method until
     * there is a winner
     */
    public void start(){
        do{
            turn();
        } while(!board.inCheckMate((turn % 2 == 0) ? Color.WHITE : Color.BLACK));
        Player winner = (turn % 2 == 1) ? white : black;
        board.print(winner.getColor());
        System.out.println("Congratulations! Player " + winner.getName() + " has won the game. ");
        String url = "http://www.google.com/search?q=victory";
        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
            }
        }else{
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("xdg-open " + url);
            } catch (IOException e) {
            }
        }
    }

    /**
     * method turn() - take a player's turn, ask for the movements to be
     * completed, move pieces as necessary
     */
    private void turn(){
        current = (turn%2 == 0) ? white : black;
        System.out.println("\n" + current.getName() + "'s Turn ("+current.getColor().toString().toLowerCase()+")");
        board.print(current.getColor());
        boolean inCheck = board.inCheck(current.getColor());
        if(inCheck) System.out.println("You are in check. Please move out of check.");

        System.out.print("Enter start position:");

        Square start = null;
        Piece p = null;
        while(start==null||p == null){
            start = askLocation();
            // cannot select an empty square
            if(start.isEmpty()){
                System.out.print("No piece here. Please enter a location containing a piece:");
                continue;
            }
            // cannot select a piece of the opposite color
            if(!start.getPiece().getColor().equals(current.getColor())){
                System.out.print("You cannot move this piece. Pick a " + current.getColor() + " piece:");
                continue;
            }
            // cannot select a piece with no poosible moves
            if(start.getPiece().getPossibleMoves().isEmpty()){
                System.out.print("This piece is trapped. Pick another piece:");
                continue;
            }
            
            // now p is a valid piece on the board, of the right color, with possible moves
            p = start.getPiece();
            
            // deselects this piece if the player is in check and this piece cannot change that
            if(inCheck){
                boolean canhelp = false;
                for(Square s : p.getPossibleMoves()){
                    Piece temp = s.getPiece();

                    start.removePiece();
                    s.setPiece(p);
                    if(!board.inCheck(current.getColor())){
                        canhelp = true;
                    }
                    start.setPiece(p);
                    s.setPiece(temp);
                    if(canhelp) break;
                }
                if(!canhelp){
                    System.out.print("You cannot move this piece to get out of check. Choose another.");
                    p = null;
                }
            }
        }
        
        System.out.print("Enter move position:");
        Square end = askLocation();
        while(!canMove(p, start, end)){
            System.out.print("You cannot move that piece here. Choose another location:");
            end = askLocation();
        }
        end.setPiece(p);
        start.removePiece();
        if(p instanceof Pawn) ((Pawn)p).pawnHasMoved();
        turn++;
    }

    /**
     * method canMove(Piece p, Square start, Square end) - checks if the Piece can
     * move from start to end
     *
     * @param Piece  p - the piece to move
     * @param Square start - the square the piece starts at
     * @param Square end - the potential end point of the piece
     * @return true - if the piece does not cause the king to be in check and
     *         the rules of that piece allow the move; false otherwise
     */
    private boolean canMove(Piece p, Square start, Square end){
        // checks if the selected end move is a possible move for the piece
        if(!p.getPossibleMoves().contains(end)) return false;
        // checks if this move will place the king in check
        boolean canMove = true;
        start.removePiece();
        end.setPiece(p);
        if(board.inCheck(p.getColor())) canMove = false;
        end.removePiece();
        start.setPiece(p);
        return canMove;
    }

    /**
     * method printInstructions() - prints instructions for the game
     */
    public static void printInstructions(){
        System.out.println("            .......................................");
        System.out.println("            .            Classic Chess            .");
        System.out.println("            .                                     .");
        System.out.println("            .     Eli Heisey & Brittany Wylie     .");
        System.out.println("            .......................................");
        System.out.println();
        System.out.println("The white pieces are displayed with a preceding \"W\":");
        System.out.println("King: WK, Queen: WQ, Bishop: WB, Knight: WN, Rook: WR, Pawn: WP");
        System.out.println();
        System.out.println("The black pieces are displayed with a preceding \"B\":");
        System.out.println("King: BK, Queen: BQ, Bishop: BB, Knight: BN, Rook: BR, Pawn: BP");
        System.out.println();
        System.out.println("This game displays standard chess coordinates on each side of");
        System.out.println("the game board each time it is displayed. Players must enter their");
        System.out.println("move selections in the form of row and column (ex. 1a, 5d, 3F, 8H).");
        System.out.println();
    }

    /**
     * method askLocation() - parse the user's location input for the location of
     * a desired square on the board
     * 
     * @return the square the user requests
     */
    private Square askLocation(){
        String loc = console.nextLine();

        if(loc.length() != 2){
            System.out.print("Incorrect format. Try in format: 1a");
            return askLocation();
        }
        int x, y;
        try{
            x = Integer.parseInt(loc.substring(0, 1)) - 1;
        } catch(NumberFormatException e){
            System.out.print("Incorrect format. Try in format: 1a");
            return askLocation();
        }
        if(!Character.isLetter(loc.charAt(1))){
            System.out.print("Incorrect format. Try in format: 1a");
            return askLocation();
        }
        switch(loc.charAt(1)){
            case 'a':
            case 'A':
                y = 7;
                break;
            case 'b':
            case 'B':
                y = 6;
                break;
            case 'c':
            case 'C':
                y = 5;
                break;
            case 'd':
            case 'D':
                y = 4;
                break;
            case 'e':
            case 'E':
                y = 3;
                break;
            case 'f':
            case 'F':
                y = 2;
                break;
            case 'g':
            case 'G':
                y = 1;
                break;
            case 'h':
            case 'H':
                y = 0;
                break;
            default:
                y = -1;
        }

        Square s = board.getSquare(x, y);
        if(s==null){
            System.out.print("Invalid Square. Try again:");
            return askLocation();
        }
        return s;

    }

}

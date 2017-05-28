package chess;

import java.util.*;

/**
 * A command-line version of the classic game of chess.
 * 
 * @author heiseed, wyliebl
 */
public class ChessGame {
    
    private final Board board;
    private final Player white, black;
    private int turn;
    private Scanner console;
    private Player current;
    /**
    constructor ChessGame() - set up a chess game with two players and find out the names of those players
    @param none
    */
    public ChessGame(){
        console = new Scanner(System.in);
        board = new Board();
        Piece.setBoard(board);
        board.initialize();
        System.out.print("Player 1 (white):");
        String w = console.nextLine();
        white = new Player(Color.WHITE, w);
        System.out.print("Player 2 (black):");
        String b = console.nextLine();
        black = new Player(Color.BLACK, b);
        current = white;
    }
    
    /**
    method start() - start the chess game and call the turn method until there is a winner, where it will prompt to play again
    @param none
    @return null
    */
    public void start(){
        do{
            turn();
        } while(!board.inCheckMate((turn%2==0) ? Color.WHITE : Color.BLACK)); 
        Color winner = (turn%2==0) ? Color.WHITE : Color.BLACK;
        board.print(winner);
        boolean answered = false;
        
        System.out.println("Congratulations! Player " + current.getName() + " has won the game. ");   
    }
    /**
    method turn() - take a player's turn, ask for the movements to be completed, move pieces as necessary
    @param none
    @return null
    */
    private void turn(){
        current = (turn%2==0) ? white : black;
        System.out.println("\n"+current.getName()+"'s Turn");
        board.print(current.getColor());
        if(board.inCheck(current.getColor())) {
            System.out.println("You are in check. Please move out of check.");
        }
        
        System.out.println("Enter start position in this format: 1a"); 
        Square start = askLocation();
        
        Piece p = null;
        while (p == null) {
            if (start.isEmpty()) {
                System.out.println("No piece here. Please enter a location in this format: 1a ");
                start = askLocation();
            } else if (!start.getPiece().getColor().equals(current.getColor())){
                System.out.println("You cannot move this piece. Pick a " + current.getColor() + " piece.");
                start = askLocation();
            } else if (start.getPiece().getPossibleMoves().isEmpty()) {
                System.out.println("You cannot move this piece. Pick another " + current.getColor() + " piece.");
                start = askLocation();
            }
                p = start.getPiece();
            //cannot choose this piece if in check
            if (board.inCheck(current.getColor())) {
                boolean canhelp = false;
                for (Square s: p.getPossibleMoves()) {
                    Piece temp = s.getPiece();
                    
                    start.removePiece();
                    s.setPiece(p);
                    if (!board.inCheck(current.getColor())) {
                        canhelp = true;
                    }
                    start.setPiece(p);
                    s.setPiece(temp);
      
                } 
                if (!canhelp) {
                    System.out.println("You cannot move this piece to get out of check. Choose another.");
                    start = askLocation();
                }
            }    
        }
        System.out.println("Enter move position in this format: 1a");
        Square end = askLocation();
        
        boolean canMoveHere = canMove(p, start, end);
        while (!canMoveHere) {
            System.out.println("You cannot move that piece here. Choose another location.");
            end = askLocation();
            canMoveHere = canMove(p, start, end);
        }
        end.setPiece(p);
        start.removePiece();
        if (p.getID().toLowerCase().equals("p")) {
            Pawn pawn = (Pawn)p;
            pawn.pawnHasMoved();
        }
        turn++;
    }
    /**
    method canMove(Piece p, Square start, Square end) - see if the Piece can move from start to end
    @param Piece p - the piece to move
    @param Square start - the square the piece starts at
    @param Square end - the potential end point of the piece
    @return true - if the piece does not cause the king to be in check and the rules of that piece allow the move
    @return false otherwise
    */
    private boolean canMove(Piece p, Square start, Square end) {
        for (Square choices: p.getPossibleMoves()) {
            if (choices.equals(end)) {
                start.removePiece();
                end.setPiece(p);
                if (board.inCheck(p.getColor())) {
                    end.removePiece();
                    start.setPiece(p);
                    System.out.println("You are in check.");
                    return false;
                }
                end.removePiece();
                start.setPiece(p);
                return true;
            }
        }    
        return false;
    }    

    /**
     *method printInstructions() - print instructions for the game 
     *@param none
     *@return null
     **/

    public static void printInstructions(){
         System.out.println("            .......................................");
         System.out.println("            .            Classic Chess            .");
         System.out.println("            .                                     .");
         System.out.println("            .     Eli Heisey & Brittany Wylie     .");
         System.out.println("            .......................................");
         System.out.println();
         System.out.println("This game displays standard chess coordinates above and beside");
         System.out.println("the game board each time it is displayed. Players must enter their");
         System.out.println("move selections in the form of row and column (ex. 1a, 5d, 3F, 8H).");
         
         System.out.println();
    }
    /**
    method askLocation() - parse the user's location input for the x and y of some square on the board
    @param null
    @return Square s - the square the user requests
     **/
    private Square askLocation() {
        console = new Scanner(System.in);
        String loc = console.nextLine();
        
        if (loc.length() == 2) {
            int x = -1;
            try {
                x = Integer.parseInt(loc.substring(0, 1)) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Bad format. Try in format: 1a");
                return askLocation();
            }
            int y = -1;
            try {
                loc.charAt(1);
            } catch (NullPointerException e) {
                System.out.println("Bad format. Try in format: 1a");
                return askLocation();
            }
            
            switch (loc.charAt(1)) {
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

            Square s;
            try {
                s = board.getSquare(x, y);
            } catch (IndexOutOfBoundsException e) {
                System.out.print("Invalid Square. Try again:");
                return askLocation();
            }
            return s;
        } else {
            System.out.println("Position input wrong length. Try in format: 1a");
            return askLocation();

        }
        
    } 
    
}
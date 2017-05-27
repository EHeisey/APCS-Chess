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
    }
    
    //call the turn method until there's a winner
    public void start(){
        
        boolean playing = true; 
        while (playing) {
            do{
                turn();
            } while(!board.inCheckMate((turn%2==0) ? Color.WHITE : Color.BLACK)); 
            Color winner = (turn%2==0) ? Color.WHITE : Color.BLACK;
            board.print(winner);
            boolean answered = false;
            System.out.println("Congratulations! Color " + winner + " has won the game. Play again? (yes/no)");
            while (!answered) {
                console = new Scanner(System.in);
                String loc = console.nextLine();
                if (loc.contains("yes")) {
                    answered = true;
                } if (loc.contains("no")) {
                    answered = true;
                    playing = false;
                } else {
                    System.out.println("Please answer the question with a yes or a no.");
                }   
            }    
        }
    }
    
    private void turn(){
        Player current = (turn%2==0) ? white : black;
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
            } else if (start.getPiece().getPossibleMoves().size() == 0) {
                System.out.println("You cannot move this piece. Pick another " + current.getColor() + " piece.");
                start = askLocation();
            }
                p = start.getPiece();
            
        }
        System.out.println("Enter move position in this format: 1a");
        Square end = askLocation();
        
        boolean canMoveHere = canMove(p, start, end);
        if (!canMoveHere) {
            System.out.println("You cannot move that piece here. Choose another location.");
            end = askLocation();
        }

        turn++;
    }
    
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
                return true;
            }
        }    
        return false;
    }    
    /*
    * 
    */
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
    private Square askLocation() {
        console = new Scanner(System.in);
        String loc = console.nextLine();
        if (loc.length() == 2) {
            //problem here... cannot catch strings and will esc??
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
                return null;
            }
            
            switch (loc.charAt(1)) {
                case 'a':
                case 'A':
                    y = 0;
                    break;
                case 'b':
                case 'B':
                    y = 1;
                    break;
                case 'c':
                case 'C':
                    y = 2;
                    break;
                case 'd':
                case 'D':
                    y = 3;
                    break;
                case 'e':
                case 'E':
                    y = 4;
                    break;
                case 'f':
                case 'F':
                    y = 5;
                    break;
                case 'g':
                case 'G':
                    y = 6;
                    break;
                case 'h':
                case 'H':
                    y = 7;
                    break;
                default:
                    y = -1;
            }

            Square s;
            try {
                s = board.getSquare(x, y);
            } catch (IndexOutOfBoundsException e) {
                System.out.print("Invalid Square. Try again:");
                s = askLocation();
            }
            return s;
        } else {
            System.out.println("Position input wrong length. Try in format: 1a");
            return askLocation();
        }
        
    } 
    
}
package chess;

import java.util.Scanner;
import java.awt.Point;

/**
 * A command-line version of the classic game of chess.
 *
 * @author heiseed, wyliebl
 */
public class ChessGame {

    private Piece[][] board;
    private Player white, black;
    private Scanner console;
    private int turn;

    /**
     * Creates a new instance of a chess game.
     * 
     * Sets up the board with all 32 pieces in position.
     */
    public ChessGame(){
        console = new Scanner(System.in);
        //set-up board and pieces
        board = new Piece[8][8];
        // white pieces
        board[0][0] = new Rook(Color.WHITE);
        board[0][1] = new Knight(Color.WHITE);
        board[0][2] = new Bishop(Color.WHITE);
        board[0][3] = new Queen(Color.WHITE);
        board[0][4] = new King(Color.WHITE);
        board[0][5] = new Bishop(Color.WHITE);
        board[0][6] = new Knight(Color.WHITE);
        board[0][7] = new Rook(Color.WHITE);
        for(int i=0; i<8; i++) board[1][i] = new Pawn(Color.WHITE);
        // black pieces
        board[7][0] = new Rook(Color.BLACK);
        board[7][1] = new Knight(Color.BLACK);
        board[7][2] = new Bishop(Color.BLACK);
        board[7][3] = new Queen(Color.BLACK);
        board[7][4] = new King(Color.BLACK);
        board[7][5] = new Bishop(Color.BLACK);
        board[7][6] = new Knight(Color.BLACK);
        board[7][7] = new Rook(Color.BLACK);
        for(int i=0; i<8; i++) board[6][i] = new Pawn(Color.BLACK);
    }
    
    /**
     * Prints a set of instructions specific to this version of chess.
     * 
     * This only instructs the player how to interact with this game. It does 
     * not describe how to play the game of chess; it is assumed the user 
     * already knows how to play chess.
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

    //call the turn method until there's a winner
    public void start(){
        System.out.print("Player 1 (white):");
        String w = console.nextLine();
        white = new Player(Color.WHITE, w);
        System.out.print("Player 2 (black):");
        String b = console.nextLine();
        black = new Player(Color.BLACK, b);
        do{
            turn();
        } while(!inCheckmate(Color.WHITE)&&!inCheckmate(Color.BLACK));
    }

    private void turn(){
        Player currentPlayer = (turn % 2 == 0) ? white : black;
        System.out.println("\n" + currentPlayer.getName() + "'s Turn");
        printBoard(currentPlayer.getColor());
        System.out.print("Enter starting position:");
        Point start = askLocation();
        while(board[start.x][start.y]==null||board[start.x][start.y].getColor()!=currentPlayer.getColor()){
            System.out.print("Not your piece. Try again:");
            start = askLocation();
        }
        System.out.print("\nEnter move position:");
        Point end = askLocation();

        turn++;
    }

    /**
     * Returns the user's response to prompts for a location on the board and 
     * parses the input to a Point that indicates indices in the array of Pieces
     * @return the point the user has entered
     */
    private Point askLocation(){
        String loc = console.nextLine();
        int x = Integer.parseInt(loc.substring(0, 1)) - 1;
        int y;
        switch(loc.charAt(1)){
            case 'a':
            case 'A': y = 0;
                break;
            case 'b':
            case 'B': y = 1;
                break;
            case 'c':
            case 'C': y = 2;
                break;
            case 'd':
            case 'D': y = 3;
                break;
            case 'e':
            case 'E': y = 4;
                break;
            case 'f':
            case 'F': y = 5;
                break;
            case 'g':
            case 'G': y = 6;
                break;
            case 'h':
            case 'H': y = 7;
                break;
            default: y = -1;
        }
        try{
            return new Point(x, y);
        } catch(IndexOutOfBoundsException e){
            System.out.print("Invalid position. Try again:");
            return askLocation();
        }
    }

    /**
     * Displays the chess board so that the currentPlayer currentPlayer's starting side is
 at the bottom of the screen. This display includes coordinates above and
     * to the left of the board for the user to read and enter positions from
     * the board.
     * @param c the color of the currentPlayer whose perspective should be used
     */
    public void printBoard(Color c){
        if(c == Color.WHITE){
            System.out.println("\n   a b c d e f g h\n");
            for(int i = 7; i > -1; i--){
                System.out.print(i + 1+" ");
                for(int j = 0; j < 8; j++){
                    if(board[i][j] == null){
                        System.out.print(" _");
                    } else{
                        System.out.print(" " + board[i][j].getID());
                    }
                }
                System.out.println();
            }
        } else{
            System.out.println("\n   h g f e d c b a\n");
            for(int i=0; i<8; i++){
                System.out.print(i + 1+" ");
                for(int j = 7; j > -1; j--){
                    if(board[i][j] == null){
                        System.out.print(" _");
                    } else{
                        System.out.print(" " + board[i][j].getID());
                    }
                }
                System.out.println();
            }
        }
    }

    // needs serious work
    public boolean inCheck(Color c){
        
        return false;
    }
    
    public boolean inCheckmate(Color c){
        
        return false;
    }

}

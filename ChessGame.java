package chess;

import java.util.Scanner;

/**
 * A command-line version of the classic game of chess.
 * 
 * @author heiseed, wyliebl
 */
public class ChessGame {
    
    private Board board;
    private Player white, black;
    private int turn;
    private Scanner console;
    
    public ChessGame(){
        console = new Scanner(System.in);
        board = new Board();
        Piece.setBoard(board);
        System.out.print("Player 1 (white):");
        String w = console.nextLine();
        white = new Player(Color.WHITE, w);
        System.out.print("Player 2 (black):");
        String b = console.nextLine();
        black = new Player(Color.BLACK, b);
    }
    
    //call the turn method until there's a winner
    public void start(){
        do{
            turn();
        } while(!board.inCheckMate((turn%2==0) ? Color.WHITE : Color.BLACK));  // change to check for a checkmate
    }
    
    private void turn(){
        Player current = (turn%2==0) ? white : black;
        System.out.println("\n"+current.getName()+"'s Turn");
        board.print(current.getColor());
        System.out.print("Enter starting position:");
        Square start = askLocation();
        System.out.print("\nEnter move position:");
        Square end = askLocation();
        
        
        turn++;
    }
    
    private Square askLocation(){
        String loc = console.nextLine();
        int x = Integer.parseInt(loc.substring(0, 1))-1;
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
        Square s;
        try{
            s = board.getSquare(x, y);
        }
        catch(IndexOutOfBoundsException e){
            System.out.print("Invalid Square. Try again:");
            s = askLocation();
        }
        return s;
    }
    
}

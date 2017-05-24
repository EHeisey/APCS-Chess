package chess;

import java.util.Scanner;

/**
 *
 * @author heiseed
 */
public class ChessGame {
    
    private Board board;
    private Player white, black;
    private int turn;
    private Scanner console;
    
    public ChessGame(){
        console = new Scanner(System.in);
        board = new Board();
        System.out.print("Player 1 (white):");
        String w = console.nextLine();
        white = new Player(Color.WHITE, w);
        System.out.print("Player 2 (black):");
        String b = console.nextLine();
        black = new Player(Color.BLACK, b);
    }
    
    public void start(){
        //call the turn method until there's a winner
    }
    
    private void turn(){
        Player current = (turn%2==0) ? white : black;
        board.print();
        System.out.print("Enter starting position");
        turn++;
    }
    
    private Square getLocation(){
        String
    }
    
}

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
        
    }
    
    public void start(){
        
    }
    
    private void turn(){
        Player current = (turn%2==0) ? white : black;
        
        turn++;
    }
    
}

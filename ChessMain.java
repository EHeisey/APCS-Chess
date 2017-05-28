package chess;

import static chess.ChessGame.printInstructions;
import java.util.Scanner;

/**
 *
 * @author heiseed, wyliebl
 */
public class ChessMain {

    public static void main(String[] args){
        printInstructions();
        
        boolean playing = true;
        while(playing){
            new ChessGame().start();
            System.out.println("Play again? (yes/no)");
            boolean answered = false;
            while(!answered){
                Scanner console = new Scanner(System.in);
                String loc = console.nextLine();
                if(loc.contains("yes")){
                    answered = true;
                }else if(loc.contains("no")){
                    answered = true;
                    playing = false;
                } else{
                    System.out.println("Please answer the question with a yes or a no.");
                }
            }
        }
    }
}

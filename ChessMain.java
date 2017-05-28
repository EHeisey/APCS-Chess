package chess;

import java.util.Scanner;

/**
 * Starts a game of chess for two players. Upon completion, prompts if the users
 * would like to play again.
 * 
 * @author heiseed, wyliebl
 */
public class ChessMain {

    public static void main(String[] args){
        Scanner console = new Scanner(System.in);
        ChessGame.printInstructions();
        playing: while(true){
            new ChessGame().start();
            System.out.println("Play again? (yes/no)");
            while(true){
                String ans = console.nextLine();
                if(ans.contains("yes")){
                    break;
                }else if(ans.contains("no")){
                    break playing;
                } else{
                    System.out.println("Please answer the question with a \"yes\" or a \"no\".");
                }
            }
        }
    }
}

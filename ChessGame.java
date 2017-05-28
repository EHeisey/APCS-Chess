package chess;

import java.util.Scanner;
import java.awt.Point;
import java.util.ArrayList;

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
    private Player currentPlayer;

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
        Color winner = (turn%2==0) ? Color.WHITE : Color.BLACK;
        printBoard(winner);
        boolean answered = false;
        System.out.println("Congratulations! Player " + currentPlayer.getName() + " has won the game. ");
    }

    private void turn(){
        currentPlayer = (turn % 2 == 0) ? white : black;
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
            System.out.println("\n a b c d e f g h\n");
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
//lol efficiency is for nerdz
    public boolean inCheck(Color c){
        for (Point loc : enemies(c)) {
            if (!(getPiece(loc)==null)) {
                Piece enemy = getPiece(loc);
                for(Point p : enemy.getMoves(board, loc)) {      
                    if(!(getPiece(p).equals(null))) {
                        if(getPiece(p).getID().toLowerCase().equals("k") && getPiece(p).getColor().equals(c)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    //totally works?????? mebbe 
    public boolean inCheckmate(Color c){
        if (inCheck(c)) {
            King k = findKing(c);
            if (k.getMoves(board, findPiece(k)).isEmpty()) {
                if (enemies(board[enemies(c).get(0).x][enemies(c).get(0).y].getColor()).isEmpty()) {
                    return true;
                } else {
                    for (Point friend : enemies(board[enemies(c).get(0).x][enemies(c).get(0).y].getColor())) {
                        Piece p =  getPiece(friend);
                        if (!p.getID().toLowerCase().equals("k")) {
                            for (Point helpful : p.getMoves(board, friend)) {
                                Piece temp = getPiece(helpful);
                                Piece f = p;
                                removePiece(friend);
                                setPiece(f, helpful);
                                if (!inCheck(c)) {
                                    setPiece(f, friend);
                                    setPiece(temp, helpful);
                                    return false;
                                } else{
                                    setPiece(f, friend);
                                    setPiece(temp, helpful);
                                }
                            } 
                            return true;
                        }
                    }
                }
            } else {
                return false;
            }

        } 
        return false;
    }
    
    public ArrayList<Point> enemies(Color c) {
        ArrayList<Point> enemies = new ArrayList<>();
        for (Piece[] r: board) {
            for (Piece p: r) {
                if(!(p==null) && !p.getColor().equals(c)) {
                    
                }    
            }
        }    
        return enemies;
    }
    public void setPiece(Piece p, Point here) {
        board[here.x][here.y] = p;
    }
    public Piece getPiece(Point p) {
        return board[p.x][p.y];
    }
    private void removePiece(Point p) {
        board[p.x][p.y] = null;
    }
    private Point findPiece(Piece p) {
        Point point = null;
        for(int i = 0; i<8; i++) {
            for(int j = 0; j<8; j++) {
                if (!(board[i][j]== null)) {
                    if (board[i][j].getColor().equals(p.getColor()) && board[i][j].getID().toLowerCase().equals(p.getID().toLowerCase())) {
                        point = new Point (i,j);
                    }
                }
            }
        }    
        return point;
    }
    //only one of these is necessary...rip
    public King findKing(Color c) {
       King k = null;
        for(Piece[] row : board){
            for(Piece p : row){
                if(p instanceof King && p.getColor()== c) {
                    return (King)p;
                }
            }
        }
        return k;
    }
    public Point findKingLoc(Color c) {
       Point k = null;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j<8; j++) {
                if(board[i][j] instanceof King) {
                    k = new Point(i,j);
                }
            }
        }    
        return k;
    }
    private Piece getPiece(int x, int y) {
        return board[x][y];
    }
    public boolean isValidLocation(Point p) {
        return p.x > -1 && p.x < 8 && p.y > -1 && p.y < 8;
    }
    
}

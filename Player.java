package chess;

/**
 * Holds the name and color of a chess player.
 * 
 * @author heiseed
 */
public class Player {
    
    private Color color;
    private String name;
    /**
     * constructor Player(Color c, String n) - create a new player with this color and name
     *
     * @param c - color of player
     * @param n - the name of the player (parsed from user input)
     */
    public Player(Color c, String n){
        color = c;
        name = n;
    }
    
    /**
     * method getName() - return name of player
     * 
     * @return name of the player
     */
    public String getName(){
        return name;
    }
    /**
     * method getColor() - return color of player
     * 
     *@return color of player
     */
    public Color getColor(){
        return color;
    }
    
}

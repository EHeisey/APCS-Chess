package chess;

/**
 *
 * @author heiseed
 */
public class Player {
    
    private Color color;
    private String name;
    
    public Player(Color c, String n){
        color = c;
        name = n;
    }
    
    public String getName(){
        return name;
    }
    
    public Color getColor(){
        return color;
    }
    
}

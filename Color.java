/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

/**
 *
 * @author heiseed, wyliebl
 */
public enum Color {
    
    WHITE,
    BLACK;
    
    private Color(){}
    
    public Color opponent(){
        if(this==WHITE) return BLACK;
        return WHITE;
    }
}

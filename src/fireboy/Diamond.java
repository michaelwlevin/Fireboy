/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fireboy;

import java.awt.Graphics;

/**
 *
 * @author michael
 */
public class Diamond {
    private double x, y;
    
    private double width, height;
    
    
    private boolean captured;
    
    public Diamond(double x, double y){
        this.width = 2;
        this.height = 2;
    }
    
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
    public double getWidth(){
        return width;
    }
    
    public double getHeight(){
        return height;
    }
    
    public boolean checkCapture(Fireboy fireboy, Watergirl watergirl){
        if(captured){
            return false;
        }

        
        if(isOverlap(fireboy) || isOverlap(watergirl)){
            captured = true;
            return true;
            
        }
        
        
        return false;
    }
    
    public boolean isOverlap(Player player){
        double px = player.getX();
        double py = player.getY();
        double pwidth = player.getWidth();
        double pheight = player.getHeight();
        
        return false;
    }
    
    
    
    public void draw(Graphics g, int x, int y, int width, int height){
        
    }
    
    public boolean isCaptured(){
        return captured;
    }
}

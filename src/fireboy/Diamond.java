/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fireboy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author michael
 */
public class Diamond {
    private double x, y;
    
    private double width, height;
    
    private static BufferedImage image;
    
    private boolean captured;
    
    public Diamond(double x, double y){
        this.x = x;
        this.y = y;
        this.width = 2;
        this.height = 2;
        
        if(image == null){
            try {
                image = ImageIO.read(new File("images/diamond.png"));
            } catch (IOException e) {
            }
        }
    }
    
    public BufferedImage getImage(){
        return image;
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
        
        if(px+pwidth >= x && px <= x+width && py-pheight <= y && py >= y-height){
            captured = true;
            return true;
        }
        
        
        
        return false;
    }
    
    
    
    public void draw(Graphics g, int x, int y, int width, int height){
        g.drawImage(getImage(), x, y, width, height, null);
    }
    
    public boolean isCaptured(){
        return captured;
    }
}

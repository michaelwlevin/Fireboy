/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fireboy;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author michael
 */
public class BlueDiamond extends Diamond {
    private static BufferedImage image;
    
    public BlueDiamond(double x, double y){
        super(x, y);
        
        if(image == null){
            try {
                image = ImageIO.read(new File("images/reddiamond.png"));
            } catch (IOException e) {
            }
        }
    }
    
    public BufferedImage getImage(){
        return image;
    }
    
    public boolean isOverlap(Player player){
        if(!(player instanceof Watergirl)){
            return false;
        }
        else{
            return super.isOverlap(player);
        }
    }
}

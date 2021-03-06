/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fireboy;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author michael
 */
public class Player {
    private double x, y;
    public static final double height = 2;
    public static final double width = 1;
    public static final double gravity = 10;
    
    private double yvelocity, jumpxvelocity;
    private double xvelocity;
    private double xaccel, xdecel, jumpxaccel;
    private double maxxvelocity, jumpmaxxvelocity;
    private double jumpvelocity;
    
    private boolean moveleft;
    private boolean moveright;
    private boolean jump;
    
    private BufferedImage image;
    
    public Player(double x, double y){

        xaccel = 7;
        jumpxaccel = 4;
        xdecel = 50;
        maxxvelocity = 7;
        jumpmaxxvelocity = 4;
        
        jumpvelocity = 8;
        
        this.x = x;
        this.y = y;
        
        
        
        
 
        try {
            image = ImageIO.read(new File("images/"+getImageName()));
        } catch (IOException e) {
        }
    }
    
    public String getImageName(){
        return "";
    }
    
    public void setMoveLeft(boolean l){
        moveleft = l;
    }
    
    public void setMoveRight(boolean r){
        moveright = r;
    }
    
    public void setJump(boolean j){
        jump = j;
    }
    
   
    
    public void nextTimestep(GameEngine game){

        
        double dt = GameEngine.timestep;
        
        double floor = game.getFloor(x, y);
        double ceiling = game.getCeiling(x, y);
        
        double leftwall = game.getLeftWall(x, y);
        double rightwall = game.getRightWall(x, y);
        
        double slope = game.getSlope(x, y);

        
        
        boolean onground = true;
        
        if(y - height == floor){
            if(jump){
                yvelocity += jumpvelocity;
                jump = false;
            }
            onground = true;
        }
        else{
            jump = false;
            slope = Math.PI/2;
            onground = false;
        }
        
        double xgravity = 0;
        double ygravity = 0;
        
        if(!onground){
            ygravity = gravity * Math.sin(slope);
            xgravity = -gravity*Math.cos(slope);
        }
        

        double deltay = yvelocity * dt - 0.5 * ygravity * dt * dt;
        yvelocity -= ygravity * dt;
        
        //if(y - height > floor)
        //    System.out.println(y+" "+yvelocity+" "+deltay);

        y += deltay;

        

        jump = false;
        
        
        
        
        
        double actionxaccel = Math.abs(slope) == Math.PI/2? jumpxaccel : xaccel;
        
        double actualxaccel = 0;
        
        if(moveleft){
            if(xvelocity > 0){
                actualxaccel = -xdecel-actionxaccel;
            }
            else{
                actualxaccel = -actionxaccel;
            }
        }
        else if(moveright){
            if(xvelocity < 0){
                actualxaccel = xdecel+actionxaccel;
            }
            else{
                actualxaccel = actionxaccel;
            }
        }
        else{
            if(xvelocity < 0){
                actualxaccel = Math.min(xdecel, -xvelocity/dt);
            }
            else if(xvelocity > 0){
                actualxaccel = -Math.min(xdecel, xvelocity/dt);
            }
        }
        
        
        
        double actualmaxxvelocity = Math.abs(slope)==Math.PI/2? jumpmaxxvelocity : maxxvelocity;
        
        xvelocity = xvelocity + actualxaccel * dt;
        
        if(xvelocity > actualmaxxvelocity){
            xvelocity = actualmaxxvelocity;
        }
        if(xvelocity < -actualmaxxvelocity){
            xvelocity = -actualmaxxvelocity;
        }
        
        double deltax = xvelocity * dt + 0.5*actualxaccel * dt * dt;
        

        
        x += deltax;
        
        
        if(onground){
            
            double h = deltax;
            
            double direction = deltax > 0? 1:-1;
            
            deltax = Math.cos(slope)*h * direction;
            deltay = Math.sin(slope)*h * direction;
        }
        
        
        if(y > ceiling){
            y = ceiling - height;
            yvelocity = 0;
        }
        else if(y - height < floor){
            y = floor + height;
            yvelocity = 0;
        }
         
        if(x < leftwall){
            x = leftwall;
        }
        else if(x > rightwall-width){
            x = rightwall-width;
        }
        
        
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
    
    public void draw(Graphics g, int x, int y, int width, int height){
        g.drawImage(image, x, y, width, height, null);
    }
}

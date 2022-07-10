/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fireboy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

/**
 *
 * @author michael
 */
public class GameEngine  implements KeyListener {
    public static final double timestep = 0.01;
            
            
    private Fireboy fireboy;
    private Watergirl watergirl;
    
    private double width, height;
    
    private Timer action;
    private GamePanel panel;
    
    private Color background = Color.white;
    
    private Rectangle fireboywin, watergirlwin;
    
    
    public static final int FLOOR = 0;
    public static final int FIRE = 1;
    public static final int WATER = 2;
    public static final int POISON = 3;
    
    public GameEngine(GamePanel panel){
        this.panel = panel;
        
        
        width = 50;
        height = 50;
        
        
        fireboywin = new Rectangle(0, 3, 2, 3);
        watergirlwin = new Rectangle(48, 3, 2, 3);
        
        fireboy = new Fireboy(10, Player.height);
        watergirl = new Watergirl(30, Player.height);
        
        action = new Timer((int)Math.round(1000*timestep), new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                nextTimestep();
                 
        
            }
        });
        
    }
    
    public void start(){
        action.start();
    }
    
    public void stop(){
        action.stop();
    }
    
    
    public void nextTimestep(){
        fireboy.nextTimestep(this);
        watergirl.nextTimestep(this);
        
        double x = fireboy.getX();
        double y = fireboy.getY();
        double width = fireboy.getWidth();
        double height = fireboy.getHeight();
        
        boolean fbwin = false;
        
        if(x >= fireboywin.x && x+width <= fireboywin.x +fireboywin.width && y <= fireboywin.y && y-height >= fireboywin.y - fireboywin.height){
            fbwin = true;
        }
        
        double floor = getFloor(x, y);
        if(floor == y){
            int floortype = getFloorType(x, y);

            if(floortype == WATER || floortype == POISON){
                lose();
            }
        }
        
        
        
        
        
        x = watergirl.getX();
        y = watergirl.getY();
        width = watergirl.getWidth();
        height = watergirl.getHeight();
        
        boolean wgwin = false;
        
        if(x >= watergirlwin.x && x+width <= watergirlwin.x +watergirlwin.width && y <= watergirlwin.y && y-height >= watergirlwin.y - watergirlwin.height){
            wgwin = true;
        }
        
        floor = getFloor(x, y);
        
        if(floor == y){
            int floortype = getFloorType(x, y);

            if(floortype == FIRE || floortype == POISON){
                lose();
            }
        }
        

        if(fbwin && wgwin){
            win();
        }
        
        
    }
    
    public void win(){
        stop();
        panel.setMode(GamePanel.WIN);
    }
    
    public void lose(){
        stop();
        panel.setMode(GamePanel.LOSE);
    }
    
    
    
    public double getFloor(double x, double y){
        return 0;
    }
    
    public double getCeiling(double x, double y){
        return height;
    }
    
    public double getLeftWall(double x, double y){
        return 0;
    }
    
    public double getRightWall(double x, double y){
        return width;
    }
    
    public int getFloorType(double x, double y){
        return FLOOR;
    }
    
    
    // slopes are between 0 and pi
    public double getSlope(double x, double y){
        return Math.PI/2;
    }
    
    
    private int pixelwidth, pixelheight;
    
    public void paint(Graphics g){
        
        pixelwidth = panel.getWidth();
        pixelheight = panel.getHeight();
        
        g.setColor(background);
        g.fillRect(0, 0, pixelwidth, pixelheight);
      
        
        
        g.setColor(new Color(255, 170, 170));
        g.fillRect(convertX(fireboywin.x), convertY(fireboywin.y), convertWidth(fireboywin.width), convertHeight(fireboywin.height));
        
        g.setColor(new Color(170, 170, 255));
        g.fillRect(convertX(watergirlwin.x), convertY(watergirlwin.y), convertWidth(watergirlwin.width), convertHeight(watergirlwin.height));
        
        
        // draw fireboy
        int x = convertX(fireboy.getX());
        int y = convertY(fireboy.getY());
        
        
        int width = convertWidth(fireboy.getWidth());
        int height = convertHeight(fireboy.getHeight());
        
        //System.out.println("fireboy: "+fireboy.getX()+" "+fireboy.getY()+" "+x+" "+y);
        
        fireboy.draw(g, x, y, width, height);
        
        
        
        // draw watergirl
        x = convertX(watergirl.getX());
        y = convertY(watergirl.getY());
        width = convertWidth(watergirl.getWidth());
        height = convertHeight(watergirl.getHeight());
        
        watergirl.draw(g, x, y, width, height);
        
        
        
        
    }
    
    public int convertX(double x){

        return (int)Math.round( x / width * pixelwidth);
    }
    
    public int convertY(double y){

        return pixelheight - (int)Math.round( y / height * pixelheight);
    }
    
    public int convertWidth(double x){

        return (int)Math.round( x / width * pixelwidth);
    }
    
    public int convertHeight(double y){

        return (int)Math.round( y / height * pixelheight);
    }
    
    public void keyPressed(KeyEvent e){
        

        switch(e.getKeyCode()){
            case KeyEvent.VK_UP: fireboy.setJump(true); break;
            case KeyEvent.VK_LEFT: fireboy.setMoveLeft(true); fireboy.setMoveRight(false); break;
            case KeyEvent.VK_RIGHT: fireboy.setMoveRight(true); fireboy.setMoveLeft(false); break;
            case KeyEvent.VK_W: watergirl.setJump(true); break;
            case KeyEvent.VK_A: watergirl.setMoveLeft(true); break;
            case KeyEvent.VK_D: watergirl.setMoveRight(true); break;
        }
    }
    
    public void keyTyped(KeyEvent e){
        
    }
    
    public void keyReleased(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT: fireboy.setMoveLeft(false); break;
            case KeyEvent.VK_RIGHT: fireboy.setMoveRight(false); break;
            case KeyEvent.VK_A: watergirl.setMoveLeft(false); break;
            case KeyEvent.VK_D: watergirl.setMoveRight(false); break;
        }
    }
}

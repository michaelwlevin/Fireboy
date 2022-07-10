/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fireboy;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

/**
 *
 * @author michael
 */
public class GamePanel extends JPanel implements KeyListener {
    
    public static final double timestep = 0.01;
            
            
    private Fireboy fireboy;
    private Watergirl watergirl;
    
    
    private double width, height;
    
    private Color background = Color.white;
    
    public GamePanel(){
        setPreferredSize(new Dimension(1080, 720));
        
        width = 50;
        height = 50;
        
        
        fireboy = new Fireboy(10, Player.height);
        watergirl = new Watergirl(30, Player.height);
        
        
        
        addKeyListener(this);
        requestFocus();
        
        Timer action = new Timer((int)Math.round(1000*timestep), new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                nextTimestep();
                 
        
            }
        });
        
        action.start();
        
        Timer painter = new Timer(1000/60, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                repaint();
            }
        });
        
        painter.start();
    }
    
    
    
    public void nextTimestep(){
        fireboy.nextTimestep(this);
        watergirl.nextTimestep(this);
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
    
    
    // slopes are between 0 and pi
    public double getSlope(double x, double y){
        return Math.PI/2;
    }
    
    
    private int pixelwidth, pixelheight;
    
    public void paint(Graphics g){
        
        g.setColor(background);
        g.fillRect(0, 0, getWidth(), getHeight());
      
        
        pixelwidth = getWidth();
        pixelheight = getHeight();
        
        // draw fireboy
        int x = convertX(fireboy.getX());
        int y = convertY(fireboy.getY());
        
        
        int width = convertWidth(fireboy.getWidth());
        int height = convertHeight(fireboy.getHeight());
        
        //System.out.println("fireboy: "+fireboy.getX()+" "+fireboy.getY()+" "+x+" "+y);
        
        g.setColor(Color.red);
        g.fillRect(x, y, width, height);
        
        // draw watergirl
        x = convertX(watergirl.getX());
        y = convertY(watergirl.getY());
        width = convertWidth(watergirl.getWidth());
        height = convertHeight(watergirl.getHeight());
        
        g.setColor(Color.blue);
        g.fillRect(x, y, width, height);
        
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
    
    
    
    public void update(Graphics g) {
        Graphics offgc;
        Image offscreen = null;
        Dimension d = getSize();

        // create the offscreen buffer and associated Graphics
        offscreen = createImage(d.width, d.height);
        offgc = offscreen.getGraphics();
        // clear the exposed area
        offgc.setColor(getBackground());
        offgc.fillRect(0, 0, d.width, d.height);
        offgc.setColor(getForeground());
        // do normal redraw
        paint(offgc);
        // transfer offscreen to window
        g.drawImage(offscreen, 0, 0, this);
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

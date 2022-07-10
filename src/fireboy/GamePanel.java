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
public class GamePanel extends JPanel{
    
    
    
    
    
    
    
    
    private int mode;
    public static final int PLAY = 0;
    public static final int WIN = 1;
    public static final int LOSE = 2;
    public static final int EMPTY = 3;
    
    private GameEngine engine;
    
    public GamePanel(){
   
        
        mode = EMPTY;
        setPreferredSize(new Dimension(1080, 720));
        
        

        
        Timer painter = new Timer(1000/60, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                repaint();
            }
        });
        
        painter.start();
    }
    
    public void setGame(GameEngine engine){
        this.engine = engine;
    }
    
    public void setMode(int mode){
        this.mode = mode;
    }
    
    public void paint(Graphics g){
        
        int width = getWidth();
        int height = getHeight();
        
        if(mode == PLAY){
            engine.paint(g);
        }
        else if(mode == LOSE){
            g.setColor(Color.white);
            g.fillRect(0, 0, width, height);
            
            g.setColor(Color.black);
            
            g.drawString("LOSE", width/2, height/2);
        }
        else if(mode == WIN){
            g.setColor(Color.white);
            g.fillRect(0, 0, width, height);
            
            g.setColor(Color.black);
            
            g.drawString("WIN", width/2, height/2);
        }
        else if(mode == EMPTY){
            g.setColor(Color.white);
            g.fillRect(0, 0, width, height);
        }
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
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fireboy;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author michael
 */
public class GameFrame extends JFrame {
    
    public GameFrame() {
    
        
        GamePanel panel = new GamePanel();
        GameEngine engine = new GameEngine(panel);
        panel.setGame(engine);
        panel.setMode(GamePanel.PLAY);
        engine.start();
       
        
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.addKeyListener(engine);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fireboy;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author michael
 */
public class Watergirl extends Player {
    public Watergirl(double x, double y){
        super(x, y);
    }
    
    public void draw(Graphics g, int x, int y, int width, int height){
        g.setColor(Color.blue);
        g.fillRect(x, y, width, height);
    }
}

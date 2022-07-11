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
public class Fireboy extends Player {
    
    public Fireboy(double x, double y){
        super(x, y);
    }
    
    public String getImageName(){
        return "fireboy.png";
    }
    

}

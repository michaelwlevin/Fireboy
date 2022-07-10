/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fireboy;

/**
 *
 * @author michael
 */
public class RedDiamond extends Diamond{
    
    public RedDiamond(double x, double y){
        super(x, y);
    }
    
    public boolean isOverlap(Player player){
        if(!(player instanceof Fireboy)){
            return false;
        }
        else{
            return super.isOverlap(player);
        }
    }
}

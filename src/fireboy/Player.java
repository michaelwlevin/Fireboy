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
    
    
    
    public Player(double x, double y){

        xaccel = 7;
        jumpxaccel = 5;
        xdecel = 50;
        maxxvelocity = 7;
        jumpmaxxvelocity = 5;
        
        jumpvelocity = 8;
        
        this.x = x;
        this.y = y;
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
    
   
    
    public void nextTimestep(GamePanel panel){

        
        double dt = GamePanel.timestep;
        
        double floor = panel.getFloor(x, y);
        double ceiling = panel.getCeiling(x, y);
        
        double leftwall = panel.getLeftWall(x, y);
        double rightwall = panel.getRightWall(x, y);
        
        double slope = panel.getSlope(x, y);

        
        
        
        
        if(y - height == floor){
            if(jump){
                yvelocity += jumpvelocity;
                jump = false;
            }
        }
        else{
            jump = false;
            slope = Math.PI/2;
        }
        
        
        double ygravity = gravity * Math.sin(slope);
        double xgravity = -gravity*Math.cos(slope);
        
        

        double deltay = yvelocity * dt - 0.5 * ygravity * dt * dt;
        yvelocity -= ygravity * dt;
        
        //if(y - height > floor)
        //    System.out.println(y+" "+yvelocity+" "+deltay);

        y += deltay;

        if(y > ceiling){
            y = ceiling - height;
            yvelocity = 0;
        }
        else if(y - height < floor){
            y = floor + height;
            yvelocity = 0;
        }

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
        
        actualxaccel += xgravity;
        
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
         
         
        if(x < leftwall){
            x = leftwall;
              
        }

        if(x > rightwall-width){
           
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
}

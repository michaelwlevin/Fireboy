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
public class point {
    
 public static int x;
 public static int y;
 
        public point(int x, int y)
         {
                   this.x = x;
                   this.y = y;
         }


        // for collinear pts
        //does pt q lie on line segment pr 
        public static boolean onSegment(point p, point q, point r)
       {

       if (q.x <= Math.max(p.x, r.x) &&
                   q.x >= Math.min(p.x, r.x) &&
                   q.y <= Math.max(p.y, r.y) &&
                   q.y >= Math.min(p.y, r.y))
               {
                   return true;
               }
               return false;

        }      
          //test if pts are collinear (same slope), otherwise clockwise or counterclockwise


        public static String checkOrientation( point p, point q, point r)
        {        
         String result="";
         double val=(q.y - p.y) * (r.x - q.x) -(q.x - p.x) * (r.y - q.y);

          if (val==0)
                  {
                      result="collinear";
                  }
          else if (val>0)
          {
              result="clockwise";
          }
          
          return result;

       }
      
        

}
 


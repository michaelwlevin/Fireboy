/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fireboy;

//easier for squares, method that works for all polygons 
import java.util.HashSet;
public class Obstacle {
    
   final private HashSet<int[]> perimeter;
   
   public Obstacle (HashSet p)  //make hashset based on equation of shape(s)
    { 
     perimeter=p;
    }
   public HashSet<int[]> getPerimeter()
   {
    return perimeter;
   }
    
   public boolean checkObstacle(int x, int y) //works for all shapes including non polygons but inneficicent 
   {
       for (int i=x-1; i<x+2; i++) //needs to be close enough to perimeter, better way? perimter less than object perimter
       for (int j=y-1; j<y+2; y++)  //a lot to check at all times
       {
           int[] arr= {i,j};
          return perimeter.contains(arr);
       }
       return false;
   }
   
   //some helper methods for checking polygons
   //for collinear points
   
   
   
}




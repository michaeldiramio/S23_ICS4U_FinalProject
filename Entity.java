import DLibX.*;
import java.awt.Color;

public class Entity {
  private DConsole dc;
  private double xPos;
  private double yPos;
  private double height;
  private double width;
  private Color color;

  public Entity(DConsole dc, double x, double y, double h, double w, Color color) {
    dc.setPaint(color);
    dc.fillRect(h, w, x, y);

  }

  public boolean Bounds(double X, double Y, double xChange, double yChange){

    if(yChange != 0){
      if(0 > Y){
       //top bound
        if(Math.abs(Y - yPos + 10) <= height/2 && Math.abs(X + xPos) <= width/2){
        return false;
        }   
      }else if(0 < X){
      //bottom bound
        if(Math.abs(Y - yPos - 10) <= height/2 && Math.abs(X + xPos) <= width/2 ){
        return false;
        }   
      }
      return true;
    }else if(xChange != 0){
      if(0 > X){
        //right bound
        if(Math.abs(X - xPos + 10) <= width/2 && Math.abs(Y + yPos) <= height/2){
          return false;
        }

      }else if(0 < X){
        //left bound
        if(Math.abs(X - xPos - 10) <= width/2 && Math.abs(Y + yPos) <= height/2){
          return false;
      }
    }
    return true;
  }
  return true;
}//end of bounds




  
}//end of entity

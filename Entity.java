import DLibX.*;
import java.awt.Color;

public class Entity{
  private DConsole dc;
  private double xPos;
  private double yPos;
  private double length;
  private double width;
  private Color color;

  public Entity(DConsole dc, double x, double y, double l, double w, Color color){
    dc.setPaint(color);
    dc.fillRect(l, w, x, y);
    
    
    
  }
  



  
}
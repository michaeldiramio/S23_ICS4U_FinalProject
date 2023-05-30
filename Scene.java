import DLibX.*;
import java.awt.Color;

public class Scene {
  
  public static void start() {
    
  }

  public static void background(DConsole dc) {
    dc.setOrigin(DConsole.ORIGIN_CENTER);
    dc.drawImage("Images/background.png", 400, 275);
  }
}
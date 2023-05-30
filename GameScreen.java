import DLibX.*;
import java.awt.*;

public class GameScreen {

  public static void select(DConsole dc) {
    boolean gameChosen = false;
    int color = 2;
    int change = 1;
    while (!gameChosen) {
      Scene.background(dc);
      dc.setOrigin(DConsole.ORIGIN_CENTER);
      if (color == 150 || color == 0) {
        change *= -1;
      }
      color += change;
      dc.setPaint(new Color(250-color,75+color,200));//changing color
      dc.setFont(new Font("Comic Sans", Font.BOLD, 80));
      dc.drawString("Choose A Game", 400, 50);
      dc.fillRect(200, 225, 160, 160);
      dc.fillRect(400, 225, 160, 160);
      dc.fillRect(600, 225, 160, 160);
      dc.fillRect(300, 425, 160, 160);
      dc.fillRect(500, 425, 160, 160);
      dc.redraw();
      dc.pause(30);
    }
    
  }
  
}
  
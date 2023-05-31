import DLibX.*;
import java.awt.*;

public class GameScreen {

  public static void join(DConsole dc) {
    boolean joined = false;
    int trans = 3;
    int change = 3;
    boolean p1 = false; //player
    boolean p2 = false;
    boolean p3 = false;
    boolean p4 = false;

    while (!joined) {
      Scene.background(dc);
      dc.setOrigin(DConsole.ORIGIN_CENTER);

      if (trans >= 120 || trans <= 0) { //changing transparency
        change *= -1;
      }
      trans += change;

      if (!p1) { //not joined
        dc.setPaint(new Color(255,255,255)); //white
        dc.fillRect(200, 200, 290, 90);
        dc.drawImage("Images/Keys/1.png", 400, 275);
        dc.setPaint(new Color(126, 217, 87,255-trans)); //green
        dc.setFont(new Font("Comic Sans", Font.BOLD, 18));
        dc.drawString("Press 'W' To Join!", 200, 35);
        dc.setPaint(new Color(0,0,0));
        dc.setFont(new Font("Comic Sans", Font.BOLD, 22));
        dc.drawString("Player 1", 60, 10);
        
      } else {
        
      }

      if (!p2) {
        dc.setPaint(new Color(255,255,255));
        dc.fillRect(600, 200, 290, 90);
        dc.drawImage("Images/Keys/2.png", 400, 275);
        dc.setPaint(new Color(126, 217, 87,255-trans));
        dc.setFont(new Font("Comic Sans", Font.BOLD, 18));
        dc.drawString("Press 'I' To Join!", 600, 35);
        dc.setPaint(new Color(0,0,0));
        dc.setFont(new Font("Comic Sans", Font.BOLD, 22));
        dc.drawString("Player 2", 465, 10);
        
      } else {
        
      }

      if (!p3) {
        dc.setPaint(new Color(255,255,255));
        dc.fillRect(200, 445, 290, 90);
        dc.drawImage("Images/Keys/3.png", 400, 275);
        dc.setPaint(new Color(126, 217, 87,255-trans));
        dc.setFont(new Font("Comic Sans", Font.BOLD, 18));
        dc.drawString("Press 'T' To Join!", 200, 280);
        dc.setPaint(new Color(0,0,0));
        dc.setFont(new Font("Comic Sans", Font.BOLD, 22));
        dc.drawString("Player 3", 60, 260);
        
        
      } else {
        
      }

      if (!p4) {
        dc.setPaint(new Color(255,255,255));
        dc.fillRect(600, 445, 290, 90);
        dc.drawImage("Images/Keys/4.png", 400, 275);
        dc.setPaint(new Color(126, 217, 87,255-trans));
        dc.setFont(new Font("Comic Sans", Font.BOLD, 18));
        dc.drawString("Press '↑' To Join!", 600, 280);
        dc.setPaint(new Color(0,0,0));
        dc.setFont(new Font("Comic Sans", Font.BOLD, 20));
        dc.drawString("Player 4", 465, 260);
        
      } else {
        
      }
      
      dc.redraw();
      dc.pause(30);
    }

    
  }

  public static void nicknames(DConsole dc) {
    Scene.background(dc);
    dc.setOrigin(DConsole.ORIGIN_CENTER);

    
  }

  public static void select(DConsole dc) {
    boolean gameChosen = false;
    int color = 2;
    int change = 1;

    while (!gameChosen) { //on sreen
      Scene.background(dc);
      dc.setOrigin(DConsole.ORIGIN_CENTER);

      //color changing
      if (color == 150 || color == 0) {
        change *= -1;
      }
      color += change;
      dc.setPaint(new Color(250-color,75+color,200));
      
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
  
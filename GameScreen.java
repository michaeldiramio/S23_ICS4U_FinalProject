import DLibX.*;
import java.awt.*;

public class GameScreen {

  private boolean joined = false;
  private int trans = 3;
  private int change = 3;
  private boolean p1 = false; //player
  private boolean p2 = false;
  private boolean p3 = false;
  private boolean p4 = false;
  private DConsole dc;

  //Constructor
  public GameScreen(DConsole dc) {
    this.dc = dc;
  }

  public void join() {

    while (!joined) {
      Scene.background(dc);
      dc.setOrigin(DConsole.ORIGIN_CENTER);

      if (trans >= 120 || trans <= 0) { //changing transparency
        change *= -1;
      }
      trans += change;

      //key press
      if (dc.isKeyPressed('w') && !p1) {
        p1 = true;
      } else if (dc.isKeyPressed('s') && p1) {
        p1 = false;
      }
      
      if (dc.isKeyPressed('i') && !p2) {
        p2 = true;
      } else if (dc.isKeyPressed('k') && p2) {
        p2 = false;
      }
      
      if (dc.isKeyPressed('t') && !p3) {
        p3 = true;
      } else if (dc.isKeyPressed('g') && p3) {
        p3 = false;
      }
      
      if (dc.isKeyPressed('b') && !p4) {
        p4 = true;
      } else if (dc.isKeyPressed('n') && p4) {
        p4 = false;
      }

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
        dc.setPaint(new Color(255,255,255)); //white
        dc.fillRect(200, 200, 290, 90);
        dc.fillRect(200, 105, 90, 90);
        dc.drawImage("Images/Keys/5.png", 400, 275);

        //draw character avatar
        
        dc.setPaint(new Color(225, 0, 0,255-trans)); //red
        dc.setFont(new Font("Comic Sans", Font.BOLD, 18));
        dc.drawString("Press 'S' To Leave!", 200, 35);
        dc.setPaint(new Color(0,0,0));
        dc.setFont(new Font("Comic Sans", Font.BOLD, 22));
        dc.drawString("Player 1", 60, 10);
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
        dc.drawString("Player 2", 460, 10);
        
      } else {
        dc.setPaint(new Color(255,255,255)); //white
        dc.fillRect(600, 200, 290, 90);
        dc.fillRect(600, 105, 90, 90);
        dc.drawImage("Images/Keys/6.png", 400, 275);

        //draw character avatar
        
        dc.setPaint(new Color(225, 0, 0,255-trans)); //red
        dc.setFont(new Font("Comic Sans", Font.BOLD, 18));
        dc.drawString("Press 'K' To Leave!", 600, 35);
        dc.setPaint(new Color(0,0,0));
        dc.setFont(new Font("Comic Sans", Font.BOLD, 22));
        dc.drawString("Player 2", 460, 10);
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
        dc.setPaint(new Color(255,255,255)); //white
        dc.fillRect(200, 350, 90, 90);
        dc.fillRect(200, 445, 290, 90);
        dc.drawImage("Images/Keys/7.png", 400, 275);
        
        //draw character avatar
        
        dc.setPaint(new Color(225, 0, 0,255-trans)); //red
        dc.setFont(new Font("Comic Sans", Font.BOLD, 18));
        dc.drawString("Press 'G' To Leave!", 200, 280);
        dc.setPaint(new Color(0,0,0));
        dc.setFont(new Font("Comic Sans", Font.BOLD, 22));
        dc.drawString("Player 3", 60, 260);
      }

      if (!p4) {
        dc.setPaint(new Color(255,255,255));
        dc.fillRect(600, 445, 290, 90);
        dc.drawImage("Images/Keys/4.png", 400, 275);
        dc.setPaint(new Color(126, 217, 87,255-trans));
        dc.setFont(new Font("Comic Sans", Font.BOLD, 18));
        dc.drawString("Press '↑' To Join!", 600, 280);
        dc.setPaint(new Color(0,0,0));
        dc.setFont(new Font("Comic Sans", Font.BOLD, 22));
        dc.drawString("Player 4", 460, 260);
        
      } else {
        dc.setPaint(new Color(255,255,255)); //white
        dc.fillRect(600, 350, 90, 90);
        dc.fillRect(600, 445, 290, 90);
        dc.drawImage("Images/Keys/8.png", 400, 275);
        
        //draw character avatar
        
        dc.setPaint(new Color(225, 0, 0,255-trans)); //red
        dc.setFont(new Font("Comic Sans", Font.BOLD, 18));
        dc.drawString("Press '↓' To Leave!", 600, 280);
        dc.setPaint(new Color(0,0,0));
        dc.setFont(new Font("Comic Sans", Font.BOLD, 22));
        dc.drawString("Player 4", 460, 260);
      }
      
      dc.redraw();
      dc.pause(20);
    }

    
  }

  public static void nicknames(DConsole dc) {
    Scene.background(dc);
    dc.setOrigin(DConsole.ORIGIN_CENTER);

    //FOR LATER
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
      dc.pause(20);
    }
    
  }

  //Return which players are playing, and which are not
  public boolean[] playerInit() {
    boolean[] players = {p1, p2, p3, p4};
    return players;
  }
  
}
  
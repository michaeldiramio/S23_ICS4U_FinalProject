import DLibX.*;
import java.awt.*;
import java.time.*;

public class GameScreen {
  boolean[] players = {false, false, false, false};
  private DConsole dc;

  //Constructor
  public GameScreen(DConsole dc) {
    this.dc = dc;
  }

  public void join() {
    LocalTime start = LocalTime.now(); //reset time to 0
    boolean joined = false;
    int width = 2;
    int c = 1;
    int trans = 2;
    int change = 2;

    while (!joined) {
      background(); //draw background
      dc.setOrigin(DConsole.ORIGIN_CENTER);

      if (trans >= 120 || trans <= 0) { //changing transparency
        change *= -1;
      }
      trans += change;

      //key press (I need Julian's help, I still can't get WorInput keyPressed to work in place of dc.isKeyPressed)
      if (dc.isKeyPressed('w') && !players[0]) { //player 1
        players[0] = true;
        start = LocalTime.now();
      } else if (dc.isKeyPressed('s') && players[0]) {
        players[0] = false;
        start = LocalTime.now();
      }
      
      if (dc.isKeyPressed('i') && !players[1]) { //player 2
        players[1] = true;
        start = LocalTime.now();
      } else if (dc.isKeyPressed('k') && players[1]) {
        players[1] = false;
        start = LocalTime.now();
      }
      
      if (dc.isKeyPressed('t') && !players[2]) { //player 3
        players[2] = true;
        start = LocalTime.now();
      } else if (dc.isKeyPressed('g') && players[2]) {
        players[2] = false;
        start = LocalTime.now();
      }
      
      if (dc.isKeyPressed('b') && !players[3]) { //player 4 (should be arrow keys "UP" and "DOWN". 'b' and 'n' were just for testing)
        players[3] = true;
        start = LocalTime.now();
      } else if (dc.isKeyPressed('n') && players[3]) {
        players[3] = false;
        start = LocalTime.now();
      }

      if (!(players[0])) { //not joined
        dc.setPaint(new Color(255,255,255)); //white
        dc.fillRect(200, 200, 290, 90);
        dc.drawImage("Images/Keys/1.png", 400, 275);
        dc.setPaint(new Color(126, 217, 87,255-trans)); //green
        dc.setFont(new Font("Comic Sans", Font.BOLD, 18));
        dc.drawString("Press 'W' To Join!", 200, 35);
        dc.setPaint(new Color(0,0,0)); //black
        dc.setFont(new Font("Comic Sans", Font.BOLD, 22));
        dc.drawString("Player 1", 60, 10);
        
      } else { //joined
        dc.setPaint(new Color(255,255,255)); //white
        dc.fillRect(200, 200, 290, 90);
        dc.fillRect(200, 105, 90, 90);
        dc.drawImage("Images/Keys/5.png", 400, 275);

        //draw character avatar
        
        dc.setPaint(new Color(225, 0, 0,255-trans)); //red
        dc.setFont(new Font("Comic Sans", Font.BOLD, 18));
        dc.drawString("Press 'S' To Leave!", 200, 35);
        dc.setPaint(new Color(0,0,0)); //black
        dc.setFont(new Font("Comic Sans", Font.BOLD, 22));
        dc.drawString("Player 1", 60, 10);
      }

      if (!(players[1])) { //not joined
        dc.setPaint(new Color(255,255,255)); //white
        dc.fillRect(600, 200, 290, 90);
        dc.drawImage("Images/Keys/2.png", 400, 275);
        dc.setPaint(new Color(126, 217, 87,255-trans)); //green
        dc.setFont(new Font("Comic Sans", Font.BOLD, 18));
        dc.drawString("Press 'I' To Join!", 600, 35);
        dc.setPaint(new Color(0,0,0)); //black
        dc.setFont(new Font("Comic Sans", Font.BOLD, 22));
        dc.drawString("Player 2", 460, 10);
        
      } else { //joined
        dc.setPaint(new Color(255,255,255)); //white
        dc.fillRect(600, 200, 290, 90);
        dc.fillRect(600, 105, 90, 90);
        dc.drawImage("Images/Keys/6.png", 400, 275);

        //draw character avatar
        
        dc.setPaint(new Color(225, 0, 0,255-trans)); //red
        dc.setFont(new Font("Comic Sans", Font.BOLD, 18));
        dc.drawString("Press 'K' To Leave!", 600, 35);
        dc.setPaint(new Color(0,0,0)); //black
        dc.setFont(new Font("Comic Sans", Font.BOLD, 22));
        dc.drawString("Player 2", 460, 10);
      }

      if (!(players[2])) { //not joined
        dc.setPaint(new Color(255,255,255)); //white
        dc.fillRect(200, 445, 290, 90); 
        dc.drawImage("Images/Keys/3.png", 400, 275);
        dc.setPaint(new Color(126, 217, 87,255-trans)); //green
        dc.setFont(new Font("Comic Sans", Font.BOLD, 18));
        dc.drawString("Press 'T' To Join!", 200, 280);
        dc.setPaint(new Color(0,0,0)); //black
        dc.setFont(new Font("Comic Sans", Font.BOLD, 22));
        dc.drawString("Player 3", 60, 260);
        
        
      } else { //joined
        dc.setPaint(new Color(255,255,255)); //white
        dc.fillRect(200, 350, 90, 90);
        dc.fillRect(200, 445, 290, 90);
        dc.drawImage("Images/Keys/7.png", 400, 275);
        
        //draw character avatar
        
        dc.setPaint(new Color(225, 0, 0,255-trans)); //red
        dc.setFont(new Font("Comic Sans", Font.BOLD, 18));
        dc.drawString("Press 'G' To Leave!", 200, 280);
        dc.setPaint(new Color(0,0,0)); //black
        dc.setFont(new Font("Comic Sans", Font.BOLD, 22));
        dc.drawString("Player 3", 60, 260);
      }

      if (!(players[3])) { //not joined
        dc.setPaint(new Color(255,255,255)); //white
        dc.fillRect(600, 445, 290, 90);
        dc.drawImage("Images/Keys/4.png", 400, 275);
        dc.setPaint(new Color(126, 217, 87,255-trans)); //green
        dc.setFont(new Font("Comic Sans", Font.BOLD, 18));
        dc.drawString("Press '↑' To Join!", 600, 280);
        dc.setPaint(new Color(0,0,0)); //black
        dc.setFont(new Font("Comic Sans", Font.BOLD, 22));
        dc.drawString("Player 4", 460, 260);
        
      } else { //joined
        dc.setPaint(new Color(255,255,255)); //white
        dc.fillRect(600, 350, 90, 90);
        dc.fillRect(600, 445, 290, 90);
        dc.drawImage("Images/Keys/8.png", 400, 275);
        
        //draw character avatar
        
        dc.setPaint(new Color(225, 0, 0,255-trans)); //red
        dc.setFont(new Font("Comic Sans", Font.BOLD, 18));
        dc.drawString("Press '↓' To Leave!", 600, 280);
        dc.setPaint(new Color(0,0,0)); //black
        dc.setFont(new Font("Comic Sans", Font.BOLD, 22));
        dc.drawString("Player 4", 460, 260);
      }
      
      LocalTime end = LocalTime.now(); //end timer
      long value = Duration.between(start, end).toMillis(); //duration of time since button was pressed
      if (value > 1850 && playerCount() > 2) {
        if (value < 5000) { //5000 miliseconds is 5 seconds and time for next screen if no more buttons are pressed

          //plusing circle effect
          if (width == 51) {
            width = 0;
            c = 2;
          } else if (width == 0) {
            c = -2;
          }
          width++;
          dc.setPaint(new Color(126, 217, 87)); //green
          dc.fillEllipse(400, 275, 150, 150);
          if (c > 0) {
            dc.setPaint(new Color(126, 217, 87,255-width*5)); //transparent green
            dc.fillEllipse(400, 275, 150+width, 150+width);
          }

          //number countdown
          dc.setPaint(new Color(255, 255, 255)); //white
          dc.setFont(new Font("Comic Sans", Font.BOLD, 60));
          dc.drawString(3-(int)((value-2000)/1000), 400, 260);
          
        } else {
          joined = true; //break loop and move to next method in main
        }
      }
      dc.redraw();
      dc.pause(20); //should be 20 milisecond pause
    }

    
  }

  public void nicknames() {
    background();
    dc.setOrigin(DConsole.ORIGIN_CENTER);

    //FOR LATER
  }

  public void select() {
    boolean gameChosen = false;
    int color = 2;
    int change = 1;

    while (!gameChosen) { //we are on screen
      background(); //draw background
      dc.setOrigin(DConsole.ORIGIN_CENTER);

      //color changing effect
      if (color == 150 || color == 0) {
        change *= -1;
      }
      color += change;
      dc.setPaint(new Color(250-color,75+color,200));
      
      dc.setFont(new Font("Comic Sans", Font.BOLD, 80));
      dc.drawString("Choose A Game", 400, 50); //head line

      //game display boxs
      dc.fillRect(200, 225, 160, 160);
      dc.fillRect(400, 225, 160, 160);
      dc.fillRect(600, 225, 160, 160);
      dc.fillRect(300, 425, 160, 160);
      dc.fillRect(500, 425, 160, 160);

      //[VOTING FUNCTION TO BE ADDED]
      
      dc.redraw();
      dc.pause(20); //should be 20 miliseconds pause
    }
    
  }

  //draw the default background which is currently halo skybox
  public void background() {
    dc.setOrigin(DConsole.ORIGIN_CENTER);
    dc.drawImage("Images/background.png", 400, 275);
  }

  //Return which players are playing, and which are not
  public boolean[] playerInit() {
    return players;
  }

  //return the number of players playing
  public int playerCount(){
    int count = 0;
    for (int i = 0; i < players.length; i++) {
      if (players[i]) {
        count++;
      }
    }
    return count;
  }
  
}
  
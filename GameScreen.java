import DLibX.*;
import java.awt.*;

import java.io.*;

import java.time.*;
import java.util.*;


public class GameScreen {
  
  private DConsole dc;
  private ArrayList<Player> playerList;
  private boolean[] players;

  //Constructor
  public GameScreen(DConsole dc, ArrayList<Player> playerList, boolean[] players) {
    this.dc = dc;
    this.playerList = playerList;
    this.players = players;
  }

   public void StartScreen(){
    //space bar isnt pressed
    while(!dc.isKeyPressed(' ')) {
      dc.clear();

      Font customFont = null;
      
      try {
          //create the font to use. Specify the size!
          customFont = Font.createFont(Font.TRUETYPE_FONT, new File("Halo.ttf")).deriveFont(45f);
          GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            
          //register the font
          ge.registerFont(customFont);
      } catch (IOException e) {
          e.printStackTrace();
      } catch(FontFormatException e) {
          e.printStackTrace();
      }

      dc.setPaint(new Color(0,25,255));
      dc.setFont(customFont);
  		dc.drawString("Definity Not A",400,75);
      dc.drawString("Mario Party Ripoff",400,150);
     
      dc.redraw();
      dc.pause(100);
    }
  }
  
  public void join() {
    LocalTime start = LocalTime.now(); //reset time to 0
    WordInput in = new WordInput(dc);
    for (int i = 0; i < players.length; i++){
      players[i] = false;
    }
    int width = 2;
    int c = 1;
    int trans = 2;
    int change = 2;
    boolean joined = false;
       

    while (!joined) {
      background(); //draw background
      dc.setOrigin(DConsole.ORIGIN_CENTER);
      in.refreshKeys();

      if (trans >= 120 || trans <= 0) { //changing transparency
        change *= -1;
      }
      trans += change;

      for(int i = 0; i < 4; i++) {
        boolean[] tempControl = playerList.get(i).getControl().getPlayerKeysPressed();
        if(tempControl[0] && !players[i]) {
          players[i] = true;
          start = LocalTime.now();
        } else if(tempControl[2] && players[i]) {
          players[i] = false;
          start = LocalTime.now();
        }
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
    WordInput in = new WordInput(dc);
    String names[] = new String[playerCount()];
    for (int i = 0; i < playerCount(); i++) {
      names[i] = "";
    }

   for (int i = 0; i < playerCount(); i++) {
     boolean select = false;
      while(!select) {
        dc.setPaint(playerList.get(i).getColor()); //color array
        dc.fillRect(400, 275, 800, 550); //background color
        in.refreshKeys();
        
        // DRAW AVATAR TOP LEFT CORNER

        dc.drawImage("Images/textbox.png", 400, 175);
        String name = in.getFinalWord();
        if (name != "" && name.length() <= 10) { 
          names[i] = name;
        }
        dc.setPaint(new Color(0, 0, 0)); //black
        dc.setFont(new Font("Comic Sans", Font.BOLD, 20));
        dc.drawString(names[i], 400, 114);
        
        
        
        dc.redraw();
        dc.pause(20);
      }
   }
    
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

      //draw game images

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
  
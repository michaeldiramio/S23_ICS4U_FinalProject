import DLibX.*;
import java.awt.*;

import java.io.*;

import java.time.*;
import java.util.*;


public class GameScreen {

  // PLEASE
  
  private DConsole dc;
  private ArrayList<Player> playerList;
  private int playerAmount;
  private Player[] activePlayers;
  private int currentGame;

  //Constructor
  public GameScreen(DConsole dc, ArrayList<Player> playerList) {
    this.dc = dc;
    this.playerList = playerList;
    this.playerAmount = 4;
    this.activePlayers = new Player[4];
    this.currentGame = 0;
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
    int width = 2;
    int c = 1;
    int trans = 2;
    int change = 2;
    boolean joined = false;
    boolean[] tempPlayers = new boolean[4];


    //halo text Players
    Font customFontPlayers = null;
      
      try {
          //create the font to use. Specify the size!
          customFontPlayers = Font.createFont(Font.TRUETYPE_FONT, new File("Halo.ttf")).deriveFont(25f);
          GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            
          //register the font
          ge.registerFont(customFontPlayers);
      } catch (IOException e) {
          e.printStackTrace();
      } catch(FontFormatException e) {
          e.printStackTrace();
      }
     
    while (!joined) {
      background(); //draw background
      dc.setOrigin(DConsole.ORIGIN_CENTER);
      in.refreshKeys();

      if (trans >= 120 || trans <= 0) { //changing transparency
        change *= -1;
      }
      trans += change;

      // goes through each player to confirm if they are joining the game based on their keypresses
      for(int i = 0; i < 4; i++) {
        boolean[] tempControl = playerList.get(i).getControl().getPlayerKeysPressed(); // gets each player's direction keys
        if(tempControl[0] && !tempPlayers[i]) {
          tempPlayers[i] = true;
          this.activePlayers[i] = this.playerList.get(i);
          start = LocalTime.now();
        } else if(tempControl[2] && tempPlayers[i]) {
          tempPlayers[i] = false;
          this.activePlayers[i] = null;
          start = LocalTime.now();
        }
      }

      // gets the amount of players in the game
      this.playerAmount = 0;
      for(int i = 0; i < tempPlayers.length; i++) {
        if(this.activePlayers[i] != null) {
          this.playerAmount++;
        }
      }
      

      

      if (!(tempPlayers[0])) { //not joined
        dc.setPaint(new Color(255,255,255)); //white
        dc.fillRect(200, 200, 290, 90);
        dc.drawImage("Images/Keys/1.png", 400, 275);
        dc.setPaint(new Color(126, 217, 87,255-trans)); //green

        dc.setFont(new Font("Comic Sans", Font.BOLD, 22));
 
        dc.drawString("Press 'W' To Join!", 200, 35);
        dc.setPaint(new Color(0,0,0)); //black
        dc.setFont(customFontPlayers);
        dc.drawString("Player 1", 60, 10);

      } else { //joined
        dc.setPaint(new Color(255,255,255)); //white
        dc.fillRect(200, 200, 290, 90);
        dc.fillRect(200, 105, 90, 90);
        dc.drawImage("Images/Keys/5.png", 400, 275);

        dc.setPaint(new Color(225, 0, 0,255-trans)); //red
        dc.setFont(new Font("Comic Sans", Font.BOLD, 22));
        dc.drawString("Press 'S' To Leave!", 200, 35);
        dc.setPaint(new Color(0,0,0)); //black
        dc.setFont(customFontPlayers);
        dc.drawString("Player 1", 60, 10);
        
        dc.drawImage("Images/Character-Icons/haloHelmetBlue.png", 30, 75);
      }

      if (!(tempPlayers[1])) { //not joined
        dc.setPaint(new Color(255,255,255)); //white
        dc.fillRect(600, 200, 290, 90);
        dc.drawImage("Images/Keys/2.png", 400, 275);
        dc.setPaint(new Color(126, 217, 87,255-trans)); //green
        dc.setFont(new Font("Comic Sans", Font.BOLD, 22));
        dc.drawString("Press 'I' To Join!", 600, 35);
        dc.setPaint(new Color(0,0,0)); //black
        dc.setFont(customFontPlayers);
        dc.drawString("Player 2", 460, 10);
        
      } else { //joined
        dc.setPaint(new Color(255,255,255)); //white
        dc.fillRect(600, 200, 290, 90);
        dc.fillRect(600, 105, 90, 90);
        dc.drawImage("Images/Keys/6.png", 400, 275);
        
        dc.setPaint(new Color(225, 0, 0,255-trans)); //red
        dc.setFont(new Font("Comic Sans", Font.BOLD, 22));
        dc.drawString("Press 'K' To Leave!", 600, 35);
        dc.setPaint(new Color(0,0,0)); //black
        dc.setFont(customFontPlayers);
        dc.drawString("Player 2", 460, 10);
        
        dc.drawImage("Images/Character-Icons/haloHelmetGreen.png", 475, 90);
      }

      if (!(tempPlayers[2])) { //not joined
        dc.setPaint(new Color(255,255,255)); //white
        dc.fillRect(200, 445, 290, 90); 
        dc.drawImage("Images/Keys/3.png", 400, 275);
        dc.setPaint(new Color(126, 217, 87,255-trans)); //green
        dc.setFont(new Font("Comic Sans", Font.BOLD, 22));
        dc.drawString("Press 'T' To Join!", 200, 280);
        dc.setPaint(new Color(0,0,0)); //black
        dc.setFont(customFontPlayers);
        dc.drawString("Player 3", 65, 260);
        
      } else { //joined
        dc.setPaint(new Color(255,255,255)); //white
        dc.fillRect(200, 350, 90, 90);
        dc.fillRect(200, 445, 290, 90);
        dc.drawImage("Images/Keys/7.png", 400, 275);
        
        dc.setPaint(new Color(225, 0, 0,255-trans)); //red
        dc.setFont(new Font("Comic Sans", Font.BOLD, 22));
        dc.drawString("Press 'G' To Leave!", 200, 280);
        dc.setPaint(new Color(0,0,0)); //black
        dc.setFont(customFontPlayers);
        dc.drawString("Player 3", 65, 260);
        
        dc.drawImage("Images/Character-Icons/haloHelmetPurple.png", 78, 325);
      }

      if (!(tempPlayers[3])) { //not joined
        dc.setPaint(new Color(255,255,255)); //white
        dc.fillRect(600, 445, 290, 90);
        dc.drawImage("Images/Keys/4.png", 400, 275);
        dc.setPaint(new Color(126, 217, 87,255-trans)); //green
        dc.setFont(new Font("Comic Sans", Font.BOLD, 22));
        dc.drawString("Press '↑' To Join!", 600, 280);
        dc.setPaint(new Color(0,0,0)); //black
        dc.setFont(customFontPlayers);
        dc.drawString("Player 4", 460, 260);
        
      } else { //joined
        dc.setPaint(new Color(255,255,255)); //white
        dc.fillRect(600, 350, 90, 90);
        dc.fillRect(600, 445, 290, 90);
        dc.drawImage("Images/Keys/8.png", 400, 275);
        
        dc.setPaint(new Color(225, 0, 0,255-trans)); //red
        dc.setFont(new Font("Comic Sans", Font.BOLD, 22));
        dc.drawString("Press '↓' To Leave!", 600, 280);
        dc.setPaint(new Color(0,0,0)); //black
        dc.setFont(customFontPlayers);
        dc.drawString("Player 4", 460, 260);
        
        dc.drawImage("Images/Character-Icons/haloHelmetRed.png", 477, 335);
      }
      
      LocalTime end = LocalTime.now(); //end timer
      long value = Duration.between(start, end).toMillis(); //duration of time since button was pressed
      if (value > 1850 && this.playerAmount > 1) {
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
      dc.pause(20); //should be 20 milisecond pause (then make it that way)
    }

    
  }

  public void nicknames() {
    background();
    dc.setOrigin(DConsole.ORIGIN_CENTER);
    WordInput in = new WordInput(dc);
    
    String avatar[] = {"Images/Character-Icons/haloHelmetBlue.png", "Images/Character-Icons/haloHelmetGreen.png", "Images/Character-Icons/haloHelmetPurple.png", "Images/Character-Icons/haloHelmetRed.png"};
    int xvals[] = {50, 101, 102, 101};
    int yvals[]= {50, 67, 50, 59};
    
    String names[] = {"", "", "", ""};

    for (int i = 0; i < 4; i++) {
      playerList.get(i).setUsername("");
      boolean select = false;
      if(this.activePlayers[i] != null) { // if the player has joined the game
        while(!select) {
          dc.setPaint(playerList.get(i).getColor()); //color array
          dc.fillRect(400, 275, 800, 550); //background color
          in.refreshKeys();

          dc.drawImage(avatar[i], xvals[i], yvals[i]);

          dc.drawImage("Images/textbox.png", 400, 225);

          String name = in.getCurrentWord(); //to show as typed
          if (name.length() <= 10) { 
            names[i] = name;
          }

          dc.setPaint(new Color(0, 0, 0)); //black
          dc.setFont(new Font("Comic Sans", Font.BOLD, 20));
          dc.drawString(names[i], 400, 164); //display names typing

          String nfinal = in.getFinalWord(); //pressed enter
           
          if (nfinal != "" && nfinal.length() <= 10) { 
            boolean inn = false;
            for (int iz = 0; iz < playerList.size(); iz++) { //check if other player already has that name
              if (nfinal.equals( playerList.get(iz).getUsername())) {
                inn = true;
              }
            }
            if (!inn) { //if no other player has that username
              names[i] = nfinal; 
              playerList.get(i).setUsername(nfinal); //set player username
              select = true; //move to next part of loop
            }
          }
           dc.redraw();
          dc.pause(20);
        }
      }
    }
  }

  public void select(ArrayList<Minigame> subMinigameList) {
    LocalTime start = LocalTime.now(); //reset time to 0
    boolean gameChosen = false;
    int maxPlayers = 4;
    int gameScreenNum = 4;
    int gameTotal = subMinigameList.size(); // will change after we add more games
    int color = 2;
    int[] voteCount = new int[maxPlayers];
    int screenNum = 1;
    int[] playerGameVoted = new int[maxPlayers];
    for(int i = 0; i < maxPlayers; i++) {
      playerGameVoted[i] = -1; // default value of array since the game numbers will go from 0-3
    }
    int[] previousVotes = new int[maxPlayers];
    for(int i = 0; i < this.playerAmount; i++) {
      previousVotes[i] = -1; // default value of array since the game numbers will go from 0-3
    }
    int totalVotes = 0;
    int width = 2;
    int c = 2;
    int trans = 2;
    int change = 2;

    

    while (!gameChosen) { //we are on screen

      totalVotes = 0;

      
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

      //game display boxes
      dc.fillRect(300, 225, 160, 160);
      dc.fillRect(500, 225, 160, 160);
      dc.fillRect(300, 425, 160, 160);
      dc.fillRect(500, 425, 160, 160);

      // eventually draw game images
      for(int i = 0; i < 0; i++) {
        
      }

      // coordinates for icons/text
      int[] xCoords = {300, 500, 300, 500};
      int[] yCoords = {225, 225, 425, 425};

      dc.setPaint(Color.BLACK);
      dc.setFont(new Font("Comic Sans", Font.BOLD, 20));
      // gets the possible games based on which page you are on
      // ex. if you are on page 1, the games shown would be slots 0-4
      for(int i = (screenNum - 1) * gameScreenNum; i < (screenNum - 1) * gameTotal; i++) {
        dc.drawString("Game " + (i + 1), xCoords[i], yCoords[i]); // temporary game image placeholder
      }

      // gets each player's keys pressed and assigns it to a vote (voting is a scam, long live the Queen!) - Carter Tedesco
      for(int i = 0; i < maxPlayers; i++) { // for each player
        boolean[] tempControl = this.playerList.get(i).getControl().getPlayerKeysPressed(); // get each player's keypresses
        for(int j = 0; j < gameScreenNum; j++) { // for each possible game
          for(int k = 0; k < tempControl.length; k++) { // for each player's controls
            if(tempControl[k] && j == k && this.activePlayers[i] != null) {
              playerGameVoted[i] = j;
            }
          }
        }
      }

      // if the new votes are different from last time reset the autostart timer
      for(int i = 0; i < maxPlayers; i++) {
        if(playerGameVoted[i] != previousVotes[i]) {
          start = LocalTime.now();
        }
      }

      // sets current votes to previous votes, as the current votes will be considered the previous votes for the next cycle
      for(int i = 0; i < maxPlayers; i++) {
        previousVotes[i] = playerGameVoted[i];
      }

      // resets vote count
      for(int i = 0; i < voteCount.length; i++) {
        voteCount[i] = 0;
      }

      // counts votes
      for(int i = 0; i < gameScreenNum; i++) {
        for(int j = 0; j < maxPlayers; j++) {
          if(this.activePlayers[j] != null && playerGameVoted[j] == i) {
            totalVotes++;
            voteCount[i]++;
          }
        }
      }

      // displays which game each character has voted on by their own coloured blip under each game
      for(int i = 0; i < playerGameVoted.length; i++) {
        for(int j = 0; j < gameScreenNum; j++) {
          if(this.activePlayers[i] != null && playerGameVoted[i] == j) {
            dc.setPaint(this.playerList.get(i).getColor());
            dc.fillEllipse(xCoords[j] - 70 + (i * 35), yCoords[j] + 100, 25, 25);
          } else if(this.activePlayers[i] != null && playerGameVoted[i] == -1) {
            dc.setPaint(this.playerList.get(i).getColor());
            dc.fillEllipse(347.5 + (i * 35), 125, 25, 25);
          }
        }
      }

      // auto-end timer code
      LocalTime end = LocalTime.now(); //end timer
      long value = Duration.between(start, end).toMillis(); //duration of time since button was pressed
      if (value > 1850 && totalVotes == this.playerAmount) {
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

        // if the timer reaches 5 seconds
        } else {

          // if game is 50/50 or mostly unanimous vote choose the highest voted game
          for(int i = 0; i < gameScreenNum; i++) {
            if(voteCount[i] > 1) {
              gameChosen = true;
              this.currentGame = ((screenNum - 1) * gameScreenNum) + i;
            }
          }

          // if every player voted a different game choose a random game
          if(!gameChosen) {
            Random randGen = new Random();
            this.currentGame = ((screenNum - 1) * gameScreenNum) + randGen.nextInt(4);
            gameChosen = true;
          }
        }
      }
      
      dc.redraw();
      dc.pause(20); //should be 20 miliseconds pause
    }
    
  }

  public void winScreen() { //bars display score trth (IN ODER OF SCORE GET ALEX CODE)
    boolean play = false;
    int up = 1;
    int trans = 1;
    int change = 1;
    int xs[] = {340, 460, 220, 580};
    WordInput in = new WordInput(dc);

    //COULD BE REMOVED (FOR TESTING)
    playerList.get(2).addToScore(50);
    playerList.get(1).addToScore(90);

    while (!play) {
      background(); //draw background
      dc.setOrigin(DConsole.ORIGIN_CENTER);
      in.refreshKeys();

      sortingByScore();; //sort array list by score

      for (int i = 0; i < playerCount(); i++) {
        dc.setPaint(playerList.get(i).getColor()); //color array
        int tempScore = playerList.get(i).getScore();
        dc.fillRect(xs[i], 550, 100, (int)((tempScore*up) / 25)); //grow to size over time
        dc.setFont(new Font("Comic Sans", Font.BOLD, 17));
        dc.drawString(playerList.get(i).getUsername(), xs[i], 535 - (int)(((tempScore*up) / 25)/2));
      }
      
      if (up < 150) {
        up++;
      } else if (up >= 150) {
        if (trans >= 150 || trans <= 0) { //changing transparency
        change *= -1;
      }
      trans += change;

        dc.setPaint(new Color(0, 0, 0,255-trans)); 
        dc.setFont(new Font("Comic Sans", Font.BOLD, 30));
        dc.drawString("Press Any Key To Play Again", 400, 100);
        String press = in.getCurrentWord();
        if (press != "") {
          play = true;
        }
      }
      
      dc.redraw();
      dc.pause(20);
    }
    playerList.clear();
    dc.pause(50);
  }

  //draw the default background which is currently halo skybox
  public void background() {
    dc.setOrigin(DConsole.ORIGIN_CENTER);
    dc.drawImage("Images/background.png", 400, 275);
  }

  //sort arraylist by points
  public void sortingByScore() {
    for (int i = 0; i < playerList.size(); i++) {
        for (int j = i + 1; j < playerList.size(); j++) {
            Player temp;
            if (playerList.get(i).getScore() < playerList.get(j).getScore()) {
              
                // Swapping
                temp = playerList.get(i);
                playerList.set(i, playerList.get(j));
                playerList.set(j, temp);
            }
        }
    }
  }

  //sort arraylist by ID
  public void sortingByID() {
    for (int i = 0; i < playerList.size(); i++) {
        for (int j = i + 1; j < playerList.size(); j++) {
            Player temp;
            if (playerList.get(i).getID() > playerList.get(j).getID()) {
              
                // Swapping
                temp = playerList.get(i);
                playerList.set(i, playerList.get(j));
                playerList.set(j, temp);
            }
        }
    }
  }

  //return the number of players playing
  public int playerCount(){
    return playerList.size();
  }

  public int getCurrentGame() {
    return this.currentGame;
  }

  public ArrayList<Player> getActivePlayers() {
    ArrayList<Player> tempActivePlayers = new ArrayList<Player>();
    for(int i = 0; i < 4; i++) {
      tempActivePlayers.add(this.activePlayers[i]);
    }
    return tempActivePlayers;
  }

  public int getPlayerAmount() {
    return this.playerAmount;
  }
  
}
  
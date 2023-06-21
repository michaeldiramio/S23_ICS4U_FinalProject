import DLibX.*;
import java.awt.*;

import java.io.*;

import java.time.*;
import java.util.*;


public class GameScreen {
  
  //instance variables
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
      //For blinking affect
      int transChange = -5;
      int trans = 250;
     
    //space bar isnt pressed
    while(!this.dc.isKeyPressed(' ')) {
      this.dc.clear();

      Font customFont = null;
      
      try {
        //create the font to use. Specify the size!
        customFont = Font.createFont(Font.TRUETYPE_FONT, new File("Halo.ttf")).deriveFont(50f);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            
        //register the font
        ge.registerFont(customFont);
      } catch (IOException e) {
          e.printStackTrace();
      } catch(FontFormatException e) {
          e.printStackTrace();
      }

      //Change Transparency
      if (trans <= 0) {
        transChange*=-1;
      } else if (trans >= 255) {
        transChange*=-1;
      }

      //Set new transparency 
      trans+=transChange; 

      //Print on Screen
      this.dc.setPaint(new Color(0,25,255));
      this.dc.setFont(customFont);
  		this.dc.drawString("Totally Not A",400,75);
      this.dc.drawString("Mario Party Rip off",400,150);
      this.dc.setPaint(new Color(255, 25, 0, trans)); //
      this.dc.setFont(new Font("Comic sans", Font.PLAIN, 18));
      this.dc.drawString("Press Space to Play", 400, 480);
      
      this.dc.redraw();
      this.dc.pause(20);
    }
  }

  //Player join screen
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
      this.dc.setOrigin(DConsole.ORIGIN_CENTER);
      in.refreshKeys();

      if (trans >= 120 || trans <= 0) { //changing transparency
        change *= -1;
      }
      trans += change;

      // goes through each player to confirm if they are joining the game based on their keypresses
      for(int i = 0; i < 4; i++) {
        boolean[] tempControl = this.playerList.get(i).getControl().getPlayerKeysPressed(); // gets each player's direction keys
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
        this.dc.setPaint(new Color(255,255,255)); //white
        this.dc.fillRect(200, 200, 290, 90);
        this.dc.drawImage("Images/Keys/1.png", 400, 275);
        this.dc.setPaint(new Color(126, 217, 87,255-trans)); //green

        this.dc.setFont(new Font("Comic Sans", Font.BOLD, 22));
 
        this.dc.drawString("Press 'W' To Join!", 200, 35);
        this.dc.setPaint(new Color(0,0,0)); //black
        this.dc.setFont(customFontPlayers);
        this.dc.drawString("Player 1", 60, 10);

      } else { //joined
        this.dc.setPaint(new Color(255,255,255)); //white
        this.dc.fillRect(200, 200, 290, 90);
        this.dc.fillRect(200, 105, 90, 90);
        this.dc.drawImage("Images/Keys/5.png", 400, 275);

        this.dc.setPaint(new Color(225, 0, 0,255-trans)); //red
        this.dc.setFont(new Font("Comic Sans", Font.BOLD, 22));
        this.dc.drawString("Press 'S' To Leave!", 200, 35);
        this.dc.setPaint(new Color(0,0,0)); //black
        this.dc.setFont(customFontPlayers);
        this.dc.drawString("Player 1", 60, 10);
        
        this.dc.drawImage("Images/Character-Icons/haloHelmetBlue.png", 30, 75);
      }

      if (!(tempPlayers[1])) { //not joined
        this.dc.setPaint(new Color(255,255,255)); //white
        this.dc.fillRect(600, 200, 290, 90);
        this.dc.drawImage("Images/Keys/2.png", 400, 275);
        this.dc.setPaint(new Color(126, 217, 87,255-trans)); //green
        this.dc.setFont(new Font("Comic Sans", Font.BOLD, 22));
        this.dc.drawString("Press 'I' To Join!", 600, 35);
        this.dc.setPaint(new Color(0,0,0)); //black
        this.dc.setFont(customFontPlayers);
        this.dc.drawString("Player 2", 460, 10);
        
      } else { //joined
        this.dc.setPaint(new Color(255,255,255)); //white
        this.dc.fillRect(600, 200, 290, 90);
        this.dc.fillRect(600, 105, 90, 90);
        this.dc.drawImage("Images/Keys/6.png", 400, 275);
        
        this.dc.setPaint(new Color(225, 0, 0,255-trans)); //red
        this.dc.setFont(new Font("Comic Sans", Font.BOLD, 22));
        this.dc.drawString("Press 'K' To Leave!", 600, 35);
        this.dc.setPaint(new Color(0,0,0)); //black
        this.dc.setFont(customFontPlayers);
        this.dc.drawString("Player 2", 460, 10);
        
        this.dc.drawImage("Images/Character-Icons/haloHelmetGreen.png", 475, 90);
      }

      if (!(tempPlayers[2])) { //not joined
        this.dc.setPaint(new Color(255,255,255)); //white
        this.dc.fillRect(200, 445, 290, 90); 
        this.dc.drawImage("Images/Keys/3.png", 400, 275);
        this.dc.setPaint(new Color(126, 217, 87,255-trans)); //green
        this.dc.setFont(new Font("Comic Sans", Font.BOLD, 22));
        this.dc.drawString("Press 'T' To Join!", 200, 280);
        this.dc.setPaint(new Color(0,0,0)); //black
        this.dc.setFont(customFontPlayers);
        this.dc.drawString("Player 3", 65, 260);
        
      } else { //joined
        this.dc.setPaint(new Color(255,255,255)); //white
        this.dc.fillRect(200, 350, 90, 90);
        this.dc.fillRect(200, 445, 290, 90);
        this.dc.drawImage("Images/Keys/7.png", 400, 275);
        
        this.dc.setPaint(new Color(225, 0, 0,255-trans)); //red
        this.dc.setFont(new Font("Comic Sans", Font.BOLD, 22));
        this.dc.drawString("Press 'G' To Leave!", 200, 280);
        this.dc.setPaint(new Color(0,0,0)); //black
        this.dc.setFont(customFontPlayers);
        this.dc.drawString("Player 3", 65, 260);
        
        this.dc.drawImage("Images/Character-Icons/haloHelmetPurple.png", 78, 325);
      }

      if (!(tempPlayers[3])) { //not joined
        this.dc.setPaint(new Color(255,255,255)); //white
        this.dc.fillRect(600, 445, 290, 90);
        this.dc.drawImage("Images/Keys/4.png", 400, 275);
        this.dc.setPaint(new Color(126, 217, 87,255-trans)); //green
        this.dc.setFont(new Font("Comic Sans", Font.BOLD, 22));
        this.dc.drawString("Press '↑' To Join!", 600, 280);
        this.dc.setPaint(new Color(0,0,0)); //black
        this.dc.setFont(customFontPlayers);
        this.dc.drawString("Player 4", 460, 260);
        
      } else { //joined
        this.dc.setPaint(new Color(255,255,255)); //white
        this.dc.fillRect(600, 350, 90, 90);
        this.dc.fillRect(600, 445, 290, 90);
        this.dc.drawImage("Images/Keys/8.png", 400, 275);
        
        this.dc.setPaint(new Color(225, 0, 0,255-trans)); //red
        this.dc.setFont(new Font("Comic Sans", Font.BOLD, 22));
        this.dc.drawString("Press '↓' To Leave!", 600, 280);
        this.dc.setPaint(new Color(0,0,0)); //black
        this.dc.setFont(customFontPlayers);
        this.dc.drawString("Player 4", 460, 260);
        
        this.dc.drawImage("Images/Character-Icons/haloHelmetRed.png", 477, 335);
      }
      
      LocalTime end = LocalTime.now(); //end timer
      long value = Duration.between(start, end).toMillis(); //duration of time since button was pressed
      if (value > 1850 && this.playerAmount == 4) {
        if (value < 5000) { //5000 miliseconds is 5 seconds and time for next screen if no more buttons are pressed

          //plusing circle effect
          if(width == 51) {
            width = 0;
            c = 2;
          } else if (width == 0) {
            c = -2;
          }
          width++;
          this.dc.setPaint(new Color(126, 217, 87)); //green
          this.dc.fillEllipse(400, 275, 150, 150);
          if (c > 0) {
            this.dc.setPaint(new Color(126, 217, 87,255-width*5)); //transparent green
            this.dc.fillEllipse(400, 275, 150+width, 150+width);
          }

          //number countdown
          this.dc.setPaint(new Color(255, 255, 255)); //white
          this.dc.setFont(new Font("Comic Sans", Font.BOLD, 60));
          this.dc.drawString(3-(int)((value-2000)/1000), 400, 260);
          
        } else {
          joined = true; //break loop and move to next method in main
        }
      }
      this.dc.redraw();
      this.dc.pause(20); //should be 20 milisecond pause (then make it that way)
    }

    
  }

  //Setting username screen
  public void nicknames() {
    background();
    this.dc.setOrigin(DConsole.ORIGIN_CENTER);
    WordInput in = new WordInput(this.dc);
    
    String avatar[] = {"Images/Character-Icons/haloHelmetBlue.png", "Images/Character-Icons/haloHelmetGreen.png", "Images/Character-Icons/haloHelmetPurple.png", "Images/Character-Icons/haloHelmetRed.png"};
    int xvals[] = {50, 101, 102, 101};
    int yvals[]= {50, 67, 50, 59};
    
    String names[] = {"", "", "", ""};

    for (int i = 0; i < 4; i++) {
      this.playerList.get(i).setUsername("");
      boolean select = false;
      if(this.activePlayers[i] != null) { // if the player has joined the game
        while(!select) {
          this.dc.setPaint(this.playerList.get(i).getColor()); //color array
          this.dc.fillRect(400, 275, 800, 550); //background color
          this.dc.setPaint(new Color(255, 255, 255));
          this.dc.setFont(new Font("Comic Sans", Font.BOLD, 35));
          this.dc.drawString("Create a Username", 400, 35);
          in.refreshKeys();

          this.dc.drawImage(avatar[i], xvals[i], yvals[i]);

          this.dc.drawImage("Images/textbox.png", 400, 225);

          String name = in.getCurrentWord(); //to show as typed
          if (name.length() <= 10) { 
            names[i] = name;
          }

          this.dc.setPaint(new Color(0, 0, 0)); //black
          this.dc.setFont(new Font("Comic Sans", Font.BOLD, 20));
          this.dc.drawString(names[i], 400, 164); //display names typing

          String nfinal = in.getFinalWord(); //pressed enter
           
          if (nfinal != "" && nfinal.length() <= 10) { 
            boolean inn = false;
            for (int iz = 0; iz < this.playerList.size(); iz++) { //check if other player already has that name
              if (nfinal.equals(this.playerList.get(iz).getUsername())) {
                inn = true;
              }
            }
            if (!inn) { //if no other player has that username
              names[i] = nfinal; 
              this.playerList.get(i).setUsername(nfinal); //set player username
              select = true; //move to next part of loop
            }
          }
          this.dc.redraw();
          this.dc.pause(20);
        }
      }
    }
  }

  //game vote screen
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
      
      this.background(); //draw background
      this.dc.setOrigin(DConsole.ORIGIN_CENTER);

      //color changing effect
      if (color == 150 || color == 0) {
        change *= -1;
      }
      color += change;
      this.dc.setPaint(new Color(250-color,75+color,200));
      
      this.dc.setFont(new Font("Comic Sans", Font.BOLD, 80));
      this.dc.drawString("Choose A Game", 400, 50); //head line

      //game display boxes
      this.dc.fillRect(300, 225, 160, 160);
      this.dc.fillRect(500, 225, 160, 160);
      this.dc.fillRect(300, 425, 160, 160);
      this.dc.fillRect(500, 425, 160, 160);

      // eventually draw game images
      this.dc.setPaint(Color.BLACK);
      this.dc.setFont(new Font("Comic Sans", Font.PLAIN, 18));
      this.dc.drawString(subMinigameList.get(0).getName(), 300, 225);
      this.dc.drawString(subMinigameList.get(1).getName(), 500, 225);
      this.dc.drawString(subMinigameList.get(2).getName(), 300, 425);
      this.dc.drawString(subMinigameList.get(3).getName(), 500, 425);
      this.dc.setFont(new Font("Comic Sans", Font.PLAIN, 36));
      this.dc.drawString("↑", 230, 155);
      this.dc.drawString("←", 440, 145);
      this.dc.drawString("↓", 230, 355);
      this.dc.drawString("→", 440, 345);

      // coordinates for icons/text
      int[] xCoords = {300, 500, 300, 500};
      int[] yCoords = {225, 225, 425, 425};

      this.dc.setPaint(Color.BLACK);
      this.dc.setFont(new Font("Comic Sans", Font.BOLD, 20));
      // gets the possible games based on which page you are on
      // ex. if you are on page 1, the games shown would be slots 0-4
      for(int i = (screenNum - 1) * gameScreenNum; i < (screenNum - 1) * gameTotal; i++) {
        this.dc.drawString("Game " + (i + 1), xCoords[i], yCoords[i]); // temporary game image placeholder
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
            this.dc.setPaint(this.playerList.get(i).getColor());
            this.dc.fillEllipse(xCoords[j] - 70 + (i * 35), yCoords[j] + 100, 25, 25);
          } else if(this.activePlayers[i] != null && playerGameVoted[i] == -1) {
            this.dc.setPaint(this.playerList.get(i).getColor());
            this.dc.fillEllipse(347.5 + (i * 35), 125, 25, 25);
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
          this.dc.setPaint(new Color(126, 217, 87)); //green
          this.dc.fillEllipse(400, 275, 150, 150);
          if (c > 0) {
            this.dc.setPaint(new Color(126, 217, 87,255-width*5)); //transparent green
            this.dc.fillEllipse(400, 275, 150+width, 150+width);
          }

          //number countdown
          this.dc.setPaint(new Color(255, 255, 255)); //white
          this.dc.setFont(new Font("Comic Sans", Font.BOLD, 60));
          this.dc.drawString(3-(int)((value-2000)/1000), 400, 260);

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
      
      this.dc.redraw();
      this.dc.pause(20); //should be 20 miliseconds pause
    }
    
  }

  public void miniWin(ArrayList<Player> playerList) { //bars display score highet to lowest
    boolean play = false;
    int time = 0;

    Player[] tempPlayerList = new Player[playerList.size()];

    //fills temp list with players
    for(int i = 0; i < playerList.size(); i++){
      try {
        tempPlayerList[i] = playerList.get(i);
      }catch (Exception e) {}
    }

    //bubble sorts players in tempPlayerArray by points
    for (int i = 0; i < playerList.size(); i++) {
        for (int j = i + 1; j < playerList.size(); j++) {
            Player temp;
            if (tempPlayerList[i].getPoints() < tempPlayerList[j].getPoints()) {
             
                // Swapping
                temp = tempPlayerList[i];
                tempPlayerList[i] = tempPlayerList[j];
                tempPlayerList[j] = temp;
            }
        }
    }

    int cycles = 0;
    int seconds = 2;

    while (!play) {
      background(); //draw background
      this.dc.setOrigin(DConsole.ORIGIN_CENTER);
      this.dc.setPaint(tempPlayerList[0].getColor());
      this.dc.fillRect(400, 275, 800, 550);

      this.dc.setPaint(new Color(255, 255, 255)); 
      this.dc.setFont(new Font("Comic Sans", Font.BOLD, 50));
      this.dc.drawString(tempPlayerList[0].getUsername() + " Wins", 400, 150);

      
      //one second has passed
      if (cycles >= 50) {
        seconds--;
        cycles = 0;
      }
      cycles++;

      if (seconds == 0) {
        play = true;
      }
      
      this.dc.redraw();
      this.dc.pause(20);
    }
  }

  //Leaderboard at end
  public void winScreen() { //bars display score highet to lowest
    boolean play = false;
    int up = 1;
    int trans = 1;
    int change = 1;
    int xs[] = {340, 460, 220, 580};
    WordInput in = new WordInput(dc);

    while (!play) {
      background(); //draw background
      this.dc.setOrigin(DConsole.ORIGIN_CENTER);
      in.refreshKeys();

      this.sortingByScore(); //sort array list by score

      for (int i = 0; i < this.playerList.size(); i++) {
        this.dc.setPaint(this.playerList.get(i).getColor()); //color array
        int tempScore = this.playerList.get(i).getScore();
        this.dc.fillRect(xs[i], 550, 100, (int)((tempScore*up) / 25)); //grow to size over time
        this.dc.setFont(new Font("Comic Sans", Font.BOLD, 17));
        this.dc.drawString(this.playerList.get(i).getUsername(), xs[i], 535 - (int)(((tempScore*up) / 25)/2));
      }
      
      if (up < 150) {
        up++;
      } else if (up >= 150) {
        if (trans >= 150 || trans <= 0) { //changing transparency
        change *= -1;
      }
      trans += change;

        this.dc.setPaint(new Color(0, 0, 0,255-trans)); 
        this.dc.setFont(new Font("Comic Sans", Font.BOLD, 30));
        this.dc.drawString("Press Space To Play Again", 400, 100);
        if (this.dc.isKeyPressed(' ')) {
          play = true;
        }
      }
      
      
      this.dc.redraw();
      this.dc.pause(20);
    }
    this.playerList.clear();
    this.dc.pause(20);
  }

  public void gameSwap(ArrayList<Player> cp) {
    boolean play = false;
    int change = 4;
    int col = 4;
    LocalTime start = LocalTime.now(); //reset time to 0
    int width = 2;
    int c = 1;
    
    /*int cPos[][] = {
      {200, 600}, {175, 375}
    };
    String avatar[] = {"Images/Character-Icons/haloHelmetBlue.png", "Images/Character-Icons/haloHelmetGreen.png", "Images/Character-Icons/haloHelmetPurple.png", "Images/Character-Icons/haloHelmetRed.png"}; */ //Images sizes need to be changed

    while (!play) {
      this.dc.setOrigin(DConsole.ORIGIN_CENTER);

      //changing color
      if (col >= 150 || col <= 0) {
        change *= -1;
      }
      col += change;

      //Make first player's box
      this.dc.setPaint(cp.get(0).getColor()); 
      this.dc.fillRect(200, 275, 400, 550);
      this.dc.setFont(new Font("Comic Sans", Font.BOLD, 40));
      this.dc.setPaint(cp.get(1).getColor()); //get the other players color (cool contrast)
      this.dc.drawString(cp.get(0).getUsername(), 225, 275);

      //2nd player's box
      this.dc.setPaint(cp.get(1).getColor());
      this.dc.fillRect(600, 275, 400, 550);
      this.dc.setFont(new Font("Comic Sans", Font.BOLD, 40));
      this.dc.setPaint(cp.get(0).getColor()); 
      this.dc.drawString(cp.get(1).getUsername(), 575, 275);

      this.dc.drawImage("Images/fire.png", 400, 275);

      this.dc.setPaint(new Color(255-col, 50+col, col)); 
      this.dc.setFont(new Font("Comic Sans", Font.BOLD, 60));
      this.dc.drawString("VS", 400, 275);

      /*for (int i = 0; i < cp.size(); i++) {
        if (cp.get(i).getID() == 1) {
          dc.drawImage(avatar[1], cPos[i][i], cPos[i][i]);
        } else if (cp.get(i).getID() == 2) {
          dc.drawImage(avatar[2], cPos[i][i], cPos[i][i]);
        } else if (cp.get(i).getID() == 3) {
          dc.drawImage(avatar[3], cPos[i][i], cPos[i][i]);
        } else if (cp.get(i).getID() == 4) {
          dc.drawImage(avatar[4], cPos[i][i], cPos[i][i]);
        }
      }*/

      //countdown
      LocalTime end = LocalTime.now(); //end timer
      long value = Duration.between(start, end).toMillis();
      if (value > 3000) {
        if (value > 5900) { //end
          play = true;
        }

        //plusing circle effect
        if (width == 51) {
          width = 0;
          c = 2;
        } else if (width == 0) {
          c = -2;
        }
        width++;
        this.dc.setPaint(new Color(126, 217, 87)); //green
        this.dc.fillEllipse(400, 275, 150, 150);
        if (c > 0) {
          this.dc.setPaint(new Color(126, 217, 87,255-width*5)); //transparent green
          this.dc.fillEllipse(400, 275, 150+width, 150+width);
        }

        //number countdown
        this.dc.setPaint(new Color(255, 255, 255)); //white
        this.dc.setFont(new Font("Comic Sans", Font.BOLD, 60));
        this.dc.drawString(6-(int)(value/1000), 400, 260);
      }
      
      this.dc.redraw();
      this.dc.pause(20);
    }
  }

  //draw the default background which is currently halo skybox
  public void background() {
    this.dc.setOrigin(DConsole.ORIGIN_CENTER);
    this.dc.drawImage("Images/background.png", 400, 275);
  }

  //sort arraylist by score
  public void sortingByScore() {
    for (int i = 0; i < this.playerList.size(); i++) {
        for (int j = i + 1; j < playerList.size(); j++) {
            Player temp;
            if (this.playerList.get(i).getScore() < this.playerList.get(j).getScore()) {
              
                // Swapping
                temp = this.playerList.get(i);
                this.playerList.set(i, this.playerList.get(j));
                this.playerList.set(j, temp);
            }
        }
    }
  }

  //sort arraylist by ID
  public void sortingByID() {
    for (int i = 0; i < this.playerList.size(); i++) {
        for (int j = i + 1; j < this.playerList.size(); j++) {
            Player temp;
            if (this.playerList.get(i).getID() > this.playerList.get(j).getID()) {
              
                // Swapping
                temp = this.playerList.get(i);
                this.playerList.set(i, this.playerList.get(j));
                this.playerList.set(j, temp);
            }
        }
    }
  }

  //return the number of players playing
  public int playerCount(){
    return this.playerList.size();
  }

  //Return the game being played
  public int getCurrentGame() {
    return this.currentGame;
  }

  //Get the players playing
  public ArrayList<Player> getActivePlayers() {
    ArrayList<Player> tempActivePlayers = new ArrayList<Player>();
    for(int i = 0; i < 4; i++) {
      tempActivePlayers.add(this.activePlayers[i]);
    }
    return tempActivePlayers;
  }

  //Get how many are playing
  public int getPlayerAmount() {
    return this.playerAmount;
  }
  
}
  
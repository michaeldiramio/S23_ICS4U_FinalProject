import DLibX.*;
import java.awt.*;
import java.util.*;

public class RockPaperScissors extends Minigame {

  //Instance Variables
  DConsole dc;
  private boolean game;
  //Choice
  int[] choice = new int[2]; //0 = rock, 1 = paper, 2 scissors
  boolean[] chosen = new boolean[2];
  //UI
  boolean draw;

  //Constructor
  public RockPaperScissors(int id, DConsole dc, ArrayList<Player> playerList) {
    super(id, dc, playerList, "Rock Paper\n  Scissors");
    this.dc = dc;
  }

  //Spawn Players
  private void spawnPlayers() {
    playerList.get(0).setPOS(300, 275);
    playerList.get(1).setPOS(500, 275);
  }

  //Play the game
  @Override
  public void play() {
    spawnPlayers(); 
    
    //Variables
    game = true;
    for (int i = 0; i < choice.length; i++) {
      chosen[i] = false;
      choice[i] = -1;
      draw = false;
    }

    int cycles = 0;
    int seconds = 1;
    
    //Game Loop
    while (game) {
      dc.clear();

      //Move characters and refresh screen
      this.moveCharacters();
      super.refreshScreen();

      //Print quick instructions
      this.dc.setFont(new Font("Comic Sans", Font.BOLD, 36));
      this.dc.drawString("←", 350, 100);
      this.dc.drawString("↑", 400, 50);
      this.dc.drawString("→", 450, 100);
      this.dc.drawString("↓", 400, 150);
      this.dc.setFont(new Font("Comic Sans", Font.PLAIN, 18));
      this.dc.drawString("Rock", 300, 105);
      this.dc.drawString("Paper", 400, 25);
      this.dc.drawString("Scissors", 500, 105);
      this.dc.drawString("Reset", 400, 175);

      //Outcome processing, 0 = rock, 1 = paper, 2 scissors
      if ((choice[0] == 0 && choice[1] == 2) || (choice[0] == 1 && choice[1] == 0) || (choice[0] == 2 && choice[1] == 1)) { //player 1 wins
        playerList.get(0).addToPoints(25);
        game = false;
      } else if ((choice[0] == 0 && choice[1] == 1) || (choice[0] == 1 && choice[1] == 2) || (choice[0] == 2 && choice[1] == 0)) { //player 2 wins
        playerList.get(1).addToPoints(25);
        game = false;
      } else if ((choice[0] != -1 && choice[1] != -1) && choice[0] == choice[1]) { //tie
        for (int i = 0; i < choice.length; i++) { //reset game for another round
          chosen[i] = false;
          choice[i] = -1;
          draw = true;
          seconds = 1;
        }
      } 

      //players drew, wait 2 seconds (to refresh keys) and play again (play until someone wins)
      if (draw) {
        this.dc.drawString("Draw! Pick Again", 400, 400);
        cycles++;
        if (cycles >= 25) { //half second pause
          cycles = 0;
          seconds--;
        }
        if (seconds == 0) {
          draw = false;
        }
      }

      //display chosen symbol
      if (chosen[0]) {
        this.dc.drawImage("Images/RPS/check.jpg", 300, 215);
      }
      if (chosen[1]) {
        this.dc.drawImage("Images/RPS/check.jpg", 500, 215);
      }
      
      dc.redraw();
      dc.pause(20);
    }
    
  }

  //Move characters
  @Override
  public void moveCharacters() {

    for(int i = 0; i < this.playerList.size(); i++) { //check what each player is pressing
      
      if(this.playerList.get(i) != null) {
        boolean[] tempControl = this.playerList.get(i).getControl().getPlayerKeysPressed(); // gets the player's currently pressed keys

        //Player choices
        if(tempControl[0]) { //paper
          this.choice[i] = 1;
          this.chosen[i] = true;
        }
        if(tempControl[1]) { //rock
          this.choice[i] = 0;
          this.chosen[i] = true;
        }
        if(tempControl[2] || draw) { //reset choice
          this.choice[i] = -1;
          this.chosen[i] = false;
        }
        if(tempControl[3]) { //sissors
          this.choice[i] = 2;
          this.chosen[i] = true;
        }
      }
    }
  }











  
}
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

  //Constructor
  public RockPaperScissors(int id, DConsole dc, ArrayList<Player> playerList) {
    super(id, dc, playerList);
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
    
    //Game Loop
    while (game) {
      dc.clear();

      //Move characters and refresh screen
      this.moveCharacters();
      super.refreshScreen();

      //if clause for rock paper and scissors

      dc.redraw();
      dc.pause(20);
    }
    
  }

  //Move characters
  @Override
  public void moveCharacters() {

    for(int i = 0; i < this.playerList.size(); i++) {
      
      if(this.playerList.get(i) != null) {
        boolean[] tempControl = this.playerList.get(i).getControl().getPlayerKeysPressed(); // gets the player's currently pressed keys

        //Player choices
        if(tempControl[0]) { //paper
          choice[i] = 1;
          chosen[i] = true;
        }
        if(tempControl[1]) { //rock
          choice[i] = 0;
          chosen[i] = true;
        }
        if(tempControl[3]) { //sissors
          choice[i] = 2;
          chosen[i] = true;
        }
      }
    }
  }











  
}
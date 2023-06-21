import DLibX.*;
import java.awt.*;
import java.util.*;

public class MashingRace extends Minigame {

  private ArrayList<Player> tempPlayerList = new ArrayList<Player>();
  boolean[] movementAllowance = {true, true}; // can they move?
  
  // contructor
  public MashingRace(int id, DConsole dc, ArrayList<Player> cp) {
    super(id, dc, cp, "Mashing Race");
  }

  @Override
  public void moveCharacters() {
    
    for(int i = 0; i < this.playerList.size(); i++) { // going through each player

      if(this.playerList.get(i) != null) {
        
        boolean[] tempControl = this.playerList.get(i).getControl().getPlayerKeysPressed(); // gets the player's currently pressed keys
        
      if(tempControl[3] && movementAllowance[i] && this.playerList.get(i).getX() < 700) { //if that player's right key is pressed
          playerList.get(i).moveX(9);
          movementAllowance[i] = false;
        }

        
        if (!tempControl[3] && !movementAllowance[i]) {
          movementAllowance[i] = true;
        }
      }
    }
  }

  @Override
  public void play() {
    boolean game = true;
    int cycles = 0;
    int seconds = 15;
     
    
    // setting the starting points of the players
    for(int i = 0; i < this.playerList.size(); i++) {
      playerList.get(i).setX(100);
      playerList.get(i).setY(150 * i + 170);
    }

    while(game) {

      dc.clear();

      this.moveCharacters();
      super.refreshScreen();

      dc.setFont(new Font("Comic Sans", Font.BOLD, 20));
      //time
      dc.setPaint(new Color(0, 0, 0));//black
      super.printTime(seconds, 100, 40);

      cycles++;
      if (cycles >= 50) { //one second has passed
        seconds--;
        cycles = 0;
      }
      
      if (seconds == 0) { //15 second are up, game ends
        game = false;
      }

      for (int i = 0; i < this.playerList.size(); i++) {
        if (this.playerList.get(i).getX() >= 700) { // if someone hits the finish
          game = false;
       }
      }

      // finish line
      dc.setPaint(new Color(252, 0, 0));
      dc.fillRect(700, 275, 3, 600);

      // writing
      dc.setFont(new Font("Comic Sans", Font.BOLD, 20));
      dc.drawString("Hit RIGHT to move!", 400, 200);
      
      dc.redraw();
      dc.pause(20);
      
    }

    for (int i = 0; i < playerList.size(); i++){
      if (playerList.get(i).getX() >= 700) { // rewarding points
        playerList.get(i).addToPoints(1000);
      }
    }
    
  }

  
}
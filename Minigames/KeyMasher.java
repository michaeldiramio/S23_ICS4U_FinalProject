import DLibX.*;
import java.awt.*;
import java.util.*;

public class KeyMasher extends Minigame {
  
  private ArrayList<Player> tempPlayerList = new ArrayList<Player>();
  private int[] timesPressed;
  private boolean[][] lastKeysPressed;

  //Constructor
  public KeyMasher(int id, DConsole dc, ArrayList<Player> playerList) {
    super(id, dc, playerList, "Key Masher");
    this.timesPressed = new int[4];
    this.lastKeysPressed = new boolean[4][4];
  }
  
  public void moveCharacters() {
    for(int i = 0; i < this.playerList.size(); i++) {
      if(this.playerList.get(i) != null) {
        boolean[] tempControl = this.playerList.get(i).getControl().getPlayerKeysPressed(); // gets the player's currently pressed keys
        
        for(int j = 0; j < tempControl.length; j++){
          // if the current key is pressed and last time it was not pressed, increase the amount of times pressed by 1
          if(tempControl[j] && !this.lastKeysPressed[i][j]) {
            this.timesPressed[i]++;
            this.playerList.get(i).addToPoints(1);
          }
          this.lastKeysPressed[i][j] = tempControl[j];
        }
      }
    }
  }

  
  //Play the game
  @Override
  public void play() {

    boolean game = true;

    int cycles = 0;
    int seconds = 10;
    while (game) { //these will be the loops that go on until game ends (refer to useful information for time limits)
      this.dc.clear();

      this.moveCharacters();

      this.dc.setFont(new Font("Comic Sans", Font.BOLD, 20));
      //time
      this.dc.setPaint(new Color(0, 0, 0));//black
      super.printTime(seconds, 100, 40);

      // print username
      this.dc.drawString(this.playerList.get(0).getUsername(), 300, 190);
      this.dc.drawString(this.playerList.get(1).getUsername(), 500, 190);
      this.dc.drawString(this.playerList.get(2).getUsername(), 300, 390);
      this.dc.drawString(this.playerList.get(3).getUsername(), 500, 390);
      
      // print times pressed
      this.dc.setFont(new Font("Comic Sans", Font.PLAIN, 20));
      this.dc.drawString(this.timesPressed[0], 300, 225);
      this.dc.drawString(this.timesPressed[1], 500, 225);
      this.dc.drawString(this.timesPressed[2], 300, 425);
      this.dc.drawString(this.timesPressed[3], 500, 425);
      

      cycles++;
      if (cycles >= 50) { //one second has passed
        seconds--;
        cycles = 0;
      }
      
      if (seconds == 0) { //10 second are up, game ends
        game = false;
      }

      this.dc.redraw();
      this.dc.pause(20);
    }
  }
}

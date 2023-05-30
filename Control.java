// imports
import DLibX.*;
import java.awt.*;
import java.util.*;

public class Control {

  // constants
  private String[] player1Keys = {"w", "a", "s", "d"};
  private int[] player1Keycodes = {87, 65, 83, 68};

  private String[] player2Keys = {"i", "j", "k", "l"};
  private int[] player2Keycodes = {73, 74, 75, 76};

  private String[] player3Keys = {"t", "f", "g", "h"};
  private int[] player3Keycodes = {84, 70, 71, 72};

  private String[] player4Keys = {"up", "left", "down", "right"};
  private int[] player4Keycodes = {38, 37, 40, 39};

  ArrayList<int[]> playerKeycodes = new ArrayList<int[]>(
    Arrays.asList(player1Keycodes, player2Keycodes, player3Keycodes, player4Keycodes)
  );

  private String[] universalKeys = player4Keys;

  
  // data
  private int playerNum;
  private DConsole dc;


  // constructor
  public Control(int playerNum, DConsole dc) {
    this.playerNum = playerNum;
    this.dc = dc;
  }

  
  /*
  // METHODS
  */
  
  // gets current keys pressed
  private int[] getCurrentKeycodesActive() {
    return this.dc.getKeys();
  }

  
  // returns a boolean array of the player's current keypresses
  public boolean[] getPlayerKeysPressed() {
    int[] tempKeys = this.getCurrentKeycodesActive();
    int[] tempKeycodes = playerKeycodes.get(this.playerNum);
    boolean[] tempKeysActive = new boolean[tempKeycodes.length];

    // checks each active key and compares it with the player's possible keys 
    // to evaluate if the key is pressed
    for(int i = 0; i < tempKeys.length; i++) {
      for(int j = 0; j < tempKeycodes.length; j++) {
        if(tempKeys[i] == tempKeycodes[j]) {
          tempKeysActive[j] = true;
        }
      }
    }

    return tempKeysActive;
  }


  // checks to see if the desired universal key has been pressed by a specific player
  public boolean keyPressed(String universalKey) {
    boolean[] tempKeys = this.getPlayerKeysPressed();

    // returns true if the key is pressed at the position same in the universal key array
    for(int i = 0; i < tempKeys.length; i++) {
      if(universalKey == universalKeys[i] && tempKeys[i]) {
        return true;
      }
    }
    return false;
  }

  
  
  

  
}
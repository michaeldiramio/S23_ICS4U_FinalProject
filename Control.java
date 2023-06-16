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

  // puts all keycodes into an easily accessable ArrayList
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
  private int[] getCurrentKeycodesPressed() {
    // gets current keys pressed from the DConsole (may be null if no keys are pressed)
    int[] tempKeycodes = this.dc.getKeys(); 
    
    if(tempKeycodes.length > 0) {
      Arrays.sort(tempKeycodes);
      return tempKeycodes; // returns an integer array of currently pressed sorted keycodes
    } else {
      return new int[4]; // if no keys are pressed the method will return an int array of 4 zeroes instead of null
    }
  }

  
  // returns a boolean array of the player's current keypresses
  public boolean[] getPlayerKeysPressed() {
    int[] tempKeys = this.getCurrentKeycodesPressed(); // gets the player's current keycodes (integer value)
    
    int[] tempKeycodes = playerKeycodes.get(this.playerNum - 1); // gets the desired player's assigned keycodes:
    //ex. player 1 will be designated 87,65,83,68 (w,a,s,d)
    
    boolean[] tempKeysActive = new boolean[tempKeycodes.length]; // creating boolean array to later store which keys are active

    // checks each active key and compares it with the player's possible keys to evaluate if the key is pressed
    // ex. if this method is used for player1, if they are pressing w and s, the method would return true,false,true,false
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

    // returns true if the key pressed is the same direction as in the universal key array
    // ex. if player1 presses 'd', player1.keyPressed("right") will return true
    for(int i = 0; i < tempKeys.length; i++) {
      if(universalKey == universalKeys[i] && tempKeys[i]) {
        return true;
      }
    }
    return false;
  }
}
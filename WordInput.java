// imports
import DLibX.*;
import java.awt.*;
import java.util.*;

public class WordInput {
  
  // data
  private DConsole dc;
  private String word;
  private ArrayList<Character> keyInput;

  
  // constructor
  public WordInput(DConsole dc) {
    this.dc = dc;
    this.word = null;
    this.keyInput = null;
  }

  
  /*
  // METHODS
  */
  
  // gets current keycodes pressed
  private int[] getCurrentKeycodesPressed() {
    int[] tempKeycodes = this.dc.getKeys();
    if(tempKeycodes.length > 0) {
      Arrays.sort(tempKeycodes);
      return tempKeycodes;
    } else {
      return null;
    }
  }

  
  // gets current keys pressed (as a char)
  private char[] getPlayerKeysPressed() {
    int[] tempKeycodes = this.getCurrentKeycodesPressed();

    if(tempKeycodes != null) {
      char[] tempKeys = new char[tempKeycodes.length];
      for(int i = 0; i < tempKeycodes.length; i++) {
        tempKeys[i] = (char)tempKeycodes[i];
      }
      return tempKeys;
    } else {
      return null;
    }
  }

  public void refreshKeys() {
    char[] tempKeys = this.getPlayerKeysPressed();
    
    if(this.keyInput != null) {
      for(int i = 0; i < keyInput.size(); i++) {
        if(!this.keyInput.contains(tempKeys[i])) {
          this.word += tempKeys[i];
        }
      }

      this.keyInput.clear();
      for(int i = 0; i < tempKeys.length; i++) {
        this.keyInput.add(tempKeys[i]);
      }
    }
  }

  public void printKeyInput() {
    if(this.keyInput != null) {
        for(int i = 0; i < this.keyInput.size(); i++) {
        System.out.print(this.keyInput.get(i));
      }
      System.out.println();
    }
  }

  
  
}
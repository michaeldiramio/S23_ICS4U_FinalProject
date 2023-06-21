// imports
import DLibX.*;
import java.awt.*;
import java.util.*;

public class WordInput {

  // constants
  private Integer[] tempIllegalKeycodes = {0,9,20,16,10,8,16,38,37,40,39,17,18,91};
  private ArrayList<Integer> illegalKeycodes = new ArrayList<>();
  
  // data
  private DConsole dc;
  private String word;
  private int characterLimit;
  private ArrayList<Character> keyInput;

  
  // constructor with preset characterLimit
  public WordInput(DConsole dc) {
    this.dc = dc;
    this.word = "";
    this.characterLimit = 99;
    this.keyInput = new ArrayList<Character>(1);

    // fills illegalKeycodes (ArrayList) with tempIllegalKeycodes values
    for(int i = 0; i < this.tempIllegalKeycodes.length; i++) {
      this.illegalKeycodes.add(tempIllegalKeycodes[i]);
    }
  }

  // constructor with dynamic characterLimit
  public WordInput(int characterLimit, DConsole dc) {
    this.dc = dc;
    this.word = "";
    this.characterLimit = characterLimit;
    this.keyInput = new ArrayList<Character>(1);

    // fills illegalKeycodes (ArrayList) with tempIllegalKeycodes values
    for(int i = 0; i < this.tempIllegalKeycodes.length; i++) {
      this.illegalKeycodes.add(this.tempIllegalKeycodes[i]);
    }
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
      return new int[1]; // if no keys are pressed the method will return an int array of 1 zero instead of null
    }
  }

  
  // gets current keys pressed (as a char array)
  private char[] getCurrentKeysPressed() {
    int[] tempKeycodes = this.getCurrentKeycodesPressed(); // gets the current keycodes (int array) 
                                                  // from previous method
    char[] tempKeys = new char[tempKeycodes.length];

    // converts keycodes (int) to its lowercase char value. does not convert a value if it is an illegal keypress
    for(int i = 0; i < tempKeycodes.length; i++) {
      if(!this.illegalKeycodes.contains(tempKeycodes[i])) {
        tempKeys[i] = Character.toLowerCase((char)tempKeycodes[i]);
      }
    }
    return tempKeys;
  }


  // gets the current keys and keypresses and adds 
  public void refreshKeys() {
    
    // gets both current keycodes (int) and keys (char)
    char[] tempKeys = this.getCurrentKeysPressed();
    int[] tempKeycodes = this.getCurrentKeycodesPressed();

    // if the keycode for shift exists in the array set shiftPressed to true
    boolean shiftPressed = (Arrays.binarySearch(tempKeycodes, 16) > -1) ? true: false;

    // if the keycode for backspace exists in the array and the current word legth is greater than zero, 
    // delete the last letter on word
    if((Arrays.binarySearch(tempKeycodes, 8) > -1) && this.word.length() > 0) {
      this.word = this.word.substring(0, this.word.length() - 1);
    }

    for(int i = 0; i < tempKeys.length; i++) {
      // checks that current keys are not the same as past keys, checks that word is below character limit, 
      // and checks that the keys are not '\0' (undefined char)
      if(!this.keyInput.contains(tempKeys[i]) && this.word.length() < this.characterLimit && tempKeys[i] != '\0') {
        // if shift is pressed then convert each char being added to word to uppercase, otherwise just add chars to word
        this.word += (shiftPressed) ? Character.toUpperCase(tempKeys[i]) : tempKeys[i];
      }
    }

    // clears last keys and sets it to current keys
    this.keyInput.clear();
    for(int i = 0; i < tempKeys.length; i++) {
      this.keyInput.add(tempKeys[i]);
    }
  }


  // gets if a key is pressed or not, char key must be lowercase
  public boolean keyPressed(char key) {
    int keycode = key; // converts key (char) into its keycode (int)
    int[] tempKeycodes = this.getCurrentKeycodesPressed(); // gets current keycodes (int array)

    // returns true if desired keycode (formerly key) is in the current keycode array
    if(Arrays.binarySearch(tempKeycodes, keycode) > -1) {
      return true;
    } else {
      return false;
    }
  }

  
  // gets current word (string stored in WordInput object)
  public String getCurrentWord() {
    return this.word;
  }

  
  // gets access to the final word once enter is pressed. otherwise it returns ""
  public String getFinalWord() {
    int[] tempKeycodes = this.getCurrentKeycodesPressed(); // get current keycodes (int array)
    String tempWord = this.word; // stores the current word so it can be returned after word is set back to ""

    // if the enter keycode is in the tempKeycodes array, return the current word
    if(Arrays.binarySearch(tempKeycodes, 10) > -1) {
      this.word = "";
      return tempWord;
    } else {
      return ""; // if enter is not pressed, return a blank string (not null)
    }
  }


  /*    EXAMPLE OF USING WORDINPUT

  ***  after you use getFinalWord() it will be set to "" (a blank string, not null) ***


  String finalWord = "";

  while(true) {
    wordInput.refreshKeys();
    dc.drawString(wordInput.getCurrentWord(), 220, 150);
    
    String tempWord = wordInput.getFinalWord();

    if(tempWord != "") {
      finalWord = tempWord;
    }
    
    dc.drawString(finalWord, 400, 150);
  }


*** EXPLAINATION ***

finalWord must be initialized, as DConsole cannot print a null string, it must be at least set to ""

For any use of WordInput, make sure you use refreshKeys() before any code to get the most recent key input

getCurrentWord() exists as a way to display the current typed word on the screen

getFinalWord() will return "" until the enter key is pressed, whereupon it will return the current word.
After getFinalWord() is called, word will be set back to ""

  */
}
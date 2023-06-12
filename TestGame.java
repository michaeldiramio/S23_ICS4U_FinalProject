import DLibX.*;
import java.awt.*;
import java.util.*;

public class TestGame extends Minigame {


  //Constructor
  public TestGame(int id, DConsole dc, ArrayList<Player> playerList, ArrayList<Entity> entities) {
    super(0, dc, playerList, entities);
  }

  //USEFUL INFORMATION: The ideal DConsole pause is 20ms, thus 50 pauses make a second, therefore, 50 cycles is one second
  //That being said, we can track time with these multiples of 50, 750 cycles is 15 seconds, and 3000 cycles is a minute
  //These cycles should increase at the end of each cycle of the while loop, and can be used to end the game upon a time limit

  //Play the game
  @Override
  public void play() {
    
    //this is how you add points to a player
    //this.playerList.get(0).addToPoints(4);
    
    boolean game = true;
    int cycles = 0;
    int seconds = 1;
    while (game) { //these will be the loops that go on until game ends (refer to useful information for time limits)
      dc.clear();
      
      super.moveCharacters();
      super.refreshScreen();
      super.printTime(seconds);
      
      cycles++;
      if (cycles >= 50) { //one second has passed
        seconds--;
        cycles = 0;
      }

      if (seconds == 0) { //15 second are up, game ends
        game = false;
      }

      dc.redraw();
      dc.pause(20);
    }
  
  }
  


}

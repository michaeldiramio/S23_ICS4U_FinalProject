import DLibX.*;
import java.awt.Color;
import java.util.*;

public class Kahoot extends Minigame {

public Kahoot(int id, DConsole dc, ArrayList<Player> players, ArrayList<Entity> entities) {
    super(id, dc, players, entities);
  }

//create entities here
  private void addEntites(){
    super.entityList.add(new Entity(1, "joesef", 50, 50, 50, 50, true, Color.GREEN, this.dc));
    super.entityList.add(new Entity(2, "Answer 1", 75, 75, 55, 55, true, Color.RED, this.dc));
    super.entityList.add(new Entity(3, "Answer 2", 75, 75, 155, 155, true, Color.GREEN, this.dc));
    super.entityList.add(new Entity(4, "Answer 3", 75, 75, 255, 55, true, Color.BLUE, this.dc));
    super.entityList.add(new Entity(5, "Answer 4", 75, 75, 355, 155, true, Color.YELLOW, this.dc));
    
  }

  //USEFUL INFORMATION: The ideal DConsole pause is 20ms, thus 50 pauses make a second, therefore, 50 cycles is one second
  //That being said, we can track time with these multiples of 50, 750 cycles is 15 seconds, and 3000 cycles is a minute
  //These cycles should increase at the end of each cycle of the while loop, and can be used to end the game upon a time limit

  //Play the game
  @Override
  public void play() {
    addEntites();
    
    //this is how you add points to a player
    //this.playerList.get(0).addToPoints(4);
    
    boolean game = true;
    int cycles = 0;
    int seconds = 8;
    while (game) { //these will be the loops that go on until game ends (refer to useful information for time limits)
      dc.clear();
      
      super.moveCharacters();
      super.refreshScreen();
      super.printTime(seconds, 300, 40);
      
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
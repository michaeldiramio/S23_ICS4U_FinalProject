import DLibX.*;
import java.awt.*;
import java.util.*;

public class TestGame extends Minigame {
  DConsole dc;
   
  //Constructor
  public TestGame(int id, DConsole dc, ArrayList<Player> playerList) {
    super(0, dc, playerList);
    this.dc = dc;
  }

  //create entities here
  private void addEntites(){
    super.entityList.add(new Entity(1, "joesef", 50, 50, 50, 50, true, Color.GREEN, this.dc));
    super.entityList.add(new Entity(1, "left barrier", -5.0, 225.0, 10.0, 600.0, true, Color.BLACK, this.dc));
    super.entityList.add(new Entity(2, "right barrier", 805.0, 225.0, 10.0, 600.0, true, Color.BLACK, this.dc));
    super.entityList.add(new Entity(3, "top barrier", 400.0, -5.0, 850.0, 10.0, true, Color.BLACK, this.dc));
    super.entityList.add(new Entity(4, "bottom barrier", 400.0, 555.0, 850.0, 10.0, true, Color.BLACK, this.dc));
    super.entityList.add(new Entity(5, "testing object 1", 500.0, 225.0, 100.0, 100.0, true, Color.RED, this.dc));
    super.entityList.add(new Entity(6, "testing object 2", 150.0, 100.0, 50.0, 70.0, false, Color.BLUE, this.dc));
    
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

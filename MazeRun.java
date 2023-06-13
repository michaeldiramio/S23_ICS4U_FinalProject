import DLibX.*;
import java.awt.*;
import java.util.*;

public class MazeRun extends Minigame {
  DConsole dc;
   
  //Constructor
  public MazeRun(int id, DConsole dc, ArrayList<Player> playerList) {
    super(0, dc, playerList);
    this.dc = dc;
    playerList.get(0).setX(50);
    playerList.get(0).setY(50);
    playerList.get(1).setX(50);
    playerList.get(1).setY(50);
  }

  //create entities here
  private void addEntites(){
    super.entityList.add(new Entity(0, "BorderL", 10, 250, 20, 600, true, Color.GREEN, this.dc));
    super.entityList.add(new Entity(0, "BorderR", 790, 250, 20, 600, true, Color.GREEN, this.dc));
    super.entityList.add(new Entity(0, "BorderT", 400, 540, 800, 20, true, Color.GREEN, this.dc));
    super.entityList.add(new Entity(0, "BorderB", 400, 10, 800, 20, true, Color.GREEN, this.dc));
    super.entityList.add(new Entity(0, "InsideTM", 350, 200, 700, 20, true, Color.GREEN, this.dc));
    super.entityList.add(new Entity(0, "InsideBM", 450, 400, 700, 20, true, Color.GREEN, this.dc));
    
  }

  

  //Play the game
  @Override
  public void play() {
    addEntites();
    
    //this is how you add points to a player
    //this.playerList.get(0).addToPoints(4);
    
    boolean game = true;
    int cycles = 0;
    int seconds = 1;
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

      if (seconds == -15) { //15 second are up, game ends
        game = false;
      }

      dc.redraw();
      dc.pause(20);
    }
  
  }
  


}

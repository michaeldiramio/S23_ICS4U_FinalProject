import DLibX.*;
import java.awt.*;
import java.util.*;

public class MazeRun extends Minigame {
  DConsole dc;
  private boolean game = true;
   
  //Constructor
  public MazeRun(int id, DConsole dc, ArrayList<Player> playerList) {
    super(id, dc, playerList);
    this.dc = dc;
  }

  //create entities here
  private void addEntites(){
    super.entityList.add(new Entity(0, "Finishline", 755, 505, 50, 200, true, Color.RED, this.dc));
    super.entityList.add(new Entity(0, "BorderL", 10, 250, 20, 600, true, Color.GREEN, this.dc));
    super.entityList.add(new Entity(0, "BorderR", 790, 250, 20, 600, true, Color.GREEN, this.dc));
    super.entityList.add(new Entity(0, "BorderT", 400, 540, 800, 20, true, Color.GREEN, this.dc));
    super.entityList.add(new Entity(0, "BorderB", 400, 10, 800, 20, true, Color.GREEN, this.dc));
    super.entityList.add(new Entity(0, "InsideTM", 350, 200, 700, 20, true, Color.GREEN, this.dc));
    super.entityList.add(new Entity(0, "InsideBM", 450, 400, 700, 20, true, Color.GREEN, this.dc));
    
  }

  @Override
  public void moveCharacters() {

    for(int i = 0; i < this.playerList.size(); i++) {
      boolean[] movementAllowance = {true, true, true, true};
      
      if(this.playerList.get(i) != null) {
        boolean[] tempControl = this.playerList.get(i).getControl().getPlayerKeysPressed(); // gets the player's currently pressed keys
  
        for(int j = 0; j < entityList.size(); j++) {
          boolean[] tempEntityBounds = this.entityList.get(j).getEntityBounds(this.playerList.get(i)); // gets entity bounds 
                                                                                   // (if a player has touched an entity)
  
          for(int k = 0; k < tempEntityBounds.length; k++) {
            // if a player touches and entity from a certain direction, the player will not be allowed to continue to move in said direction
            // ex. if a player moves right and hits the left side of an object, the player will not be allowed to move right anymore
            if(j == 0 && tempEntityBounds[k]) {
              playerList.get(i).addToPoints(1);
              game = false;
              
            } else if(tempEntityBounds[k]) {
              movementAllowance[k] = false;
            }
          }
        }

  
        // movement based on key input and if movement is allowed (from entity bounds)
        if(tempControl[0] && movementAllowance[0]) { //if that player's up key is pressed (w for player 1, t for player 2, etc.)
          playerList.get(i).moveY(-5);
        }
        if(tempControl[1] && movementAllowance[1]) { //left
          playerList.get(i).moveX(-5);
        }
        if(tempControl[2] && movementAllowance[2]) { //down
          playerList.get(i).moveY(5);
        }
        if(tempControl[3] && movementAllowance[3]) { //right
          playerList.get(i).moveX(5);
        }
      }
    }
  }

  

  //Play the game
  @Override
  public void play() {
    addEntites();
    game = true;
    playerList.get(0).setX(50);
    playerList.get(0).setY(50);
    playerList.get(1).setX(50);
    playerList.get(1).setY(50);
    
    //this is how you add points to a player
    //this.playerList.get(0).addToPoints(4);
    
    int cycles = 0;
    int seconds = 1;
    while (game) { //these will be the loops that go on until game ends (refer to useful information for time limits)
      dc.clear();
      
      this.moveCharacters();
      super.refreshScreen();
      super.printTime(seconds, 300, 40);
      
      cycles++;
      if (cycles >= 50) { //one second has passed
        seconds--;
        cycles = 0;
      }

      if (seconds == -10) { //15 second are up, game ends
        game = false;
      }

      

      dc.redraw();
      dc.pause(20);
    }
  
  }
  


}

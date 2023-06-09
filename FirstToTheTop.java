import DLibX.*;
import java.awt.*;
import java.util.*;

public class FirstToTheTop extends Minigame {

  //Constructor
  public FirstToTheTop(int id, DConsole dc, ArrayList<Player> playerList, ArrayList<Entity> entityList) {
    super(1, dc, playerList, entityList);
  }


  int spawnCoordX = 300;
  int spawnCoordY = 510;


  
  public void moveCharacters() {
    boolean[] movementAllowance = {true, true, true, true};
    
    for(int i = 0; i < this.playerList.size(); i++) {
      if(this.playerList.get(i) != null) {
        boolean[] tempControl = this.playerList.get(i).getControl().getPlayerKeysPressed(); // gets the player's currently pressed keys
  
        for(int j = 0; j < entityList.size(); j++) {
          boolean[] tempEntityBounds = this.entityList.get(j).getEntityBounds(this.playerList.get(i)); // gets entity bounds 
                                                                                   // (if a player has touched an entity)
          for(int k = 0; k < tempEntityBounds.length; k++) {
            
            // if a player hits an entity put them back to their spawnpoint
            if(tempEntityBounds[j]) {
              this.playerList.get(i).setX(getNewSpawnPointX(this.playerList.get(i).getID()));
            }
            
            // if a player touches and entity from a certain direction, the player will not be allowed to continue to move in said direction
            // ex. if a player moves right and hits the left side of an object, the player will not be allowed to move right anymore
            if(tempEntityBounds[k]) {
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

    boolean game = true;
    int cycles = 0;
    int seconds = 15;

    
    while (game) { //these will be the loops that go on until game ends (refer to useful information for time limits)
      dc.clear();

      cycles++;
      if (cycles >= 50) { //one second has passed
        seconds--;
        cycles = 0;
      }

      if (seconds == 0) { //15 second are up, game ends
        game = false;
      }











      
      super.moveCharacters();
      super.refreshScreen();
      super.printTime(seconds);

      dc.redraw();
      dc.pause(20);
    }
  
  }


  public int getNewSpawnPointX(int playerID) {
    return spawnCoordX + ((playerID - 1) * 35); // it is -1 because ids go from 1-4, whereas for muliplication it should go from 0-3 
  }
  


}

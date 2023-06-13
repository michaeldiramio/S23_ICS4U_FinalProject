import DLibX.*;
import java.awt.*;
import java.util.*;

public class FirstToTheTop extends Minigame {

  //Constructor
  public FirstToTheTop(int id, DConsole dc, ArrayList<Player> playerList, ArrayList<Entity> entityList) {
    super(1, dc, playerList, entityList);
  }


  double spawnCoordX = 347.5;
  double spawnCoordY = 510.0;


  public void moveCharacters() {
    boolean[] movementAllowance = {true, true, true, true};
    
    for(int i = 0; i < this.playerList.size(); i++) {
      if(this.playerList.get(i) != null) {
        boolean[] tempControl = this.playerList.get(i).getControl().getPlayerKeysPressed(); // gets the player's currently pressed keys
  
        for(int j = 0; j < entityList.size(); j++) {
          boolean[] tempEntityBounds = this.entityList.get(j).getEntityBounds(this.playerList.get(i)); // gets entity bounds (if a player has touched an entity)
          
          for(int k = 0; k < tempEntityBounds.length; k++) {
            
            // if a player touches and entity from a certain direction, the player will not be allowed to continue to move in said direction
            // ex. if a player moves right and hits the left side of an object, the player will not be allowed to move right anymore
            // also if a player hits an entity put them back to their spawnpoint
            if(tempEntityBounds[k]) {
              movementAllowance[k] = false;
              this.playerList.get(i).setX(getNewSpawnPointX(i + 1));
              this.playerList.get(i).setY(spawnCoordY);
            }
          }
        }
  
        // movement based on key input and if movement is allowed (from entity bounds)
        if(tempControl[0] && movementAllowance[0]) { //if that player's up key is pressed (w for player 1, t for player 2, etc.)
          playerList.get(i).moveY(-3.5);
        }
        if(tempControl[1] && movementAllowance[1]) { //left
          playerList.get(i).moveX(-3.5);
        }
        if(tempControl[2] && movementAllowance[2]) { //down
          playerList.get(i).moveY(3.5);
        }
        if(tempControl[3] && movementAllowance[3]) { //right
          playerList.get(i).moveX(3.5);
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

    // set every player to their original spawnpoint
    for(int i = 0; i < 4; i++) {
      if(this.playerList.get(i) != null) {
        this.playerList.get(i).setX(this.getNewSpawnPointX(i + 1));
        this.playerList.get(i).setY(spawnCoordY);
      }
    }

    
    while (game) { //these will be the loops that go on until game ends (refer to useful information for time limits)
      dc.clear();

      int mouseX = this.dc.getMouseXPosition();
      int mouseY = this.dc.getMouseYPosition();

      if(this.dc.isMouseButton(1)) {
        System.out.println("(" + mouseX + ", " + mouseY + ")");
        while(this.dc.isMouseButton(1)) {
          this.dc.pause(1);
        }
      }

      cycles++;
      if (cycles >= 50) { //one second has passed
        seconds--;
        cycles = 0;
      }

      if (seconds == 0) { //15 second are up, game ends
        game = false;
      }











      
      this.moveCharacters();
      super.refreshScreen();
      super.printTime(seconds);

      dc.redraw();
      dc.pause(20);
    }
  
  }


  public double getNewSpawnPointX(int playerID) {
    return spawnCoordX + ((playerID - 1) * 35.0); // it is -1 because ids go from 1-4, whereas for muliplication it should go from 0-3 
  }
  


}

import DLibX.*;
import java.awt.*;
import java.util.*;

public class Jump extends Minigame {

  //Instance Variables
  DConsole dc;
  private boolean game;
   
  //Constructor
  public Jump(int id, DConsole dc, ArrayList<Player> playerList) {
    super(0, dc, playerList);
    this.dc = dc;
  }

  //Entities
  private void addEntites(){
    
  }

  //Play the game
  @Override
  public void play() {
    addEntites();

    //Game Loop
    while (game) {
      dc.clear();

      //Move characters and refresh screen
      this.moveCharacters();
      super.refreshScreen();


      dc.redraw();
      dc.pause(20);
    }
    
  }


  //New character movement
  @Override
  public void moveCharacters() {
    boolean[] movementAllowance = {true, true, true, true};

    for(int i = 0; i < this.playerList.size(); i++) {
      if(this.playerList.get(i) != null) { //get current pressed keys
        boolean[] tempControl = this.playerList.get(i).getControl().getPlayerKeysPressed(); 
        
        for(int j = 0; j < entityList.size(); j++) {  // gets entity bounds
          boolean[] tempEntityBounds = this.entityList.get(j).getEntityBounds(this.playerList.get(i));
          
          //player touches an entity
          for(int k = 0; k < tempEntityBounds.length; k++) {
            //if a player touches and entity from a certain direction, the player will not be allowed to continue to move in said direction
            //ex. if a player moves right and hits the left side of an object, the player will not be allowed to move right anymore
            if(tempEntityBounds[k]) {
              movementAllowance[k] = false;
            }
          }
        }
  
        // movement based on key input and if movement is allowed (from entity bounds)
        if(tempControl[0] && movementAllowance[0]) { //Up
          playerList.get(i).moveY(-5);
        }
        if(tempControl[2] && movementAllowance[2]) { //down
          playerList.get(i).moveY(5);
        }
      }
    }
  }











  
}
import DLibX.*;
import java.awt.*;
import java.util.*;

public class Jump extends Minigame {

  //USEFUL INFORMATION: The ideal DConsole pause is 20ms, thus 50 pauses make a second, therefore, 50 cycles is one second
  //That being said, we can track time with these multiples of 50, 750 cycles is 15 seconds, and 3000 cycles is a minute
  //These cycles should increase at the end of each cycle of the while loop, and can be used to end the game upon a time limit

  //Instance Variables
  DConsole dc;
  private boolean game;
   
  //Constructor
  public Jump(int id, DConsole dc, ArrayList<Player> playerList) {
    super(id, dc, playerList);
    this.dc = dc;
  }

  //Entities
  private void addEntites(){
    super.entityList.add(new Entity(0, 400, 490, 800.0, 10.0, Color.BLACK, this.dc));
  }

  //Play the game
  @Override
  public void play() {
    addEntites();


    int cycles = 0;
    int seconds = 15;
    game = true;
    //Game Loop
    while (game) {
      dc.clear();

      //Move characters and refresh screen
      this.moveCharacters();
      super.refreshScreen();

      cycles++;
       //one second has passed
      if (cycles >= 50) {
        seconds--;
        cycles = 0;
      }

      //15 second are up, game ends
      if (seconds == 0) { 
        game = false;
      }

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

        int work;
  
        // movement based on key input and if movement is allowed (from entity bounds)
        if(tempControl[0] && movementAllowance[0] && !movementAllowance[2]) { //Up
          playerList.get(i).moveY(-75);
        }
        if(movementAllowance[2]) { //down
          playerList.get(i).moveY(2);
        }
      }
    }
  }











  
}
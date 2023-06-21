import DLibX.*;
import java.awt.*;
import java.util.*;

public class FirstToLeft extends Minigame {

  //USEFUL INFORMATION: The ideal DConsole pause is 20ms, thus 50 pauses make a second, therefore, 50 cycles is one second
  //That being said, we can track time with these multiples of 50, 750 cycles is 15 seconds, and 3000 cycles is a minute
  //These cycles should increase at the end of each cycle of the while loop, and can be used to end the game upon a time limit

  //Instance Variables
  DConsole dc;
  private boolean game;
  //To keep track of time
  private int cycles;
  private int seconds; 
  //Keep track of alive players
  private boolean[] alivePlayers = {true, true, true, true};
   
  //Constructor
  public FirstToLeft(int id, DConsole dc, ArrayList<Player> playerList) {
    super(id, dc, playerList, "Race 2");
    this.dc = dc;
  }

  //Entities
  private void addEntities(){
    super.entityList.add(new Entity(0, "finish line", 20.0, 225.0, 40.0, 800.0, true, Color.GREEN, this.dc));
  }

  //Set player spawn and size
  private void spawnPlayers() {
    playerList.get(0).setPOS(770, 150);
    playerList.get(1).setPOS(770, 200);
    playerList.get(2).setPOS(770, 250);
    playerList.get(3).setPOS(770, 300);
    for(int i = 0; i < alivePlayers.length; i++) { //set players to alive
      alivePlayers[i] = true;
    }
  }

  //Play the game
  @Override
  public void play() {
    addEntities(); //load entities
    spawnPlayers(); //spawn players

    //Variables
    cycles = 0;
    seconds = 8;
    game = true;
    
    //Game Loop
    while (game) {
      dc.clear();

      //Move characters and refresh screen
      this.dc.setPaint(new Color(0,0,0));
      this.moveCharacters();
      super.refreshScreen();
      this.dc.setFont(new Font("Comic Sans", Font.PLAIN, 18));
      super.printTime(seconds, 760, 10);

      //Print Jump
      this.dc.setFont(new Font("Comic Sans", Font.BOLD, 200));
      this.dc.drawString("←", 400, 10);
        
      cycles++;
       //one second has passed
      if (cycles >= 50) {
        seconds--;
        cycles = 0;
      }

      //time is up or all players win, game ends
      if (seconds == 0 || (!alivePlayers[0] && !alivePlayers[1] && !alivePlayers[2] && !alivePlayers[3])) { 
        game = false;
        this.entityList.clear(); //clear entities so they spawn in their original position if replayed
      }

      dc.redraw();
      dc.pause(20);
    }
    
  }

  //Move Characters (only to the right)
  @Override
  public void moveCharacters() {
    for(int i = 0; i < this.playerList.size(); i++) {
      boolean[] movementAllowance = {true, true, true, true};
      
      if(this.playerList.get(i) != null && this.alivePlayers[i]) { //get current pressed keys
        boolean[] tempControl = this.playerList.get(i).getControl().getPlayerKeysPressed(); 
        
        for(int j = 0; j < entityList.size(); j++) {  // gets entity bounds
          boolean[] tempEntityBounds = this.entityList.get(j).getEntityBounds(this.playerList.get(i));
          
          //player touches an entity
          for(int k = 0; k < tempEntityBounds.length; k++) {
            //if a player touches and entity from a certain direction, the player will not be allowed to continue to move in said direction
            //ex. if a player moves right and hits the left side of an object, the player will not be allowed to move right anymore
            if (j == 0 && tempEntityBounds[k]) { //a player hits the red block
              this.playerList.get(i).addToPoints(seconds); //their points depend on how long they lived
              this.playerList.get(i).setPOS(playerList.get(i).getX(), 700); //move them off the board
              this.alivePlayers[i] = false; 
            }
          }
        }

        // movement based on key input and if movement is allowed (from entity bounds)
        if(tempControl[1] && movementAllowance[1]) { //Left
          this.playerList.get(i).moveX(-5);
        }
      }
    }
  }
  
 









  
}
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
  //To keep track of time
  private int cycles;
  private int seconds; 
  //Keep track of alive players
  private boolean[] alivePlayers = {true, true, true, true};
  //Speed
  private int xChange;
   
  //Constructor
  public Jump(int id, DConsole dc, ArrayList<Player> playerList) {
    super(id, dc, playerList, "Jump");
    this.dc = dc;
  }

  //Entities
  private void addEntities(){
    super.entityList.add(new Entity(0, 400, 550, 800, 70, Color.BLACK, this.dc)); //floor
    super.entityList.add(new Entity(1, 400, 100, 800, 400, Color.BLACK, this.dc)); //cieling
    super.entityList.add(new Entity(2, 750, 500, 10, 10, Color.RED, this.dc)); //red block
  }

  //Set player spawn and size
  private void spawnPlayers() {
    playerList.get(0).setPOS(150, 480);
    playerList.get(1).setPOS(300, 480);
    playerList.get(2).setPOS(450, 480);
    playerList.get(3).setPOS(600, 480);
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
    seconds = 25;
    xChange = -5;
    game = true;
    
    //Game Loop
    while (game) {
      dc.clear();

      //Move characters and refresh screen
      this.moveBlock();
      this.moveCharacters();
      super.refreshScreen();

      //Print Jump
      this.dc.setPaint(new Color(255,255,255));
      this.dc.setFont(new Font("Comic Sans", Font.BOLD, 200));
      this.dc.drawString("JUMP!", 400, 100);
      this.dc.setPaint(new Color(0,0,0));

      cycles++;
       //one second has passed
      if (cycles >= 50) {
        seconds--;
        cycles = 0;
      }

      //time is up or all players die, game ends
      if (seconds == 0 || (!alivePlayers[0] && !alivePlayers[1] && !alivePlayers[2] && !alivePlayers[3])) { 
        game = false;
        this.entityList.clear(); //clear entities so they spawn in their original position if replayed
      }

      dc.redraw();
      dc.pause(20);
    }
    
  }

  //Move Characters (only up and down)
  @Override
  public void moveCharacters() {
    for(int i = 0; i < this.playerList.size(); i++) {
      boolean[] movementAllowance = {true, true, true, true};
      
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
            if (j == 2 && tempEntityBounds[k]) { //a player hits the red block
              playerList.get(i).addToPoints(25 - seconds); //their points depend on how long they lived
              playerList.get(i).setPOS(playerList.get(i).getX(), 700); //move them off the board
              alivePlayers[i] = false; 
            }
          }
        }

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
  
  //Move the block entity
  public void moveBlock() {

    //check bounds
    if (this.entityList.get(2).getX() >= 800) { //right bound
      xChange*=-1;
    } else if (this.entityList.get(2).getX() <= 0) { //left bound
      xChange*=-1;
    }

    //move 
    this.entityList.get(2).move(xChange, 0);
    
  }









  
}
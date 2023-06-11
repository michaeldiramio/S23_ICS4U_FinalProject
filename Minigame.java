import DLibX.*;
import java.awt.Color;
import java.util.*;

public abstract class Minigame {

  //Instance variables
  public int id;
  public DConsole dc; //Since the extensions need to access the DConsole, it needs to be public here
  public ArrayList<Player> playerList;
  public ArrayList<Entity> entityList;

  //Constructor
  public Minigame(int id, DConsole dc, ArrayList<Player> players, ArrayList<Entity> entities) {
    this.id = id;
    this.dc = dc;
    this.playerList = players;
    this.entityList = entities;
  }

  //get ID
  public int getID() {
    return this.id;
  }

  //**NOTE:** WHEN MAKING A GAME WITH DIFFERENT MOVEMENT, USE THE @OVERRIDE METHOD IN YOUR MINIGAME TO REMAKE THIS METHOD AS YOU PLEASE
  //Move characters 
  public void moveCharacters() {
    boolean[] movementAllowance = {true, true, true, true};

    for(int i = 0; i < this.playerList.size(); i++) {
      if(this.playerList.get(i) != null) {
        boolean[] tempControl = this.playerList.get(i).getControl().getPlayerKeysPressed(); // gets the player's currently pressed keys
  
        for(int j = 0; j < entityList.size(); j++) {
          boolean[] tempEntityBounds = this.entityList.get(j).getEntityBounds(this.playerList.get(i)); // gets entity bounds 
                                                                                   // (if a player has touched an entity)
  
          for(int k = 0; k < tempEntityBounds.length; k++) {
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

  public void refreshScreen() {
    for(int i = 0; i < entityList.size(); i++) {
      this.entityList.get(i).draw();
    }
    for(int i = 0; i < playerList.size(); i++) {
      this.playerList.get(i).draw();
    }
  }

  //Award points on victory
  public void rewardPoints(Player p, int amount) {
    p.addToScore(amount);
  }

  //Play game
  public abstract void play(); //note to everyone else: use @override in your minigame, and code the play method 

  //Print time
  public void printTime(int s, int x, int y) {
    dc.drawString("Time: " + s, x, y);
  }

  public void setPlayers(ArrayList<Player> playerList) {
    this.playerList = playerList;
  }
}
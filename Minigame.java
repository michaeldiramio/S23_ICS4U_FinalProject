import DLibX.*;
import java.awt.Color;
import java.util.*;

public abstract class Minigame {

  //Instance variables
  private int id;
  public DConsole dc; //Since the extensions need to access the DConsole, it needs to be public here
  private ArrayList<Player> playerList;
  private ArrayList<Entity> entityList = new ArrayList<Entity>();
  
  

  //Constructor
  public Minigame(int id, DConsole dc, ArrayList<Player> players) {
    this.id = id;
    this.dc = dc;
    this.playerList = players;
    entityList.add(new Entity(1, "left barrier", -5.0, 225.0, 10.0, 600.0, true, Color.BLACK, dc));
  }

  //add entites
  public void addEntity(){
    entityList.add(new Entity(1, "left barrier", -5.0, 225.0, 10.0, 600.0, true, Color.BLACK, dc));
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
      boolean[] tempControl = this.playerList.get(i).getControl().getPlayerKeysPressed();

      for(int j = 0; j < entityList.size(); j++) {
        boolean[] tempEntityBounds = this.entityList.get(j).getEntityBounds(this.playerList.get(i));

        for(int k = 0; k < tempEntityBounds.length; k++) {
          if(tempEntityBounds[k]) {
            movementAllowance[k] = false;
          }
        }
      }

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

  //This is just for testing 
  public void printTime(int s) {
    dc.drawString("Time: " + s, 300, 40);
  }
}
import DLibX.*;
import java.awt.Color;
import java.util.*;

public abstract class Minigame {

  //Instance variables
  private int id;
  public DConsole dc;
  private ArrayList<Player> playerList;
  private ArrayList<Control> controlList;

  //Constructor
  public Minigame(int id, DConsole dc, ArrayList<Player> players, ArrayList<Control> controls) {
    this.id = id;
    this.dc = dc;

    this.playerList = players;
    this.controlList = controls;
  }

  //get ID
  public int getID() {
    return this.id;
  }

  //**NOTE:** WHEN MAKING A GAME WITH DIFFERENT MOVEMENT, USE THE @OVERRIDE METHOD IN YOUR MINIGAME TO REMAKE THIS METHOD AS YOU PLEASE
  //Move characters 
  public void moveCharacters() {
    for(int i = 0; i < playerList.size(); i++) { //check for each player to see if any of their designated keys are pressed
      boolean[] tempControl = controlList.get(i).getPlayerKeysPressed(); //get all the current keys pressed
      if(tempControl[0] == true && playerList.get(i).getY() >= 10) { //if that player's up key is pressed (w for player 1, t for player 2, etc.)
        playerList.get(i).moveY(-5);
      } else if(tempControl[1] == true && playerList.get(i).getX() >= 10) { //left
        playerList.get(i).moveX(-5);
      } else if(tempControl[2] == true && playerList.get(i).getY() <= 590) { //down
        playerList.get(i).moveY(5);
      } else if(tempControl[3] == true && playerList.get(i).getX() <= 590) { //right
        playerList.get(i).moveX(5);
      } 
      playerList.get(i).draw(); //draw the players with their new position
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
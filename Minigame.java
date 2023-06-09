import DLibX.*;
import java.awt.Color;
import java.util.*;

public abstract class Minigame {

  //Instance variables
  public int id;
  public DConsole dc; //Since the extensions need to access the DConsole, it needs to be public here
  public ArrayList<Player> playerList;
  public ArrayList<Entity> entityList = new ArrayList<Entity>();
  public String name;

  //Constructor
  public Minigame(int id, DConsole dc, ArrayList<Player> players, ArrayList<Entity> entities, String name) {
    this.id = id;
    this.dc = dc;
    this.playerList = players;
    this.entityList = entities;
    this.name = name;
  }

  //No Entities
   public Minigame(int id, DConsole dc, ArrayList<Player> players, String name) {
    this.id = id;
    this.dc = dc;
    this.playerList = players;
    this.name = name;
  }

  //get ID
  public int getID() {
    return this.id;
  }

  //get name
  public String getName() {
    return this.name;
  }

  //**NOTE:** WHEN MAKING A GAME WITH DIFFERENT MOVEMENT, USE THE @OVERRIDE METHOD IN YOUR MINIGAME TO REMAKE THIS METHOD AS YOU PLEASE
  //Move characters 
  public void moveCharacters() {

    for(int i = 0; i < this.playerList.size(); i++) {
      boolean[] movementAllowance = {true, true, true, true};
      
      if(this.playerList.get(i) != null) {
        boolean[] tempControl = this.playerList.get(i).getControl().getPlayerKeysPressed(); // gets the player's currently pressed keys
  
        for(int j = 0; j < this.entityList.size(); j++) {
          boolean[] tempEntityBounds = this.entityList.get(j).getEntityBounds(this.playerList.get(i)); // gets entity bounds (if a player has touched an entity)
  
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
          this.playerList.get(i).moveY(-5);
        }
        if(tempControl[1] && movementAllowance[1]) { //left
          this.playerList.get(i).moveX(-5);
        }
        if(tempControl[2] && movementAllowance[2]) { //down
          this.playerList.get(i).moveY(5);
        }
        if(tempControl[3] && movementAllowance[3]) { //right
          this.playerList.get(i).moveX(5);
        }
      }
    }
  }

  //Redraw everything
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

  //Ends game and rewards points
  public void endGame(){
    //makes temporary list of players
    Player[] tempPlayerList = new Player[4];

    //fills temp list with players
    for(int i = 0; i < playerList.size(); i++){
      try {
        tempPlayerList[i] = playerList.get(i);
      } catch(Exception e) {}
    }

    //bubble sorts players in tempPlayerArray by points
    for (int i = 0; i < this.playerList.size(); i++) {
        for (int j = i + 1; j < this.playerList.size(); j++) {
            Player temp;
            if (tempPlayerList[i].getPoints() < tempPlayerList[j].getPoints()) {
             
                // Swapping
                temp = tempPlayerList[i];
                tempPlayerList[i] = tempPlayerList[j];
                tempPlayerList[j] = temp;
            }
        }
    }
    

    //rewards score to players based on where they are in tempPlayerArray
    for(int i = 0; i < tempPlayerList.length; i++){
      if(tempPlayerList[i] != null) {

        //codes for ties
        if(i == 0){
          this.playerList.get(tempPlayerList[i].getID()-1).addToScore(15);
        } else if(i == 1){
          if(tempPlayerList[0].getPoints() != tempPlayerList[1].getPoints()){
            this.playerList.get(tempPlayerList[i].getID()-1).addToScore(12);
          } else this.playerList.get(tempPlayerList[i].getID()-1).addToScore(15);
        } else if(i == 2){
          if(tempPlayerList[1].getPoints() != tempPlayerList[2].getPoints()){
            this.playerList.get(tempPlayerList[i].getID()-1).addToScore(8);
          } else this.playerList.get(tempPlayerList[i].getID()-1).addToScore(12);
        } else if(i == 3){
          if(tempPlayerList[2].getPoints() != tempPlayerList[3].getPoints()){
            this.playerList.get(tempPlayerList[i].getID()-1).addToScore(4);
          } else this.playerList.get(tempPlayerList[i].getID()-1).addToScore(8);
        }
        

        //old code no ties
        /*
        if(i == 0){
          playerList.get(tempPlayerList[i].getID()-1).addToScore(15);
        }else if(i == 1){
          playerList.get(tempPlayerList[i].getID()-1).addToScore(12);
        }else if(i == 2){
          playerList.get(tempPlayerList[i].getID()-1).addToScore(8);
        }else if(i == 3){
          playerList.get(tempPlayerList[i].getID()-1).addToScore(4);
        }
        */
        
      }
    }

    //reset all player points for next game
    for (int i = 0; i < tempPlayerList.length; i++) {
      this.playerList.get(i).resetPoints();
    }
    
  }

  public double getNewSpawnPointX(int playerID, double spawnCoord) {
    return spawnCoord + ((playerID - 1) * 35.0); // it is -1 because ids go from 1-4, whereas for muliplication it should go from 0-3 
  }

  //Reset player points
  public void resetPlayerPoints() {
    for (int i = 0; i < this.playerList.size(); i++) {
      this.playerList.get(i).resetPoints();
    }
  }


  //Print time
  public void printTime(int s, int x, int y) {
    this.dc.drawString("Time: " + s, x, y);
  }

  //Set players
  public void setPlayers(ArrayList<Player> playerList) {
    this.playerList = playerList;

  }
}
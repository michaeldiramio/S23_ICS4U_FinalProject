import DLibX.*;
import java.awt.*;
import java.util.*;

public class FindColor extends Minigame {

  //Instance Variables
  DConsole dc;
  private boolean game;
  //To keep track of time
  private int cycles;
  private int seconds; 
  //Color array
  private ArrayList<Color> colors = new ArrayList<Color>();
  //Player alive array
  private boolean[] alivePlayers = {true, true, true, true};

  //Constructor
  public FindColor(int id, DConsole dc, ArrayList<Player> playerList) {
    super(id, dc, playerList);
    this.dc = dc;
  }

  //Spawn Players
  private void spawnPlayers() {
    playerList.get(0).setPOS(300, 180);
    playerList.get(1).setPOS(500, 180);
    playerList.get(2).setPOS(300, 360);
    playerList.get(3).setPOS(500, 360);

    //get each players color
    for (int i = 0; i < playerList.size(); i++) {
      colors.add(playerList.get(i).getColor()); 
    }
    
    //set players to alive
    for(int i = 0; i < alivePlayers.length; i++) { 
      alivePlayers[i] = true;
    }
  }

  //Entities
  private void addEntities(){
    super.entityList.add(new Entity(0, 50, 50, 100, 100, colors.get(0), this.dc)); //top left
    super.entityList.add(new Entity(1, 750, 50, 100, 100, colors.get(1), this.dc)); //top right
    super.entityList.add(new Entity(2, 50, 500, 100, 100, colors.get(2), this.dc)); //bottom left
    super.entityList.add(new Entity(3, 750, 500, 100, 100, colors.get(3), this.dc)); //bottom right

    //barriers
    super.entityList.add(new Entity(4, "left barrier", -5.0, 225.0, 10.0, 600.0, true, Color.BLACK, this.dc));
    super.entityList.add(new Entity(5, "right barrier", 805.0, 225.0, 10.0, 600.0, true, Color.BLACK, this.dc));
    super.entityList.add(new Entity(6, "top barrier", 400.0, -5.0, 850.0, 10.0, true, Color.BLACK, this.dc));
    super.entityList.add(new Entity(7, "bottom barrier", 400.0, 555.0, 850.0, 10.0, true, Color.BLACK, this.dc));
  }

  //Play the game
  @Override
  public void play() {
    spawnPlayers(); //spawn players
    Collections.shuffle(colors); //shuffle colors
    addEntities(); //load entities
    
    //Variables
    cycles = 0;
    seconds = 10;
    game = true;
    
    //Game Loop
    while (game) {
      dc.clear();

      //Move characters and refresh screen
      this.moveCharacters();
      super.refreshScreen();
      dc.setFont(new Font("Comic Sans", Font.BOLD, 36));
      super.printTime(seconds, 400, 50);

      cycles++;
       //one second has passed
      if (cycles >= 50) {
        seconds--;
        cycles = 0;
      }

      //time is up or all players win, game ends
      if (seconds == 0 || (!alivePlayers[0] && !alivePlayers[1] && !alivePlayers[2] && !alivePlayers[3])) { 
        game = false;
        this.colors.clear(); //refresh colorlist
        this.entityList.clear(); //refresh colorlist
      }

      dc.redraw();
      dc.pause(20);
    }
    
  }

  //Move characters
  @Override
  public void moveCharacters() {

    for(int i = 0; i < this.playerList.size(); i++) {
      boolean[] movementAllowance = {true, true, true, true};
      
      if(this.playerList.get(i) != null) {
        boolean[] tempControl = this.playerList.get(i).getControl().getPlayerKeysPressed(); // gets the player's currently pressed keys
  
        for(int j = 0; j < entityList.size(); j++) {
          boolean[] tempEntityBounds = this.entityList.get(j).getEntityBounds(this.playerList.get(i)); // gets entity bounds (if a player has touched an entity)
  
          for(int k = 0; k < tempEntityBounds.length; k++) {
            // if a player touches and entity from a certain direction, the player will not be allowed to continue to move in said direction
            // ex. if a player moves right and hits the left side of an object, the player will not be allowed to move right anymore
            if(tempEntityBounds[k]) {
              movementAllowance[k] = false;
              if (this.entityList.get(j).getColor() == this.playerList.get(i).getColor()) { //the entity they touched is of the same color (they win)
                this.playerList.get(i).addToPoints(seconds);
                this.playerList.get(i).setPOS(500,700);
                this.alivePlayers[i] = false;
              }
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

  









  
}

import DLibX.*;
import java.awt.*;
import java.util.*;

public class FirstToTheTop extends Minigame {

  private int gifCounter;
  private boolean[] reachedEnd;
  private boolean game;
  private int firstPlayer;

  //Constructor
  public FirstToTheTop(int id, DConsole dc, ArrayList<Player> playerList) {
    super(1, dc, playerList, "Maze 1");
    this.gifCounter = 0;
    this.reachedEnd = new boolean[2];
    this.game = false;
    this.firstPlayer = -1;
  }

  public void addEntities() {
    super.entityList.add(new Entity(1, "left barrier", -5.0, 225.0, 10.0, 600.0, true, Color.BLACK, this.dc));
    super.entityList.add(new Entity(2, "right barrier", 805.0, 225.0, 10.0, 600.0, true, Color.BLACK, this.dc));
    super.entityList.add(new Entity(3, "top barrier", 400.0, -5.0, 850.0, 10.0, true, Color.BLACK, this.dc));
    super.entityList.add(new Entity(4, "bottom barrier", 400.0, 555.0, 850.0, 10.0, true, Color.BLACK, this.dc));
    super.entityList.add(new Entity(5, "maze block 1", 125.0, 525.0, 310.0, 50.0, true, Color.BLACK, this.dc));
    super.entityList.add(new Entity(6, "maze block 2", 675.0, 500.0, 310.0, 125.0, true, Color.BLACK, this.dc));
    super.entityList.add(new Entity(7, "maze block 3", 500.0, 450.0, 700.0, 25.0, true, Color.BLACK, this.dc));
    super.entityList.add(new Entity(8, "maze block 4", 100.0, 455.0, 125.0, 35.0, true, Color.BLACK, this.dc));
    super.entityList.add(new Entity(9, "maze block 5", 400.0, 405.0, 200.0, 70.0, true, Color.BLACK, this.dc));
    super.entityList.add(new Entity(10, "maze block 6", 120.0, 365.0, 265.0, 75.0, true, Color.BLACK, this.dc));
    super.entityList.add(new Entity(11, "maze block 7", 500.0, 332.5, 600.0, 10.0, true, Color.BLACK, this.dc));
    super.entityList.add(new Entity(12, "maze block 8", 500.0, 332.5, 600.0, 10.0, true, Color.BLACK, this.dc));
    super.entityList.add(new Entity(13, "maze block 9", 665.0, 355.0, 150.0, 50.0, true, Color.BLACK, this.dc));
    super.entityList.add(new Entity(14, "maze block 10", 510.0, 415.0, 65.0, 65.0, true, Color.BLACK, this.dc));
    super.entityList.add(new Entity(15, "maze block 11", 775.0, 390.0, 70.0, 105.0, true, Color.BLACK, this.dc));
    super.entityList.add(new Entity(16, "maze end", 700.0, 410.0, 30.0, 30.0, true, Color.YELLOW, this.dc));
  }


  double spawnCoordX = 400.0;
  double spawnCoordY = 510.0;

  @Override
  public void moveCharacters() {
    for(int i = 0; i < this.playerList.size(); i++) {
      boolean[] movementAllowance = {true, true, true, true};
      if(this.playerList.get(i) != null) {
        boolean[] tempControl = this.playerList.get(i).getControl().getPlayerKeysPressed(); // gets the player's currently pressed keys
  
        for(int j = 0; j < this.entityList.size(); j++) {
          boolean[] tempEntityBounds = this.entityList.get(j).getEntityBounds(this.playerList.get(i)); // gets entity bounds (if a player has touched an entity)
          
          for(int k = 0; k < tempEntityBounds.length && !reachedEnd[i]; k++) {
            
            // if they hit the end of the maze
            if(j == 15 && tempEntityBounds[k]) {
              reachedEnd[i] = true;

              // checks if you are the first player to beat the maze
              if(this.firstPlayer == -1) {
                this.firstPlayer = i;
              } 
              
              
              
              // if a player touches and entity from a certain direction, the player will not be allowed to continue to move in said direction
            // ex. if a player moves right and hits the left side of an object, the player will not be allowed to move right anymore
            // also if a player hits an entity put them back to their spawnpoint
            } else if(tempEntityBounds[k]) {
              movementAllowance[k] = false;
              this.playerList.get(i).setX(spawnCoordX);
              this.playerList.get(i).setY(spawnCoordY);
            }
          }
        }

        if(!reachedEnd[i]) {
          // movement based on key input and if movement is allowed (from entity bounds)
          if(tempControl[0] && movementAllowance[0]) { //if that player's up key is pressed (w for player 1, t for player 2, etc.)
            this.playerList.get(i).moveY(-1.5);
          }
          if(tempControl[1] && movementAllowance[1]) { //left
            this.playerList.get(i).moveX(-2);
          }
          if(tempControl[2] && movementAllowance[2]) { //down
            this.playerList.get(i).moveY(1.5);
          }
          if(tempControl[3] && movementAllowance[3]) { //right
            this.playerList.get(i).moveX(2);
          }
        }
      }
    }
  }

  
  //Play the game
  @Override
  public void play() {

    this.addEntities();

    this.game = true;
    int cycles = 0;
    int seconds = 35;

    // set every player to their original spawnpoint
    for(int i = 0; i < this.playerList.size(); i++) {
      if(this.playerList.get(i) != null) {
        this.playerList.get(i).setX(spawnCoordX);
        this.playerList.get(i).setY(spawnCoordY);
      }
    }

    
    while(this.game) { //these will be the loops that go on until game ends (refer to useful information for time limits)
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
      if (cycles >= 50) {
        seconds--;
        cycles = 0;
      }

      // if the time limit is reached or both players reached the end
      if(seconds == 0 || Collections.frequency(Arrays.asList(this.reachedEnd), true) == 2) { 
        this.game = false;
      }
      
      this.moveCharacters();
      this.refreshScreen();
      super.printTime(seconds, 400, 15);

      this.dc.redraw();
      this.dc.pause(20);
    }

    // if you are the first to reach the end you get two points, if you are second you get one, and if you don't reach the end you don't get any
    for(int i = 0; i < this.reachedEnd.length; i++) {
      if(this.reachedEnd[i] == true) {
        if(this.firstPlayer == i) {
          this.playerList.get(i).addToPoints(2);
        } else {
          this.playerList.get(i).addToPoints(1);
        }
      }
    }

    this.entityList.clear();
    this.firstPlayer = -1;
    this.reachedEnd[0] = false;
    this.reachedEnd[1] = false;
    
  
  }

  @Override
  public void refreshScreen() {
    for(int i = 0; i < this.entityList.size(); i++) {
      this.entityList.get(i).draw();
    }
    for(int i = 0; i < this.playerList.size(); i++) {
      this.playerList.get(i).draw();
    }


    this.gifCounter++;

    if(this.gifCounter < 15) {
      this.dc.drawImage("Images/FTTT_Gif/yep1.jpg", 400, 175);
    } else if(this.gifCounter < 30) {
      this.dc.drawImage("Images/FTTT_Gif/yep2.jpg", 400, 175);
    } else if(this.gifCounter < 45) {
      this.dc.drawImage("Images/FTTT_Gif/yep3.jpg", 400, 175);
    } else if(this.gifCounter < 60) {
      this.dc.drawImage("Images/FTTT_Gif/yep4.jpg", 400, 175);
    } else if(this.gifCounter == 60) {
      this.dc.drawImage("Images/FTTT_Gif/yep1.jpg", 400, 175);
      this.gifCounter = 0;
    }
  }
}

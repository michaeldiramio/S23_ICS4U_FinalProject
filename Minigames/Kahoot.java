import DLibX.*;
import java.awt.Color;
import java.util.*;

public class Kahoot extends Minigame {

  private ArrayList<Color> colors = new ArrayList<Color>(); 
  private Random r = new Random();
  boolean game;
  int cycles;
  int seconds;

  private Color rightAnswer;

public Kahoot(int id, DConsole dc, ArrayList<Player> players) {
    super(id, dc, players, "Kahoot");
  }

  //Spawn Players
  private void spawnPlayers() {
    playerList.get(0).setPOS(300, 180);
    playerList.get(1).setPOS(500, 180);
    playerList.get(2).setPOS(300, 360);
    playerList.get(3).setPOS(500, 360);

    //add colora
    colors.add(Color.RED);  
    colors.add(Color.BLUE);
    colors.add(Color.GREEN);
    colors.add(Color.YELLOW);
  }

//create entities here
  private void addEntites(){
    super.entityList.add(new Entity(2, "Answer 1", 200, 138, 150, 150, true, colors.get(0), this.dc));
    super.entityList.add(new Entity(3, "Answer 2", 200, 413, 150, 150, true, colors.get(1), this.dc));
    super.entityList.add(new Entity(4, "Answer 3", 600, 138,  150, 150, true, colors.get(2), this.dc));
    super.entityList.add(new Entity(5, "Answer 4", 600, 413, 150, 150, true, colors.get(3), this.dc));
    super.entityList.add(new Entity(6, "left barrier", -5.0, 225.0, 10.0, 600.0, true, Color.BLACK, this.dc));
    super.entityList.add(new Entity(7, "right barrier", 805.0, 225.0, 10.0, 600.0, true, Color.BLACK, this.dc));
    super.entityList.add(new Entity(8, "top barrier", 400.0, -5.0, 850.0, 10.0, true, Color.BLACK, this.dc));
    super.entityList.add(new Entity(9, "bottom barrier", 400.0, 555.0, 850.0, 10.0, true, Color.BLACK, this.dc));
  }

  //USEFUL INFORMATION: The ideal DConsole pause is 20ms, thus 50 pauses make a second, therefore, 50 cycles is one second
  //That being said, we can track time with these multiples of 50, 750 cycles is 15 seconds, and 3000 cycles is a minute
  //These cycles should increase at the end of each cycle of the while loop, and can be used to end the game upon a time limit

  //Play the game
  @Override
  public void play() {
    this.spawnPlayers();
    this.addEntites();

    this.rightAnswer = this.colors.get(r.nextInt(this.colors.size()));

   
    
    //this is how you add points to a player
    //this.playerList.get(0).addToPoints(4);
    this.cycles = 0;
    this.seconds = 10;
    this.game = true;

    while (this.game) { //these will be the loops that go on until game ends (refer to useful information for time limits)
      this.dc.clear();
      
      this.moveCharacters();
      super.refreshScreen();
      super.printTime(this.seconds, 300, 40);

      this.dc.setPaint(rightAnswer);
      this.dc.fillRect(400, 275, 50, 50);
      
      this.cycles++;
      if (this.cycles >= 50) { //one second has passed
        this.seconds--;
        this.cycles = 0;
      }

      if (this.seconds == 0) { //15 second are up, game ends
        this.game = false;
      }

      this.dc.redraw();
      this.dc.pause(20);
    }
  
  }

  //Move characters
  @Override
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
              if (this.entityList.get(j).getColor() == rightAnswer) { //the entity they touched is of the same color (they win)
                this.playerList.get(i).addToPoints(seconds);
                this.playerList.get(i).setPOS(500,700);
              }
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
  




  
}
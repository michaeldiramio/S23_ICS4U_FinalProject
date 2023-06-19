import DLibX.*;
import java.awt.Color;
import java.util.*;

public class Kahoot extends Minigame {

  private int[] playerAnswer;
public Kahoot(int id, DConsole dc, ArrayList<Player> players) {
    super(0, dc, players);
    this.playerAnswer = new int[4];
    for(int i = 0; i < playerAnswer.length; i++) {
      this.playerAnswer[i] = -1;
    }
  }

//create entities here
  private void addEntites(){
    //super.entityList.add(new Entity(id, "name", xPos, yPos, Width, Height, Collision, Color, this.dc));
    super.entityList.add(new Entity(0, "Answer 1", 200, 138, 267, 150, true, Color.RED, this.dc));
    super.entityList.add(new Entity(1, "Answer 2", 200, 413, 267, 150, true, Color.GREEN, this.dc));
    super.entityList.add(new Entity(2, "Answer 3", 600, 138, 267, 150, true, Color.BLUE, this.dc));
    super.entityList.add(new Entity(3, "Answer 4", 600, 413, 267, 150, true, Color.YELLOW, this.dc));
    
  }

  @Override
  public void moveCharacters() {
    boolean[] movementAllowance = {true, true, true, true};

    for(int i = 0; i < playerAnswer.length; i++) {
      this.playerAnswer[i] = -1;
    }

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
              playerAnswer[i] = j;
              System.out.print(playerAnswer[i]);
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
        
        System.out.println();
      }
    }
  }





  

  //USEFUL INFORMATION: The ideal DConsole pause is 20ms, thus 50 pauses make a second, therefore, 50 cycles is one second
  //That being said, we can track time with these multiples of 50, 750 cycles is 15 seconds, and 3000 cycles is a minute
  //These cycles should increase at the end of each cycle of the while loop, and can be used to end the game upon a time limit

  //Play the game
  @Override
  public void play() {
    addEntites();
    playerList.get(0).setX(400);
    playerList.get(0).setY(225);
    playerList.get(1).setX(400);
    playerList.get(1).setY(225);
    playerList.get(2).setX(400);
    playerList.get(2).setY(225);
    playerList.get(3).setX(400);
    playerList.get(3).setY(225);



    
    
    //this is how you add points to a player
    //this.playerList.get(0).addToPoints(4);
    
    boolean game = true;
    int cycles = 0;
    int seconds = 8;
    while (game) { //these will be the loops that go on until game ends (refer to useful information for time limits)
      dc.clear();
      
      this.moveCharacters();
      super.refreshScreen();
      super.printTime(seconds, 300, 40);
      
      cycles++;
      if (cycles >= 50) { //one second has passed
        seconds--;
        cycles = 0;
      }

      if (seconds == 0) { //15 second are up, game ends
        game = false;
      }

      dc.redraw();
      dc.pause(20);
    }
  
  }
  




  
}
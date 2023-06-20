import DLibX.*;
import java.awt.*;
import java.util.*;

public class Pong extends Minigame {

  //Constructor
  public Pong (int id, DConsole dc, ArrayList<Player> playerList) {
    super(id, dc, playerList);
  }
  
  //Play the game
  @Override
  public void play() {
    boolean game = true;
    
    //ball
    int ballSpeed = 4;
    int bx = 400;
    int by = 275;
    int bxc = ballSpeed;
    int byc = ballSpeed;
    Random rand = new Random();
    if(rand.nextInt(2) == 1) { //random start direction
      bxc*=-1;
    }

    playerList.get(0).setSize(20, 100);
    playerList.get(1).setSize(20, 100);

    playerList.get(0).setX(50);
    playerList.get(1).setX(750);
    playerList.get(0).setY(275);
    playerList.get(1).setY(275);

    while (game) { //these will be the loops that go on until game ends (refer to useful information for time limits)
      dc.clear();

      moveCharacters();
      super.refreshScreen();

      //collision of ball with up/down edges
      if(by <= 10 || by >= 540) { 
        byc*=-1;
      }

      //collision of ball with paddles
      if(bx >= 40 && bx <= 70 && by >= playerList.get(0).getY() - 50 && by <= playerList.get(0).getY() + 50) {
        bxc = ballSpeed; 
      } else if(bx >= 730 && bx <= 780 && by >= playerList.get(1).getY() - 50 && by <= playerList.get(1).getY() + 50) {
        bxc = ballSpeed * -1;
      } 

      //ball passes edge (WIN)
      if(bx < 0 || bx > 800) {
        
        if(bx < 0) { //player 2 wins
          playerList.get(1).addToPoints(1000);
        } else if (bx > 800) { //player 1 wins
          playerList.get(0).addToPoints(1000);
        }
        
        game = false;
        //resize characters back to normal
        playerList.get(0).setSize(20, 20);
        playerList.get(1).setSize(20, 20);
      }

      //move ball 
      bx+=bxc;
      by+=byc;
      dc.fillEllipse(bx, by, 20, 20); //ball

      dc.redraw();
      dc.pause(20);
    }
  
  }

  @Override
  public void moveCharacters() {

    for(int i = 0; i < this.playerList.size(); i++) {
      boolean[] movementAllowance = {true, true, true, true};
      if(this.playerList.get(i) != null) {
        boolean[] tempControl = this.playerList.get(i).getControl().getPlayerKeysPressed(); // gets the player's currently pressed keys
  
        if(tempControl[0] && movementAllowance[0] && playerList.get(i).getY() > 55) { //if that player's up key is pressed (w for player 1, t for player 2, etc.)
          playerList.get(i).moveY(-5);
        }
        if(tempControl[2] && movementAllowance[2] && playerList.get(i).getY() < 495) { //down
          playerList.get(i).moveY(5);
        }
      }
    }
  }
  
}

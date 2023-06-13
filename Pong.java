import DLibX.*;
import java.awt.*;
import java.util.*;

public class Pong extends Minigame {

  //Constructor
  public Pong (int id, DConsole dc, ArrayList<Player> playerList, ArrayList<Entity> entityList) {
    super(1, dc, playerList, entityList);
  }
  
  //Play the game
  @Override
  public void play() {
    boolean game = true;
    WordInput in = new WordInput(dc);

    ArrayList<Player> tempPlayerList = new ArrayList<>();
    for (int i = 0; i < playerList.size(); i++) {
      tempPlayerList.set(i, playerList.get(i));
    }

    int x[] = new int[2];
    int y[] = new int[2];

    x[0] = 50;
    x[1] = 750;
    y[0] = 275;
    y[1] = 275;

    int speed = 2;

    //ball
    int bx = 400;
    int by = 275;
    int bxc = 5;
    int byc = 5;
    Random rand = new Random();
    if(rand.nextInt(2) == 1) { //random start direction
      bxc*=-1;
    }

    while (game) { //these will be the loops that go on until game ends (refer to useful information for time limits)
      dc.clear();
      in.refreshKeys();

      // goes through each player to confirm if they are joining the game based on their keypresses
      for(int i = 0; i < tempPlayerList.size()-2; i++) {
        boolean[] tempControl = playerList.get(i).getControl().getPlayerKeysPressed();
        if(tempControl[0]) { //up
          if (y[i] > 20) {
            y[i]-=speed;
          }
        } else if(tempControl[2]) { //down
          if (y[i] < 530) {
            y[i]+=speed;
          }
        }
      }
      
      dc.fillRect(x[0], y[0], 20, 100); //p1
      dc.fillRect(x[1], y[1], 20, 100); //p2

      //collision of ball with up/down edges
      if(by <= 10 || by >= 540) { 
          byc*=-1;
        }

      //collision of ball with paddles
      if(bx >= 20 && bx <= 40 && by >= y[0] - 50 && by <= y[0] + 50) {
        bxc*=-1; 
      } else if(bx >= 510 && bx <= 530 && by >= y[1] - 50 && by <= y[1] + 50) {
        bxc*=-1;
      } 

      //move ball 
      bx+=bxc;
      by+=byc;
      dc.fillEllipse(bx, by, 20, 20); //ball

      dc.redraw();
      dc.pause(20);
    }
  
  }
  
}

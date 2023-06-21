import DLibX.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.lang.Math;
import java.awt.*;

public class GhostChase extends Minigame {
  private List<Coordinate> ghost;
  private boolean win = false;

  //Constructor
  public GhostChase (int id, DConsole dc, ArrayList<Player> playerList) {
    super(id, dc, playerList);
    ghost = new ArrayList<>();
  }
  
  @Override
  public void play() {
    win = false;
    ghost.clear();
    Random rand = new Random();
    for (int i = 0; i < 3; i++) {
      int x = rand.nextInt(750)+25;
      int y = 550;
      ghost.add(new Coordinate(x, y));
    }
    for (int i = 0; i<playerList.size(); i++) {
      playerList.get(i).setX(rand.nextInt(600)+100); //xpos
      playerList.get(i).setY(50); //ypos
    }

    int g[] = new int[3];
    for (int i = 0; i < 3; i++) {
      g[i] = rand.nextInt(2);
    }
    int t = 0;

    while (!win) {
      dc.clear();
      dc.setPaint(new Color(112,128,144));
      dc.fillRect(400, 275, 800, 550);

      for (int i = 0; i < ghost.size(); i++) {
        if (g[i] == 1) {
          ghost.get(i).chase(playerList.get(0).getX(), playerList.get(0).getY());
        } else {
          ghost.get(i).chase(playerList.get(1).getX(), playerList.get(1).getY());
        }
        dc.drawImage("Images/ghost.png", ghost.get(i).getX(), ghost.get(i).getY());
      }

      moveCharacters();
      super.refreshScreen();

      dc.redraw();
      dc.pause(20);
      if (t == 50) {
        for (int i = 0; i < 3; i++) {
          g[i] = rand.nextInt(2);
        }
        t=0;
      }
      t++;
      
    }
    dc.pause(500);
  }

  @Override
  public void moveCharacters() {

    for(int i = 0; i < this.playerList.size(); i++) {
      boolean[] movementAllowance = {true, true, true, true};
      if(this.playerList.get(i) != null) {
        boolean[] tempControl = this.playerList.get(i).getControl().getPlayerKeysPressed(); // gets the player's currently pressed keys
    
        if(tempControl[0] && movementAllowance[0] && playerList.get(i).getY() > 15) { //if that player's up key is pressed (w for player 1, t for player 2, etc.)
          playerList.get(i).moveY(-5);
        }
        if(tempControl[1] && movementAllowance[1] && playerList.get(i).getX() > 15) { //if that player's up key is pressed (w for player 1, t for player 2, etc.)
          playerList.get(i).moveX(-5);
        }
        if(tempControl[2] && movementAllowance[2] && playerList.get(i).getY() < 535) { //down
          playerList.get(i).moveY(5);
        }
        if(tempControl[3] && movementAllowance[3] && playerList.get(i).getX() < 785) { //down
          playerList.get(i).moveX(5);
        }
      }
  
      // player caught
      for (int j = ghost.size() -1; j >= 0; j--) {
        if (ghost.get(j).at((int) playerList.get(i).getX(), (int) playerList.get(i).getY())) {
          playerList.get(i).addToPoints(-1000);
          win = true;
        }  
      } 
      
    }
  }

  public static class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public int getX() {
      return x;
    }

    public int getY() {
      return y;
    }

    public Coordinate add(Coordinate other) {
      return new Coordinate(this.x + other.x, this.y + other.y);
    }

    public void chase(double x, double y) {
      double vx = x - this.x;
      double vy = y - this.y;

      double angle = Math.atan2(vy, vx);
      this.x += (int) (5 * Math.cos(angle));
      this.y += (int) (5 * Math.sin(angle));
    }

    public boolean at(int x, int y) {
      double dis = Math.sqrt(((x-this.x)*(x-this.x)) + ((y-this.y)*(y-this.y)));
      
      if (dis < 20) {
        return true;
      }
      return false;
    }
  }
}
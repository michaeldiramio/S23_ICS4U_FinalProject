import DLibX.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.lang.Math;
import java.awt.*;

public class Lazer extends Minigame {
  private List<Coordinate> lazer;
  private boolean win = false;

  //Constructor
  public Lazer (int id, DConsole dc, ArrayList<Player> playerList) {
    super(id, dc, playerList, "Lazer Array");
    lazer = new ArrayList<>();
  }
  
  @Override
  public void play() {
    win = false;
    lazer.clear();
    Random rand = new Random();
    int l = rand.nextInt(8) +12;
    for (int i = 0; i < l; i++) {
      int x = 800;
      int y = rand.nextInt(500) + 25;
      lazer.add(new Coordinate(x, y));
    }
    for (int i = 0; i<playerList.size(); i++) {
      playerList.get(i).setX(100); //xpos
      playerList.get(i).setY(rand.nextInt(500)+25); //ypos
    }

    while (!win) {
      dc.clear();
      dc.setPaint(new Color(115,58,58));
      dc.fillRect(400, 275, 800, 550);

      for (int i = 0; i < lazer.size(); i++) {
          lazer.get(i).chase(playerList.get(1).getX(), playerList.get(1).getY());
        dc.setPaint(new Color(255,0,0));
        dc.fillEllipse(lazer.get(i).getX(), lazer.get(i).getY(), 50, 10);
      }

      moveCharacters();
      super.refreshScreen();

      dc.redraw();
      dc.pause(20);      
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
        if(tempControl[2] && movementAllowance[2] && playerList.get(i).getY() < 535) { //down
          playerList.get(i).moveY(5);
        }
      }
  
      // player caught
      for (int j = lazer.size() -1; j >= 0; j--) {
        if (lazer.get(j).at((int) playerList.get(i).getX(), (int) playerList.get(i).getY())) {
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
      this.x -= 6;
      Random rand = new Random();
      if (this.x<0) {
        this.x = 800;
        this.y = rand.nextInt(500) + 25;
      }
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
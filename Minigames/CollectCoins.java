import DLibX.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.lang.Math;
import java.awt.*;

public class CollectCoins extends Minigame {
  private List<Coordinate> coins;

  //Constructor
  public CollectCoins (int id, DConsole dc, ArrayList<Player> playerList) {
    super(id, dc, playerList, "Coin Game");
    coins = new ArrayList<>();
  }
  
  @Override
  public void play() {
    Random rand = new Random();
    int coinNum = rand.nextInt(6)+15;
    for (int i = 0; i < coinNum; i++) {
      int x = rand.nextInt(750)+25;
      int y = rand.nextInt(500)+25;
      this.coins.add(new Coordinate(x, y));
    }
    for (int i = 0; i<playerList.size(); i++) {
      this.playerList.get(i).setX(rand.nextInt(600)+100); //xpos
      this.playerList.get(i).setY(rand.nextInt(500)+25); //ypos
    }

    boolean game = true;

    while (game) {
      this.dc.clear();
      this.dc.setPaint(new Color(112,128,144));
      this.dc.fillRect(400, 275, 800, 550);

      for (int i = 0; i < coins.size(); i++) {
        this.dc.drawImage("Images/coin.png", coins.get(i).getX(), coins.get(i).getY());
      }

      this.moveCharacters();
      super.refreshScreen();

      game = !isGameOver();

      this.dc.redraw();
      this.dc.pause(20);
    }
    this.dc.pause(1000);
  }

  @Override
  public void moveCharacters() {

    for(int i = 0; i < this.playerList.size(); i++) {
      boolean[] movementAllowance = {true, true, true, true};
      if(this.playerList.get(i) != null) {
        boolean[] tempControl = this.playerList.get(i).getControl().getPlayerKeysPressed(); // gets the player's currently pressed keys
    
        if(tempControl[0] && movementAllowance[0] && this.playerList.get(i).getY() > 15) { //if that player's up key is pressed (w for player 1, t for player 2, etc.)
          this.playerList.get(i).moveY(-5);
        }
        if(tempControl[1] && movementAllowance[1] && this.playerList.get(i).getX() > 15) { //if that player's up key is pressed (w for player 1, t for player 2, etc.)
          this.playerList.get(i).moveX(-5);
        }
        if(tempControl[2] && movementAllowance[2] && this.playerList.get(i).getY() < 535) { //down
          this.playerList.get(i).moveY(5);
        }
        if(tempControl[3] && movementAllowance[3] && this.playerList.get(i).getX() < 785) { //down
          this.playerList.get(i).moveX(5);
        }
      }
  
      // Check if the player has collected a coin
      for (int j = coins.size() -1; j >= 0; j--) {
        if (this.coins.get(j).at((int) this.playerList.get(i).getX(), (int) this.playerList.get(i).getY())) {
          this.coins.remove(j);
          this.playerList.get(i).addToPoints(1000);
        }  
      } 
      
    }
  }

  public boolean isGameOver() {
    if (this.coins.size() == 0) {
      return true;
    }
    return false;
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

    public boolean at(int x, int y) {
      double dis = Math.sqrt(((x-this.x)*(x-this.x)) + ((y-this.y)*(y-this.y)));
      
      if (dis < 20) {
        return true;
      }
      return false;
    }
  }
}
import DLibX.*;
import java.awt.Color;

public class Entity {
  
  //Instance variables
  private int id;
  private String name;
  private DConsole dc;
  private double x;
  private double y;
  private double height;
  private double width;
  private Color color;
  private boolean collision;

  //Basic entity with collisions
  public Entity(int id, double x, double y, double width, double height, Color color, DConsole dc) {
    this.id = id;
    this.name = "null";
    this.dc = dc;
    this.x = x;
    this.y = y;
    this.height = height;
    this.width = width;
    this.collision = true;
    this.color = color;
  }

  //Entity with possible no collisions
  public Entity(int id, String name, double x, double y, double width, double height, boolean collision, Color color, DConsole dc) {
    this.id = id;
    this.name = name;
    this.dc = dc;
    this.x = x;
    this.y = y;
    this.height = height;
    this.width = width;
    this.collision = collision;
    this.color = color;
  }

  //Get entity bounds
  public boolean[] getEntityBounds(Player player) {
    boolean[] boundsArray = new boolean[4];

    //player collides with entitiy
    if(this.collision) {
      for(int i = 0; i < boundsArray.length; i++) {
        boundsArray[i] = boundsCalculation(i, player) ? true : false;
      }
      return boundsArray;
    } else {
      return boundsArray;
    }
    
  }

  //============================================================Calculations for Bounds=======================================================================
  private boolean boundsCalculation(int orientation, Player player) {
    double playerX = player.getX();
    double playerY = player.getY();
    double playerXRadius = player.getSizeX() / 2;
    double playerYRadius = player.getSizeY() / 2;
    double entityXRadius = this.width / 2;
    double entityYRadius = this.height / 2;

    double playerLeft = playerX - playerXRadius;
    double playerRight = playerX + playerXRadius;
    double playerTop = playerY - playerYRadius;
    double playerBottom = playerY + playerYRadius;

    double entityLeft = this.x - entityXRadius;
    double entityRight = this.x + entityXRadius;
    double entityTop = this.y - entityYRadius;
    double entityBottom = this.y + entityYRadius;
    
    
    if(orientation == 0) {
      return playerTop < entityBottom + 5.0 && playerBottom > entityTop + 1.0 && ((playerLeft > entityLeft - 1.0 && playerLeft < entityRight) || (playerRight < entityRight + 1.0 && playerRight > entityLeft));
    } else if(orientation == 1) {
      return playerLeft < entityRight + 5.0 && playerLeft > entityRight - 1.0 && ((playerTop < entityBottom && playerTop > entityTop - 1.0) || (playerBottom > entityTop && playerBottom < entityBottom + 1.0));
    } else if(orientation == 2) {
      return playerBottom > entityTop - 5.0 && playerBottom < entityBottom - 1.0 && ((playerLeft > entityLeft - 1.0 && playerLeft < entityRight) || (playerRight < entityRight + 1.0 && playerRight > entityLeft));
    } else if(orientation == 3) {
      return playerRight > entityLeft - 5.0 && playerRight < entityLeft + 1.0 && ((playerTop < entityBottom && playerTop > entityTop - 1.0) || (playerBottom > entityTop && playerBottom < entityBottom + 1.0));
    }
    
    return false;
  }
  //=========================================================================================================================================================

  //Entity movement
  public void move(double xChange, double yChange){
    this.x += xChange;
    this.y += yChange;
  }

  //Draw the entity
  public void draw() {
    this.dc.setPaint(this.color);
    this.dc.fillRect(this.x, this.y, this.width, this.height);
  }

  //Get entitiy xPOS
  public double getX() {
    return this.x;
  }

  //get yPOS
  public double getY() {
    return this.y;
  }
}
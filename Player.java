//Imports
import DLibX.*;
import java.awt.*;
import java.util.*;
import java.util.ArrayList;

//Player class keeps player data 
public class Player {

  // PLEASE

  //Instance Variables
  private int id;
  private int score;
  private Color color;
  private double xPOS;
  private double yPOS;
  private int xSize; //Default is 20x20
  private int ySize;
  private int minigamePoints;
  private Control control;
  private String username;
  private DConsole dc;

  // undefined username constructor
  public Player(int id, Color color, double x, double y, DConsole dc) {
    this.id = id;
    this.color = color;
    this.xPOS = x;
    this.yPOS = y;
    this.xSize = 20;
    this.ySize = 20;
    this.control = new Control(id, dc);
    this.username = "Player " + id;
    this.dc = dc;
    this.score = 0; //score counter
  }

  //----------------------------Methods-------------------------

  //Draw
  public void draw() {
    this.dc.setPaint(this.color);
    this.dc.setOrigin(DConsole.ORIGIN_CENTER);
    this.dc.fillRect(this.xPOS, this.yPOS, this.xSize, this.ySize);
    this.dc.setPaint(new Color(0,0,0));
    dc.setFont(new Font("DejaVu Sans", Font.BOLD, 15));
    this.dc.drawString(this.username, xPOS, yPOS - 30);
  }

  //Move X
  public void moveX(double xChange) {
    this.xPOS += xChange;
  }

  //move Y
  public void moveY(double yChange) {
    this.yPOS += yChange;
  }

  //Get score
  public int getScore() {
    return this.score;
  }

  //Get color
  public Color getColor() {
    return this.color;
  }

  //Add to score
  public void addToScore(int amt) {
    this.score += amt;
  }

  //get ID
  public int getID() {
    return this.id;
  }

  //get xPosition
  public double getX() {
    return this.xPOS;
  }

  //get yPosition
  public double getY() {
    return this.yPOS;
  }

  // get control
  public Control getControl() {
    return this.control;
  }

  //get username
  public String getUsername() {
    return this.username;
  }

  //set username
  public void setUsername(String username) {
    this.username = username;
  }

  //Set size
  public void setSize(int length, int height) {
    this.xSize = length;
    this.ySize = height;
  }

  //Get size x
  public double getSizeX() {
    return xSize;
  }

  //Get size y
  public double getSizeY() {
    return ySize; 
  }

  //getMinigame points
  public int getPoints() {
    return this.minigamePoints;
  }

  //add to minigame points
  public void addToPoints(int amt) {
    this.minigamePoints += amt;
  }

  //reset minigame points
  public void resetPoints() {
    this.minigamePoints = 0;
  }

  //set full position
  public void setPOS(double x, double y) {
    
  }

  //set position X
  public void setX(double x) {
    this.xPOS = x;
  }

  //set position Y
  public void setY(double y) {
    this.yPOS = y;
  }
}
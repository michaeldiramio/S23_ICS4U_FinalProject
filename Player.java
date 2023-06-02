//Imports
import DLibX.*;
import java.awt.*;
import java.util.*;
import java.util.ArrayList;

//Player class keeps player data 
public class Player {

  //Instance Variables
  private int id;
  private int score;
  private Color color;
  private double xPOS;
  private double yPOS;
  private Control control;
  private String username;
  private DConsole dc;

  //Controls
  //private ArrayList<Control> controls = new Arraylist<>();

  // undefined username constructor
  public Player(int id, Color color, double x, double y, DConsole dc) {
    this.id = id;
    this.color = color;
    this.xPOS = x;
    this.yPOS = y;
    this.control = new Control(id, dc);
    this.username = "Player " + id;
    this.dc = dc;
    this.score = 0; //score counter
  }

  //----------------------------Methods-------------------------

  //Draw
  public void draw() {
    dc.setPaint(this.color);
    dc.setOrigin(DConsole.ORIGIN_CENTER);
    dc.fillRect(this.xPOS, this.yPOS, 20, 20);
    dc.setPaint(new Color(0,0,0));
  }

  //Move (Will be called in minigame, could be overridden for gravity or only up and down) 
  public void moveX(int xChange) {
    this.xPOS += xChange;
  }

  //move y
  public void moveY(int yChange) {
    this.yPOS += yChange;
  }

  //Get score
  public int getScore() {
    return this.score;
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

  // get username
  public String getUsername() {
    return this.username;
  }

  // set username
  public void setUsername(String username) {
    this.username = username;
  }
}
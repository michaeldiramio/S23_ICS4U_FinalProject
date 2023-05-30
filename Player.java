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
  private DConsole dc;

  //Controls
  //private ArrayList<Control> controls = new Arraylist<>();

  //Constructor
  public Player(int id, Color color, double x, double y, DConsole dc) {
    this.id = id;
    this.color = color;
    this.xPOS = x;
    this.yPOS = y;
    this.dc = dc;
    this.score = 0; //score counter
  }

  //----------------------------Methods-------------------------

  //Draw
  public void draw() {
    dc.fillRect(10, 10, this.xPOS, this.yPOS);
  }

  //Move (Will be called in minigame, could be overridden for gravity or only up and down) (leave this to abeer)
  public void move(int xChange, int yChange) {
    
    if (this.xPOS <= 795 ) { //move right // &&control[c].isPressed
      
    } else if (this.xPOS >= 5) { //move left
      
    } else if (this.yPOS <= 545) { //move down
      
    } else if (this.yPOS >= 5) { //move up
      
    }
    
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

  //Add controls 
  /*
  public void addControl(Control c) {
    controls.add(c);
  }

  */





  
}
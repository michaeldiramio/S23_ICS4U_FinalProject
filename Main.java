import DLibX.*;
import java.awt.Color;
import java.util.*;

public class Main {

  // data
  public DConsole dc;

  // initializes DConsole
  public void DInit(int width, int height) {
    this.dc = new DConsole(width, height);
    this.dc.setOrigin(this.dc.ORIGIN_CENTER);
  }

  // runs methods from other classes
  public void run() {
    ArrayList<Player> playerList = new ArrayList<>();

    //-----------------------Testing Chamber---------------------
    Minigame test = new TestGame(0, dc, playerList);

    //Game screen testing (currently unfinished)
    GameScreen n = new GameScreen(dc);
    n.join(); //go through the joining process (code doesnt get passed here, waiting for kieran's input for this)
    boolean[] playersPresent = n.playerInit(); //get an array of which players are playing
    for (int i = 0; i < playersPresent.length; i++) { //create the players 
      if (playersPresent[i]) { //player at i is playing
        playerList.add(new Player(i + 1, Color.BLACK, 200, 200, dc)); //make that player 
      }
    }

    test.play();
    //---------------------------------------------------------
  }
  

  // main
  public static void main(String[] args) {
    Main m = new Main();
    m.DInit(800, 550); 
    while(true) {
      m.run();
    }
  }
}
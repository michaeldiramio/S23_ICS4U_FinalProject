import DLibX.*;
import java.awt.*;
import java.util.*;

public class Main { //git test

  // data
  static DConsole dc;
  static ArrayList<Player> playerList = new ArrayList<>();

  // initializes DConsole
  public void DInit(int width, int height) {
    dc = new DConsole(width, height);
    dc.setOrigin(dc.ORIGIN_CENTER);
  }

  // runs methods from other classes
  public static void run() {

    playerList.add(new Player(1, new Color (0, 0, 200), 200, 200, dc));
    playerList.add(new Player(2, new Color (17, 188, 76), 200, 200, dc));
    playerList.add(new Player(3, new Color (153, 51, 255), 200, 200, dc));
    playerList.add(new Player(4, new Color (200, 0, 0), 200, 200, dc));

    //------------------------Game Creation Area--------------------------

    ArrayList<Minigame> minigameList = new ArrayList<Minigame>();

    //------------------------------"Test"-----------------------------------
    
    ArrayList<Player> testPlayerList = new ArrayList<>();
    ArrayList<Entity> testEntityList = new ArrayList<>();
    testEntityList.add(new Entity(1, "left barrier", -5.0, 225.0, 10.0, 600.0, true, Color.BLACK, dc));
    testEntityList.add(new Entity(2, "right barrier", 805.0, 225.0, 10.0, 600.0, true, Color.BLACK, dc));
    testEntityList.add(new Entity(3, "top barrier", 400.0, -5.0, 850.0, 10.0, true, Color.BLACK, dc));
    testEntityList.add(new Entity(4, "bottom barrier", 400.0, 555.0, 850.0, 10.0, true, Color.BLACK, dc));
    testEntityList.add(new Entity(5, "testing object 1", 500.0, 225.0, 100.0, 100.0, true, Color.RED, dc));
    testEntityList.add(new Entity(6, "testing object 2", 150.0, 100.0, 50.0, 70.0, false, Color.BLUE, dc));
    
    Minigame test = new TestGame(0, dc, testPlayerList, testEntityList);

    //-------------------------------------------------------------------------
    //-------------------------Other Game----------------------------------


    
    //-------------------------------------------------------------------------

    minigameList.add(test);
    //-------------------------------------------------------------------------
    
    GameScreen n = new GameScreen(dc, playerList, minigameList);
    
    n.join();
    
    n.nicknames();
    n.winScreen();
    
    minigameList.get(n.getCurrentGame()).play();
    
  }
  

  // main
  public static void main(String[] args) {
    Main m = new Main();
    m.DInit(800, 550); 
    while(true) {
      run();
    }
  }
}
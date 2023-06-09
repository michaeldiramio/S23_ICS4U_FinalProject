  import DLibX.*;
import java.awt.*;
import java.util.*;

public class Main { //git test

  // data
  public DConsole dc;
  ArrayList<Player> playerList = new ArrayList<>();

  // initializes DConsole
  public void DInit(int width, int height) {
    this.dc = new DConsole(width, height);
    this.dc.setOrigin(this.dc.ORIGIN_CENTER);
  }

  // runs methods from other classes
  public void run() {

    this.playerList.add(new Player(1, Color.BLUE, 200, 200, dc));
    this.playerList.add(new Player(2, Color.PINK, 200, 200, dc));
    this.playerList.add(new Player(3, Color.RED, 200, 200, dc));
    this.playerList.add(new Player(4, Color.GREEN, 200, 200, dc));

  

    ArrayList<Minigame> minigameList = new ArrayList<Minigame>();

   
    
    ArrayList<Player> testPlayerList = new ArrayList<>();
    Minigame test = new TestGame(0, this.dc, testPlayerList);

   

    minigameList.add(test);
   
    
    GameScreen n = new GameScreen(dc, this.playerList, minigameList);
    
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
      m.run();
    }
  }
}
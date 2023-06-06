import DLibX.*;
import java.awt.*;
import java.util.*;

public class Main {

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
    boolean[] players = {true, true, true, true};
    fillPlayers(players);
    
    GameScreen n = new GameScreen(dc, playerList, players);
    //n.StartScreen();
    n.join();

    players = n.playerInit();
    playerList.clear();
    fillPlayers(players);
    
    n.nicknames();
    n.select();
    

    //-----------------------Testing Chamber---------------------
    ArrayList<Player> playerList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();
    entityList.add(new Entity(1, "left barrier", -5.0, 225.0, 10.0, 600.0, true, Color.BLACK, this.dc));
    entityList.add(new Entity(2, "right barrier", 805.0, 225.0, 10.0, 600.0, true, Color.BLACK, this.dc));
    entityList.add(new Entity(3, "top barrier", 400.0, -5.0, 850.0, 10.0, true, Color.BLACK, this.dc));
    entityList.add(new Entity(4, "bottom barrier", 400.0, 555.0, 850.0, 10.0, true, Color.BLACK, this.dc));
    entityList.add(new Entity(5, "testing object 1", 500.0, 225.0, 100.0, 100.0, true, Color.RED, this.dc));
    entityList.add(new Entity(6, "testing object 2", 150.0, 100.0, 50.0, 70.0, false, Color.BLUE, this.dc));
    
    Minigame test = new TestGame(0, dc, playerList, entityList);
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

  public void fillPlayers(boolean[] players) {
    Color cols[] = {Color.BLUE, Color.PINK, Color.RED, Color.GREEN};
    for (int i = 0; i < players.length; i++) { //create the players 
      if (players[i]){
        playerList.add(new Player(i + 1, cols[i], 200, 200, dc));
      }
    }
  }
}
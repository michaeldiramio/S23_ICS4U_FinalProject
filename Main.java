import DLibX.*;
import java.awt.Color;
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
    n.join();

    players = n.playerInit();
    playerList.clear();
    fillPlayers(players);
    

    //-----------------------Testing Chamber---------------------
    Minigame test = new TestGame(0, dc, playerList);

    
    n.nicknames();
    n.select();

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
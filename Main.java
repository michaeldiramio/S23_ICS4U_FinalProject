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
    //Players
    ArrayList<Player> playerList = new ArrayList<>();
    playerList.add(new Player(1,Color.RED,300,300,this.dc));
    playerList.add(new Player(2,Color.BLUE,200,200,this.dc));
    playerList.add(new Player(3,Color.GREEN,400,400,this.dc));
    playerList.add(new Player(4,Color.BLACK,500,500,this.dc));

    //-----------------------Testing Chamber---------------------
    Minigame test = new TestGame(0, dc, playerList);

    test.play();
    //---------------------------------------------------------
  }

  // main
  public static void main(String[] args) {
    Main m = new Main();
    m.DInit(600, 600); 
    while(true) {
      m.run();
    }
  }
}
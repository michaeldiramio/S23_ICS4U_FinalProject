import DLibX.*;
import java.awt.Color;
import java.util.*;

public class Main {

  // data
  private DConsole dc;

  // initializes DConsole
  public void DInit(int width, int height) {
    this.dc = new DConsole(width, height);
    this.dc.setOrigin(this.dc.ORIGIN_CENTER);
  }

  // runs methods from other classes
  public void run() {
    GameScreen.join(dc);
  }

  // main
  public static void main(String[] args) {

    Main m = new Main();
    m.DInit(800, 550); //should be 800 by 550 px -Kieran
    while(true) {
      m.run();
    }
  }
}
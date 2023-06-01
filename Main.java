import DLibX.*;
import java.awt.Color;
import java.util.*;

public class Main {

  // data
  private DConsole dc;
  WordInput wordInput;
  private String finalWord = "";
  

  // initializes DConsole
  public void DInit(int width, int height) {
    this.dc = new DConsole(width, height);
    this.dc.setOrigin(this.dc.ORIGIN_CENTER);
    this.wordInput = new WordInput(10, this.dc);
  }

  // runs methods from other classes
  public void run() {
    this.dc.clear();
    
    this.dc.redraw();
    this.dc.pause(100);
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
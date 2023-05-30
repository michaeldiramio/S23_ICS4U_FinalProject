import DLibX.*;
import java.awt.Color;

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
    this.dc.clear();

    this.dc.setPaint(Color.RED);
    this.dc.fillEllipse(300, 300, 60, 40);

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
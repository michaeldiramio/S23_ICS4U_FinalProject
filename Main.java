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
    

    ArrayList<Player> playerList = new ArrayList<>();
    playerList.add(new Player(1,Color.RED,300,300,this.dc));
    playerList.add(new Player(2,Color.BLUE,200,200,this.dc));
    playerList.add(new Player(3,Color.GREEN,400,400,this.dc));
    playerList.add(new Player(4,Color.BLACK,500,500,this.dc));

    ArrayList<Control> controlList = new ArrayList<>();
    controlList.add(new Control(0, this.dc));
    controlList.add(new Control(1, this.dc));
    controlList.add(new Control(2, this.dc));
    controlList.add(new Control(3, this.dc));

    while(true) {
      this.dc.clear();
      for(int i = 0; i < playerList.size(); i++) {
      boolean[] tempControl = controlList.get(i).getPlayerKeysPressed();
      if(tempControl[0] == true) {
        playerList.get(i).move(0, 5);
      }
      System.out.println(tempControl[i]);
      playerList.get(i).draw();
    }
      this.dc.redraw();
    this.dc.pause(100);
    }
    


    

    
  }

  public void firstCycle() {
    
    
  }

  // main
  public static void main(String[] args) {

    Main m = new Main();
    m.DInit(600, 600);
    m.firstCycle();
    while(true) {
      m.run();
    }
  }
	
}
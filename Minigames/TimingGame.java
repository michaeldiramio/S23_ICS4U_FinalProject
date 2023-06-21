import DLibX.*;
import java.awt.*;
import java.util.*;

public class TimingGame extends Minigame {
  DConsole dc;
  int time;

  private ArrayList<Double> pScores = new ArrayList<Double>();
  double Gtime = 5.5;
  
  //Constructor
  public TimingGame(int id, DConsole dc, ArrayList<Player> playerList) {
    super(0, dc, playerList);
    this.dc = dc;
  }

  //USEFUL INFORMATION: The ideal DConsole pause is 20ms, thus 50 pauses make a second, therefore, 50 cycles is one second
  //That being said, we can track time with these multiples of 50, 750 cycles is 15 seconds, and 3000 cycles is a minute
  //These cycles should increase at the end of each cycle of the while loop, and can be used to end the game upon a time limit

  //Play the game
  @Override
  public void play() {
  
    //this is how you add points to a player
    //this.playerList.get(0).addToPoints(4);
    boolean game = true;
    int CounterCycle = 0;
    int cycles = 0;
    int seconds = 11;

    this.pScores.add(0.0);
    this.pScores.add(0.0);
    this.pScores.add(0.0);
    this.pScores.add(0.0);

    while (game) { //these will be the loops that go on until game ends (refer to useful information for time limits)
      dc.clear();
      
      this.moveCharacters();

      this.dc.setPaint(new Color(255,255,255));
      this.dc.setFont(new Font("Comic Sans", Font.BOLD, 200));
      this.dc.drawString(Gtime, 400, 500); // Counter
      this.dc.drawString(pScores.get(0), 100, 250); //p1
      this.dc.drawString(pScores.get(1), 300/3, 250); //p2
      this.dc.drawString(pScores.get(2), 500/2, 250); //p3
      this.dc.drawString(pScores.get(3), 700, 250); //p4
      this.dc.setPaint(new Color(0,0,0));
     
  
      cycles++;
      if (cycles >= 50) { //one second has passed
        seconds--;
        cycles = 0;
      }

      if(CounterCycle >= 20){//one ms has passed
        Gtime += 0.1;
        CounterCycle = 0;
      }

      if(pScores.get(0) != 0 && pScores.get(1) != 0 && pScores.get(2) != 0 && pScores.get(3) != 0 ){
        
        game = false;
      }

      

      if (seconds == 0) { //15 second are up, game ends
        
        game = false;
      }

      dc.redraw();
      dc.pause(20);
    }
  
    }

    @Override
  public void moveCharacters() {

    for(int i = 0; i < this.playerList.size(); i++) {
      if(this.playerList.get(i) != null) {
        boolean[] tempControl = this.playerList.get(i).getControl().getPlayerKeysPressed(); // gets the player's currently pressed keys
  
        // movement based on key input and if movement is allowed (from entity bounds)
        
        if(tempControl[0]) { //if that player's up key is pressed (w for player 1, t for player 2, etc.)
          if(i == 0){
            pScores.set(0, Gtime);
          }if(i == 1){
            pScores.set(1, Gtime);
          }if(i==2){
            pScores.set(2, Gtime);
          }if(i == 3){
            pScores.set(3, Gtime);
          }
        }
      }
    }
  }
}

    
  

  



import DLibX.*;
import java.awt.*;
import java.util.*;

public class SpamPress extends Minigame {
  DConsole dc;
  
   
  //Constructor
  public SpamPress(int id, DConsole dc, ArrayList<Player> playerList) {
    super(0, dc, playerList, "Tug of War");
    this.dc = dc;
  }

  private double LPerc = 50;
  private double RPerc = 50;
  private boolean p1p = false;
  private boolean p2p = false;

  //screensize 800, 550
  private void sidesDraw(){
    dc.setPaint(this.playerList.get(0).getColor());
    dc.fillRect(0, 275, ((1600/100)*LPerc), 550);//left screen
    dc.setPaint(this.playerList.get(1).getColor());
    dc.fillRect(800, 275, ((1600/100)*RPerc), 550);//right screen
  }

  private void LPress(){
    this.LPerc += 1;
    this.RPerc-=1;
  }

  private void RPress(){
    this.RPerc+=1;
    this.LPerc-=1;

  }

  //USEFUL INFORMATION: The ideal DConsole pause is 20ms, thus 50 pauses make a second, therefore, 50 cycles is one second
  //That being said, we can track time with these multiples of 50, 750 cycles is 15 seconds, and 3000 cycles is a minute
  //These cycles should increase at the end of each cycle of the while loop, and can be used to end the game upon a time limit

  //Play the game
  @Override
  public void play() {
    this.LPerc = 50;
    this.RPerc = 50;

    //this is how you add points to a player
    //this.playerList.get(0).addToPoints(4);
    boolean game = true;
    int cycles = 0;
    int seconds = 8;

    while (game) { //these will be the loops that go on until game ends (refer to useful information for time limits)
      dc.clear();

      
      this.moveCharacters();
      this.sidesDraw();
      

      if(this.LPerc <= 0){
        game = false;
        this.playerList.get(1).addToPoints(5);
      }
      
       if(this.RPerc <= 0){
        game = false;
        this.playerList.get(0).addToPoints(5);
      }
    
  
      cycles++;
      if (cycles >= 50) { //one second has passed
        seconds--;
        cycles = 0;
      }

      if (seconds == 0) { //15 second are up, game ends
        game = false;
        if(LPerc > RPerc){
    this.playerList.get(0).addToPoints(5);
        }else if(RPerc > LPerc){
          this.playerList.get(1).addToPoints(5);
        }else{
          this.playerList.get(0).addToPoints(1);
          this.playerList.get(0).addToPoints(1);
        }
        
        this.p1p = false;
        this.p2p = false;
      }

      this.dc.redraw();
      this.dc.pause(20);

    }
  
    }

  @Override
  public void moveCharacters() {

    for(int i = 0; i < this.playerList.size(); i++) {
      if(this.playerList.get(i) != null) {
        boolean[] tempControl = this.playerList.get(i).getControl().getPlayerKeysPressed(); // gets the player's currently pressed keys
  
        // movement based on key input and if movement is allowed (from entity bounds)

        if(i == 0){
          if(!tempControl[0]){

            this.p1p = false;
          }
        }

        if(i == 1){
          if(!tempControl[0]){

            this.p2p = false;

          }
        }

        if(tempControl[0]) { //if that player's up key is pressed (w for player 1, t for player 2, etc.)
          if(i == 0){
            if(this.p1p == false){
              this.p1p = true;
              this.LPress();

            }
          }
          
          if(i == 1){
            if(this.p2p == false){
              this.p2p = true;
              this.RPress();
            }
          }
        }
      }
    }
  }

    
  

  


}
import DLibX.*;
import java.awt.*;
import java.util.*;

public class PressTheButton extends Minigame {

  private int correctKey;
  private Random r = new Random();
  private ArrayList<Player> tempPlayerList = new ArrayList<Player>();
  private boolean[] canPress;

  //Constructor
  public PressTheButton(int id, DConsole dc, ArrayList<Player> playerList) {
    super(id, dc, playerList, "Button Press");
    this.correctKey = r.nextInt(3); // 0-3 random correct key
    this.canPress = new boolean[playerList.size()];
    for(int i = 0; i < canPress.length; i++) {
      canPress[i] = true; 
    }
  }



  
  public void moveCharacters() {
    //makes temporary list of players
    

    for(int i = 0; i < this.playerList.size(); i++){
      if(this.playerList.get(i) != null) {
        boolean[] tempControl = this.playerList.get(i).getControl().getPlayerKeysPressed(); // gets the player's currently pressed keys
    

        
        
        

        //if worng key has been pressed makes canPress false
        for(int j = 0; j < 4; j++) {
          if(tempControl[j] && j != correctKey) {
            canPress[i] = false;
          }
        }

        //if wrong key hasnt been pressed (which means they must have hit the right key) and player hasnt hit the right one more then once adds the mto temp player array
        for(int j = 0; j < 4; j++) {
          if(canPress[i] && tempControl[j]) {
            tempPlayerList.add(playerList.get(i));
            canPress[i] = false;
          }
        }

        
        
      }
    }
  }

  
  //Play the game
  @Override
  public void play() {

    boolean game = true;
    String[] key = {"UP","Left","Down","Right"};

    int cycles = 0;
    int seconds = 15;
    while (game) { //these will be the loops that go on until game ends (refer to useful information for time limits)
      dc.clear();

      dc.setFont(new Font("Comic Sans", Font.BOLD, 20));
      //time
      dc.setPaint(new Color(0, 0, 0));//black
      super.printTime(seconds, 100, 40);

    
      //button
      dc.setPaint(new Color(225, 0, 0)); //red
      dc.fillEllipse(400, 300, 300, 300);

      dc.setPaint(new Color(0, 0, 0));//black
      dc.setFont(new Font("Comic Sans", Font.BOLD, 40));
      dc.drawString("Press the button!!!", 400, 100); 
      
      dc.setFont(new Font("Comic Sans", Font.BOLD, 50));
      dc.drawString(key[correctKey], 400, 275); 
      

      cycles++;
      if (cycles >= 50) { //one second has passed
        seconds--;
        cycles = 0;
      }
      
      if (seconds == 0) { //15 second are up, game ends
        game = false;
      }

      if(canPress[0] == false && canPress[1] == false && canPress[2] == false && canPress[3] == false){
        game = false;
      }
      
      
      this.moveCharacters();

      dc.redraw();
      dc.pause(20);
    }


    //rewards points to players based on where they are in tempPlayerArrayList
    for(int i = 0; i < tempPlayerList.size(); i++){
      if(tempPlayerList.size() != 0) {
        if(i == 0){
          playerList.get(tempPlayerList.get(i).getID()-1).addToPoints(1000);
        }else if(i == 1){
          playerList.get(tempPlayerList.get(i).getID()-1).addToPoints(100);
        }else if(i == 2){
          playerList.get(tempPlayerList.get(i).getID()-1).addToPoints(10);
        }else if(i == 3){
          playerList.get(tempPlayerList.get(i).getID()-1).addToPoints(1);
        }
      }
    }
    
    
  
  }
  


}

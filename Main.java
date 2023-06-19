import DLibX.*;
import java.awt.*;
import java.util.*;

public class Main { //git test
  
  // data
  DConsole dc;
  ArrayList<Player> playerList = new ArrayList<>();
  ArrayList<Player> cp = new ArrayList<>(); //"current players" arraylist

  // initializes DConsole
  public void DInit(int width, int height) {
    dc = new DConsole(width, height);
    dc.setOrigin(dc.ORIGIN_CENTER);
  }

  // runs methods from other classes
  public void run() {

    ArrayList<ArrayList<Minigame>> minigameList = new ArrayList<ArrayList<Minigame>>(); //arraylist of player numbers

    ArrayList<Minigame> twoPlayerMinigameList = new ArrayList<Minigame>();
    minigameList.add(twoPlayerMinigameList); //two player

    ArrayList<Minigame> threePlayerMinigameList = new ArrayList<Minigame>();
    minigameList.add(threePlayerMinigameList); //three player
    
    ArrayList<Minigame> fourPlayerMinigameList = new ArrayList<Minigame>();
    minigameList.add(fourPlayerMinigameList); //four player
    

    // making players for all games
    this.playerList.add(new Player(1, new Color (0, 0, 200), 200, 200, this.dc));
    this.playerList.add(new Player(2, new Color (17, 188, 76), 200, 200, this.dc));
    this.playerList.add(new Player(3, new Color (153, 51, 255), 200, 200, this.dc));
    this.playerList.add(new Player(4, new Color (200, 0, 0), 200, 200, this.dc));

    //------------------------------"Test"-----------------------------------
    Minigame test = new TestGame(0, this.dc, this.cp); //pass off cp instead of the whole thing (cp can change as we please)
    Minigame Kahoot = new Kahoot(0, this.dc, this.cp);

    //-------------------------------------------------------------------------
    //-------------------------FirstToTheTop-------------------------------
    ArrayList<Entity> FTTTEntityList = new ArrayList<>();
    FTTTEntityList.add(new Entity(1, "left barrier", -5.0, 225.0, 10.0, 600.0, true, Color.BLACK, this.dc));
    FTTTEntityList.add(new Entity(2, "right barrier", 805.0, 225.0, 10.0, 600.0, true, Color.BLACK, this.dc));
    FTTTEntityList.add(new Entity(3, "top barrier", 400.0, -5.0, 850.0, 10.0, true, Color.BLACK, this.dc));
    FTTTEntityList.add(new Entity(4, "bottom barrier", 400.0, 555.0, 850.0, 10.0, true, Color.BLACK, this.dc));
    FTTTEntityList.add(new Entity(5, "maze block 1", 125.0, 525.0, 310.0, 50.0, true, Color.RED, this.dc));
    FTTTEntityList.add(new Entity(6, "maze block 2", 675.0, 525.0, 310.0, 50.0, true, Color.RED, this.dc));
    FTTTEntityList.add(new Entity(7, "maze block 3", 300.0, 400.0, 100.0, 300.0, true, Color.RED, this.dc));
    FTTTEntityList.add(new Entity(8, "maze block 4", 500.0, 225.0, 100.0, 100.0, true, Color.RED, this.dc));
    FTTTEntityList.add(new Entity(9, "maze block 5", 500.0, 225.0, 100.0, 100.0, true, Color.RED, this.dc));
    
    
    Minigame FirstToTheTop = new FirstToTheTop(1, this.dc, this.cp, FTTTEntityList);

    //QUICK FORMAT:
    //-----------------------MINIGAME NAME-----------------------------------
    //create entity list for that game
    //make entities for that game
    //make minigame

    //remember to add it to the specific game arraylist (note that some 4 player games can still be played by 2 or 3 players)
    
    //-----------------------ADDING MINIGAMES------------------------

    fourPlayerMinigameList.add(test);
    fourPlayerMinigameList.add(FirstToTheTop);
    fourPlayerMinigameList.add(Kahoot);
    
    //-------------------------------------------------------------------------
    
    GameScreen n = new GameScreen(dc, this.playerList);
    
    n.join();
    this.playerList = n.getActivePlayers();
    int playerAmount = n.getPlayerAmount();

    // gets the max amount of minigames that would exist in tempSubMinigameList by counting how many minigames exist in total
    int maxMinigameAmount = 0;
    for(int i = 0; i < minigameList.size(); i++) {
      for(int j = 0; j < minigameList.get(j).size(); j++) {
        maxMinigameAmount++;
      }
    }
    
    ArrayList<Minigame> tempSubMinigameList = new ArrayList<Minigame>();

    // sets active sub minigame list (sublist based on player amount)
    // it is -2 because the minigame slots correlate to 0-2, whereas the player amount ranges from 2-4
    tempSubMinigameList = minigameList.get(playerAmount - 2);
    
    // update sub minigame list with new active players
    for(int i = 0; i < tempSubMinigameList.size(); i++) {
      tempSubMinigameList.get(i).setPlayers(this.playerList);
    }
    
    n.nicknames();
    
    // play 4 games for now 
    for(int i = 0; i < 4; i++) {
      n.select(tempSubMinigameList); 

      //Loop twice for 2 1v1s
      for (int j = 0; j <= 3; j+=2) { 
        cp.add(playerList.get(j)); //get the first player
        cp.add(playerList.get(j + 1)); //2nd player
        System.out.println(cp.get(0).getID() + " " + cp.get(1).getID()); //for testing
        tempSubMinigameList.get(n.getCurrentGame()).play(); //play the game (cp has already been passed into the constructor, if it hasnt that means you can play the game with all four players, so the rest of this code wont matter in that case)
        cp.clear(); 
      }

      tempSubMinigameList.get(n.getCurrentGame()).endGame();
      
    }
    
    n.winScreen();
    
  }

  //Player linear search
  public Player findByID(int id) {
    for (int i = 0; i < playerList.size(); i++) {
      if (id == playerList.get(i).getID()) {
        return playerList.get(i);
      }
    }
    return null;
  }
  

  // main
  public static void main(String[] args) {
    Main m = new Main();
    m.DInit(800, 550); 
    while(true) {
      m.run();
    }
  }
}
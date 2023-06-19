import DLibX.*;
import java.awt.*;
import java.util.*;

public class Main { 
  
  // data
  DConsole dc;
  ArrayList<Player> playerList = new ArrayList<>(); //all players
  ArrayList<Player> cp = new ArrayList<>(); //current players arraylist (some games split up the players)
  ArrayList<ArrayList<Minigame>> minigameList = new ArrayList<ArrayList<Minigame>>(); //arraylist of games
  ArrayList<Minigame> twoPlayerMinigameList = new ArrayList<Minigame>(); //games for 2 players
  ArrayList<Minigame> fourPlayerMinigameList = new ArrayList<Minigame>(); //games for 4 players

  // initializes DConsole
  public void DInit(int width, int height) {
    dc = new DConsole(width, height);
    dc.setOrigin(dc.ORIGIN_CENTER);
  }

  // runs methods from other classes
  public void run() {

    minigameList.add(twoPlayerMinigameList); //two player
    minigameList.add(fourPlayerMinigameList); //four player
    
    //making players for all games
    this.playerList.add(new Player(1, new Color (0, 0, 200), 200, 200, this.dc));
    this.playerList.add(new Player(2, new Color (17, 188, 76), 200, 200, this.dc));
    this.playerList.add(new Player(3, new Color (153, 51, 255), 200, 200, this.dc));
    this.playerList.add(new Player(4, new Color (200, 0, 0), 200, 200, this.dc));

    //---------------------------------------------------Add MiniGames Here------------------------------------------------------------------
    //Creating Minigames
    Minigame test = new TestGame(0, this.dc, this.cp);
    Minigame firstToTheTop = new FirstToTheTop(1, this.dc, this.cp);
    Minigame pong = new Pong(2, this.dc, this.cp);
    Minigame mazeRun = new MazeRun(3, this.dc, this.cp);
    Minigame pressTheButton = new PressTheButton(4, this.dc, this.playerList); //This game uses 4 players
    Minigame jump = new Jump(5, this.dc, this.cp);
    Minigame collectCoins = new CollectCoins(6, this.dc, this.cp);
    //Minigame findColor = new FindColor(7, this.dc, this.playerList);

    //add minigames to lists here
    twoPlayerMinigameList.add(mazeRun);
    twoPlayerMinigameList.add(collectCoins);
    twoPlayerMinigameList.add(pong);
    twoPlayerMinigameList.add(firstToTheTop);  
    fourPlayerMinigameList.add(jump);
    fourPlayerMinigameList.add(pressTheButton);
    fourPlayerMinigameList.add(findColor);

    //------------------------------------------------------------------------------------------------------------------------------------------

    //Game screens
    GameScreen n = new GameScreen(dc, this.playerList);

    //Title screen
    n.StartScreen();

    //Initilize game
    n.join();
    this.playerList = n.getActivePlayers();
    int playerAmount = n.getPlayerAmount();

    // gets the max amount of minigames that would exist in tempSubMinigameList by counting how many minigames exist in total
    int maxMinigameAmount = 0;
    for(int i = 0; i < minigameList.size(); i++) {
      for(int j = 0; j < minigameList.get(i).size(); j++) {
        maxMinigameAmount++;
      }
    }

     //stores all the minigames
    ArrayList<Minigame> tempSubMinigameList = new ArrayList<Minigame>();
    //put every single game in here
    for (int i = 0; i < minigameList.size(); i++) { //go through the 2player list and 4player list
      for (int j = 0; j < minigameList.get(i).size(); j++) { //go through each index in those lists
        tempSubMinigameList.add(minigameList.get(i).get(j)); //add each game
      }
    }
    
    // update sub minigame list with new active players
    for(int i = 0; i < tempSubMinigameList.size(); i++) {
      tempSubMinigameList.get(i).setPlayers(this.playerList);
    }

    //set names
    n.nicknames();
    
    // play 4 games
    for(int i = 0; i < 4; i++) {
      //Shuffle Minigames
      Collections.shuffle(tempSubMinigameList); //comment this out if you are testing a minigame

      //Players vote
      n.select(tempSubMinigameList); 

      //The selected minigame is a 2 player type
      if (findMinigame(tempSubMinigameList.get(n.getCurrentGame()).getID()) == twoPlayerMinigameList) {
        //Loop twice for 2 1v1s
        for (int j = 0; j <= 3; j+=2) { 
          cp.clear();
          cp.add(playerList.get(j)); //get the first player
          cp.add(playerList.get(j + 1)); //2nd player
          n.gameSwap(cp); //show which players are playing
          tempSubMinigameList.get(n.getCurrentGame()).setPlayers(cp); //only play with 2 players
          tempSubMinigameList.get(n.getCurrentGame()).play(); //play the game 
          n.miniWin(cp);
        }
      } else { //The minigame is a 4 player type
        tempSubMinigameList.get(n.getCurrentGame()).setPlayers(playerList); //play with everyone
        tempSubMinigameList.get(n.getCurrentGame()).play(); //play the game
        n.miniWin(playerList);
      }
      
      //use all 4 players to end the game
      tempSubMinigameList.get(n.getCurrentGame()).setPlayers(playerList);
      tempSubMinigameList.get(n.getCurrentGame()).endGame();
      
    }

    //Display the leaderboard
    n.winScreen();
    
  } //end of run

  //Player linear search
  public Player findByID(int id) {
    for (int i = 0; i < playerList.size(); i++) {
      if (id == playerList.get(i).getID()) {
        return playerList.get(i);
      }
    }
    return null;
  }

  //Get minigame folder
  public ArrayList<Minigame> findMinigame(int id) {
    for (int i = 0; i < minigameList.size(); i++) { //go through the 2player list and 4player list
      for (int j = 0; j < minigameList.get(i).size(); j++) { //go through each index in those lists
        if (id == minigameList.get(i).get(j).getID()) { //get id from the arraylist inside the arraylist
          return minigameList.get(i); //return the arraylist its in
        }
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
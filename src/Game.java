/**
 * [Game.java]
 * The game class which contains the list of players, and runs the game
 * @author Katelyn Wang and Brian Li
 * June 14 2018
 */

public class Game{
    private Player[] players;
    private Map map;
    private Space[][] tileMap;
    private int turn = 0;
    private boolean gameEnd = false; //Initialized to false, when game is over, will be set to true ending the game

    /**
     * Game Constructor
     * @param players the array of all the players
     * @param map The game's randomly generated map
     */
    Game(Player[] players, Map map){
        this.players = players;
        this.map = map;
        this.tileMap = map.getMap();
    }

    /**
     * Runs the game in a loop until it is won by someone
     */
    public void runGame(){
        do {
            removePlayersLost();
            turn++;
            for (int i = 0; i < players.length; i++){
                if (!checkGameEnd()) {//ensure the games still in play and that there isn't a winner yet in the middle of the turnloop
                    //loops each through player for each of their turns
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                    }
                    GameFrame gameFrame = new GameFrame(map, players[i].getMask(), players[i]); //during in each player's turn, creates new GameFrame with their stats
                    players[i].turnCurrencyIncrease(); //gives the player their currency increase per turn
                    players[i].turn();
                    players[i].setTurnEnd(false); //reinitialize boolean values which are used to end their turn
                    gameFrame.dispose(); //closes the window when the turn is over
                }
            }
        } while (!checkGameEnd()); //runs as long as the game is still in play
        new WinnerFrame(getWinnerName());
    }

    /**
     * Gets the current turn number
     * @return the turn number of the game
     */
    public int getTurn(){
        return this.turn;
    }

    /**
     * checks if the game is over
     * @return true if there is only one plauyer left, false if there are still 2 or more
     */
    private boolean checkGameEnd() {
        boolean tribesLeft[] = new boolean[players.length];
        int numTribesLeft = 0;
        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap.length; j++) {
                if ((tileMap[i][j].getCity() != null) && tileMap[i][j].getCity().getTribe() != 4) {
                    if (!(tribesLeft[tileMap[i][j].getCity().getTribe()]) && (tileMap[i][j].getCity().isCapital())) {
                        tribesLeft[tileMap[i][j].getCity().getTribe()] = true;
                        numTribesLeft++;
                    }
                }
            }
        }
        if (numTribesLeft == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Removed the players who no longer have their capital city out of the game
     */
    private void removePlayersLost() {
        boolean tribesLeft[] = new boolean[players.length];
        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap.length; j++) {
                if ((tileMap[i][j].getCity() != null) && (tileMap[i][j].getCity().getTribe() != 4)) {
                    if (!(tribesLeft[tileMap[i][j].getCity().getTribe()]) && (tileMap[i][j].getCity().isCapital())) {
                        tribesLeft[tileMap[i][j].getCity().getTribe()] = true;
                    }
                }
            }
        }
        for (int i = 0; i < players.length; i++) {
            if (i < players.length) { //Check that the player still exists (since removing from list inside the loop)
                if (!tribesLeft[players[i].getTribe()]) {
                    players[i] = null; //Remove a player from the game if they lost their capital city
                }
            }
        }
    }

    /**
     * Gets the last remaining player's name to display at the end
     * @return the winner's (the last reminaing person) name
     */
    private String getWinnerName() {
        for (int i = 0; i < players.length; i++) {
            if (players[i] != null) {
                return players[i].getName(); //Return the name of the only player left
            }
        }
        return "";
    }
}

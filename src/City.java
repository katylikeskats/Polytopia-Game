/**
 * [City.java]
 * The city object class
 * @author Katelyn Wang and Brian Li
 * June 14 2018
 */
public class City{
    private int row;
    private int col;
    private boolean capital;
    private int cityRadius;
    private int maxPop;
    private int maxUnits;
    private int currPop;
    private int currUnits;
    private int level;
    private int tribe;
    private Player player;
    private boolean capturing;

    /**
     * City Constructor
     * @param row The city's row coordinate in the map
     * @param col The city's column coordinate in the map
     * @param capital Boolean value which indicates if it is the capital or not; true if it is the capital, false if it isn't
     */
    public City(int row, int col, boolean capital){
        this.row = row;
        this.col = col;
        this.capital = capital;
        this.cityRadius = 1;
        this.maxUnits = 2;
        this.maxPop = 2;
        this.currPop = 0;
        this.level = 1;
        this.tribe = 4;
    }

    /**
     * Checks whether or not the city is the capital
     * @return true if it is the capital, false if it isn't
     */
    public boolean isCapital() {
        return capital;
    }

    /**
     * Gets the row coordinate
     * @return row coordinate
     */
    public int getRow() {
        return row;
    }

    /**
     * Sets row coordinate
     * @param row row coordinate
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Gets the column coordinate
     * @return column coordinate
     */
    public int getCol() {
        return col;
    }

    /**
     * Sets column coordinate
     * @param col column coordinate
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * Changes the capital status (used when assigning capital cities during intialization
     * @param capital boolean value which indicates if the city is the capital or not
     */
    public void setCapital(boolean capital) {
        this.capital = capital;
    }

    /**
     *
     * @return
     */
    public int getCityRadius() {
        return cityRadius;
    }

    /**
     *
     * @param cityRadius
     */
    public void setCityRadius(int cityRadius) {
        this.cityRadius = cityRadius;
    }

    /**
     * Gets the most amount of population the city can hold
     * @return maximum amount of population
     */
    public int getMaxPop() {
        return maxPop;
    }


    /**
     * Gets the most amount of units the city can support
     * @return maximum amount of units
     */
    public int getMaxUnits() {
        return maxUnits;
    }

    /**
     * Gets the current population
     * @return current population
     */
    public int getCurrPop() {
        return currPop;
    }

    /**
     * Sets the current population
     * @param currPop the new population of the city
     */
    public void setCurrPop(int currPop) {
        this.currPop = currPop;
    }

    /**
     * Gets the current number of supported units
     * @return current number of units
     */
    public int getCurrUnits() {
        return currUnits;
    }

    /**
     * Sets the current number of units
     * @param currUnits the new number of units
     */
    public void setCurrUnits(int currUnits) {
        this.currUnits = currUnits;
    }

    /**
     * Gets city level
     * @return city level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Gets the tribe to which the city belongs
     * @return the city's tribe
     */
    public int getTribe() {
        return tribe;
    }

    /**
     * Assigns this city to a tribe
     * @param tribe the city to which this tribe will be assigned
     */
    public void setTribe(int tribe) {
        this.tribe = tribe;
    }

    /**
     * Gets the player which owns this city
     * @return player which owns the city
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Assigns this city to whoever owns it
     * @param player the player who owns this city
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * When the population has met maximum population, will level up, increasing the number of units it can carry and resetting the population status
     */
    public void levelUp(){
       this.level++;
       this.currPop = 0;
       this.maxPop++;
       this.maxUnits++;
       //call on level up bonus offer
    }

    /**
     * Increases this city's population, checks if it should level up and responds appropriately
     * @param num the number by how much the city's population needs to be increased
     */
    public void increasePop(int num){
        if ((currPop + num) < maxPop) {
            this.currPop += num;
        } else {
            currPop = (currPop + num) - maxPop;
            levelUp();
        }
    }

    /**
     * Determines if there is available slots to create a new unit
     * @return true if there is space to create a new unit, false if there is no space
     */
    public boolean hasSpace(){
        if (currUnits<maxUnits){
            return true;
        }
        return false;
    }

    public void setCapturing(boolean capturing) {
        this.capturing = capturing;
    }

    public boolean getCapturing() {
        return capturing;
    }

}


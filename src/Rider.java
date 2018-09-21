/**
 * [Rider.java]
 * Rider class (object) that represents a rider unit in the Polytopia game
 * Authors: Katelyn Wang & Brian Li
 * Date: June 14, 2018
 */
public class Rider extends Unit{
    private static int cost = 3; //Integer to represent the cost to train it

    /**
     * Rider
     * This constructor takes in the row and column coordinates, the unit's tribe, and the unit's city, and sets them accordingly
     * @param An integer representing the unit's row coordinate on the game's map
     * @param An integer representing the unit's column coordinate on the game's map
     * @param An integer representing the unit's tribe
     * @param An integer representing the city that the unit belongs to
     * @return nothing
     */
    Rider(int r, int c, int tribe, City city){
        super(r, c, 2, 1, 10, 2, 1, tribe, city); //Set the values accordingly, as well as other unit stats
    }

    /**
     * getCost
     * This method returns the cost that it takes to train a rider
     * @param nothing
     * @return An integer representing the amount of currency (stars) that it costs to train this unit
     */
    public static int getCost(){
        return cost; //Return the cost to train the unit
    }
}

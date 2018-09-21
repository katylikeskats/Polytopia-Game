/**
 * [Swordsperson.java]
 * Swordsperson class (object) that represents a swordsperson unit in the Polytopia game
 * Authors: Katelyn Wang & Brian Li
 * Date: June 14, 2018
 */
 
public class Swordsperson extends Unit {
    private static int cost = 5; //Integer to represent the cost to train it

    /**
     * Swordsperson
     * This constructor takes in the row and column coordinates, the unit's tribe, and the unit's city, and sets them accordingly
     * @param r integer representing the unit's row coordinate on the game's map
     * @param c integer representing the unit's column coordinate on the game's map
     * @param tribe integer representing the unit's tribe
     * @param city integer representing the city that the unit belongs to
     * @return nothing
     */
    Swordsperson(int r, int c, int tribe, City city){
        super(r, c, 3, 3, 15, 1, 1, tribe, city); //Set the values accordingly, as well as other unit stats
    }

    /**
     * getCost
     * This method returns the cost that it takes to train a swordsperson
     * @return An integer representing the amount of currency (stars) that it costs to train this unit
     */
    public static int getCost(){
        return cost; //Return the cost to train the unit
    }
}

/**
 * [Archer.java]
 * The archer object class, is a type of unit
 * @author Katelyn Wang and Brian Li
 * June 14 2018
 */

public class Archer extends Unit {
    private static int cost = 3;
    /**
     * Archer Constructor
     * @param row The archer's row coordinate in the map
     * @param col The archer's column coordinate in the map
     * @param tribe Int representing the tribe the unit belongs to
     * @param city The city in which the unit was created
     */
    Archer(int row, int col, int tribe, City city){
        super(row, col, 2, 1, 10, 1, 2, tribe, city);
    }

    /**
    Returns the cost of the unit
     */
    public static int getCost(){
        return cost;
    }
}

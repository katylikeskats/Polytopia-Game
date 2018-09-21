/**
 * [Catapult.java]
 * The catapult object class, is a type of unit
 * @author Katelyn Wang and Brian Li
 * June 14 2018
 */
public class Catapult extends Unit{
    private static int cost = 8;
    /**
     * Catapult Constructor
     * @param row The catapult's row coordinate in the map
     * @param col The catapult's column coordinate in the map
     * @param tribe Int representing tribe the unit belongs to
     * @param city The city in which the unit was created
     */
    Catapult(int row, int col, int tribe, City city){
        super(row, col, 6, 0, 10, 1, 3, tribe, city);
    }

    public static int getCost(){
        return cost;
    }
}

/**
 * [Fruit.java]
 * The fruit object class, is a type of resource
 * @author Katelyn Wang and Brian Li
 * June 14 2018
 */

public class Fruit extends Resource {
    /**
     * Fruit Constructor
     * @param row The fruit's row coordinate in the map
     * @param col The fruit's column coordinate in the map
     */
    public Fruit(int row, int col){
        super(row, col, 1, 2);
    }
}

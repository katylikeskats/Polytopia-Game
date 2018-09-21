/**
 * [Forest.java]
 * The forest object class, is a type of resource
 * @author Katelyn Wang and Brian Li
 * June 14 2018
 */

public class Forest extends Resource{
    /**
     * Forest Constructor
     * @param row The forest's row coordinate in the map
     * @param col The forest's column coordinate in the map
     */
    Forest(int row, int col){
        super(row, col, 1, 2);
    }
}

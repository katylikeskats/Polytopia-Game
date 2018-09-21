/**
 * [Crop.java]
 * The crop object class, is a type of resource
 * @author Katelyn Wang and Brian Li
 * June 14 2018
 */

public class Crop extends Resource{
    /**
     * Crop Constructor
     * @param row The crop's row coordinate in the map
     * @param col The crop's column coordinate in the map
     */
    public Crop(int row, int col){
        super(row, col, 2, 5);
    }
}

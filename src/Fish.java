/**
 * [Fish.java]
 * The fish object class, is a type of resource
 * @author Katelyn Wang and Brian Li
 * June 14 2018
 */

public class Fish extends Resource{
    /**
     * Fish Constructor
     * @param row The fish's row coordinate in the map
     * @param col The fish's column coordinate in the map
     */
    public Fish(int row, int col){
        super(row, col, 1, 2);
    }
}


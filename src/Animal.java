/**
 * [Animal.java]
 * The animal object class, is a type of resource
 * @author Katelyn Wang and Brian Li
 * June 14 2018
 */

public class Animal extends Resource{
    /**
     * Animal Constructor
     * @param row The animal's row coordinate on the map
     * @param col The animal's column coordinate on the map
     */
    public Animal(int row, int col){
        super(row, col, 1, 2);
    }
}

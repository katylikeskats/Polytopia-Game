/**
 * [Warrior.java]
 * The warrior class, a type of unit
 * @author Katelyn Wang and Brian Li
 * June 14 2018
 */
public class Warrior extends Unit {
    private static int cost = 2;

    Warrior (int r, int c, int tribe, City city){
        super(r, c, 2, 2, 10, 1, 1, tribe, city);
    }

    public static int getCost(){
        return cost;
    }
}

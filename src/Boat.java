/**
 * [Boat.java]
 * The boat object class, is a type of unit
 * @author Katelyn Wang and Brian Li
 * June 14 2018
 */
public class Boat extends Unit {

    private Unit unit;
    private int level = 0;
    Boat (int r, int c, Unit unit){
        super (r, c, 2, 3, unit.getCurrHealth(), 1, 2, unit.getTribe(), unit.getCity());
        this.unit = unit;
    }

    /**
     * Needs to override setCurrHealth method as the unit in the boat takes damage too
     * @param health
     */
    @Override
    public void setCurrHealth(int health) {
        super.setCurrHealth(health);
        unit.setCurrHealth(health);
    }

    @Override
    public void levelUp() {
        this.setAttack(this.getAttack()+5);
        this.level++;
    }

    public Unit getUnitContained() {
        return unit;
    }

    public int getLevel(){
        return this.level;
    }

}
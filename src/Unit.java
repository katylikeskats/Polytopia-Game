/**
 * [Unit.java]
 * The unit object class
 * @author Katelyn Wang and Brian Li
 * June 14 2018
 */
abstract public class Unit {
    private int r;
    private int c;
    private int maxHealth;
    private int currHealth;
    private int movement;
    private int attack;
    private int defense;
    private int range;
    private int tribe;
    private int killCount;
    private City city;
    private boolean moved; //To indicate if a unit has moved already in a turn (set to having been moved when created)

    public Unit(int r, int c, int attack, int defense, int health, int movement, int range, int tribe, City city){
        this.r = r;
        this.c = c;
        this.attack = attack;
        this.defense = defense;
        this.currHealth = health;
        this.maxHealth = health;
        this.movement = movement;
        this.range = range;
        this.tribe = tribe;
        this.city = city;
        //this.moved = true;
    }

    public boolean getMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public City getCity() { return city; }

    public void setCity(City city) {
        this.city = city;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int health) {
        this.maxHealth = health;
    }

    public int getCurrHealth() {
        return currHealth;
    }

    public void setCurrHealth(int currHealth) {
        this.currHealth = currHealth;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getRange() {
        return range;
    }

    public int getTribe() {
        return tribe;
    }

    public void setTribe(int tribe) {
        this.tribe = tribe;
    }

    public int getKillCount() {
        return killCount;
    }

    public void setKillCount(int killCount) {
        this.killCount = killCount;
    }

    public int getDamage(Unit other) {
        double attackForce = this.attack * (this.currHealth / this.maxHealth);
        double defenseForce = other.getDefense() * (other.getCurrHealth() / other.getMaxHealth());
        double totalDamage = attackForce + defenseForce;
        return (int) Math.round((attackForce / totalDamage) * this.attack * 4.5);
    }

    public void levelUp() {
        this.currHealth = maxHealth + 5;
        this.maxHealth += 5;
        this.killCount = 0;
    }
}
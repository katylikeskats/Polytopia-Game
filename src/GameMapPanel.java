/**
 * [GameMapPanel.java]
 * The panel which displays the map
 * @author Katelyn Wang and Brian Li
 * June 14 2018
 */

import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Color;

//Mouse imports
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

class GameMapPanel extends JPanel{

    static int selectedR;
    static int selectedC;
    static int selectedR2;
    static int selectedC2;

    static boolean unitSelected;
    static boolean citySelected;
    static boolean resourceSelected;

    static boolean createPort;
    static boolean resourceHarvest;
    static boolean trainUnit;
    private static String trainUnitType;
    private static Resource selectedResource;
    private static Unit selectedUnit;

    private Map map;
    private Space[][] tileMap;
    private Interactions interactions;
    static int tileDim; //Size of a tile (length)

    boolean[][] playerMask;
    Player player;

    private boolean unitMove;
    private int r = 0;
    private int c = 0;

    private static final Image redTarget = Toolkit.getDefaultToolkit().getImage("assets/RedTarget.png");
    private static final Image greyTarget = Toolkit.getDefaultToolkit().getImage("assets/GreyTarget.png");
    private static final Image grassImage = Toolkit.getDefaultToolkit().getImage("assets/Grass.png");
    private static final Image waterImage = Toolkit.getDefaultToolkit().getImage("assets/Water.png");
    private static final Image mountainImage = Toolkit.getDefaultToolkit().getImage("assets/Mountain.png");
    private static final Image[] warrior = {Toolkit.getDefaultToolkit().getImage("assets/Warrior1.png"),
            Toolkit.getDefaultToolkit().getImage("assets/Warrior2.png"),
            Toolkit.getDefaultToolkit().getImage("assets/Warrior3.png"),
            Toolkit.getDefaultToolkit().getImage("assets/Warrior4.png"),
            Toolkit.getDefaultToolkit().getImage("assets/Warrior5.png")};
    private static final Image[] swordsperson = {Toolkit.getDefaultToolkit().getImage("assets/Swordsperson1.png"),
            Toolkit.getDefaultToolkit().getImage("assets/Swordsperson2.png"),
            Toolkit.getDefaultToolkit().getImage("assets/Swordsperson3.png"),
            Toolkit.getDefaultToolkit().getImage("assets/Swordsperson4.png"),
            Toolkit.getDefaultToolkit().getImage("assets/Swordsperson5.png")};
    private static final Image[] rider = {Toolkit.getDefaultToolkit().getImage("assets/Rider1.png"),
            Toolkit.getDefaultToolkit().getImage("assets/Rider2.png"),
            Toolkit.getDefaultToolkit().getImage("assets/Rider3.png"),
            Toolkit.getDefaultToolkit().getImage("assets/Rider4.png"),
            Toolkit.getDefaultToolkit().getImage("assets/Rider5.png")};
    private static final Image[] knight = {Toolkit.getDefaultToolkit().getImage("assets/Knight1.png"),
            Toolkit.getDefaultToolkit().getImage("assets/Knight2.png"),
            Toolkit.getDefaultToolkit().getImage("assets/Knight3.png"),
            Toolkit.getDefaultToolkit().getImage("assets/Knight4.png"),
            Toolkit.getDefaultToolkit().getImage("assets/Knight5.png")};
    private static final Image[] archer = {Toolkit.getDefaultToolkit().getImage("assets/Archer1.png"),
            Toolkit.getDefaultToolkit().getImage("assets/Archer2.png"),
            Toolkit.getDefaultToolkit().getImage("assets/Archer3.png"),
            Toolkit.getDefaultToolkit().getImage("assets/Archer4.png"),
            Toolkit.getDefaultToolkit().getImage("assets/Archer5.png")};
    private static final Image[] catapult = {Toolkit.getDefaultToolkit().getImage("assets/Catapult1.png"),
            Toolkit.getDefaultToolkit().getImage("assets/Catapult2.png"),
            Toolkit.getDefaultToolkit().getImage("assets/Catapult3.png"),
            Toolkit.getDefaultToolkit().getImage("assets/Catapult4.png"),
            Toolkit.getDefaultToolkit().getImage("assets/Catapult5.png")};
    private static final Image[] mindbender = {Toolkit.getDefaultToolkit().getImage("assets/Mindbender1.png"),
            Toolkit.getDefaultToolkit().getImage("assets/Mindbender2.png"),
            Toolkit.getDefaultToolkit().getImage("assets/Mindbender3.png"),
            Toolkit.getDefaultToolkit().getImage("assets/Mindbender4.png"),
            Toolkit.getDefaultToolkit().getImage("assets/Mindbender5.png")};
    private static final Image[] defender = {Toolkit.getDefaultToolkit().getImage("assets/Defender1.png"),
            Toolkit.getDefaultToolkit().getImage("assets/Defender2.png"),
            Toolkit.getDefaultToolkit().getImage("assets/Defender3.png"),
            Toolkit.getDefaultToolkit().getImage("assets/Defender4.png"),
            Toolkit.getDefaultToolkit().getImage("assets/Defender5.png")};
    private static final Image[][] boat = {
            {Toolkit.getDefaultToolkit().getImage("assets/Boat11.png"),
                    Toolkit.getDefaultToolkit().getImage("assets/Boat12.png"),
                    Toolkit.getDefaultToolkit().getImage("assets/Boat13.png"),
                    Toolkit.getDefaultToolkit().getImage("assets/Boat14.png"),
                    Toolkit.getDefaultToolkit().getImage("assets/Boat15.png")},

            {Toolkit.getDefaultToolkit().getImage("assets/Boat21.png"),
                    Toolkit.getDefaultToolkit().getImage("assets/Boat22.png"),
                    Toolkit.getDefaultToolkit().getImage("assets/Boat23.png"),
                    Toolkit.getDefaultToolkit().getImage("assets/Boat24.png"),
                    Toolkit.getDefaultToolkit().getImage("assets/Boat25.png")},

            {Toolkit.getDefaultToolkit().getImage("assets/Boat31.png"),
                    Toolkit.getDefaultToolkit().getImage("assets/Boat32.png"),
                    Toolkit.getDefaultToolkit().getImage("assets/Boat33.png"),
                    Toolkit.getDefaultToolkit().getImage("assets/Boat34.png"),
                    Toolkit.getDefaultToolkit().getImage("assets/Boat35.png")}};
    private static final Image[] cityImage = {Toolkit.getDefaultToolkit().getImage("assets/AquarionCity.png"),
            Toolkit.getDefaultToolkit().getImage("assets/ImperiusCity.png"),
            Toolkit.getDefaultToolkit().getImage("assets/XinXiCity.png"),
            Toolkit.getDefaultToolkit().getImage("assets/OumanjiCity.png"),
            Toolkit.getDefaultToolkit().getImage("assets/City.png"),};
    private static final Image animalImage = Toolkit.getDefaultToolkit().getImage("assets/Animal.png");
    private static final Image fishImage = Toolkit.getDefaultToolkit().getImage("assets/Fish.png");
    private static final Image fruitImage = Toolkit.getDefaultToolkit().getImage("assets/Fruit.png");
    private static final Image treeImage = Toolkit.getDefaultToolkit().getImage("assets/Tree.png");
    private static final Image cropImage = Toolkit.getDefaultToolkit().getImage("assets/Crop.png");
    private static final Image whaleImage = Toolkit.getDefaultToolkit().getImage("assets/Whale.png");
    private static final Image starImage = Toolkit.getDefaultToolkit().getImage("assets/Star.png");
    private static final Image cloudImage = Toolkit.getDefaultToolkit().getImage("assets/Cloud.png");
    private static final Image portImage = Toolkit.getDefaultToolkit().getImage("assets/Port.png");

    Color grey = new Color(100,100,100);

    /**
     *
     * @param map
     * @param height
     * @param mask
     * @param player
     */
    GameMapPanel(Map map, int height, boolean[][] mask, Player player) {
        this.map = map;
        interactions = new Interactions(map);
        this.tileMap = map.getMap();

        setSize(new Dimension(height, height));
        tileDim = (height/tileMap.length);

        MyMouseListener mouseListener = new MyMouseListener();
        this.addMouseListener(mouseListener);

        playerMask = mask;
        this.player = player;

    }

    /**
     *
     * @param g
     */
    public void paintComponent(Graphics g) {
        int currX = 1; //For later (like menu option shit)
        int currY = 1; //For later
        super.paintComponent(g); //required
        setDoubleBuffered(true); //What is this

        //Tile types
        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap.length; j++) {
                if (tileMap[i][j].getTerrain() instanceof Water) {
                    g.drawImage(waterImage, (tileDim*j), (tileDim*i), tileDim, tileDim, this);
                } else if (tileMap[i][j].getTerrain() instanceof Grass) {
                    g.drawImage(grassImage, (tileDim*j), (tileDim*i), tileDim, tileDim, this);
                } else if (tileMap[i][j].getTerrain() instanceof Mountain) {
                    g.drawImage(mountainImage, (tileDim*j), (tileDim*i), tileDim, tileDim, this);
                }
                if (tileMap[i][j].getTerrain() instanceof Port){
                    g.drawImage(portImage, (tileDim*j), (tileDim*i), tileDim, tileDim, this);
                }
                if (tileMap[i][j].getCity() != null) {
                    g.drawImage(cityImage[tileMap[i][j].getCity().getTribe()], (tileDim * j), (tileDim * i), tileDim, tileDim, this);
                }
            }
        }

        //Resources
        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap.length; j++) {
                if (tileMap[i][j].getResource() instanceof Fruit) {
                    g.drawImage(fruitImage, (tileDim*j), (tileDim*i), tileDim, tileDim, this);
                } else if (tileMap[i][j].getResource() instanceof Crop) {
                    g.drawImage(cropImage, (tileDim*j), (tileDim*i), tileDim, tileDim, this);
                } else if (tileMap[i][j].getResource() instanceof Fish) {
                    g.drawImage(fishImage, (tileDim*j), (tileDim*i), tileDim, tileDim, this);
                } else if (tileMap[i][j].getResource() instanceof Forest) {
                    g.drawImage(treeImage, (tileDim*j), (tileDim*i), tileDim, tileDim, this);
                } else if (tileMap[i][j].getResource() instanceof Animal) {
                    g.drawImage(animalImage, (tileDim*j), (tileDim*i), tileDim, tileDim, this);
                } else if (tileMap[i][j].getResource() instanceof Whale) {
                    g.drawImage(whaleImage, (tileDim*j), (tileDim*i), tileDim, tileDim, this);
                }
            }
        }

        //Units
        for (int row = 0; row < tileMap.length; row++) {
            for (int col = 0; col < tileMap.length; col++) {
                if (tileMap[row][col].getUnit() instanceof Warrior) {
                    g.drawImage(warrior[tileMap[row][col].getUnit().getTribe()], (tileDim * col), (tileDim * row), tileDim, tileDim, this);
                } else if (tileMap[row][col].getUnit() instanceof Swordsperson) {
                    g.drawImage(swordsperson[tileMap[row][col].getUnit().getTribe()], (tileDim * col), (tileDim * row), tileDim, tileDim, this);
                } else if (tileMap[row][col].getUnit() instanceof Rider) {
                    g.drawImage(rider[tileMap[row][col].getUnit().getTribe()], (tileDim * col), (tileDim * row), tileDim, tileDim, this);
                } else if (tileMap[row][col].getUnit() instanceof Knight) {
                    g.drawImage(knight[tileMap[row][col].getUnit().getTribe()], (tileDim * col), (tileDim * row), tileDim, tileDim, this);
                } else if (tileMap[row][col].getUnit() instanceof Archer) {
                    g.drawImage(archer[tileMap[row][col].getUnit().getTribe()], (tileDim * col), (tileDim * row), tileDim, tileDim, this);
                } else if (tileMap[row][col].getUnit() instanceof Catapult) {
                    g.drawImage(catapult[tileMap[row][col].getUnit().getTribe()], (tileDim * col), (tileDim * row), tileDim, tileDim, this);
                } else if (tileMap[row][col].getUnit() instanceof Mindbender) {
                    g.drawImage(mindbender[tileMap[row][col].getUnit().getTribe()], (tileDim * col), (tileDim * row), tileDim, tileDim, this);
                } else if (tileMap[row][col].getUnit() instanceof Defender) {
                    g.drawImage(defender[tileMap[row][col].getUnit().getTribe()], (tileDim * col), (tileDim * row), tileDim, tileDim, this);
                } else if (tileMap[row][col].getUnit() instanceof Boat){
                    g.drawImage(boat[((Boat)tileMap[row][col].getUnit()).getLevel()][tileMap[row][col].getUnit().getTribe()], (tileDim * col), (tileDim * row), tileDim, tileDim, this);
                }
            }
        }

        //City borders
        //Have to change this if we're doing border expansion
        for (int i = 0; i < map.getMap().length; i++) {
            for (int j = 0; j < map.getMap().length; j++) {
                if (map.getMap()[i][j].getCity() != null) {
                    if (map.getMap()[i][j].getCity().getTribe() != 4) {
                        if (map.getMap()[i][j].getCity().getTribe() == player.getTribe()) {
                            g.setColor(Color.YELLOW);
                        } else {
                            g.setColor(Color.RED);
                        }
                        g.drawRect((tileDim * (j - map.getMap()[i][j].getCity().getCityRadius())), (tileDim * (i - map.getMap()[i][j].getCity().getCityRadius())), (tileDim * ((map.getMap()[i][j].getCity().getCityRadius() * 2) + 1)), (tileDim * ((map.getMap()[i][j].getCity().getCityRadius() * 2) + 1)));
                    }
                }
            }
        }

        //City pop/max unit num indicators
        //Works for max populations UP TO 5 ONLY!!!!
        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap.length; j++) {
                if (tileMap[i][j].getCity() != null) {
                    g.setColor(Color.BLACK);
                    for (int a = 0; a < tileMap[i][j].getCity().getMaxPop(); a++) { //Display the boxes for population
                        if (((tileMap[i][j].getCity().getMaxPop() == 3) && (a == 2)) || ((tileMap[i][j].getCity().getMaxPop() > 3) && (a == 3))) {
                            g.drawRect((int)(((tileDim*j)+(tileDim/2)-(((double)(tileMap[i][j].getCity().getLevel()+1)*((double)(tileDim)/4))/2))+(a*((double)(tileDim)/4)))+1, ((tileDim*(i+1))-(tileDim/5)), (int)(Math.round((double)(tileDim)/4))-1, (tileDim/5)+(tileDim/10));
                        } else {
                            g.drawRect((int)(((tileDim*j)+(tileDim/2)-(((double)(tileMap[i][j].getCity().getLevel()+1)*((double)(tileDim)/4))/2))+(a*((double)(tileDim)/4))), ((tileDim*(i+1))-(tileDim/5)), (int)(Math.round((double)(tileDim)/4)), (tileDim/5)+(tileDim/10));
                        }
                    }
                    g.setColor(Color.BLUE);
                    for (int a = 0; a < tileMap[i][j].getCity().getMaxPop(); a++) { //Display the current population (filled in bars)
                        if (a >= tileMap[i][j].getCity().getCurrPop()) {
                            g.setColor(grey);
                        }
                        if (((tileMap[i][j].getCity().getMaxPop() == 3) && (a == 2)) || ((tileMap[i][j].getCity().getMaxPop() > 3) && (a == 3))) {
                            g.fillRect((int)(((tileDim*j)+(tileDim/2)-(((double)(tileMap[i][j].getCity().getLevel()+1)*((double)(tileDim)/4))/2))+(a*((double)(tileDim)/4)))+2, ((tileDim*(i+1))-(tileDim/5))+1, (int)(Math.round((double)(tileDim)/4))-2, ((tileDim/5)+(tileDim/10))-1);
                        } else {
                            g.fillRect((int)(((tileDim*j)+(tileDim/2)-(((double)(tileMap[i][j].getCity().getLevel()+1)*((double)(tileDim)/4))/2))+(a*((double)(tileDim)/4)))+1, ((tileDim*(i+1))-(tileDim/5))+1, (int)(Math.round((double)(tileDim)/4))-1, ((tileDim/5)+(tileDim/10))-1);
                        }
                    }
                    g.setColor(Color.WHITE); //Draws white circles if they aren't on a blue filled rectangle
                    for (int a = 0; a < tileMap[i][j].getCity().getCurrUnits(); a++) {
                        if (a >= tileMap[i][j].getCity().getCurrPop()) {
                            g.setColor(Color.BLACK); //If there are more units than there is current population, start drawing black circles (since the background will now be black)
                        }
                        g.fillOval((int)(((tileDim*j)+(tileDim/2)-(((double)(tileMap[i][j].getCity().getLevel()+1)*((double)(tileDim)/4))/2))+(a*((double)(tileDim)/4)))+4, ((tileDim*(i+1))-(tileDim/5))+4, (int)(Math.round((double)(tileDim)/4))-8, ((tileDim/5)+(tileDim/10))-8);
                    }
                    if (tileMap[i][j].getCity().isCapital()) {
                        g.drawImage(starImage, (int)((tileDim*j)+(tileDim/2)-(((double)(tileMap[i][j].getCity().getLevel()+1)*((double)(tileDim)/4))/2))-(int)(Math.round((double)(tileDim)/4)), ((tileDim*(i+1))-(tileDim/5)), (int)(Math.round((double)(tileDim)/4)), ((tileDim/5)+(tileDim/10)), this);
                    }
                }
            }
        }

        //Cover stuff with mask
        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap.length; j++) {
                if (playerMask[i][j]) {
                    g.drawImage(cloudImage, (tileDim*j), (tileDim*i), tileDim, tileDim, this);
                }
            }
        }


        //Display movement and attack targets for a selected unit
        //Now the targets display, but only within the city?
        if ((unitSelected) && !(tileMap[selectedR][selectedC].getUnit().getMoved())) { //If a unit is selected
            for (int i = -(tileMap[selectedR][selectedC].getUnit().getMovement()); i <= tileMap[selectedR][selectedC].getUnit().getMovement(); i++){
                for (int j = -(tileMap[selectedR][selectedC].getUnit().getMovement()); j <= tileMap[selectedR][selectedC].getUnit().getMovement(); j++) {
                    if ((!((i == 0) && (j == 0))) && ((selectedR + i) >= 0) && ((selectedC + j) >= 0) && ((selectedR + i) < tileMap.length) && ((selectedC + j) < tileMap.length)) {
                        if (interactions.validateMove(tileMap[selectedR][selectedC].getUnit(), selectedR + i, selectedC + j, player, playerMask)) {
                            g.drawImage(greyTarget, (tileDim*(j + selectedC)), (tileDim*(i + selectedR)), tileDim, tileDim, this);
                            //Ensure that if attacking another unit, and the another unit is killed, the unit defeating it cannot move onto the tile unless it has tech required
                        } else if ((tileMap[selectedR + i][selectedC + j].getUnit() != null) && (interactions.validateAttack(tileMap[selectedR][selectedC].getUnit(), selectedR + i, selectedC + j, player))){
                            g.drawImage(redTarget, (tileDim*(j + selectedC)), (tileDim*(i + selectedR)), tileDim, tileDim, this);
                        }
                    }
                }
            }
        }


        //UNIT MOVEMENT AND ATTACKING
        if ((unitMove) && !(tileMap[selectedR][selectedC].getUnit().getMoved())) { //If the player selected the unit, then clicked on a different tile
            //if the spot under newR, newC is a unit, validate attack with unit on selectedR, selectedC, and putting in selectedR2 and C2
            if (tileMap[selectedR2][selectedC2].getUnit() != null) {
                if (interactions.validateAttack(tileMap[selectedR][selectedC].getUnit(), selectedR2, selectedC2, player)) {
                    tileMap[selectedR][selectedC].getUnit().setMoved(true); //Set the unit to having moved this turn
                    interactions.attack(tileMap[selectedR][selectedC].getUnit(), tileMap[selectedR2][selectedC2].getUnit());
                    if (tileMap[selectedR2][selectedC2].getUnit() == null) {
                        if (interactions.validateMove(tileMap[selectedR][selectedC].getUnit(), selectedR2, selectedC2, player, playerMask)) {
                            interactions.move(tileMap[selectedR][selectedC].getUnit(), selectedR2, selectedC2, playerMask); //Make the unit that just defeated another unit (if applicable) move onto that space
                        }
                    }
                }
            } else { //If the unit is to move (not attack)
                if (interactions.validateMove(tileMap[selectedR][selectedC].getUnit(), selectedR2, selectedC2, player, playerMask)) {
                    tileMap[selectedR][selectedC].getUnit().setMoved(true); //Set the unit to having moved this turn
                    interactions.move(tileMap[selectedR][selectedC].getUnit(), selectedR2, selectedC2, playerMask);
                }
            }
            //Reset these two after whatever is clicked on after unitMove is activated
            unitSelected = false; //Reset to say no unit is selected if the above is false (cannot attack)
            unitMove = false; //Reset to say no unit is going to be moved (attempted to) if above is false
        } else if (unitMove) {
            unitSelected = false;
            unitMove = false;
        }


        if (trainUnit) {
            interactions.trainUnit(player, trainUnitType, selectedR, selectedC);
                trainUnit = false;
                citySelected = false;
                GameFrame.setDisplayOptions(false);
            }


        //If a resource is selected, and the button in the options panel was selected to confirm
        if (resourceHarvest) {
            if (interactions.harvestItem(player, selectedR, selectedC)) { //Check if the resource can be harvested (and harvest it if so)
                resourceHarvest = false; //Set it so that no resource is selected/to be harvested if one was just harvested
                resourceSelected = false;
                GameFrame.setDisplayOptions(false); //Make the options panel disappear if a resource was just harvested
            }
        }

        if (createPort){
            if (interactions.portCheck(selectedR, selectedC)) {
                interactions.makePort(selectedR, selectedC, player);
            }
            createPort = false;
            OptionsPanel.showPort = false;
        }

    }



    //Put in thing to ensure that they can click on neutral space
    private class MyMouseListener implements MouseListener {

        public void mouseClicked(MouseEvent e) {

        }

        public void mousePressed(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            int option = 0;
            if ((x <= tileDim * (tileMap.length)) && (x >= 0) && (y <= tileDim * (tileMap.length)) && (y >= 0)) {
                if (unitSelected) {
                    selectedR2 = (int) Math.floor((((double) y) ) / ((double) tileDim));
                    selectedC2 = (int) Math.floor((((double) x) ) / ((double) tileDim));
                } else {
                    selectedR = (int) Math.floor((((double) y) ) / ((double) tileDim));
                    selectedC = (int) Math.floor((((double) x) ) / ((double) tileDim));
                }
                option = interactions.displayOptions(selectedR, selectedC, selectedR2, selectedC2, unitSelected, playerMask);
            }
            if (option == 1) {
                resourceSelected = false;
                citySelected = false;
                GameFrame.setDisplayOptions(false);
                resourceHarvest = false;
                trainUnit = false;

                if (!unitSelected) {
                    unitSelected = true;
                }
                selectedUnit = tileMap[selectedR][selectedC].getUnit();
            } else if (option == 2) {
                resourceSelected = false;
                unitSelected = false;
                GameFrame.setDisplayOptions(false);
                OptionsPanel.showUnit = false;
                OptionsPanel.showResource = false;
                OptionsPanel.showPort = false;
                resourceHarvest = false;
                trainUnit = false;

                if (!citySelected){
                    citySelected = true;
                    GameFrame.setDisplayOptions(true);
                    OptionsPanel.showUnit = true;

                } else {
                    citySelected = false;
                }

            } else if (option == 3) {
                unitSelected = false;
                citySelected = false;
                GameFrame.setDisplayOptions(false);
                OptionsPanel.showUnit = false;
                OptionsPanel.showResource = false;
                OptionsPanel.showPort = false;
                resourceHarvest = false;
                trainUnit = false;

                if (!resourceSelected) {
                    resourceSelected = true; //Ok at least this part works
                    GameFrame.setDisplayOptions(true);
                    OptionsPanel.showResource = true;
                    selectedResource = tileMap[selectedR][selectedC].getResource();
                } else {
                    resourceSelected = false;
                }
            } else if (option == 4) {
                unitMove = true;
            } else if (option == 5) {
                resourceSelected = false;
                unitSelected = false;
                citySelected = false;
                resourceHarvest = false;
                trainUnit = false;
                GameFrame.setDisplayOptions(false);
                OptionsPanel.showResource = true;
            } else if (option == 6){
                resourceSelected = false;
                unitSelected = false;
                citySelected = false;
                resourceHarvest = false;
                OptionsPanel.showResource = false;
                OptionsPanel.showUnit = false;
                trainUnit = false;
                GameFrame.setDisplayOptions(false);
                if (!OptionsPanel.showPort) {
                    GameFrame.setDisplayOptions(true);
                    OptionsPanel.showPort = true;
                } else {
                    OptionsPanel.showPort = false;
                }
            }

        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

    } //end of mouselistener

    /**
     *
     * @param bool
     */
    public static void setTrainUnit(boolean bool){
        trainUnit = bool;
    }

    /**
     *
     * @param unitType
     */
    public static void setTrainUnitType(String unitType){
        trainUnitType = unitType;
    }

    public static Resource getSelectedResource(){
        return selectedResource;
    }

    public static Unit getSelectedUnit(){
        return selectedUnit;
    }

}
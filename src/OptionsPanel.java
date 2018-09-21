/**
 * [OptionsPanel.java]
 * Options panel which gives players the option to harvest resources, build ports or buy units
 * @author Katelyn Wang and Brian Li
 * June 14 2018
 */

//Swing imports
import javax.swing.JPanel;

//Graphics &GUI imports
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Color;

//Mouse imports
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


class OptionsPanel extends JPanel {


  static boolean showPort;
    static boolean showUnit;
    static boolean showResource;
    private static int initialX = 550;
    private static int changeX = 90;
    private static int diameter = 60;
    private static int[][] unitCoords = new int[9][2];
    private static final String[] allUnits = {"Warrior", "Swordsperson", "Rider", "Knight", "Archer", "Catapult", "Defender", "Mindbender", "Ninja"};
    private static final int[] costs = {2, 5, 3, 8, 3, 8, 3, 5, 3};
    private static int prevClick;
    //private static boolean displayConfirmation = false;

    private static Image[] headPic = {Toolkit.getDefaultToolkit().getImage("assets/AquarionHead.png"),
            Toolkit.getDefaultToolkit().getImage("assets/ImperiusHead.png"),
            Toolkit.getDefaultToolkit().getImage("assets/XinXiHead.png"),
            Toolkit.getDefaultToolkit().getImage("assets/OumanjiHead.png")};
    private static final Image warrior = Toolkit.getDefaultToolkit().getImage("assets/Warrior.png");
    private static final Image swordsperson = Toolkit.getDefaultToolkit().getImage("assets/Swordsperson.png");
    private static final Image rider = Toolkit.getDefaultToolkit().getImage("assets/Rider.png");
    private static final Image knight = Toolkit.getDefaultToolkit().getImage("assets/Knight.png");
    private static final Image archer = Toolkit.getDefaultToolkit().getImage("assets/Archer.png");
    private static final Image catapult = Toolkit.getDefaultToolkit().getImage("assets/Catapult.png");
    private static final Image defender = Toolkit.getDefaultToolkit().getImage("assets/Defender.png");
    private static final Image mindbender = Toolkit.getDefaultToolkit().getImage("assets/Mindbender.png");
    private static final Image ninja = Toolkit.getDefaultToolkit().getImage("assets/Ninja.png");
    private static final Image greyWarrior = Toolkit.getDefaultToolkit().getImage("assets/GreyWarrior.png");
    private static final Image greySwordsperson = Toolkit.getDefaultToolkit().getImage("assets/GreySwordsperson.png");
    private static final Image greyRider = Toolkit.getDefaultToolkit().getImage("assets/GreyRider.png");
    private static final Image greyKnight = Toolkit.getDefaultToolkit().getImage("assets/GreyKnight.png");
    private static final Image greyArcher = Toolkit.getDefaultToolkit().getImage("assets/GreyArcher.png");
    private static final Image greyCatapult = Toolkit.getDefaultToolkit().getImage("assets/GreyCatapult.png");
    private static final Image greyDefender = Toolkit.getDefaultToolkit().getImage("assets/GreyDefender.png");
    private static final Image greyMindbender = Toolkit.getDefaultToolkit().getImage("assets/GreyMindbender.png");
    private static final Image greyNinja = Toolkit.getDefaultToolkit().getImage("assets/GreyNinja.png");
    private static final Image portImage = Toolkit.getDefaultToolkit().getImage("assets/Port.png");
    private static final Image fruitImage = Toolkit.getDefaultToolkit().getImage("assets/Fruit.png");
    private static final Image starImage = Toolkit.getDefaultToolkit().getImage("assets/Star2.png");
    private static final Image greenCheck = Toolkit.getDefaultToolkit().getImage("assets/Checkmark.png");
    Player player;

    //Constructor - this runs first
    OptionsPanel(int height, int width, Player player) {
        this.player = player;
        this.setSize(width, height);
        this.setBackground(new Color(221, 221, 221));

        OptionsMouseListener optionsListener = new OptionsMouseListener();
        this.addMouseListener(optionsListener);

    } //End of Constructor


    /** --------- INNER CLASSES ------------- **/

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setDoubleBuffered(true);
        Font titleFont = Utilities.getFont("assets/AdequateLight.ttf", 20f);
        Font textFont = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
        if (showUnit){
            g.setColor(Color.black);


            decideToDraw(g, "Warrior", warrior, greyWarrior, initialX, changeX, diameter, 0);
            decideToDraw(g, "Swordsperson", swordsperson, greySwordsperson,  initialX, changeX, diameter, 1);
            decideToDraw(g, "Rider", rider, greyRider, initialX, changeX, diameter, 2);
            decideToDraw(g, "Knight", knight, greyKnight, initialX, changeX, diameter, 3);
            decideToDraw(g, "Archer", archer, greyArcher, initialX, changeX, diameter, 4);
            decideToDraw(g, "Catapult", catapult, greyCatapult,  initialX, changeX, diameter, 5);
            decideToDraw(g, "Defender", defender, greyDefender,  initialX, changeX, diameter, 6);
            decideToDraw(g, "Mindbender", mindbender, greyMindbender,  initialX, changeX, diameter, 7);
            decideToDraw(g, "Ninja", ninja, greyNinja, initialX, changeX, diameter, 8);

            g.setFont(titleFont);
            g.drawString("Unit Options!", 30, 30);
            g.drawImage(headPic[player.getTribe()], 70, 40, 60, 60, this);
        }
        if (showResource){
            g.setFont(titleFont);
            g.drawString("Harvest Resource?",  430,30);
            g.drawImage(fruitImage, 470, 40, 60,60, this);
            g.drawImage(greenCheck, 700, 10, 40, 40, this);
            g.drawString(Integer.toString(GameMapPanel.getSelectedResource().getCost()), 750, 30);
            g.drawImage(starImage, 780, 10, 25,25, this);
            g.setFont(textFont);
            g.drawString("**Increases population by "+GameMapPanel.getSelectedResource().getPopIncrease(), 900, 20);
            g.drawString("  Requires certain technology.", 900, 40);
        }
        if (showPort){
            g.setFont(titleFont);
            g.drawString("Create port?", 430,30);
            g.drawImage(portImage, 470, 40, 60, 60, this);
            g.drawImage(greenCheck, 700, 10, 40, 40, this);
            g.drawString("10", 750, 30);
            g.drawImage(starImage, 780, 10, 25,25, this);
            g.setFont(textFont);
            g.drawString("**Increases stars/turn by 1.", 900, 20);
            g.drawString("  Requires sailing technology.", 900, 40);
        }
    }

    public void decideToDraw(Graphics g, String unit, Image colouredPic, Image greyPic, int initialX, int changeX, int diameter, int index){
        g.setColor(Color.black);
        if (Utilities.contains(player.getPractical(), unit)){
            if (player.getCurrency() < costs[index]){
                g.setColor(Color.red);
            }
            g.drawImage(colouredPic, initialX + index*changeX + 5, 15, diameter - 10, diameter - 10, this);
        } else {
            g.drawImage(greyPic, initialX + index*changeX + 5, 15, diameter - 10, diameter - 10, this);
        }
        g.drawOval(initialX + index*changeX,10,diameter,diameter);
        drawCenteredString(g, initialX + index*changeX + diameter, initialX + index*changeX,  90, unit);
        unitCoords[index][0] = initialX + index*changeX + diameter/2;
        unitCoords[index][1] = 10 + diameter/2;
    }


    public void drawCenteredString(Graphics g, int rightX, int leftX, int y, String string) {
        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
        FontMetrics fontMetrics = g.getFontMetrics(font);
        g.setFont(font);
        g.drawString(string, (rightX - leftX - fontMetrics.stringWidth(string)) / 2 + leftX, y);

    }

    private class OptionsMouseListener implements MouseListener {

        public void mouseClicked(MouseEvent e) {
        }

        public void mousePressed(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            if ((showResource) && (x > 700) && (x < 740) && (y > 10) && (y < 50)){
                GameMapPanel.resourceHarvest = true;
            } else if (showUnit) {
                for (int i = 0; i < unitCoords.length; i++) {
                    if ((Math.pow((e.getX() - unitCoords[i][0]), 2) + Math.pow(e.getY() - unitCoords[i][1], 2)) <= Math.pow((diameter/2), 2)) {
                        if (Utilities.contains(player.getPractical(), allUnits[i])) {
                            if (!GameFrame.getDisplayConfirmation()) {
                                GameFrame.setDisplayConfirmation(true);
                                prevClick = i;
                            } else if ((GameFrame.getDisplayConfirmation()) && (prevClick != i)) {
                                GameFrame.setDisplayConfirmation(true);
                                prevClick = i;
                            } else if ((GameFrame.getDisplayConfirmation()) && (prevClick == i)) {
                                GameFrame.setDisplayConfirmation(false);
                            }
                        }
                    }
                }
            } else if ((showPort) && (x > 300) && (x > 700) && (x < 740) && (y > 10) && (y < 50)){
                GameMapPanel.createPort = true;
            }
        }

        public void mouseReleased(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }
    }

    public static String[] getAllUnits(){
            return allUnits;
        }

    public static int[] getCosts(){
        return costs;
    }

    public static int getPrevClick() {
            return prevClick;
        }

}
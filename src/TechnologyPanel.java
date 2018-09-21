/**
 * [TechnologyPanel.java]
 * The Panel which displays the technology tree and its branches
 * @author Katelyn Wang and Brian Li
 * June 14 2018 
 */

//Swing imports
import javax.swing.JPanel;

//Graphic imports
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.FontMetrics;

//Utility imports
import java.util.ArrayList;


public class TechnologyPanel extends JPanel {
  //uploading all images
  private static final Image[] headPic = {Toolkit.getDefaultToolkit().getImage("assets/AquarionHead.png"),  Toolkit.getDefaultToolkit().getImage("assets/ImperiusHead.png"),  Toolkit.getDefaultToolkit().getImage("assets/XinXiHead.png"),  Toolkit.getDefaultToolkit().getImage("assets/OumanjiHead.png")};

  private static final Image riding = Toolkit.getDefaultToolkit().getImage("assets/RidingTech.png");
  private static final Image mathematics = Toolkit.getDefaultToolkit().getImage("assets/MathematicsTech.png");
  private static final Image chivalry = Toolkit.getDefaultToolkit().getImage("assets/ChivalryTech.png");
  
  private static final Image organization = Toolkit.getDefaultToolkit().getImage("assets/OrganizationTech.png");
  private static final Image shields = Toolkit.getDefaultToolkit().getImage("assets/ShieldsTech.png");
  private static final Image farming = Toolkit.getDefaultToolkit().getImage("assets/FarmingTech.png");
  
  private static final Image climbing = Toolkit.getDefaultToolkit().getImage("assets/ClimbingTech.png");
  private static final Image smithery = Toolkit.getDefaultToolkit().getImage("assets/SmitheryTech.png");
  private static final Image philosophy = Toolkit.getDefaultToolkit().getImage("assets/PhilosophyTech.png");
  
  private static final Image fishing = Toolkit.getDefaultToolkit().getImage("assets/FishingTech.png");
  private static final Image whaling = Toolkit.getDefaultToolkit().getImage("assets/WhalingTech.png");
  private static final Image sailing = Toolkit.getDefaultToolkit().getImage("assets/SailingTech.png");
  
  private static final Image hunting = Toolkit.getDefaultToolkit().getImage("assets/HuntingTech.png");
  private static final Image forestry = Toolkit.getDefaultToolkit().getImage("assets/ForestryTech.png");
  private static final Image archery = Toolkit.getDefaultToolkit().getImage("assets/ArcheryTech.png");

  private static final Image star = Toolkit.getDefaultToolkit().getImage("assets/Star2.png");

  private static int diameter;
  private static int[][] techCoords = new int[15][2];

  private int height;
  private int width;
  private ArrayList<String> obtainedTech;
  private Player player;
  private int tierOneCost;
  private int tierTwoCost;
  private int stars;

  /**
   * TechnologyPanel Constructor
   * @param height The height of the panel
   * @param width The width of the panel
   * @param player The player whose technology panel is being represented
   */
  public TechnologyPanel(int height, int width, Player player){
    this.height = height;
    this.width = width;
    this.obtainedTech = player.getTechnology();
    this.tierOneCost = player.getTierOneCost();
    this.tierTwoCost = player.getTierTwoCost();
    this.stars = player.getCurrency();
    this.player = player;
    this.setOpaque(false);
  }

  /**
   * Method to paint all the graphics
   * @param g Graphics object used to paint the graphics
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    setDoubleBuffered(true);

    //reference X and Y for the center of the circles
    int refX;
    int refY;

    //The center reference of the page
    int midX = width/2;
    int midY = height/2 + 20;
    diameter = (int) Math.round(height*0.15);

    //technology bubbles
    int lineLength = diameter + 30;

    //top vertical branch
    refX = midX;
    refY = midY - lineLength;
    decideToDraw(g, "Archery", "Hunting", archery, refX, refY, (int) Math.round(lineLength*(Math.cos((0.33)*Math.PI))), (int) Math.round(lineLength*(Math.sin((0.33)*Math.PI))), diameter, 0);
    decideToDraw(g, "Forestry", "Hunting", forestry, refX, refY, -(int) Math.round(lineLength * (Math.cos((0.33) * Math.PI))), (int) Math.round(lineLength * (Math.sin((0.33) * Math.PI))), diameter, 1);
    decideToDraw(g, "Hunting", "StartingTech", hunting, midX, midY, 0, lineLength, diameter, 2);

    //bottom right branch
    refX = midX + (int) Math.round(lineLength*(Math.cos(0.3*Math.PI)));;
    refY = midY + (int) Math.round(lineLength*(Math.sin(0.3*Math.PI)));
    decideToDraw(g, "Farming", "Organization", farming, refX, refY, (int) Math.round(lineLength*(Math.cos((0.5)*Math.PI))), (int) -Math.round(lineLength*(Math.sin((0.5)*Math.PI))), diameter, 3);
    decideToDraw(g,"Shields", "Organization", shields, refX, refY, (int) -Math.round(lineLength*(Math.cos((0.16)*Math.PI))), (int) -Math.round(lineLength*(Math.sin((0.16)*Math.PI))), diameter, 4);
    decideToDraw(g, "Organization", "StartingTech", organization, midX, midY, (int) -Math.round(lineLength*(Math.cos(0.3*Math.PI))), (int) -Math.round(lineLength*(Math.sin(0.3*Math.PI))), diameter, 5);
    
    //bottom left branch
    refX = midX - (int) Math.round(lineLength*(Math.cos(0.3*Math.PI)));;
    refY = midY + (int) Math.round(lineLength*(Math.sin(0.3*Math.PI)));
    decideToDraw(g, "Philosophy", "Climbing", philosophy, refX, refY, (int) -Math.round(lineLength*(Math.cos((0.5)*Math.PI))), (int) -Math.round(lineLength*(Math.sin((0.5)*Math.PI))), diameter, 6);
    decideToDraw(g, "Smithery", "Climbing", smithery, refX, refY, (int) Math.round(lineLength*(Math.cos((0.16)*Math.PI))), (int) -Math.round(lineLength*(Math.sin((0.16)*Math.PI))), diameter, 7);
    decideToDraw(g, "Climbing", "StartingTech", climbing, midX, midY, (int) Math.round(lineLength*(Math.cos(0.3*Math.PI))), (int) -Math.round(lineLength*(Math.sin(0.3*Math.PI))), diameter, 8);
    
    //top right branch
    refX = midX + (int) Math.round(lineLength*(Math.cos(0.1*Math.PI)));;
    refY = midY - (int) Math.round(lineLength*(Math.sin(0.1*Math.PI)));
    decideToDraw(g, "Mathematics","Riding", mathematics, refX, refY, (int) -Math.round(lineLength*(Math.cos((0.33)*Math.PI))), (int) Math.round(lineLength*(Math.sin((0.33)*Math.PI))), diameter, 9);
    decideToDraw(g, "Chivalry", "Riding", chivalry, refX, refY, (int) -Math.round(lineLength*(Math.cos((0)*Math.PI))), (int) Math.round(lineLength*(Math.sin((0)*Math.PI))), diameter, 10);
    decideToDraw(g, "Riding", "StartingTech", riding, midX, midY, (int) -Math.round(lineLength*(Math.cos(0.1*Math.PI))), (int) Math.round(lineLength*(Math.sin(0.1*Math.PI))), diameter, 11);
    
    //top left branch
    refX = midX - (int) Math.round(lineLength*(Math.cos(0.1*Math.PI)));;
    refY = midY - (int) Math.round(lineLength*(Math.sin(0.1*Math.PI)));
    decideToDraw(g, "Whaling", "Fishing", whaling, refX, refY, (int) Math.round(lineLength*(Math.cos((0.33)*Math.PI))), (int) Math.round(lineLength*(Math.sin((0.33)*Math.PI))), diameter, 12);
    decideToDraw(g, "Sailing", "Fishing", sailing, refX, refY, (int) Math.round(lineLength*(Math.cos((0)*Math.PI))), (int) Math.round(lineLength*(Math.sin((0)*Math.PI))), diameter, 13);
    decideToDraw(g, "Fishing", "StartingTech", fishing, midX, midY, (int) Math.round(lineLength*(Math.cos(0.1*Math.PI))), (int) Math.round(lineLength*(Math.sin(0.1*Math.PI))), diameter, 14);
    
    g.drawImage(headPic[player.getTribe()], midX - diameter/2,midY-diameter/2,diameter,diameter, this);

    //Fonts for the title and information at the top
    Font titleFont = Utilities.getFont("assets/AdequateLight.ttf", 30f);
    Font subtitleFont = new Font(Font.SANS_SERIF, Font.PLAIN, 15);
    Font resourceFont = Utilities.getFont("assets/AdequateLight.ttf", 20f);
    g.setColor(Color.white);
    g.setFont(titleFont);
    g.drawString("Research Center", 5,35);
    g.setFont(subtitleFont);
    g.drawString("Technologies get more expensive", 5, 65);
    g.drawString("the more cities you have.", 5, 80);
    g.setFont(resourceFont);
    g.drawString("Resources", 5, 120);
    //Displays how many stars the player has
    g.drawImage(star, 5, 128, 20,20, this);
    g.drawString(Integer.toString(stars), 25, 145);
    
  }
  
  /**
   * Takes in a technology, and calls on the appropriate draw method whether it is locked, unlocked or obtained (bought)
   * @param g Graphics to pass into the methods which will draw the images
   * @param technology String which is the technology it is determining how to draw (needs to determine whether it has been unlocked, bought or is still locked)
   * @param previousTech The technology which unlocks the current technology; "StartingTech" if it is one of the tier 1 technologies
   * @param pic The picture of the technology which will go inside the circle
   * @param refX The reference X (from which it is branching)
   * @param refY The reference Y (from which it is branching)
   * @param changeX The change in the refX coordinate (to its actual position)
   * @param changeY The change in the refX coordinate (to its actual position)
   * @param diameter The length of the line connecting the circle to the previous circle
   */
  public void decideToDraw (Graphics g, String technology, String previousTech, Image pic, int refX, int refY, int changeX, int changeY, int diameter, int index){
    if (Utilities.contains(obtainedTech, technology)) {
      drawObtainedTech(g,refX, refY, changeX, changeY, diameter, pic, technology);
    } else if (Utilities.contains(obtainedTech, previousTech)) {
      techCoords[index][0] = refX - changeX;
      techCoords[index][1] = refY - changeY;
      int lengthStar = (int) Math.round(diameter*0.18);
      if (previousTech.equals("StartingTech")){
        drawUnlockedTech(g,refX, refY, changeX, changeY, diameter, pic, technology, tierOneCost);
        drawCost(g,refX - changeX + diameter/2, refX - changeX - diameter/2, refY - changeY - diameter/2 + (int) Math.round(lengthStar*1.5), lengthStar, tierOneCost);
      } else {
        drawUnlockedTech(g,refX, refY, changeX, changeY, diameter, pic, technology, tierTwoCost);
        drawCost(g,refX - changeX + diameter/2, refX - changeX - diameter/2, refY - changeY - diameter/2 + (int) Math.round(lengthStar*1.5), lengthStar, tierTwoCost);
      }
    } else {
      drawLockedTech(g,refX, refY, changeX, changeY, diameter);
    }
  }

  /**
   * The method which draws the technology which has not yet been unlocked (will paint grey circles)
   * @param g Graphics to draw the graphics
   * @param refX The reference X (from which it is branching)
   * @param refY The reference Y(from which it is branching)
   * @param changeX The change in the refX coordinate (to its actual position)
   * @param changeY The change in the refX coordinate (to its actual position)
   * @param diameter The length of the line connecting the circle to the previous circle
   */
  public void drawLockedTech(Graphics g, int refX, int refY, int changeX, int changeY, int diameter){
    Color black = new Color(1,1,1);
    Color grey = new Color(100, 100, 100);
    g.setColor(grey);
    g.drawLine(refX, refY, refX - changeX, refY - changeY);
    g.setColor(black);
    g.fillOval(refX - changeX - diameter/2,refY - changeY - diameter/2, diameter, diameter);
    g.setColor(grey);
    g.drawOval(refX - changeX - diameter/2, refY - changeY - diameter/2, diameter, diameter);
  }

  /**
   * The method which draws the technology which has not yet been obtained (mean player has bought it), will draw green circles
   * @param g Graphics to draw the graphics
   * @param refX The reference X (from which it is branching)
   * @param refY The reference Y(from which it is branching)
   * @param changeX The change in the refX coordinate (to its actual position)
   * @param changeY The change in the refX coordinate (to its actual position)
   * @param diameter The length of the line connecting the circle to the previous circle
   */
  public void drawObtainedTech(Graphics g, int refX, int refY, int changeX, int changeY, int diameter, Image pic, String technology){
    Color green = new Color(18,207,65);
    g.setColor(green);
    g.drawLine(refX, refY, refX - changeX, refY - changeY);
    g.fillOval(refX - changeX - diameter/2,refY - changeY - diameter/2, diameter, diameter);
    g.drawImage(pic, refX - changeX - diameter/2,refY - changeY - diameter/2, diameter, diameter, this);
    g.setColor(Color.white);
    Utilities.drawCenteredString(g, refX - changeX + diameter/2, refX - changeX - diameter/2, refY - changeY + diameter/3, technology);
  }

  /**
   * The method which draws the technology which has been unlocked, but not yet bought (will draw blue circles)
   * @param g Graphics to draw the graphics
   * @param refX The reference X (from which it is branching)
   * @param refY The reference Y(from which it is branching)
   * @param changeX The change in the refX coordinate (to its actual position)
   * @param changeY The change in the refX coordinate (to its actual position)
   * @param diameter The length of the line connecting the circle to the previous circle
   */
  public void drawUnlockedTech(Graphics g, int refX, int refY, int changeX, int changeY, int diameter, Image pic, String technology, int cost){
    Color blue = new Color(82,185,255);
    g.setColor(Color.white);
    g.drawLine(refX, refY, refX - changeX, refY - changeY);
    g.setColor(blue);
    g.fillOval(refX - changeX - diameter/2,refY - changeY - diameter/2, diameter, diameter);
    g.setColor(Color.white);
    Utilities.drawCenteredString(g, refX - changeX + diameter/2, refX - changeX - diameter/2, refY - changeY + diameter/3, technology);
    //Checks if player has enough money to purchase the technology, if they don't will display the text and outline in red
    if (stars < cost) {
      g.setColor(Color.red);
    }
    g.drawOval(refX - changeX - diameter/2, refY - changeY - diameter/2, diameter, diameter);
    g.drawImage(pic, refX - changeX - diameter/2,refY - changeY - diameter/2, diameter, diameter, this);
  }

  /**
   * Draws the price of the technology
   * @param g Graphics to draw the graphics
   * @param rightX The right boundary used for centering the text
   * @param leftX The left boundary used for centering the text
   * @param y The y coordinate at which the price will be drawn
   * @param lengthStar The length of the star image
   * @param price The price of the technology
   */
  public void drawCost(Graphics g, int rightX, int leftX, int y, int lengthStar, int price){
    String priceStr = Integer.toString(price);
    Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 13);
    FontMetrics fontMetrics = g.getFontMetrics(font);
    g.setFont(font);
    int x = (rightX - leftX - (fontMetrics.stringWidth(priceStr)+3+lengthStar))/2 + leftX;
    g.drawImage(star, x, y - lengthStar, lengthStar, lengthStar,this);
    g.drawString(priceStr, x + lengthStar + 3, y-3);
  }

  /**
   * Gets the coordinates of each of the Technology bubbles midpoint
   * @return The 2D array containing each of the coordinate pairs
   */
  public static int[][] getTechCoords() {
    return techCoords;
  }

  /**
   * Gets the diameter of the bubbles
   * @return the diameter of the bubbles
   */
  public static int getDiameter(){
    return diameter;
  }
}

/**
 * [TechTreeFrame.java]
 * The Frame which displays the technology tree
 * @author Katelyn Wang and Brian Li
 * June 14 2018
 */

//Swing imports
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;

//Graphics imports
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;

//Action imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Mouse imports
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//IO imports
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

//Utility imports
import java.util.ArrayList;

public class TechTreeFrame extends JFrame {
    private JFrame thisFrame;
    private TechOptionsPanel optionsPanel;
    private BufferedImage backButtonPic = null;
    private String[] allTech = {"Archery", "Forestry", "Hunting", "Farming", "Shields", "Organization", "Philosophy", "Smithery", "Climbing", "Mathematics", "Chivalry", "Riding", "Whaling", "Sailing", "Fishing"};
    private String[] practicalTech = {"Archer", "Forest", "Animal", "Crop", "Defender", "Fruit", "Mindbender", "Swordsperson", "Mountain", "Catapult", "Knight", "Rider", "Whale", "Water", "Fish"};
    private String[] startingTech = {"Hunting", "Climbing", "Organization", "Riding", "Fishing"};
    private int[][] techCoords;
    private TechnologyPanel mainPanel;

    private Player player;
    private boolean displayOptions = false;
    private int maxX;
    private int maxY;
    private int prevClick;
    private int prevCost;
    private int tierOneCost;
    private int tierTwoCost;
    private ArrayList<String> obtainedTech;

    /**
     * TechTreeFrame Constructor
     * @param player the Player whose tech tree is being displayed
     */
    public TechTreeFrame(Player player){
        super("TechTree");
        this.thisFrame = this;
        maxX = 1530;
        maxY = 1150;
        this.setSize(maxX, maxY);
        this.setLocationRelativeTo(null); //start the frame in the center of the screen
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setUndecorated(true);

        this.player = player;
        this.tierOneCost = player.getTierOneCost();
        this.tierTwoCost = player.getTierTwoCost();
        this.obtainedTech = player.getTechnology();

        //Creating a frame for the black background (otherwise would interfere with otherlayers if each panel had a black background)
        JPanel blackBackground = new JPanel();
        blackBackground.setSize(new Dimension(maxX, maxY));
        blackBackground.setBackground(new Color(1,1,1));
        blackBackground.setLocation(0,0);

        //Creating the main TechnologyPanel
        mainPanel = new TechnologyPanel(maxY, maxX, player);
        techCoords = TechnologyPanel.getTechCoords();

        //Getting the button image to close the frame
       try{
            backButtonPic = ImageIO.read(new File("assets/XButton.png"));
        } catch (IOException e){
        }
        //Creating round button
        JButton backButton = new RoundButton(35, new ImageIcon(backButtonPic));
        backButton.addActionListener(new BackButtonListener());
        backButton.setFocusable(false); //Making it unfocused so that mouselistener still works
        backButton.setAlignmentX(Component.RIGHT_ALIGNMENT);

        //Configuring the mainPanel
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.PAGE_AXIS));
        mainPanel.add(Box.createRigidArea(new Dimension(0,15)));
        mainPanel.add(backButton);

        //Creating the confirmation panel which will pop up when a technology is clicked
        optionsPanel = new TechOptionsPanel(maxY, maxX);

        //Created LayeredPane
        JLayeredPane lPane = new JLayeredPane();
        lPane.setPreferredSize(new Dimension(maxX, maxY));

        //Adding the main panel to the layeredpane
        mainPanel.setSize(new Dimension(maxX, maxY));
        mainPanel.setLocation(0,0);
        mainPanel.setOpaque(false);
        lPane.add(mainPanel, new Integer(1));

        //Adding the black background to the layeredpane
        lPane.add(blackBackground, new Integer(0));

        //Adding the optionsPanel to the layeredpane
        optionsPanel.setSize(new Dimension(maxX, maxY));
        optionsPanel.setLocation(0,0);
        optionsPanel.setVisible(false);
        optionsPanel.setOpaque(false);
        lPane.add(optionsPanel, new Integer(2));
        lPane.setOpaque(false);
        this.add(lPane, BorderLayout.CENTER);

        //Create listener
        MyMouseListener mouseListener = new MyMouseListener();
        this.addMouseListener(mouseListener);

        //Start the game loop in a separate thread
        Thread t = new Thread(new Runnable() { public void run() { animate(); }}); //start the gameLoop
        t.start();

        this.setVisible(true);
    }

    /**
     * Will update the frame to display the confirmation panel depending on whether or not a technology was clicked
     */
    public void animate(){
        while(true){
            this.repaint();
            if (displayOptions){
                optionsPanel.setVisible(true);
            } else {
                optionsPanel.setVisible(false);
            }
        }
    }

    /**
     * When the back button is clicked, will close the frame to return to the game
     */
    private class BackButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event)  {
            thisFrame.dispose();
        }

    }

    //-----Inner classes------
    private class TechOptionsPanel extends JPanel {
        int maxX;
        int maxY;
        JPanel thisPanel;

        /**
         * Makes the panel based a maxX and maxY parameter
         * @param maxY the maximum height
         * @param maxX the maximum width
         */
        TechOptionsPanel(int maxY, int maxX) {
            thisPanel = this;
            this.setMaximumSize(new Dimension(maxX, maxY));

            this.maxX = maxX;
            this.maxY = maxY;

            Color lightGrey = new Color(203, 203, 203);
            JButton cancelButton = new RoundedRectButton(110, 30, lightGrey, "CANCEL");
            cancelButton.addActionListener(new CancelButtonListener());

            Color blue = new Color(0, 122, 203);
            JButton confirmButton = new RoundedRectButton(110, 30, blue, "RESEARCH");
            confirmButton.addActionListener(new ConfirmButtonListener());

            this.setLayout(null);
            confirmButton.setBounds(maxX - 765, maxY - 555, ((RoundedRectButton) cancelButton).width, ((RoundedRectButton) cancelButton).height);
            cancelButton.setBounds(maxX - 880, maxY - 555, ((RoundedRectButton) confirmButton).width, ((RoundedRectButton) confirmButton).height);
            this.add(confirmButton);
            this.add(cancelButton);
            this.setOpaque(false);
            this.setVisible(false);

        }


        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Image star = Toolkit.getDefaultToolkit().getImage("assets/Star2.png");
            int width = 240;
            int height = 135;
            int x = (maxX - width) / 2;
            int y = (maxY - height) / 2;
            g.setColor(Color.black);

            g.fillRoundRect(x, y, width, height, 20, 20);
            g.setColor(Color.white);
            Font titleFont = Utilities.getFont("assets/AdequateLight.ttf", 18f);
            Font textFont = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
            g.setFont(titleFont);
            g.drawString(allTech[prevClick], x + 10, y + 25);
            g.setFont(textFont);
            FontMetrics fontMetrics = g.getFontMetrics(textFont);

            g.drawString(Integer.toString(prevCost), x + width - fontMetrics.stringWidth(Integer.toString(prevCost)) - 10, y + 20);
            g.drawImage(star, x + width - 35 - fontMetrics.stringWidth(Integer.toString(prevCost)), y + 5, 20, 20, this);
        }

        private class RoundedRectButton extends JButton {
            int width;
            int height;
            Color color;
            String name;

            public RoundedRectButton(int width, int height, Color color, String name) {
                super();
                this.width = width;
                this.height = height;
                this.color = color;
                this.name = name;
                Dimension size = new Dimension(width, height);
                setPreferredSize(size);
                setBorderPainted(false);
                setContentAreaFilled(false);
            }

            public void paintComponent(Graphics g) {
                g.setColor(color);
                g.fillRoundRect(0, 0, width, height, 20, 20);
                g.setColor(Color.white);
                drawCenteredString(g, width, 0, height - 10, name);
            }
        }
    }

        public void drawCenteredString(Graphics g, int rightX, int leftX, int y, String string) {
            Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
            FontMetrics fontMetrics = g.getFontMetrics(font);
            g.setFont(font);
            g.drawString(string, (rightX - leftX - fontMetrics.stringWidth(string)) / 2 + leftX, y);

        }

        private class CancelButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                displayOptions = false;
            }
        }

        private class ConfirmButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                if (player.getCurrency() >= prevCost){
                    player.setCurrency(player.getCurrency()-prevCost);
                    player.addTechnology(allTech[prevClick]);
                    player.addPractical(practicalTech[prevClick]);
                    displayOptions = false;
                    thisFrame.dispose();
                    new TechTreeFrame(player);
                }
            }
        }

    private class MyMouseListener implements MouseListener {

        public void mouseClicked(MouseEvent e) {
        }

        public void mousePressed(MouseEvent e) {
            for (int i = 0; i < techCoords.length ; i++){
                if ((Math.pow((e.getX() - techCoords[i][0]), 2)+Math.pow(e.getY() - techCoords[i][1],2)) <= Math.pow(((TechnologyPanel.getDiameter()/2)), 2)) {
                    if (!Utilities.contains(obtainedTech, allTech[i])){
                        if (!displayOptions) {
                            displayOptions = true;
                            prevClick = i;
                            if (Utilities.contains(startingTech, allTech[i])){
                                prevCost = tierOneCost;
                            } else {
                                prevCost = tierTwoCost;
                            }
                        } else if ((displayOptions) && (prevClick != i)) {
                            displayOptions = true;
                            prevClick = i;
                        } else if ((displayOptions) && (prevClick == i)) {
                            displayOptions = false;
                        }
                    }
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
}

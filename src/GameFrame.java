/**
 * [GameFrame.java]
 * The thisFrame which displays the map, stats, option panel to commit an action, and the buttons to access the technology thisFrame and end the turn
 * @author Katelyn Wang and Brian Li
 * June 14 2018
 */

// Swing imports
import javax.swing.*;

//Graphics imports
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;

public class GameFrame extends JFrame {
    private static GameMapPanel mapPanel;
    private static MenuPanel menuPanel;
    private static OptionsPanel optionsPanel;
    private static StatsPanel statsPanel;
    private static OptionsConfirmationPanel confirmationPanel;
    private static JPanel mainPanel;
    private static JPanel sidePanel;
    private static JPanel bottomPanel;

    private static int maxX;
    private static int maxY;
    private static int mapPanelLength;
    private static int optionsPanLength;

    private static boolean displayConfirmation = false;
    private static boolean displayOptions = false;
    private static boolean turnEnd = false;

    private static final int optionsPanHeight = 100;
    private static final int menuPanelWidth = 100;

    GameFrame(Map map, boolean[][] mask, Player player) {
        super("Polytopia");
        maxX = 1530;
        maxY = 1150;

        //Initializing the frame
        this.setSize(maxX, maxY);
        this.setLocationRelativeTo(null); //start the frame in the center of the screen
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setUndecorated(true);
        mapPanelLength = maxY - optionsPanHeight ;

        //Creating all of the panels
        mapPanel = new GameMapPanel(map, mapPanelLength, mask, player);
        menuPanel = new MenuPanel(mapPanelLength, menuPanelWidth,player);
        optionsPanel = new OptionsPanel(optionsPanHeight, maxX, player);
        mainPanel = new JPanel();
        sidePanel = new JPanel();
        statsPanel = new StatsPanel(mapPanelLength, maxX-mapPanelLength-menuPanelWidth, player);
        bottomPanel = new JPanel();

        mainPanel.setOpaque(false);
        sidePanel.setOpaque(false);
        statsPanel.setOpaque(false);
        bottomPanel.setOpaque(false);

        JPanel blackBackground = new JPanel();
        blackBackground.setSize(new Dimension(maxX, maxY));
        blackBackground.setBackground(new Color(1,1,1));
        blackBackground.setLocation(0,0);

        optionsPanLength = optionsPanel.getWidth();

        //Setting their respective sizes
        menuPanel.setPreferredSize(new Dimension(menuPanelWidth, mapPanelLength));
       // sidePanel.setPreferredSize(new Dimension(maxX-mapPanelLength-menuPanelWidth, mapPanelLength));
        bottomPanel.setPreferredSize(new Dimension(maxX, optionsPanHeight));
        statsPanel.setPreferredSize(new Dimension(maxX-mapPanelLength-menuPanelWidth, mapPanelLength));

        //Adding the optionsPanel to the bottom panel
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(optionsPanel, BorderLayout.CENTER);
        bottomPanel.setVisible(false);

       // sidePanel.setLayout(new BorderLayout());
       // sidePanel.add(statsPanel, BorderLayout.CENTER);

        //Adding all the panels onto the main panel
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(mapPanel, BorderLayout.CENTER);
        mainPanel.add(statsPanel, BorderLayout.WEST);
        mainPanel.add(menuPanel, BorderLayout.EAST);
        mainPanel.add(bottomPanel, BorderLayout.PAGE_END);

        confirmationPanel = new OptionsConfirmationPanel(maxY, maxX, player);
        confirmationPanel.setSize(new Dimension(maxX, maxY));
        confirmationPanel.setLocation(0,0);
        confirmationPanel.setVisible(false);
        confirmationPanel.setOpaque(false);


        JLayeredPane lPane = new JLayeredPane();
        lPane.setPreferredSize(new Dimension(maxX, maxY+50));
        mainPanel.setSize(new Dimension(maxX, maxY));
        mainPanel.setLocation(0,0);
        confirmationPanel.setLocation(0,0);
        lPane.add(blackBackground, new Integer(0));
        lPane.add(mainPanel, new Integer(1));
        lPane.add(confirmationPanel, new Integer(2));
        lPane.setOpaque(false);
        this.add(lPane, BorderLayout.CENTER);
        //this.pack();
        //Start the game loop in a separate thread
        Thread t = new Thread(new Runnable() { public void run() { animate(); }}); //start the gameLoop
        t.start();

        this.setVisible(true);
    }

    /**
     * Constantly updates the frame, checks if displayOptions is true, which prompts the display of the options panel
     */
    public void animate(){
        while(true){
            this.repaint();
            if (displayOptions){
                bottomPanel.setVisible(true);
            } else {
                bottomPanel.setVisible(false);
            }
            if (displayConfirmation){
                confirmationPanel.setVisible(true);
            } else {
                confirmationPanel.setVisible(false);
            }
        }
    }

    /**
     * updates the display options boolean value
     * @param bool the new value of the displayOptions variable
     */
    public static void setDisplayOptions(boolean bool){
        displayOptions = bool;
    }

    /**
     * updates the display confirmation boolean value
     * @param bool the new value of the displayConfirmation variable
     */
    public static void setDisplayConfirmation(boolean bool){
        displayConfirmation = bool;
    }

    /**
     * gets the displayConfirmation value
     * @return the displayConfirmation value
     */
    public static boolean getDisplayConfirmation(){
        return displayConfirmation;
    }

    /**
     * updates the turn end boolean value
     * @param bool the new value of the turnEnd variable
     */
    public static void setTurnEnd(boolean bool){
        turnEnd = bool;
    }
}
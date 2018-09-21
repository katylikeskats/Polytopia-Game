/**
 * [InitialGameFrame.java]
 * The frame which pops up from the "play" button on the homescreen
 * Gets how many players are playing the game
 * @author Katelyn Wang and Brian Li
 * June 14 2018
 **/

//Swing Imports
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.ImageIcon;

//Graphics Imports
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;

//Action Imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Item Imports
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

//ImageIO imports
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class InitialGameFrame extends JFrame {
    private static final Image background = Toolkit.getDefaultToolkit().getImage("assets/background3.png");

    private static InitialGamePanel mainPanel;
    private static JPanel checkBoxPanel;
    private static JPanel backgroundPanel;
    private static JPanel confirmPanel;
    private static BufferedImage backButtonPic = null;
    private static BufferedImage confirmButtonPic = null;

    private int maxX;
    private int maxY;
    private int numPlayers;
    private boolean numPlayersSelected;
    private JFrame thisFrame;
    private String name = "";

    //InitialGameFrame Constructor
    InitialGameFrame(){
        super("Polytopia - Start");
        //configure the window
        this.thisFrame = this;
        maxY = 400;
        maxX = 600;
        this.setSize(maxX, maxY);
        this.setLocationRelativeTo(null); //start the frame in the center of the screen
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable (false);
        this.setUndecorated(true);

        //Getting the button images
        try{
            backButtonPic = ImageIO.read(new File("assets/backbutton.png"));
            confirmButtonPic = ImageIO.read(new File("assets/BlueConfirmButton.png"));
        } catch (IOException e){
        }

        //Configuring the backbutton
        JButton backButton = new JButton(new ImageIcon(backButtonPic));
        backButton.addActionListener(new BackButtonListener());
        backButton.setMaximumSize(new Dimension (backButtonPic.getWidth(),backButtonPic.getHeight()));
        backButton.setContentAreaFilled(false);
        backButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        backButton.setFocusable(false);

        //creating the panel to display the graphics
        mainPanel = new InitialGamePanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.PAGE_AXIS));
        mainPanel.add(Box.createRigidArea(new Dimension(0,15)));
        mainPanel.add(backButton);

        backgroundPanel = new BackgroundPanel();

        //Configuring the checkbox to get how many players are there
        JCheckBox checkBox2 = new JCheckBox("2");
        checkBox2.setFocusable(false);
        JCheckBox checkBox3 = new JCheckBox("3");
        checkBox3.setFocusable(false);
        JCheckBox checkBox4 = new JCheckBox("4");
        checkBox4.setFocusable(false);

        ItemListener itemListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Object source = e.getItemSelectable();
                if (source == checkBox2){
                   numPlayers = 2;
                   numPlayersSelected = true;
                   checkBox3.setSelected(false);
                   checkBox4.setSelected(false);
                } else if (source == checkBox3){
                    numPlayers = 3;
                    numPlayersSelected = true;
                    checkBox2.setSelected(false);
                    checkBox4.setSelected(false);
                } else if (source == checkBox4){
                    numPlayers = 4;
                    numPlayersSelected = true;
                    checkBox2.setSelected(false);
                    checkBox3.setSelected(false);
                }

                if (e.getStateChange() == ItemEvent.DESELECTED){
                    numPlayersSelected = false;
                    numPlayers = 0;
                }
            }
        };
        checkBox2.addItemListener(itemListener);
        checkBox3.addItemListener(itemListener);
        checkBox4.addItemListener(itemListener);

        JButton confirmButton = new JButton(new ImageIcon(confirmButtonPic));
        confirmButton.addActionListener(new ConfirmButtonListener());
        confirmButton.setMaximumSize(new Dimension (confirmButtonPic.getWidth(), confirmButtonPic.getHeight()));
        confirmButton.setContentAreaFilled(false);
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmButton.setFocusable(false);

        confirmPanel = new JPanel();
        confirmPanel.setOpaque(false);
        confirmPanel.setLayout(new BoxLayout(confirmPanel, BoxLayout.PAGE_AXIS));
        confirmPanel.add(Box.createRigidArea(new Dimension(0, 200)));
        confirmPanel.add(confirmButton);

        checkBoxPanel = new JPanel();
        checkBoxPanel.setOpaque(false);
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.LINE_AXIS));
        //checkBoxPanel.add(Box.createRigidArea(new Dimension(500, maxX)));
        checkBoxPanel.add(Box.createRigidArea(new Dimension(0, 27)));
        checkBoxPanel.add(Box.createRigidArea(new Dimension(240, 0)));
        checkBox2.setAlignmentY(TOP_ALIGNMENT);
        checkBoxPanel.add(checkBox2);
        checkBoxPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        checkBox3.setAlignmentY(TOP_ALIGNMENT);
        checkBoxPanel.add(checkBox3);
        checkBoxPanel.add(Box.createRigidArea(new Dimension(20,0)));
        checkBox4.setAlignmentY(TOP_ALIGNMENT);
        checkBoxPanel.add(checkBox4);

        JLayeredPane lPane = new JLayeredPane();
        mainPanel.setLocation(0,0);
        checkBoxPanel.setLocation(0,0);
        backgroundPanel.setLocation(0,0);
        confirmPanel.setLocation(0, 0);

        mainPanel.setSize(new Dimension(maxX, maxY));
        checkBoxPanel.setSize(new Dimension(maxX, maxY));
        backgroundPanel.setSize(new Dimension(maxX, maxY));
        confirmPanel.setSize(new Dimension(maxX, maxY));
        lPane.setSize(new Dimension(maxX, maxY));

        lPane.add(mainPanel, new Integer(2));
        lPane.add(checkBoxPanel, new Integer(3));
        lPane.add(backgroundPanel, new Integer(1));
        lPane.add(confirmPanel, new Integer(4));
        lPane.setOpaque(false);

        //this.add(checkBoxPanel);
        this.add(lPane);
        this.setVisible(true);

        //Start the game loop in a separate thread
        Thread t = new Thread(new Runnable() { public void run() { animate(); }}); //start the gameLoop
        t.start();

    } //End of Constructor

    //the main gameloop where the game state is updated
    public void animate() {
        while(true){
            try{ Thread.sleep(10);} catch (Exception exc){}
            this.repaint();
        }
    }

    /** --------- INNER CLASSES ------------- **/
    private class InitialGamePanel extends JPanel {
        InitialGamePanel(){
            this.setOpaque(false);
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Font font1 = Utilities.getFont("assets/AdequateLight.ttf", 30f);
            FontMetrics fontMetrics = g.getFontMetrics(font1);
            g.setFont(font1);
            g.setColor(Color.white);
            g.drawString("Start Game", (maxX-fontMetrics.stringWidth("Start Game"))/2, 40);
            g.drawString("No. of Players", (maxX-fontMetrics.stringWidth("No. of Players"))/2, 120);
            g.setColor(new Color(1,1,1, 150));
            g.fillRect(0, 130, maxX, 45);
            g.setColor(new Color(255, 255, 255));
            g.drawString(name.toLowerCase(), (maxX-fontMetrics.stringWidth(name.toLowerCase()))/2, 370);
        }
    }

    private class BackgroundPanel extends JPanel {
        BackgroundPanel(){
        }
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(background, 0,0, maxX, maxY, this);
        }
    }


    private class BackButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event)  {
            thisFrame.dispose();
            new StartingFrame();
        }

    }

    private class ConfirmButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event)  {
            if (numPlayersSelected) {
                thisFrame.dispose();
                Map map = new Map(25, numPlayers);
                Player[] players = new Player[numPlayers];
                Interactions interactions = new Interactions(map);
                new NameFrame(0, numPlayers, map, players, interactions);
            }
        }

    }
}
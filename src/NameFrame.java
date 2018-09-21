/**
 * [NameFrame.java]
 * The frame which collects the player's names
 * @author Katelyn Wang and Brian Li
 * June 14 2018
 **/

//Graphics &GUI imports
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.ImageIcon;
import javax.swing.Box;
import javax.swing.BoxLayout;

//Graphics and GUI imports
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.Font;
import java.awt.FontMetrics;

//Keyboard imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;


public class NameFrame extends JFrame {
    private static final Image background = Toolkit.getDefaultToolkit().getImage("assets/background3.png");
    JFrame thisFrame;
    static GameAreaPanel mainPanel;
    static JPanel backgroundPanel;
    static JPanel confirmPanel;

    private int numPlayer;
    private int totalNumPlayers;

    private int maxX;
    private int maxY;
    String name = "";
    int length = 0;
    BufferedImage backButtonPic = null;
    BufferedImage confirmButtonPic = null;

    Map map;
    Player[] players;
    Interactions interactions;

    //Constructor
    NameFrame(int numPlayer, int totalNumPlayers, Map map, Player[] players, Interactions interactions){
        super("Polytopia - Start");
        //configure the window
        this.thisFrame = this;
        this.numPlayer = numPlayer;
        this.totalNumPlayers = totalNumPlayers;
        this.map = map;
        this.players = players;
        this.interactions = interactions;

        maxY = 400;
        maxX = 600;
        this.setSize(maxX, maxY);

        this.setLocationRelativeTo(null); //start the frame in the center of the screen
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable (false);
        this.setUndecorated(true);

        try{
            backButtonPic = ImageIO.read(new File("assets/backbutton.png"));
            confirmButtonPic = ImageIO.read(new File("assets/BlueConfirmButton.png"));
        } catch (IOException e){
        }

        JButton backButton = new JButton(new ImageIcon(backButtonPic));
        backButton.addActionListener(new BackButtonListener());
        backButton.setMaximumSize(new Dimension (backButtonPic.getWidth(),backButtonPic.getHeight()));
        backButton.setContentAreaFilled(false);
        backButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        backButton.setFocusable(false);

        mainPanel = new GameAreaPanel();

        backgroundPanel = new BackgroundPanel();


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

        JLayeredPane lPane = new JLayeredPane();
        mainPanel.setLocation(0,0);
        backgroundPanel.setLocation(0,0);
        confirmPanel.setLocation(0, 0);

        mainPanel.setSize(new Dimension(maxX, maxY));
        backgroundPanel.setSize(new Dimension(maxX, maxY));
        confirmPanel.setSize(new Dimension(maxX, maxY));
        lPane.setSize(new Dimension(maxX, maxY));

        lPane.add(mainPanel, new Integer(2));
        lPane.add(backgroundPanel, new Integer(1));
        lPane.add(confirmPanel, new Integer(4));
        lPane.setOpaque(false);

        MyKeyListener keyListener = new MyKeyListener();
        this.addKeyListener(keyListener);

        MyMouseListener mouseListener = new MyMouseListener();
        this.addMouseListener(mouseListener);

        //this.add(checkBoxPanel);
        this.add(lPane);
        this.setVisible(true);

        //Start the game loop in a separate thread
        Thread t = new Thread(new Runnable() { public void run() { animate(); }}); //start the gameLoop
        t.start();

    } //End of Constructor

    //the main gameloop - this is where the game state is updated
    public void animate() {
        while(true){
            try{ Thread.sleep(10);} catch (Exception exc){}
            this.repaint();
        }
    }

    /** --------- INNER CLASSES ------------- **/

    // Inner class for the the game area - This is where all the drawing of the screen occurs
    private class GameAreaPanel extends JPanel {
        GameAreaPanel(){
            this.setOpaque(false);
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g); //removed to keep transparent panel
            Font font1 = Utilities.getFont("assets/AdequateLight.ttf", 30f);
            FontMetrics fontMetrics = g.getFontMetrics(font1);
            g.setFont(font1);
            g.setColor(Color.white);
            g.drawString("Start Game", (maxX-fontMetrics.stringWidth("Start Game"))/2, 40);
            g.drawString("Name of Player " +(numPlayer+1),(maxX-fontMetrics.stringWidth("Name of Player"))/2, 120);
            g.setColor(new Color(1,1,1, 150));
            g.fillRect(0, 130, maxX, 60);
            g.setColor(new Color(255, 255, 255));
            g.drawString(name.toLowerCase(), (maxX-fontMetrics.stringWidth(name.toLowerCase()))/2, 170);
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

    // -----------  Inner class for the keyboard listener - this detects key presses and runs the corresponding code
    private class MyKeyListener implements KeyListener {

        public void keyTyped(KeyEvent e) {
        }

        public void keyPressed(KeyEvent e) {
            String[] keyCodes = {"Backspace", "Space", "Shift", "Semicolon", "Period", "Comma", "Minus", "Quote", "Open Bracket", "Close Bracket", "Enter"};
            if (name.length() < 10){
                if (!Utilities.contains(keyCodes, KeyEvent.getKeyText(e.getKeyCode()))) {
                    name = name + KeyEvent.getKeyText(e.getKeyCode());
                } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("Space")) {
                    name = name + " ";
                } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("Semicolon")) {
                    name = name + ";";
                } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("Period")) {
                    name = name + ".";
                } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("Comma")) {
                    name = name + ",";
                } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("Minus")) {
                    name = name + "-";
                } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("Quote")) {
                    name = name + "'";
                } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("Open Bracket")) {
                    name = name + "{";
                } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("Close Bracket")) {
                    name = name + "}";
                }
            }
            if (name.length()>0){
                if (KeyEvent.getKeyText(e.getKeyCode()).equals("Backspace")) {
                    name = name.substring(0, name.length() - 1);
                }
            }
            if (KeyEvent.getKeyText(e.getKeyCode()).equals("Enter")){
                if (numPlayer + 1 == totalNumPlayers){
                    addPlayer();
                    startGame();
                } else {
                    addPlayer();
                    new NameFrame(numPlayer + 1, totalNumPlayers, map, players, interactions);
                }
            }

        }

        public void keyReleased(KeyEvent e) {
        }
    } //end of keyboard listener

    // -----------  Inner class for the keyboard listener - This detects mouse movement & clicks and runs the corresponding methods
    private class MyMouseListener implements MouseListener {

        public void mouseClicked(MouseEvent e) {
            System.out.println("Mouse Clicked");
            System.out.println("X:"+e.getX() + " y:"+e.getY());
        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    } //end of mouselistener

    private class BackButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event)  {
            thisFrame.dispose();
            new StartingFrame();
        }

    }

    private class ConfirmButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event)  {
            if (numPlayer + 1 == totalNumPlayers){
                addPlayer();
                startGame();
            } else {
                addPlayer();
                new NameFrame(numPlayer + 1, totalNumPlayers, map, players, interactions);
            }
        }

    }

    public void addPlayer(){
        thisFrame.dispose();
        players[numPlayer] = new Player(map.getCapitalCities()[numPlayer], map.getCapitalCities()[numPlayer].getTribe(), interactions, map.getMapLength(), name.toLowerCase());
        try{ Thread.sleep(500);} catch (InterruptedException e){}
    }

    public void startGame(){
        try{ Thread.sleep(500);} catch (InterruptedException e){}
        Game game = new Game(players, map);
        Utilities.setGame(game);
        (new Thread(new Runnable(){
          public void run() {
              game.runGame();
          }
        })).start();


    }
}
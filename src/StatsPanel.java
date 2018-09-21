/**
 * [StatsPanel.java]
 * The panel which displays the player's info and unit info if a unit is clicked
 * @author Katelyn Wang and Brian Li
 * June 14 2018
 */

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.FontMetrics;

public class StatsPanel extends JPanel {
    private static final Image star = Toolkit.getDefaultToolkit().getImage("assets/star2.png");
    Player player;
    StatsPanel(int height, int width, Player player){
        this.setSize(new Dimension(height, width));
        this.player = player;
    }

    public void paintComponent(Graphics g){
        Font titleFont = Utilities.getFont("assets/AdequateLight.ttf", 30f);
        g.setFont(titleFont);
        g.setColor(new Color(227, 227, 227));
        g.drawString(player.getName(), 10, 40);
        Font subtitleFont = Utilities.getFont("assets/AdequateLight.ttf", 20f);
        g.setFont(subtitleFont);
        FontMetrics fontMetrics = g.getFontMetrics(subtitleFont);
        g.drawString("Turn:", 10, 90);
        g.drawString("Number of Cities: ", 10, 140);
        g.drawString("Tribe:", 10, 190);
        g.drawString("Stars:", 10, 260);
        g.drawString(Integer.toString(player.getCurrency()), 10, 315);
        g.drawImage(star, 15 + fontMetrics.stringWidth(Integer.toString(player.getCurrency())), 300, 25,25, this);
        Font textFont = new Font(Font.SANS_SERIF, Font.PLAIN, 15);
        g.setFont(textFont);
        g.drawString(Integer.toString(Utilities.getGame().getTurn()), 10, 110);
        g.drawString(Integer.toString(player.getNumCities()), 10, 160);
        String tribe = "";
        if (player.getTribe() == 0){
            tribe = "Aquarion";
        } else if (player.getTribe() == 1){
            tribe = "Imperius";
        } else if (player.getTribe() == 2){
            tribe = "XinXi";
        } else if (player.getTribe() == 3){
            tribe = "Oumanji";
        }
        g.drawString(tribe, 10, 210);
        g.drawString("(+"+Integer.toString(player.calculateCurrIncrease())+")", 50, 285);

        if (GameMapPanel.unitSelected){
            g.setColor(new Color(255, 255, 255, 119));
            g.fillRect(30, 350, 200, 150);

            g.drawString("Health: "+Integer.toString(GameMapPanel.getSelectedUnit().getCurrHealth()), 40, 410);
            g.drawString("Attack: "+Integer.toString(GameMapPanel.getSelectedUnit().getAttack()), 40, 430);
            g.drawString("Defense: "+Integer.toString(GameMapPanel.getSelectedUnit().getDefense()), 40, 450);
            g.drawString("Movement: "+Integer.toString(GameMapPanel.getSelectedUnit().getMovement()), 40, 470);
            g.setFont(subtitleFont);
            g.drawString(GameMapPanel.getSelectedUnit().getClass().getSimpleName(), 40, 380);
        }
    }
}

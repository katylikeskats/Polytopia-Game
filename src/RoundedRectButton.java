/**
 * [RoundedRectButton.java]
 * The rounded rectangle button class
 * @author Katelyn Wang and Brian Li
 * June 14 2018
 */

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

public class RoundedRectButton extends JButton {
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
        Utilities.drawCenteredString(g, width, 0, height - 10, name);
    }
}
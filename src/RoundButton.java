/**
 * [RoundButton.java]
 * Round button object
 * @author Katelyn Wang and Brian Li
 * June 14 2018
 */

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class RoundButton extends JButton {
    ImageIcon image;

    public RoundButton(int diameter, ImageIcon image) {
        super(image);
        Dimension size = new Dimension(diameter, diameter);
        setFocusable(false);
        setPreferredSize(size);
        setBorderPainted(false);
        setContentAreaFilled(false);
    }
}

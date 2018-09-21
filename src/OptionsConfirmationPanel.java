/**
 * [OptionsConfirmationPanel.java]
 * The panel which confirms that they want to buy a unit triggered from the options panel
 * @author Katelyn Wang and Brian Li
 * June 14 2018
 */

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsConfirmationPanel extends JPanel {
    int maxX;
    int maxY;
    JPanel thisPanel;
    Player player;

    public OptionsConfirmationPanel(int maxY, int maxX, Player player) {
        thisPanel = this;
        this.setSize(new Dimension(maxX, maxY));
        this.maxX = maxX;
        this.maxY = maxY;
        this.player = player;

        Color lightGrey = new Color(203, 203, 203);
        JButton cancelButton = new RoundedRectButton(110, 30, lightGrey, "CANCEL");
        cancelButton.addActionListener(new CancelButtonListener());
        Color blue = new Color(0, 122, 203);
        JButton confirmButton = new RoundedRectButton(110, 30, blue, "CONFIRM");
        confirmButton.addActionListener(new ConfirmButtonListener());

        this.setLayout(null);
        confirmButton.setBounds(maxX - 765, maxY - 545,((RoundedRectButton) cancelButton).width, ((RoundedRectButton) cancelButton).height);
        cancelButton.setBounds(maxX - 880, maxY - 545, ((RoundedRectButton) confirmButton).width, ((RoundedRectButton) confirmButton).height);
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
        g.drawString(OptionsPanel.getAllUnits()[OptionsPanel.getPrevClick()], x + 10, y + 25);
        g.setFont(textFont);
        FontMetrics fontMetrics = g.getFontMetrics(textFont);

        g.drawString(Integer.toString(OptionsPanel.getCosts()[OptionsPanel.getPrevClick()]), x + width - fontMetrics.stringWidth(Integer.toString(OptionsPanel.getCosts()[OptionsPanel.getPrevClick()])) - 10, y + 20);
        g.drawImage(star, x + width - 35 - fontMetrics.stringWidth(Integer.toString(OptionsPanel.getCosts()[OptionsPanel.getPrevClick()])), y + 5, 20, 20, this);
    }

    private class CancelButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            GameFrame.setDisplayConfirmation(false);
        }
    }

    private class ConfirmButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
           GameMapPanel.setTrainUnit(true);
           GameMapPanel.setTrainUnitType(OptionsPanel.getAllUnits()[OptionsPanel.getPrevClick()]);
           GameFrame.setDisplayConfirmation(false);
        }
    }
}

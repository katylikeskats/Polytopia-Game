/**
 * [Utilities.java]
 * A class containing static methods to be used throughout the program
 * @author Katelyn Wang and Brian Li
 * June 14 2018
 */

import java.awt.Graphics;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.FontMetrics;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Utilities {
    private static Game game;

    public static Font getFont(String fileName, float size){
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(fileName)).deriveFont(size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.PLAIN,new File(fileName)));
        } catch (IOException | FontFormatException e){
            font = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
        }
        return font;
    }

    public static boolean contains(ArrayList<String> list, String element){
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).equals(element)){
                return true;
            }
        }
        return false;
    }

    public static boolean contains(String[] list, String element){
        for (int i = 0; i < list.length; i++){
            if (list[i].equals(element)){
                return true;
            }
        }
        return false;
    }

    public static void drawCenteredString(Graphics g, int rightX, int leftX, int y, String string) {
        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
        FontMetrics fontMetrics = g.getFontMetrics(font);
        g.setFont(font);
        g.drawString(string, (rightX - leftX - fontMetrics.stringWidth(string)) / 2 + leftX, y);
    }

    public static void setGame(Game currGame){
        game = currGame;
    }

    public static Game getGame(){
        return game;
    }
}

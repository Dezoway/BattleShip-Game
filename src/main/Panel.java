package main;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    private static int sizeX = 40;
    private static int sizeY = 40;
    public Panel(){

    }

    @Override
    protected void printComponent(Graphics g) {
        g.setColor(Color.YELLOW);
        g.drawRect(0,0,sizeX,sizeY);
    }
}

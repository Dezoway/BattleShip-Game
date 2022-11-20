package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Panel extends JPanel {
    protected static int counter = 1;
    private static int coordX = 0;
    private static int coordY = 0;
    private static int sizeX = 63;
    private static int sizeY = 70;
    public Panel(){
        this.setBounds(coordX,coordY,sizeX,sizeY);
        this.setOpaque(false);
        coordX+=sizeX+1;
        if (counter % 10 == 0){
            coordY += sizeY-2;
            coordX = 0;
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.YELLOW);
        g.drawRect(0,0,sizeX,sizeY);

    }
}

package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame {
    private GameArea area;
    public MainFrame(){
        super("BattleShip");
        prepareFrame();

    }
    public void prepareFrame(){
        this.setSize(900,800);
        this.setVisible(true);
        JLayeredPane jLayeredPane = getLayeredPane();
        JLabel label = new JLabel();
        try{
            Image img = ImageIO.read(new File("src/resources/GameArea.png"));
            img = img.getScaledInstance(700,750,Image.SCALE_DEFAULT);
            label.setIcon(new ImageIcon(img));
        }catch (IOException e){

        }
        label.setSize(700,750);
        this.add(label);
        this.setResizable(false);
    }
}

package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame {
    private GameArea area;
    ImageLoader imageLoader;
    SoundLoader soundLoader;
    AnimationLoader animationLoader;
    public MainFrame(){
        super("BattleShip");
        imageLoader = new ImageLoader();
        soundLoader = new SoundLoader();
        animationLoader = new AnimationLoader();
        area = new GameArea();
        prepareFrame();

    }
    public void prepareFrame(){
        this.setSize(900,800);
        this.setVisible(true);
        JLayeredPane jLayeredPane = getLayeredPane();
        JLabel label = new JLabel();
        Image img = (Image) imageLoader.getResource("GameArea.png");
        img =  imageLoader.setSizeImage(img, 700,750);
        label.setIcon(new ImageIcon(img));
        label.setSize(700,750);
        jLayeredPane.add(label,1);
        for(int x = 0; x != area.panels.size(); x++){
            jLayeredPane.add(area.panels.get(x),2);
        }
        //this.setResizable(false);
    }
}

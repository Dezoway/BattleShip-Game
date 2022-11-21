package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

public class MainFrame extends JFrame {
    private GameArea area;
    public static ImageLoader imageLoader;
    public static SoundLoader soundLoader;
    public static AnimationLoader animationLoader;
    private DialogWindow dialogWindow;

    public MainFrame(){
        super("BattleShip");
        imageLoader = new ImageLoader();
        soundLoader = new SoundLoader();
        animationLoader = new AnimationLoader();
        area = new GameArea();
        this.dialogWindow = new DialogWindow(area.getPlayer().getShips().stream()
                .map(x->new JLabel(new ImageIcon(imageLoader.setSizeImage(x.shipImage.getImage(), 160,160))))
                .collect(Collectors.toCollection(ArrayList::new))); // Лямбда-выражение для получения иконок кораблей из объектов класса BattleShip
        prepareFrame(); // Метод для создания окон
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void prepareFrame(){
        this.setSize(1100,800);
        this.setVisible(true);
        JLayeredPane jLayeredPane = getLayeredPane();
        jLayeredPane.add(dialogWindow);
        JLabel label = new JLabel();
        JLabel label1 = new JLabel();
        label1.setBounds(0,2,640,690);
        label1.setLayout(new GridLayout(10,10));
        label1.setBorder(BorderFactory.createLineBorder(Color.BLUE,4));
        Image img = (Image) imageLoader.getResource("GameArea.png");
        img =  imageLoader.setSizeImage(img, 700,750);
        label.setIcon(new ImageIcon(img));
        label.setBounds(0,0,700,760);
        label.add(label1);
        jLayeredPane.add(label,1);
        for(int x = 0; x != area.panels.size(); x++){
            label1.add(area.panels.get(x));
        }


    }
}

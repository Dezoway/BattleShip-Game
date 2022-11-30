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
        this.dialogWindow = new DialogWindow(area.getPlayer().getShips().stream().map(x->new JLabel(new ImageIcon(imageLoader.
                setSizeImage(x.getShipImage(), 210, 250)))).
                collect(Collectors.toCollection(ArrayList::new)));
        prepareFrame(); // Метод для создания окон
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void prepareFrame(){
        this.setSize(1100,800);
        this.setVisible(true);
        JLayeredPane jLayeredPane = getLayeredPane();
        jLayeredPane.add(dialogWindow);
        jLayeredPane.add(this.area,1);
    }
}

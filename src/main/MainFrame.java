package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
        setResizable(false);
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
        this.addMouseMotionListener(new UpdateState());
    }
    public void startBattle(){
        this.setSize(this.getWidth()+250,this.getHeight());
        this.getLayeredPane().remove(0);
        this.getLayeredPane().add(new GameArea(TypePlayer.COMPUTERPLAYER),1);
    }
    private class UpdateState extends MouseMotionAdapter { // Отслеживание изменений из главного фрейма

        @Override
        public void mouseMoved(MouseEvent e) {
            DialogWindow innerWindow = (DialogWindow)((DialogWindow) MainFrame.this.getLayeredPane().getComponent(0)).getComponent(0); // Получить объект внутреннего окна
            if(innerWindow.getJRadioButtons().stream().allMatch(x->x.getText().split(" ")[1].equals("0"))){
                innerWindow.getStartGame().setEnabled(true); // По окончанию расстановки кораблей включить кнопку
            }

        }
    }

}

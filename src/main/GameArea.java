package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class GameArea extends JLabel {
    public ArrayList<Panel> panels = new ArrayList<>();
    private static JLabel locationLabel;
    private Player player;
    private  ComputerPlayer computerPlayer;
    public GameArea(){
        for(int x = 0; x != 100; x++){
            panels.add(new Panel());
        }
        this.setIcon(new ImageIcon(MainFrame.imageLoader.
                setSizeImage((Image)MainFrame.imageLoader.getResource("GameArea.png"), 700,750)));
        this.setBounds(0,0,700,760);
        this.player = new Player(); // Создания класса игрок
        this.computerPlayer = new ComputerPlayer(); // Создание класса компьютер
        JLabel tableCells = new JLabel(); //
        locationLabel = new JLabel();
        locationLabel.setBounds(0,2,640,690);
        locationLabel.setBorder(BorderFactory.createLineBorder(Color.green,4));
        locationLabel.setLayout(null);
        this.add(locationLabel);
        tableCells.setBounds(0,2,640,690);
        tableCells.setLayout(new GridLayout(10,10));
        tableCells.setBorder(BorderFactory.createLineBorder(Color.BLUE,4));
        for(int x = 0; x != this.panels.size(); x++){
            tableCells.add(this.panels.get(x));
        }
        this.add(tableCells);
    }
    public Player getPlayer(){
        return this.player;
    }
    public static void setShip(Rectangle rec, BattleShips type, Panel panel){
        ArrayList<BattleShip> ships = Player.getShips().stream().filter(x->type.equals(x.typeBattleShip)&&!x.isActive).collect(Collectors.toCollection(ArrayList::new));

        if (ships.size() == 0) System.out.println("Ошибка");// TODO реализовать всплывающее окно
        else if (type.equals(BattleShips.x1Ship)){
            BattleShip selectedShip = ships.get(0);
            selectedShip.isActive = true;
            selectedShip.setBounds(rec);
            locationLabel.add(selectedShip);
            locationLabel.repaint();
        }

    }
    public ComputerPlayer getComputerPlayer(){
        return this.computerPlayer;
    }

}

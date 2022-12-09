package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
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
        this.player = new Player((Panel) tableCells.getComponent(2)); // Создания класса игрок
        this.computerPlayer = new ComputerPlayer((Panel) tableCells.getComponent(0)); // Создание класса компьютер
        this.add(tableCells);
    }
    public Player getPlayer(){
        return this.player;
    }
    public static void setShip(Rectangle rec, BattleShips type, Panel panel){
        ArrayList<BattleShip> ships = Player.getShips().stream().filter(x->type.equals(x.typeBattleShip)&&!x.isActive).collect(Collectors.toCollection(ArrayList::new));//Выбирать из пула только те корабли которые не размещены на карте
        if (ships.size() == 0)return;//Если в пуле не осталось кораблей то отменить постановку корабля
        else if (type.equals(BattleShips.x1Ship)){
            int index = ((GameArea) locationLabel.getParent()).panels.indexOf(panel);
            BattleShip selectedShip = ships.get(0);
            selectedShip.setBoundsShip(rec);
            if (selectedShip.checkCells(((GameArea) locationLabel.getParent()).panels, index))return;
            if (((GameArea)locationLabel.getParent()).checkOutBorder(selectedShip,selectedShip.getOrientation()))return;
            selectedShip.setEmoloyedPanels(((GameArea) locationLabel.getParent()).panels, index);
            DialogWindow.instanceShipButton.setText("Осталось "+(Integer.parseInt(DialogWindow.instanceShipButton.getText().split(" ")[1])-1));
            locationLabel.add(selectedShip);
        }
        else if (type.equals(BattleShips.x2Ship)){
            int index = ((GameArea) locationLabel.getParent()).panels.indexOf(panel); // Получить индекс начальной клетки
            BattleShip selectedShip = ships.get(0);
            selectedShip.setBoundsShip(rec);
            if (selectedShip.checkCells(((GameArea) locationLabel.getParent()).panels, index))return;
            if (((GameArea)locationLabel.getParent()).checkOutBorder(selectedShip,selectedShip.getOrientation()))return;
            selectedShip.setEmoloyedPanels(((GameArea) locationLabel.getParent()).panels, index);
            DialogWindow.instanceShipButton.setText("Осталось "+(Integer.parseInt(DialogWindow.instanceShipButton.getText().split(" ")[1])-1));
            locationLabel.add(selectedShip);
        }
        else if (type.equals(BattleShips.x3Ship)){
            int index = ((GameArea) locationLabel.getParent()).panels.indexOf(panel);
            BattleShip selectedShip = ships.get(0);
            selectedShip.setBoundsShip(rec);
            if (selectedShip.checkCells(((GameArea) locationLabel.getParent()).panels, index))return; // Проверка расположения корабля относительно других кораблей
            if (((GameArea)locationLabel.getParent()).checkOutBorder(selectedShip,selectedShip.getOrientation()))return; // Проверка выхода корабля за границы карты
            selectedShip.setEmoloyedPanels(((GameArea) locationLabel.getParent()).panels, index);
            DialogWindow.instanceShipButton.setText("Осталось "+(Integer.parseInt(DialogWindow.instanceShipButton.getText().split(" ")[1])-1));
            locationLabel.add(selectedShip);
        } else if (type.equals(BattleShips.x4Ship)) {
            int index = ((GameArea) locationLabel.getParent()).panels.indexOf(panel);
            BattleShip selectedShip = ships.get(0);
            selectedShip.setBoundsShip(rec);
            if (selectedShip.checkCells(((GameArea) locationLabel.getParent()).panels, index))return;
            if (((GameArea)locationLabel.getParent()).checkOutBorder(selectedShip,selectedShip.getOrientation()))return;
            selectedShip.setEmoloyedPanels(((GameArea) locationLabel.getParent()).panels, index);
            DialogWindow.instanceShipButton.setText("Осталось "+(Integer.parseInt(DialogWindow.instanceShipButton.getText().split(" ")[1])-1));

            locationLabel.add(selectedShip);
        }
        if (DialogWindow.instanceShipButton.getText().split(" ")[1].equals("0"))DialogWindow.instanceShipButton.setEnabled(false);//Выключить кнопку если кораблей не осталось
        locationLabel.repaint();
    }
    public boolean checkOutBorder(BattleShip ship, int orientation){
        if (orientation==0){    //Проверка выхода за границу по оси X
            return ship.getX()+ship.getWidth() > locationLabel.getWidth();
        }
        else{   // Проверка выхода за границу по оси Y
            return ship.getY()+ship.getHeight() > locationLabel.getHeight();
        }
    }
    public ComputerPlayer getComputerPlayer(){
        return this.computerPlayer;
    }

}

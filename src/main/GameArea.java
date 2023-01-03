package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class GameArea extends JLabel {
    /*
        Данный класс реализует игровое поле. В классе реализованы перегруженные конструкторы для разделения на поле
        игрока и поле противника
     */
    public static ArrayList<Panel> playerPanels = new ArrayList<>();
    public ArrayList<Panel> computerPanels = new ArrayList<>();

    public static JLabel locationLabel;
    public static JLabel tableCells;
    public static JLabel locationLabel2;

    public volatile static boolean move = false;    // Атомарная переменная для опредения чей будет ход
    private Player player;
    public TypePlayer typePlayer;
    private  ComputerPlayer computerPlayer;
    public GameArea(){
        for(int x = 0; x != 100; x++){
            playerPanels.add(new Panel());
        }
        this.setIcon(new ImageIcon(MainFrame.imageLoader.
                setSizeImage((Image)MainFrame.imageLoader.getResource("GameArea.png"), 700,750)));  // Добавление игрового поля
        this.setBounds(0,0,700,760);
        tableCells = new JLabel(); //
        locationLabel = new JLabel();
        locationLabel.setOpaque(false);
        locationLabel.setBounds(0,2,640,690);
        locationLabel.setBorder(BorderFactory.createLineBorder(Color.green,2));
        locationLabel.setLayout(null);
        this.add(locationLabel);
        tableCells.setBounds(0,2,640,690);
        tableCells.setLayout(new GridLayout(10,10));
        tableCells.setBorder(BorderFactory.createLineBorder(Color.BLUE,4));
        for(int x = 0; x != playerPanels.size(); x++){
            tableCells.add(playerPanels.get(x));
        }
        this.player = new Player(); // Создания класса игрок
        this.add(tableCells);
        this.typePlayer = TypePlayer.PLAYER;
    }
    public GameArea(TypePlayer typePlayer){
        for(int x = 0; x!= 100;x++){
            this.computerPanels.add(new Panel(Color.BLUE));
        }
        this.computerPlayer = new ComputerPlayer();
        this.setIcon(new ImageIcon(MainFrame.imageLoader.
                setSizeImage((Image)MainFrame.imageLoader.getResource("GameArea.png"), 700,750)));
        this.setBounds(700,0,700,760);
        JLabel tableCells2 = new JLabel();
        locationLabel.setOpaque(false);
        locationLabel2 = new JLabel();
        locationLabel2.setBounds(0,2,640,690);
        locationLabel2.setBorder(BorderFactory.createLineBorder(Color.green,4));
        locationLabel2.setLayout(null);
        this.add(locationLabel2);
        tableCells2.setBounds(0,2,640,690);
        tableCells2.setLayout(new GridLayout(10,10));
        tableCells2.setBorder(BorderFactory.createLineBorder(Color.BLACK,4));
        for(int x = 0; x != this.computerPanels.size(); x++){
            tableCells2.add(this.computerPanels.get(x));
        }
        this.add(tableCells2);
        computerPlayer.placeShips(this,locationLabel2);
        this.typePlayer = typePlayer;
        Thread thread = new MovePlayersThread();
        thread.start();


    }

    class MovePlayersThread extends Thread {
        @Override
        public void run() {
            while (true) {
                if (GameArea.move) {
                    GameArea.this.computerPlayer.shot();
                    GameArea.move = false;
                }
                if(GameArea.tableCells == null)break;
            }
        }
    }

    public Player getPlayer(){
        return this.player;
    }
    public static void setShip(Rectangle rec, BattleShips type, Panel panel){
        ArrayList<BattleShip> ships = Player.getShips().stream().filter(x->type.equals(x.typeBattleShip)&&!x.isActive).collect(Collectors.toCollection(ArrayList::new));//Выбирать из пула только те корабли которые не размещены на карте
        if (ships.size() == 0)return;//Если в пуле не осталось кораблей то отменить постановку корабля
        else if (type.equals(BattleShips.x1Ship)){
            int index = ((GameArea) locationLabel.getParent()).playerPanels.indexOf(panel);
            BattleShip selectedShip = ships.get(0);
            selectedShip.setBoundsShip(rec);
            if (selectedShip.checkCells(((GameArea) locationLabel.getParent()).playerPanels, index))return;
            if (((GameArea)locationLabel.getParent()).checkOutBorder(selectedShip,selectedShip.getOrientation()))return;
            selectedShip.setEmoloyedPanels(((GameArea) locationLabel.getParent()).playerPanels, index);
            DialogWindow.instanceShipButton.setText("Осталось "+(Integer.parseInt(DialogWindow.instanceShipButton.getText().split(" ")[1])-1));
            locationLabel.add(selectedShip);
        }
        else if (type.equals(BattleShips.x2Ship)){
            int index = ((GameArea) locationLabel.getParent()).playerPanels.indexOf(panel); // Получить индекс начальной клетки
            BattleShip selectedShip = ships.get(0);
            selectedShip.setBoundsShip(rec);
            if (selectedShip.checkCells(((GameArea) locationLabel.getParent()).playerPanels, index))return;
            if (((GameArea)locationLabel.getParent()).checkOutBorder(selectedShip,selectedShip.getOrientation()))return;
            selectedShip.setEmoloyedPanels(((GameArea) locationLabel.getParent()).playerPanels, index);
            DialogWindow.instanceShipButton.setText("Осталось "+(Integer.parseInt(DialogWindow.instanceShipButton.getText().split(" ")[1])-1));
            locationLabel.add(selectedShip);
        }
        else if (type.equals(BattleShips.x3Ship)){
            int index = ((GameArea) locationLabel.getParent()).playerPanels.indexOf(panel);
            BattleShip selectedShip = ships.get(0);
            selectedShip.setBoundsShip(rec);
            if (selectedShip.checkCells(((GameArea) locationLabel.getParent()).playerPanels, index))return; // Проверка расположения корабля относительно других кораблей
            if (((GameArea)locationLabel.getParent()).checkOutBorder(selectedShip,selectedShip.getOrientation()))return; // Проверка выхода корабля за границы карты
            selectedShip.setEmoloyedPanels(((GameArea) locationLabel.getParent()).playerPanels, index);
            DialogWindow.instanceShipButton.setText("Осталось "+(Integer.parseInt(DialogWindow.instanceShipButton.getText().split(" ")[1])-1));
            locationLabel.add(selectedShip);
        } else if (type.equals(BattleShips.x4Ship)) {
            int index = ((GameArea) locationLabel.getParent()).playerPanels.indexOf(panel);
            BattleShip selectedShip = ships.get(0);
            selectedShip.setBoundsShip(rec);
            if (selectedShip.checkCells(((GameArea) locationLabel.getParent()).playerPanels, index))return;
            if (((GameArea)locationLabel.getParent()).checkOutBorder(selectedShip,selectedShip.getOrientation()))return;
            selectedShip.setEmoloyedPanels(((GameArea) locationLabel.getParent()).playerPanels, index);
            DialogWindow.instanceShipButton.setText("Осталось "+(Integer.parseInt(DialogWindow.instanceShipButton.getText().split(" ")[1])-1));

            locationLabel.add(selectedShip);
        }
        if (DialogWindow.instanceShipButton.getText().split(" ")[1].equals("0"))DialogWindow.instanceShipButton.setEnabled(false);//Выключить кнопку если кораблей не осталось
        locationLabel.repaint();
    }
    public static void clearArea(){
        locationLabel.removeAll();
        locationLabel.repaint();
        for(Panel panel: GameArea.playerPanels)panel.setActiveCell(false);
        for(BattleShip ship:Player.getShips())ship.isActive=false;
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

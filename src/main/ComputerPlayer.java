package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class ComputerPlayer {
    public volatile static int shotCount = 20;
    private ArrayList<BattleShip> ships = new ArrayList<>(); // Список кораблей для противника
    public ComputerPlayer(){
        /*
            Создание объектов типа корабль путём инициализации координат и размера изображения с последующим добавлением в список
         */
        ships.add(new BattleShip(BattleShips.x1Ship, TypePlayer.COMPUTERPLAYER));
        ships.add(new BattleShip(BattleShips.x1Ship, TypePlayer.COMPUTERPLAYER));
        ships.add(new BattleShip(BattleShips.x1Ship, TypePlayer.COMPUTERPLAYER));
        ships.add(new BattleShip(BattleShips.x1Ship, TypePlayer.COMPUTERPLAYER));
        ships.add(new BattleShip(BattleShips.x2Ship, TypePlayer.COMPUTERPLAYER));
        ships.add(new BattleShip(BattleShips.x2Ship, TypePlayer.COMPUTERPLAYER));
        ships.add(new BattleShip(BattleShips.x2Ship, TypePlayer.COMPUTERPLAYER));
        ships.add(new BattleShip(BattleShips.x3Ship, TypePlayer.COMPUTERPLAYER));
        ships.add(new BattleShip(BattleShips.x3Ship, TypePlayer.COMPUTERPLAYER));
        ships.add(new BattleShip(BattleShips.x4Ship, TypePlayer.COMPUTERPLAYER));
    }
    public void placeShips(GameArea area, JLabel location){
        int counter = 0;
        Panel[][] panels = new Panel[10][10]; // Представление клеток в виде двумерного массива для отслеживания границ карты
        for(int x = 0; x != 10;x++){
            for(int i = 0; i != 10; i++){
                panels[x][i] = area.computerPanels.get(x+i);
            }
        }
        for(int x = this.ships.size()-1; x != -1; x--){     // Расстановка кораблей происходит от мелких к большим т.к. есть вероятность уйти в бесконечный цикл
            if(new Random().nextInt(2) == 1)ships.get(x).setVerticalOrientation(); // Случайная ориентация корабля
            while(true){
                counter++;
                if(counter == 10000){ // Данное условие сбрасывает все размещения кораблей в случае если нет возможности разместить один из кораблей из-за пересечений на карте
                    for(Panel panel: area.computerPanels){
                        panel.setActiveCell(true);
                    }
                    x = ships.size()-1;
                    counter = 0;
                }
                int index = new Random().nextInt(99);   // Случайная позиция корабля
                if(ships.get(x).checkCells(area.computerPanels,index))continue; // Пропустить итерацию если корабли пересекаются
                if(area.computerPanels.get(x).getIsActiveCell())continue;
                if(!checkOutBorder(panels, index, ships.get(x)))continue;   // Пропустить итерацию если корабль выходит за границу картьы
                ships.get(x).setEmoloyedPanels(area.computerPanels, index); // Разместить корабль в случае удовлетворительных условий
                break;  // Остановить вложенный цикл
                }
        }
    }
    private boolean checkOutBorder(Panel[][]panels, int index, BattleShip ship){ // Реализация выхода за границу через исключения
        if(ship.getOrientation() == 0){
            try{
                Panel panel = panels[index/10][(index%10)+ship.length];
            }catch (IndexOutOfBoundsException e){return false;}
        }
        else {
            try {
                Panel panel = panels[(index / 10) + ship.length][index % 10];
            } catch (IndexOutOfBoundsException e) {
                return false;
            }
        }
        return true; // В случае если исключение не произошло то вернуть истину
    }
    public void shot(){
        System.out.println(Player.shotCount+" "+shotCount);
        ArrayList<Panel> notIsBeatPanels = GameArea.playerPanels.stream().filter(x->!x.isBeat).collect(Collectors.toCollection(ArrayList::new)); // Выбрать только те клетки которые не были выбиты
        System.out.println("Количество оставшихся:"+notIsBeatPanels.size());
        Panel panel = notIsBeatPanels.get(new Random().nextInt(notIsBeatPanels.size())); // Получить случайную клетку
        JLabel label = new JLabel();
        label.setBounds(panel.getX(),panel.getY(),panel.getWidth(), panel.getHeight()); //создать область которая будет распологаться над выбранной клектой
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        if(!panel.getIsActiveCell()){
            label.setIcon(null);
            label.setOpaque(true);
            label.setBackground(Color.GREEN);
            GameArea.locationLabel.add(label);
        }
        else{
            label.setBackground(Color.RED);
            label.setOpaque(true);
            GameArea.locationLabel.add(label);
            shotCount--;
            panel.isBeat=true;
            shot();
        }
        panel.isBeat=true;
        GameArea.locationLabel.repaint();
    }

    public ArrayList<BattleShip> getShips(){ // Геттер кораблей для противника
        return this.ships;
    }
}

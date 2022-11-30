package main;

import java.util.ArrayList;

public class ComputerPlayer {
    private ArrayList<BattleShip> ships = new ArrayList<>(); // Список кораблей для противника

    public ComputerPlayer(){
        /*
            Создание объектов типа корабль путём инициализации координат и размера изображения с последующим добавлением в список
         */
        ships.add(new BattleShip(BattleShips.x1Ship,150,150));
        ships.add(new BattleShip(BattleShips.x2Ship,60,60));
        ships.add(new BattleShip(BattleShips.x3Ship,80,80));
        ships.add(new BattleShip(BattleShips.x4Ship,100,100));
        ships.add(new BattleShip(BattleShips.x2Ship,60,60));
        ships.add(new BattleShip(BattleShips.x2Ship,60,60));
        ships.add(new BattleShip(BattleShips.x1Ship,150,150));
        ships.add(new BattleShip(BattleShips.x1Ship,150,150));
        ships.add(new BattleShip(BattleShips.x1Ship,150,150));
        ships.add(new BattleShip(BattleShips.x3Ship,80,80));
    }

    public ArrayList<BattleShip> getShips(){ // Геттер кораблей для противника
        return this.ships;
    }
}

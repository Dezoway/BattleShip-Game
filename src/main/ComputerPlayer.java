package main;

import java.util.ArrayList;

public class ComputerPlayer {
    private ArrayList<BattleShip> ships = new ArrayList<>(); // Список кораблей для противника
    public ComputerPlayer(Panel panel){
        /*
            Создание объектов типа корабль путём инициализации координат и размера изображения с последующим добавлением в список
         */
        ships.add(new BattleShip(BattleShips.x1Ship,panel));
        ships.add(new BattleShip(BattleShips.x2Ship,panel));
        ships.add(new BattleShip(BattleShips.x3Ship,panel));
        ships.add(new BattleShip(BattleShips.x4Ship,panel));
        ships.add(new BattleShip(BattleShips.x2Ship,panel));
        ships.add(new BattleShip(BattleShips.x2Ship,panel));
        ships.add(new BattleShip(BattleShips.x1Ship,panel));
        ships.add(new BattleShip(BattleShips.x1Ship,panel));
        ships.add(new BattleShip(BattleShips.x1Ship,panel));
        ships.add(new BattleShip(BattleShips.x3Ship,panel));
    }

    public ArrayList<BattleShip> getShips(){ // Геттер кораблей для противника
        return this.ships;
    }
}

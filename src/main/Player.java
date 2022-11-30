package main;

import java.util.ArrayList;

public class Player {


    private static ArrayList<BattleShip> ships = new ArrayList<>(); // Список кораблей для игрока
    public Player(){
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
    public static ArrayList<BattleShip> getShips(){ // Геттер кораблей для игрока
        return ships;
    }
}

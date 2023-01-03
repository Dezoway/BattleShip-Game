package main;

import java.util.ArrayList;
import java.util.EventListener;

public class Player {
    public volatile static int shotCount = 20; //Переменная подсчёта попаданий, помечена атомарной для того чтобы потоки её не кешировали


    private static ArrayList<BattleShip> ships = new ArrayList<>(); // Список кораблей для игрока
    public Player(){
        ships.add(new BattleShip(BattleShips.x1Ship, TypePlayer.PLAYER));
        ships.add(new BattleShip(BattleShips.x2Ship, TypePlayer.PLAYER));
        ships.add(new BattleShip(BattleShips.x3Ship, TypePlayer.PLAYER));
        ships.add(new BattleShip(BattleShips.x4Ship, TypePlayer.PLAYER));
        ships.add(new BattleShip(BattleShips.x2Ship, TypePlayer.PLAYER));
        ships.add(new BattleShip(BattleShips.x2Ship, TypePlayer.PLAYER));
        ships.add(new BattleShip(BattleShips.x1Ship, TypePlayer.PLAYER));
        ships.add(new BattleShip(BattleShips.x1Ship, TypePlayer.PLAYER));
        ships.add(new BattleShip(BattleShips.x1Ship, TypePlayer.PLAYER));
        ships.add(new BattleShip(BattleShips.x3Ship, TypePlayer.PLAYER));
    }
    public static ArrayList<BattleShip> getShips(){ // Геттер кораблей для игрока
        return ships;
    }
}

package main;

import java.util.ArrayList;
import java.util.EventListener;

public class Player {


    private static ArrayList<BattleShip> ships = new ArrayList<>(); // Список кораблей для игрока
    Panel panelSize;
    public Player(Panel panel){
        this.panelSize = panel;
        ships.add(new BattleShip(BattleShips.x1Ship,panelSize));
        ships.add(new BattleShip(BattleShips.x2Ship,panelSize));
        ships.add(new BattleShip(BattleShips.x3Ship,panelSize));
        ships.add(new BattleShip(BattleShips.x4Ship,panelSize));
        ships.add(new BattleShip(BattleShips.x2Ship,panelSize));
        ships.add(new BattleShip(BattleShips.x2Ship,panelSize));
        ships.add(new BattleShip(BattleShips.x1Ship,panelSize));
        ships.add(new BattleShip(BattleShips.x1Ship,panelSize));
        ships.add(new BattleShip(BattleShips.x1Ship,panelSize));
        ships.add(new BattleShip(BattleShips.x3Ship,panelSize));
    }
    public static ArrayList<BattleShip> getShips(){ // Геттер кораблей для игрока
        return ships;
    }
}

package main;

import java.util.ArrayList;

public class Player {
    private ArrayList<BattleShip> ships = new ArrayList<>(); // Список кораблей для игрока
    public Player(){
        for(BattleShips b: BattleShips.values()){
            ships.add(new BattleShip(b));
        }
    }
    public ArrayList<BattleShip> getShips(){ // Геттер кораблей для игрока
        return this.ships;
    }
}

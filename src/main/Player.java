package main;

import java.util.ArrayList;

public class Player {


    private ArrayList<BattleShip> ships = new ArrayList<>(); // Список кораблей для игрока
    public Player(){
        this.ships.add(new BattleShip(BattleShips.x1Ship));
        this.ships.add(new BattleShip(BattleShips.x2Ship));
        this.ships.add(new BattleShip(BattleShips.x3Ship));
        this.ships.add(new BattleShip(BattleShips.x4Ship));
        this.ships.add(new BattleShip(BattleShips.x2Ship));
        this.ships.add(new BattleShip(BattleShips.x2Ship));
        this.ships.add(new BattleShip(BattleShips.x1Ship));
        this.ships.add(new BattleShip(BattleShips.x1Ship));
        this.ships.add(new BattleShip(BattleShips.x1Ship));
        this.ships.add(new BattleShip(BattleShips.x3Ship));
    }
    public ArrayList<BattleShip> getShips(){ // Геттер кораблей для игрока
        return this.ships;
    }
}

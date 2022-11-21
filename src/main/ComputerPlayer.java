package main;

import java.util.ArrayList;

public class ComputerPlayer {
    private ArrayList<BattleShip> ships = new ArrayList<>(); // Список кораблей для противника

    public ComputerPlayer(){
        this.ships.add(new BattleShip(BattleShips.x1Ship));
        this.ships.add(new BattleShip(BattleShips.x1Ship));
        this.ships.add(new BattleShip(BattleShips.x1Ship));
        this.ships.add(new BattleShip(BattleShips.x1Ship));
        this.ships.add(new BattleShip(BattleShips.x2Ship));
        this.ships.add(new BattleShip(BattleShips.x2Ship));
        this.ships.add(new BattleShip(BattleShips.x2Ship));
        this.ships.add(new BattleShip(BattleShips.x3Ship));
        this.ships.add(new BattleShip(BattleShips.x3Ship));
        this.ships.add(new BattleShip(BattleShips.x4Ship));
    }

    public ArrayList<BattleShip> getShips(){ // Геттер кораблей для противника
        return this.ships;
    }
}

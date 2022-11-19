package main;

import java.util.ArrayList;

public class ComputerPlayer {
    private ArrayList<BattleShip> ships = new ArrayList<>(); // Список кораблей для противника

    public ComputerPlayer(){
        for(BattleShips b: BattleShips.values()){
            this.ships.add(new BattleShip(b));
        }
    }

    public ArrayList<BattleShip> getShips(){ // Геттер кораблей для противника
        return this.ships;
    }
}

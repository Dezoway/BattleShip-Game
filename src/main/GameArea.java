package main;

import java.util.ArrayList;

public class GameArea {
    public ArrayList<Panel> panels = new ArrayList<>();
    private Player player;
    private  ComputerPlayer computerPlayer;
    public GameArea(){
        for(int x = 0; x != 100; x++){
            panels.add(new Panel());
            Panel.counter++;
        }
        this.player = new Player();
        this.computerPlayer = new ComputerPlayer();
    }
    public Player getPlayer(){
        return this.player;
    }

    public ComputerPlayer getComputerPlayer(){
        return this.computerPlayer;
    }
}

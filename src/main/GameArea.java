package main;

import java.util.ArrayList;

public class GameArea {
    public ArrayList<Panel> panels = new ArrayList<>();
    public GameArea(){
        for(int x = 0; x != 100; x++){
            panels.add(new Panel());
            Panel.counter++;
        }
    }
}

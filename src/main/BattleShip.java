package main;

import javax.imageio.ImageReader;
import javax.swing.*;
import java.awt.*;

public class BattleShip {
    public BattleShips typeBattleShip;
    public ImageIcon shipImage;
    public int headPositionX;
    public int getHeadPositionY;

    public BattleShip(BattleShips type){
        this.typeBattleShip = type;
        this.shipImage = new ImageIcon((Image)new ImageLoader().getResource(type.name()+".png"));
    }
}

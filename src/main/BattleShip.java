package main;

import javax.imageio.ImageReader;
import javax.swing.*;
import java.awt.*;

public class BattleShip extends JLabel{
    public BattleShips typeBattleShip;
    public boolean isActive = false;
    private Image shipImage;

    public BattleShip(BattleShips type, int sizeX, int sizeY){
        this.typeBattleShip = type;
        ImageLoader loader = new ImageLoader();
        this.shipImage = (Image) loader.getResource(type.name()+".png");
        this.setHorizontalAlignment(CENTER);
        this.setIcon(new ImageIcon(loader.setSizeImage(this.shipImage, sizeX,sizeY)));
    }

    public Image getShipImage(){
        return this.shipImage;
    }
}

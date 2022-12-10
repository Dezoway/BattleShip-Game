package main;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class BattleShip extends JLabel{
    public BattleShips typeBattleShip;
    public boolean isActive = false;
    public ArrayList<Panel> emoloyedPanels;
    private ImageLoader loader;
    public int length;
    private int orientation; // Ориентация корабля, если горизонтальная - 0 иначе 1
    private BufferedImage shipImage;

    public BattleShip(BattleShips type, Panel panel){
        switch (type){
            case x1Ship -> {this.length=1;}
            case x2Ship -> {this.length=2;}
            case x3Ship -> {this.length=3;}
            case x4Ship -> {this.length=4;}
        }
        this.typeBattleShip = type;
        this.setLayout(new BorderLayout());
        loader = new ImageLoader();
        this.shipImage = (BufferedImage) loader.getResource(type.name()+".png");
        this.setHorizontalAlignment(CENTER);
        this.setVerticalAlignment(CENTER);
    }

    public void setEmoloyedPanels(List<Panel> panels, int index){
        this.emoloyedPanels = new ArrayList<>();
        if (orientation == 0){
            for(int x = index; x != index + length;x++)panels.get(x).setActiveCell(false);
        }
        else{
            for(int x = index; x != index + 10 * length;x+=10)panels.get(x).setActiveCell(false);
        }
        this.isActive=true;
    }
    public boolean checkCells(List<Panel> panels, int index){ // Проверка занятых клеток
        if (orientation == 0){
            for(int x = index; x != index + length;x++){
                try{if(panels.get(x-10).getIsActiveCell())return true;}catch (IndexOutOfBoundsException e){}//Каждая проверка обернута в обработчик, т.к может быть выбрана крайняя клетка поля
                try{if(panels.get(x+10).getIsActiveCell())return true;}catch (IndexOutOfBoundsException e){}
                try{if(panels.get(x-11).getIsActiveCell())return true;}catch (IndexOutOfBoundsException e){}
                try{if(panels.get(x-9).getIsActiveCell())return true;}catch (IndexOutOfBoundsException e){}
                try{if(panels.get(x+11).getIsActiveCell())return true;}catch (IndexOutOfBoundsException e){}
                try{if(panels.get(x+9).getIsActiveCell())return true;}catch (IndexOutOfBoundsException e){}
                try{if(panels.get(x-1).getIsActiveCell())return true;}catch (IndexOutOfBoundsException e){}
                try{if(panels.get(x+1).getIsActiveCell())return true;}catch (IndexOutOfBoundsException e){}
            }
        }
        else{
            for(int x = index; x != index + 10 * length;x+=10){
                try{if(panels.get(x-10).getIsActiveCell())return true;}catch (IndexOutOfBoundsException e){}
                try{if(panels.get(x+10).getIsActiveCell())return true;}catch (IndexOutOfBoundsException e){}
                try{if(panels.get(x-11).getIsActiveCell())return true;}catch (IndexOutOfBoundsException e){}
                try{if(panels.get(x-9).getIsActiveCell())return true;}catch (IndexOutOfBoundsException e){}
                try{if(panels.get(x+11).getIsActiveCell())return true;}catch (IndexOutOfBoundsException e){}
                try{if(panels.get(x+9).getIsActiveCell())return true;}catch (IndexOutOfBoundsException e){}
                try{if(panels.get(x-1).getIsActiveCell())return true;}catch (IndexOutOfBoundsException e){}
                try{if(panels.get(x+1).getIsActiveCell())return true;}catch (IndexOutOfBoundsException e){}
        }

    }
        return false;
    }
    public void setBoundsShip(Rectangle rec){
        this.orientation = DialogWindow.getOrientation();
        if (this.orientation == 0){
            rec.setSize(rec.width*this.length, rec.height);
            this.setBounds(rec);
            this.setIcon(new ImageIcon(ImageLoader.resize(shipImage,this,0)));
        }
        else{
            rec.setSize(rec.width, rec.height*this.length);
            this.setBounds(rec);
            this.setIcon(new ImageIcon(ImageLoader.resize(shipImage,this,1)));
        }
        this.setBorder(BorderFactory.createLineBorder(Color.green,3));
    }
    public int getOrientation(){
        return this.orientation;
    }


    public Image getShipImage(){
        return this.shipImage;
    }
}

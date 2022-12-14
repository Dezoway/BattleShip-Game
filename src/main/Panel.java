package main;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Panel extends JPanel {
    private boolean isActiveCell = true; // Не занята ли клетка кораблём
    private static int counter;
    private int counterSelf;

    public Panel(){
        counter++;
        this.counterSelf = counter;
        this.setPreferredSize(new Dimension(150,150));
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        this.addMouseListener(new MouseListenerEvent());

    }
    public Panel(Color color){
        counter++;
        this.counterSelf = counter;
        this.setPreferredSize(new Dimension(150,150));
        this.setLayout(new BorderLayout());
        this.setBackground(color);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        this.addMouseListener(new MouseListenerEvent());
    }
    public void setActiveCell(boolean val){this.isActiveCell = val;}
    public boolean getIsActiveCell(){
        return !this.isActiveCell;
    }

    class MouseListenerEvent extends MouseAdapter {
        Border panelBorder;
        @Override
        public void mouseClicked(MouseEvent e) {
            if (DialogWindow.selectedButton == 0)return;
            Rectangle rec = Panel.this.getBounds();
            switch (DialogWindow.selectedButton) {
                case 1 -> GameArea.setShip(rec, BattleShips.x1Ship, Panel.this);
                case 2 -> GameArea.setShip(rec, BattleShips.x2Ship, Panel.this);
                case 3 -> GameArea.setShip(rec, BattleShips.x3Ship, Panel.this);
                case 4 -> GameArea.setShip(rec, BattleShips.x4Ship, Panel.this);
            }
            }



        @Override
        public void mouseEntered(MouseEvent e) {
            if (Panel.this.isActiveCell){
                panelBorder = Panel.this.getBorder();
                Panel.this.setBorder(BorderFactory.createLineBorder(Color.RED,2));

            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            Panel.this.setBorder(panelBorder);

        }
    }

}

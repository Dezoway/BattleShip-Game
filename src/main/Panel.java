package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Panel extends JPanel {
    private boolean isActiveCell = true;

    public Panel(){
        this.setPreferredSize(new Dimension(150,150));
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        this.addMouseListener(new MouseListenerEvent());

    }


    class MouseListenerEvent implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {

            Rectangle rec = Panel.this.getBounds();
            switch (DialogWindow.selectedButton) {
                case 1 -> GameArea.setShip(rec, BattleShips.x1Ship,Panel.this);
                case 2 -> GameArea.setShip(rec, BattleShips.x2Ship,Panel.this);
                case 3 -> GameArea.setShip(rec, BattleShips.x3Ship,Panel.this);
                case 4 -> GameArea.setShip(rec, BattleShips.x4Ship,Panel.this);
                }
                Panel.this.isActiveCell=false;
            }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (Panel.this.isActiveCell)Panel.this.setBorder(BorderFactory.createLineBorder(Color.RED,2));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            Panel.this.setBorder(null);

        }
    }

}

package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Panel extends JPanel {

    public Panel(){
        this.setPreferredSize(new Dimension(150,150));

        this.setOpaque(false);
        this.addMouseListener(new MouseListenerEvent());

    }
    class MouseListenerEvent implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            Panel.this.setBorder(BorderFactory.createLineBorder(Color.RED,2));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            Panel.this.setBorder(null);

        }
    }

}

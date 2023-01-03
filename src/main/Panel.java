package main;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.TimerTask;

public class Panel extends JPanel {
    private boolean isActiveCell = true; // Не занята ли клетка кораблём
    private static int counter;
    public boolean isBeat = false;
    private int counterSelf;
    /*
    В данном классе осуществлена реализация клеток на игровом поле, перегруженные конструкторы служат для разделения
    на клетки игрока и противника. Так же у каждой клетки реализован свой прослушиватель действий ввода с мыши.
     */
    public Panel(){
        counter++;
        this.counterSelf = counter;
        this.setPreferredSize(new Dimension(150,150));
        this.setLayout(new BorderLayout()); //Задать менеджер расположений
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
        this.addMouseListener(new MouseListenerEvent()); //Добавление к каждой созданной клетке прослушивателя мыши
    }
    public void setActiveCell(boolean val){this.isActiveCell = val;}

    public boolean getIsActiveCell(){
        return !this.isActiveCell;
    }

    class MouseListenerEvent extends MouseAdapter {
        Border panelBorder;
        @Override
        public void mouseClicked(MouseEvent e) {
            if (((GameArea) Panel.this.getParent().getParent()).typePlayer == TypePlayer.COMPUTERPLAYER) {  //Если игрок кликнул на вражескую клетку
                if(!Panel.this.isActiveCell){   //Если клетка активна
                    Panel.this.setBackground(null);
                    try {// Данный блок осуществляет воспроизведение звуковой дорожки
                        Clip clip = AudioSystem.getClip(); // Получить объект для воспроизведения звука
                        SoundLoader soundLoader = new SoundLoader();
                        clip.open((AudioInputStream) soundLoader.getResource("hit.wav"));   //Получить аудио поток
                        clip.start();   //запустить аудио поток
                    }catch (LineUnavailableException | IOException exception){} //Обработка возможных исключений из методов объекта clip и soundLoader
                    JLabel label = new JLabel();
                    label.setBounds(0,0,Panel.this.getWidth(), Panel.this.getHeight());
                    Panel.this.setBackground(Color.RED);    //Присвоить клетке красный цвет
                    Panel.this.add(label);
                    Player.shotCount--;
                }
                else{Panel.this.setBackground(Color.GREEN);GameArea.move=true;}
                Panel.this.removeMouseListener(Panel.this.getMouseListeners()[0]); // Удаление прослушивателя, т.к игрок уже попал по данной клетке
                Panel.this.setBorder(panelBorder);  // Восстановить изначальные рамки
            } else {
                if (DialogWindow.selectedButton == 0) return;
                Rectangle rec = Panel.this.getBounds();
                switch (DialogWindow.selectedButton) {
                    case 1 -> GameArea.setShip(rec, BattleShips.x1Ship, Panel.this);
                    case 2 -> GameArea.setShip(rec, BattleShips.x2Ship, Panel.this);
                    case 3 -> GameArea.setShip(rec, BattleShips.x3Ship, Panel.this);
                    case 4 -> GameArea.setShip(rec, BattleShips.x4Ship, Panel.this);
                }
            }
        }


        @Override
        public void mouseEntered(MouseEvent e) {
            if (Panel.this.isActiveCell && ((GameArea)Panel.this.getParent().getParent()).typePlayer==TypePlayer.PLAYER){   // Если было осуществлено движение мышью в поле игрока то убрать подсветку расстановленных кораблей
                panelBorder = Panel.this.getBorder();
                Panel.this.setBorder(BorderFactory.createLineBorder(Color.RED,2));
            }
            else{   //Если движение мышью было во вражеском поле то оставить подсветку по клеткам для того чтобы не выдать позиции противника
                panelBorder = Panel.this.getBorder();
                Panel.this.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            }
        }

        @Override
        public void mouseExited(MouseEvent e) { //При выходе указателя мыши из клетки вернуть прежние рамки
            Panel.this.setBorder(panelBorder);

        }
    }


}

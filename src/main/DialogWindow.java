package main;

import javax.swing.*;
import java.awt.*;

public class DialogWindow extends JPanel {
    private DialogWindow innerWindow; // поле для хранения внутреннего окна
    private JRadioButton radioButton;
    private ButtonGroup buttonGroup;
    private  int posX;
    private  int posY;
    private  int width;
    private  int height;

    public DialogWindow(){
        /*
        Конструктор для внешнего окна
         */
        this.posX = 700;
        this.posY = 0;
        this.width = 385;
        this.height = 750;
        this.setBounds(this.posX, this.posY, this.width, this.height); // расположение диалогового окна на главном фрейме
        innerWindow = new DialogWindow(0, 0, this.width,this.height-100); // инициализация внутреннего окна через конструктор с параметрами
        this.setLayout(new BorderLayout()); // Добавление во внешнее окно менеджера расположения компонентов
        this.add(innerWindow, BorderLayout.SOUTH); // Добавить компонент в окно, указать расположение через менеджера

    }
    public DialogWindow(int x, int y, int width, int height){
        /*
        Конструктор для внутреннего окна
         */
        this.posX = x;
        this.posY = y;
        this.width = width;
        this.height = height;

        this.setPreferredSize(new Dimension(this.width, this.height));
        this.radioButton = new JRadioButton("x1");
        this.add(radioButton);
        this.add(new JRadioButton("x2"));
        ///this.setBounds(this.posX, this.posY, this.width, this.height);
    }

    @Override
    public void paintComponent(Graphics g){
        g.setColor(Color.BLACK);// Задать цвет границ окна
        ((Graphics2D) g).setStroke(new BasicStroke(4)); // Задать толщину границ
        g.drawRect(0, 0, this.width - 2, this.height - 2);//Отрисовка границ

    }
}

package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Stream;

public class DialogWindow extends JPanel {
    private DialogWindow innerWindow; // поле для хранения внутреннего окна
    private ArrayList<JRadioButton> jRadioButtons;
    private static ArrayList<JLabel> imageForButtons;

    private  int posX;
    private  int posY;
    private  int width;
    private  int height;

    public DialogWindow(ArrayList<JLabel> icons){
        /*
        Конструктор для внешнего окна
         */
        imageForButtons = icons; // Готовые иконки кораблей, полученные из MainFrame
        this.posX = 700;
        this.posY = 0;
        this.width = 385;
        this.height = 750;
        this.setBounds(this.posX, this.posY, this.width, this.height); // расположение диалогового окна на главном фрейме
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK,4)); // Выделить границы внешнего окна
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
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK,2)); // Выделить границы внутреннего окна
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.setLayout(null); // Установить во внутреннем окне абсолютное позционирование
        this.initialButtons();
    }

    private void initialButtons(){
        this.jRadioButtons = new ArrayList<>();
        int posX = 50;
        int posY = 60;
        for(int x = 1; x!= 5;x++){
            this.jRadioButtons.add(new JRadioButton(""+x));
            this.jRadioButtons.get(x-1).setBounds(posX,posY,50,25);
            posY += 150;
        }
        posY = 60;
        for(int x = 0; x != 4;x++){
            this.add(jRadioButtons.get(x));
            imageForButtons.get(x).setBounds(posX+50,posY,150,50);
            this.add(imageForButtons.get(x));
            posY+=150;

        }

    }

}

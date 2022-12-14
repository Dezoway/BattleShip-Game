package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class DialogWindow extends JPanel {
    private DialogWindow innerWindow; // поле для хранения внутреннего окна
    private ArrayList<JRadioButton> jRadioButtons;
    private ButtonGroup buttonGroupShips = new ButtonGroup();
    private ButtonGroup buttonGroupOrientation = new ButtonGroup();
    private static  ArrayList<JLabel> ships;
    private JButton startGame;
    public static JRadioButton instanceShipButton;
    private JButton clearArea;
    private static JRadioButton orientationX;
    private static JRadioButton orientationY;
    public static int selectedButton;

    public DialogWindow(ArrayList<JLabel> shipsLabels){
        /*
        Конструктор для внешнего окна
         */
        ships = shipsLabels; // Готовые иконки кораблей, полученные из MainFrame
        this.setBounds(700,0,385,750); // расположение диалогового окна на главном фрейме
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK,4)); // Выделить границы внешнего окна
        innerWindow = new DialogWindow(0, 0, this.getWidth(),this.getHeight()-100); // инициализация внутреннего окна через конструктор с параметрами
        this.setLayout(new BorderLayout()); // Добавление во внешнее окно менеджера расположения компонентов
        this.add(innerWindow, BorderLayout.SOUTH); // Добавить компонент в окно, указать расположение через менеджера
        JLabel label = new JLabel(Messages.PrepareShips.toString());
        label.setFont(new Font("TimesRoman", Font.BOLD,26));
        label.setBorder(BorderFactory.createLineBorder(Color.BLUE,3));
        this.add(label, BorderLayout.CENTER);

    }
    public ArrayList<JRadioButton> getJRadioButtons(){
        return this.jRadioButtons;
    }
    public JButton getStartGame(){
        return this.startGame;
    }
    public DialogWindow(int x, int y, int width, int height){
        /*
        Конструктор для внутреннего окна
         */
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK,2)); // Выделить границы внутреннего окна
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(null); // Установить во внутреннем окне абсолютное позционирование
        this.initialButtons();
    }
    public static int getOrientation(){
        return orientationX.isSelected()? 0:1;
    }

    private void initialButtons(){
        orientationX = new JRadioButton("Горизонтальная");
        orientationY = new JRadioButton("Вертикальная");
        this.startGame = new JButton("Начать игру");
        this.clearArea = new JButton("Отменить расстановку");
        orientationX.setBounds(50,500,140,25);
        orientationY.setBounds(200,500,140,25);
        buttonGroupOrientation.add(orientationX);
        buttonGroupOrientation.add(orientationY);
        this.add(orientationX);// По умолчанию горизнотальная ориентация
        orientationX.setSelected(true);
        this.add(orientationY);
        this.jRadioButtons = new ArrayList<>();
        int posX = 50;
        int posY = 60;
        for(int x = 1; x!= 5;x++){
            this.jRadioButtons.add(new JRadioButton("Осталось "+(5-x)));
            this.jRadioButtons.get(x-1).setBounds(posX,posY,100,25);
            posY += 100;
        }
        posY = 60;
        for(int x = 0; x != 4;x++){
            buttonGroupShips.add(jRadioButtons.get(x));
            jRadioButtons.get(x).addActionListener(new ButtonListener());
            this.add(jRadioButtons.get(x));
            ships.get(x).setBounds(posX+80,posY,150,50);
            this.add(ships.get(x));
            posY+=100;

        }
        startGame.setBounds(50,580,120,50);
        clearArea.setBounds(190,580,160,50);
        clearArea.addActionListener(new ButtonListener());
        startGame.addActionListener(new ButtonListener());
        this.add(clearArea);
        this.add(startGame);
        startGame.setEnabled(false);

    }
    private class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().getClass().equals(JRadioButton.class)) {
                DialogWindow.selectedButton = jRadioButtons.indexOf((JRadioButton) e.getSource()) + 1;
                instanceShipButton = jRadioButtons.get(selectedButton - 1);
            }
            if(e.getSource().getClass().equals(JButton.class)){
                if (((JButton) e.getSource()).getText().equals("Отменить расстановку")){
                    GameArea.clearArea(); // Очистка игрового поля
                    DialogWindow.this.removeAll(); // Удалить все компоненты внутреннего окна
                    DialogWindow.this.initialButtons(); // Выставить все компоненты заново
                    DialogWindow.this.repaint(); // Перерисовать окно
                    DialogWindow.selectedButton = 0;
                } else if (((JButton) e.getSource()).getText().equals("Начать игру")) {
                    ((MainFrame)DialogWindow.this.getParent().getParent().getParent().getParent()).startBattle();

                }
            }


        }

    }

}

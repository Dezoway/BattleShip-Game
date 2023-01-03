package main;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MainFrame extends JFrame {
    private GameArea area;
    private GameArea area2;
    public int status = 1;
    public static ImageLoader imageLoader;
    public static SoundLoader soundLoader;
    private DialogWindow dialogWindow;

    public MainFrame(){
        super("BattleShip");
        imageLoader = new ImageLoader();
        soundLoader = new SoundLoader();
        area = new GameArea();
        setResizable(false);
        this.dialogWindow = new DialogWindow(area.getPlayer().getShips().stream().map(x->new JLabel(new ImageIcon(imageLoader.
                setSizeImage(x.getShipImage(), 210, 250)))).
                collect(Collectors.toCollection(ArrayList::new)));
        prepareFrame(); // Метод для создания окон
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void prepareFrame(){ // Расстановка компонентов во фрейме
        this.setSize(1100,800);
        this.setVisible(true);
        JLayeredPane jLayeredPane = this.getLayeredPane();
        jLayeredPane.add(dialogWindow);
        jLayeredPane.add(this.area,1);
        this.addMouseMotionListener(new UpdateState());
    }
    public void startBattle(){
        this.setSize(this.getWidth()+250,this.getHeight());
        this.getLayeredPane().remove(0);//Удаление диалогового окна
        this.removeMouseMotionListener(this.getMouseMotionListeners()[0]); //Удаление прослушивателя диалогового окна
        area2 = new GameArea(TypePlayer.COMPUTERPLAYER);    // Создание дополнительной игровой области
        for(Panel panel: GameArea.playerPanels){
            panel.removeMouseListener(panel.getMouseListeners()[0]); // Удаление прослушивателей у каждой клетки
        }
        this.getLayeredPane().add(area2,1);
        ListenerWinner listenerWinner = new ListenerWinner();
        listenerWinner.start();

    }
    public void endGame(TypePlayer typePlayer){ //Метод для объявления победителя во всплывающем окне
        String[] values = {"Да", "Нет"};
        int choice;
        if(typePlayer.equals(TypePlayer.PLAYER)){
            choice = JOptionPane.showOptionDialog(this, "Вы победили условного противника, начать новую игру?", "Вы выиграли!",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, values, values[0]);
        }
        else{
            choice = JOptionPane.showOptionDialog(this, "Вы проиграли условному противнику... Начать новую игру?", "Вы проиграли!",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, values, values[0]);
        }
        if (choice == 0){
            this.getContentPane().removeAll(); // Удаление всех компонентов
            this.dispose(); // Закрытие текущего фрейма
            area.removeAll(); //Очистка дочерних компонентов
            area2.removeAll();
            dialogWindow.removeAll();
            GameArea.locationLabel.removeAll();
            GameArea.tableCells.removeAll();
            this.area=null;
            this.dialogWindow=null;
            this.area2=null;
            GameArea.playerPanels =new ArrayList<>();//Создать список клеток заного
            GameArea.locationLabel=null;
            GameArea.tableCells=null;
            Player.shotCount = 20;
            ComputerPlayer.shotCount = 20;
            new MainFrame();//Новый фрейм
            System.out.println(area+" "+area2+" "+dialogWindow+" "+imageLoader);
            System.out.println(Thread.activeCount());
        }

    }
    class ListenerWinner extends Thread{    //Поток для отслеживания победителя
        @Override
        public void run() {
            while(true) {
                if (Player.shotCount == 0) {
                    endGame(TypePlayer.PLAYER);
                    break;
                } else if (ComputerPlayer.shotCount == 0) {
                    endGame(TypePlayer.COMPUTERPLAYER);
                    break;
                }
            }
        }
    }
    private class UpdateState extends MouseMotionAdapter { // Отслеживание изменений из главного фрейма

        @Override
        public void mouseMoved(MouseEvent e) {
            DialogWindow innerWindow = (DialogWindow)((DialogWindow) MainFrame.this.getLayeredPane().getComponent(0)).getComponent(0); // Получить объект внутреннего окна
            if(innerWindow.getJRadioButtons().stream().allMatch(x->x.getText().split(" ")[1].equals("0"))){
                innerWindow.getStartGame().setEnabled(true); // По окончанию расстановки кораблей включить кнопку
            }

        }
    }

}

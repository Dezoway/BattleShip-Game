import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Test extends JFrame {
    public Test(){
        this.setSize(400,400);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String... args) throws IOException {
        Test test = new Test();
        File file = new File(".");
        for(String txt:file.list()) System.out.println(txt);
        Image image = Toolkit.getDefaultToolkit().createImage("src/resources/explosion.gif");
        ImageIcon icon = new ImageIcon(image);
        JLabel label = new JLabel();
        label.setIcon(icon);
        test.add(label);
    }
}

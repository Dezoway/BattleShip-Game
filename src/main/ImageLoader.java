package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;


public class ImageLoader implements Loader{
    @Override
    public Object getResource(String fileName) {
        try{
            BufferedImage img= ImageIO.read(new FileInputStream("src/resources/"+fileName));

            return img;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public   Image setSizeImage (Image img, int width, int height) {
        img = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        return img;
    }

    public static BufferedImage resize(BufferedImage img, JLabel label, int orientation){
        Image tmp;
        if (orientation == 0) {
            tmp = img.getScaledInstance(label.getWidth()+100, label.getHeight()+100, Image.SCALE_SMOOTH);
            img = new BufferedImage(label.getWidth()+100, label.getHeight()+100, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = img.createGraphics();
            g2d.drawImage(tmp, 0, 0, null);
            g2d.dispose();
        }
        else{
            tmp = img.getScaledInstance(label.getWidth()+150, label.getHeight()+60, Image.SCALE_SMOOTH);
            img = new BufferedImage(label.getWidth()+150, label.getHeight()+25, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = img.createGraphics();
            g2d.rotate(Math.toRadians(90),img.getWidth()/2, img.getHeight()/2);
            g2d.drawImage(tmp, 0, 0, null);
            g2d.dispose();
        }
        return img;
    }


}

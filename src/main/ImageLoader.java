package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class ImageLoader implements Loader{
    @Override
    public Object getResource(String fileName) {
        try{
            Image img = ImageIO.read(new File("src/resources/"+fileName));
            return img;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public  Image setSizeImage (Image img, int width, int height) {
        img = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        return img;
    }


}

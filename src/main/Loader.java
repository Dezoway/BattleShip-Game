package main;

import java.awt.*;

public interface Loader {

    Object getResource(String fileName);



    default public Image setSizeImage(Image img , int height, int width){
        return null;
    }

}

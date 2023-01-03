package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class SoundLoader implements Loader{
    @Override
    public Object getResource(String fileName){
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("src/resources/" + fileName));
            return inputStream;
        } catch (IOException | UnsupportedAudioFileException exception){}
        return null;
    }
}

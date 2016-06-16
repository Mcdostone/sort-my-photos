package app.model;

import app.view.swing.LogsWindow;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * Created by mcdostone on 16/06/16.
 */
public class BufferedImageLoader extends Thread {

    private Media m;
    private int index;

    public BufferedImageLoader(int index, Media m) {
        this.m = m;
        this.index = index;
    }

    public void run() {
        try {
            MediaLoader.getInstance().add(index, ImageIO.read(new File(m.getPath())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        LogsWindow.createInstance().update("LOAD: " + m.getPath());
    }
}

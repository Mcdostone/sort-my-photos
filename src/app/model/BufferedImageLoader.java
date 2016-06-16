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

    public BufferedImageLoader(Media m) {
        this.m = m;
    }

    public void run() {
        try {
            MediaLoader.getInstance().add(this.m , ImageIO.read(new File(this.m.getPath())));
            LogsWindow.createInstance().update("#LOAD\t" + m.getPath() + "\n");
        } catch (IOException e) {  e.printStackTrace();  }

    }
}

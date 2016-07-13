package app.util;

import app.model.Media;
import app.model.MediaLoader;
import app.view.swing.LogsWindow;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * Thread which on load image in RAM.
 *
 * @author Mcdostone
 */
public class BufferedImageLoader extends Thread {

    private Media m;

    public BufferedImageLoader(Media m) {
        this.m = m;
    }

    public void run() {
        try {
            MediaLoader.getInstance().add(this.m , ImageIO.read(new File(this.m.getPath())));
            LogsWindow.getInstance().update("#LOAD\t" + m.getPath());
        } catch (IOException e) {  System.out.println(this.m + "\n" + e.getMessage());  }

    }
}

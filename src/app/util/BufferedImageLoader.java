package app.util;

import app.model.Media;
import app.model.MediaLoader;
import javafx.scene.image.Image;

import java.io.File;

/**
 * Thread which on load image in RAM.
 *
 * @author Mcdostone
 */
public class BufferedImageLoader extends Thread {

    private Media m;
    private MediaLoader loader;

    public BufferedImageLoader(MediaLoader ml, Media m) {
        this.loader = ml;
        this.m = m;
    }

    public void run() {
        this.loader.add(this.m , new Image(new File(m.getPath()).toURI().toString(), 1600, 900, true, false));
    }
}
package app.model;

import app.util.ArrayCircularList;
import app.view.swing.LogsWindow;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * The MediaLoader object enables to load images in advance in order to reduce lag during loading of file.
 *
 * @author Mcdostone
 */
public class MediaLoader {

    //private ArrayCircularList<BufferedImage> images;
    private HashMap<Media, BufferedImage> images;
    private static MediaLoader loader;
    private int size;

    private MediaLoader() {
        this.images = new HashMap<>();
        this.size = 5;
    }


    public static MediaLoader getInstance() {
        if(MediaLoader.loader == null)
            MediaLoader.loader = new MediaLoader();

        return MediaLoader.loader;
    }

    public void add(Media m, BufferedImage image) {
        this.images.put(m, image);
    }

    public void add(Media m) {
        try {
            this.images.put(m, ImageIO.read(new File(m.getPath())));
            LogsWindow.createInstance().update("LOAD: " + m.getPath());
        } catch (IOException e) {  e.printStackTrace();  }
    }

    public BufferedImage getImage(Media m) {
        return this.images.get(m);
    }

    public int size() {
        return this.size;
    }

    public void nextMedia(Media toRemove, Media toAdd) {
        this.images.remove(toRemove);
        new BufferedImageLoader(toAdd).start();
    }

    public void previousMedia(Media toRemove, Media toAdd) {
        this.images.remove(toRemove);
        new BufferedImageLoader(toAdd).start();
    }

    public String toString() {
        return this.images.toString();
    }
}

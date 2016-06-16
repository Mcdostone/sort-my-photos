package app.model;

import app.util.ArrayCircularList;

import java.awt.image.BufferedImage;

/**
 * The MediaLoader object enables to load images in advance in order to reduce lag during loading of file.
 *
 * @author Mcdostone
 */
public class MediaLoader extends Thread {

    private ArrayCircularList<BufferedImage> images;
    private static MediaLoader loader;

    private MediaLoader() {
        super("MediaLoader");
        this.images = new ArrayCircularList<>();
    }


    public static MediaLoader getInstance() {
        if(MediaLoader.loader == null)
            MediaLoader.loader = new MediaLoader();

        return MediaLoader.loader;
    }

    public void add(BufferedImage i) {
        this.images.add(i);
    }

    public BufferedImage getImage() {
        return this.images.next();
    }

    public int size() {
        return this.images.size();
    }


    public void run() {

    }

    public String toString() {
        return this.images.toString();
    }

}

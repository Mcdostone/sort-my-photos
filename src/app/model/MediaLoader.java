package app.model;

import app.util.BufferedImageLoader;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * The MediaLoader object enables to load images in advance in order to reduce freezer lag during loading of file.
 * By default, MediaLoader has a capacity of 5 medias (any kind).
 *
 *
 * @author Mcdostone
 */
public class MediaLoader {

    private HashMap<Media, BufferedImage> images;
    private static MediaLoader loader;
    private int capacity;

    private MediaLoader() {
        this.images = new HashMap<>();
        this.capacity = 5;
    }

    public static MediaLoader getInstance() {
        if(MediaLoader.loader == null)
            MediaLoader.loader = new MediaLoader();

        return MediaLoader.loader;
    }

    public void add(Media m, BufferedImage image) {
        if(this.images.size() < this.capacity)
            this.images.put(m, image);
    }

    public void add(Media m) {
        if(this.images.size() < this.capacity)
            new BufferedImageLoader(m).run();
    }

    public BufferedImage getImage(Media m) {
        return this.images.get(m);
    }

    public int capacity() {  return this.capacity;  }

    public void nextMedia(Media toRemove, Media toAdd) {
        this.images.remove(toRemove);
        new BufferedImageLoader(toAdd).start();
    }

    public void previousMedia(Media toRemove, Media toAdd) {
        this.images.remove(toRemove);
        new BufferedImageLoader(toAdd).start();
    }

    public String toString() {
        String desc = "{\n";
        for(Media m : this.images.keySet()) {
            desc += "\t" + m.toString() + " => " + this.images.get(m) + "\n";
        }
        desc += "}";
        return desc;
    }
}

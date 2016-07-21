package app.model;

import app.util.BufferedImageLoader;

import java.util.HashMap;

/**
 * The MediaLoader object enables to load images in advance in order to reduce freezer lag during loading of file.
 * By default, MediaLoader has a capacity of 5 medias (any kind).
 *
 * @author Mcdostone
 */
public class MediaLoader {

    private HashMap<Media, javafx.scene.image.Image> images;
    private int capacity;

    public MediaLoader() {
        this.images = new HashMap<>();
        this.capacity = 5;
    }

    public void add(Media m, javafx.scene.image.Image image) {
        if(this.images.size() < this.capacity)
            this.images.put(m, image);
    }

    public void add(Media m) {
        if(this.images.size() < this.capacity)
            new BufferedImageLoader(this, m).run();
    }

    public javafx.scene.image.Image getImage(Media m) {
        return this.images.get(m);
    }

    public int capacity() {  return this.capacity;  }

    public void nextMedia(Media toRemove, Media toAdd) {
        this.images.remove(toRemove);
        new BufferedImageLoader(this, toAdd).start();
    }

    public void previousMedia(Media toRemove, Media toAdd) {
        this.images.remove(toRemove);
        new BufferedImageLoader(this, toAdd).start();
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

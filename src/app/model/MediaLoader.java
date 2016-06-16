package app.model;

import app.util.ArrayCircularList;
import app.view.swing.LogsWindow;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The MediaLoader object enables to load images in advance in order to reduce lag during loading of file.
 *
 * @author Mcdostone
 */
public class MediaLoader {

    private ArrayCircularList<BufferedImage> images;
    private static MediaLoader loader;

    private MediaLoader() {  this.images = new ArrayCircularList<>();  }


    public static MediaLoader getInstance() {
        if(MediaLoader.loader == null)
            MediaLoader.loader = new MediaLoader();

        return MediaLoader.loader;
    }

    public void add(Media m) {
        try {
            this.images.add(ImageIO.read(new File(m.getPath())));
            LogsWindow.createInstance().update("LOAD: " + m.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage() {
        return this.images.get(this.images.size() / 2);
    }

    public int size() {
        return this.images.size();
    }

    public void nextMedia(Media m) {
        this.images.shiftLeft();
        new BufferedImageLoader(this.images.size() - 1, m).run();
    }

    public void add(int index, BufferedImage im) {
        this.images.add(index, im);
        System.out.println(this.images);
    }

    public void previousMedia(Media m) {
        this.images.shiftRight();
        new BufferedImageLoader(0, m).run();
    }

    public String toString() {
        return this.images.toString();
    }

}

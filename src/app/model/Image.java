package app.model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * An image is a media, nothing else.
 *
 * @author Mcdostone
 */
public class Image extends Media {

    public Image(String path) {  super('I', path);  }

    @Override
    protected void setProperties() {
        try {
            BufferedImage readImage = ImageIO.read(new File(this.getPath()));
            this.getProperties().put("height", readImage.getHeight() + "px");
            this.getProperties().put("width", readImage.getWidth() + "px");
        } catch (Exception e) {}
    }
}

/**
 * An image is media.
 *
 * @author Mcdostone
 */
public class Image extends Media {

    public Image(String path) {  super(path);  }

    public String toString() {  return "[I] " + this.getPath();  }
}

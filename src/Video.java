/**
 * A video is a media.
 *
 * @author Mcdostone
 */
public class Video extends Media {

    public Video(String path) {  super(path); }

    public String toString() {  return "[V] " + this.getPath();  }
}

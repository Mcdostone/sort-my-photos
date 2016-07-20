package app.model;

/**
 * A video is a media.
 *
 * @author Mcdostone
 */
public class Video extends Media {

    public Video(String path) {  super('V', path); }

    @Override
    public void loadMediaProperties() {

    }
}

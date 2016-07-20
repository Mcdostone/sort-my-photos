package app.model;

/**
 * An Audio is a media.
 *
 * @author Mcdostone
 */
public class Audio extends Media {

    public Audio(String path) {
        super('A', path);
    }

    @Override
    public void loadMediaProperties() {

    }
}

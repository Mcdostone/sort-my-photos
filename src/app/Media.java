package app;

/**
 * A media is an abstract class, representing any kind of media (image, video, sound, animated image ...).
 * A app.Media is defined by its path on the hard drive (for the moment).
 *
 * @author Mcdostone
 */
public abstract class Media {

    private String path;
    private char type;

    /**
     * Constructor
     * @param type Type of media (Image, video, sound ...)
     * @param path Filepath to access to this media
     */
    public Media(char type, String path) {
        this.type = type;
        this.path = path;
    }

    public String getPath() {  return this.path;  }

    public char getType() {  return this.type;  }

    /**
     * @param o Object to compare
     * @return {@code true} only if the two medias have the same filepath.
     */
    public boolean equals(Object o) {
        if(!(o instanceof Media)) return false;
        return this.getPath().equals(((Media) o).getPath());
    }

    public String toString() {  return "[" + this.getType()  + "] " + this.getPath();  }
}

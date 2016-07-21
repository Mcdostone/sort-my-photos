package app.model;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.util.HashMap;

/**
 * A media is an abstract class, representing any kind of media (image, video, sound, animated image ...).
 * A Media is defined by its path on the hard drive and its type.
 *
 * @author Mcdostone
 */
public abstract class Media {

    private String path;
    private char type;
    private String mimetype;
    private MediaProperties properties;

    /**
     * Constructor
     * @param type Type of media (Image, video, sound ...)
     * @param path Filepath to access to this media
     */
    public Media(char type, String path) {
        this.type = type;
        this.path = path;
        this.mimetype = MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType(new File(path));
        this.properties = new MediaProperties();
        this.loadMediaProperties();
    }

    public String getPath() {  return this.path;  }

    public char getType() {  return this.type;  }

    public String getMimetype() {  return this.mimetype;  }

    public String toString() {  return "[" + this.getType()  + "] " + this.getPath();  }

    public MediaProperties getProperties() {  return this.properties;  }

    public abstract void loadMediaProperties();

    public void setPath(String newPath) { this.path = newPath;  }

    /**
     * @param o Object to compare
     * @return {@code true} only if the two medias have the same filepath.
     */
    public boolean equals(Object o) {
        if(!(o instanceof Media)) return false;
        return this.getPath().equals(((Media) o).getPath()) && this.getType() == ((Media) o).getType();
    }
}

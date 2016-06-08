package app;


import javax.activation.MimetypesFileTypeMap;

/**
 * MediaFactory enables to create medias, depending on their types.
 *
 * @author Mcdostone
 */
public class MediaFactory {

    private static final MimetypesFileTypeMap mimeAnalyzer = new MimetypesFileTypeMap();

    public static Media create(String filepath) {
        String mimetype = mimeAnalyzer.getContentType(filepath);

        switch(mimetype.split("/")[0]) {
            case "image":
                return new Image(filepath);
            case "video":
                return new Video(filepath);
            case "audio":
                return new Audio(filepath);
        }

        return null;
    }
}

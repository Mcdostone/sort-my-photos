package app.model;


import app.conf.Configuration;
import app.view.swing.LogsWindow;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * MediaFactory enables to create medias, depending on their types.
 *
 * @author Mcdostone
 */
public class MediaPlayerFactory {

    private static MimetypesFileTypeMap mimeAnalyzer;

    private static void initMmeAnalyzer() {
        MediaPlayerFactory.mimeAnalyzer = new MimetypesFileTypeMap();
        MediaPlayerFactory.mimeAnalyzer.addMimeTypes(Configuration.getInstance().SUPPORTED_MIME_TYPES);
    }

    public static MediaPlayer createMediaPlayer(List<File> paths) {
        MediaPlayerFactory.initMmeAnalyzer();
        MediaPlayer m = new MediaPlayer(paths.get(0).getAbsolutePath());
        String rootDir = null;
        for (File file : paths) {
            try {
                if (file.isDirectory()) {
                    Files.walk(Paths.get(file.getAbsolutePath())).forEach(filePath -> {
                        m.addMedia(MediaPlayerFactory.createMedia(filePath.toString()));
                    });
                } else if (Files.isRegularFile(Paths.get(file.getAbsolutePath())))
                    m.addMedia(MediaPlayerFactory.createMedia(file.toString()));
            } catch (Exception e) {
                LogsWindow.getInstance().update(e.getMessage());
            }
        }

        return m;
    }

    private static Media createMedia(String filepath) {
        String mimetype = mimeAnalyzer.getContentType(new File(filepath));

        switch(mimetype.split("/")[0]) {
            case "image":
                return new Image(filepath);
            case "video":
                return new Video(filepath);
            case "audio":
                return  new Audio(filepath);
            default:
                return null;
        }
    }

}

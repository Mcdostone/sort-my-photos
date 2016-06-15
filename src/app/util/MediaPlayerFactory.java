package app.util;


import app.model.*;
import app.view.LogsWindow;

import javax.activation.MimetypesFileTypeMap;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.io.File;

/**
 * MediaFactory enables to create medias, depending on their types.
 *
 * @author Mcdostone
 */
public class MediaPlayerFactory {

    private static final MimetypesFileTypeMap mimeAnalyzer = new MimetypesFileTypeMap();


    public static MediaPlayer createMediaPlayer(List<File> paths) {
        MediaPlayer m = new MediaPlayer();
        for (File file : paths) {
            try {
                if (file.isDirectory()) {
                    Files.walk(Paths.get(file.getAbsolutePath())).forEach(filePath -> {
                        if (Files.isRegularFile(filePath))
                            m.addMedia(MediaPlayerFactory.createMedia(filePath.toString()));
                    });
                } else if (Files.isRegularFile(Paths.get(file.getAbsolutePath())))
                    m.addMedia(MediaPlayerFactory.createMedia(file.toString()));
            } catch (Exception e) {
                LogsWindow.createInstance().update(e.getMessage());
            }
        }

        return m;
    }

    private static Media createMedia(String filepath) {
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

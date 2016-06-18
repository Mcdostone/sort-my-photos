package app.util;


import app.conf.Configuration;
import app.model.*;
import app.view.swing.LogsWindow;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
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
        MediaPlayerFactory.mimeAnalyzer.addMimeTypes(Configuration.SUPPORTED_MIME_TYPES);
    }

    public static MediaPlayer createMediaPlayer(List<File> paths) {
        MediaPlayerFactory.initMmeAnalyzer();
        MediaPlayer m = new MediaPlayer();
        String rootDir = null;

        for (File file : paths) {
            try {
                if (file.isDirectory())
                {
                    rootDir = file.getAbsolutePath();
                    Files.walk(Paths.get(file.getAbsolutePath())).forEach(filePath -> {
                        m.addMedia(MediaPlayerFactory.createMedia(filePath.toString()));
                    });
                } else if (Files.isRegularFile(Paths.get(file.getAbsolutePath())))
                    m.addMedia(MediaPlayerFactory.createMedia(file.toString()));
            } catch (Exception e) {
                LogsWindow.createInstance().update(e.getMessage());
            }

            SortingManager.getInstance(rootDir, m);
        }
        LogsWindow.createInstance().update("FILES FOUND : \n" + m.toString());

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

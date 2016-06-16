package app.util;


import app.conf.Configuration;
import app.model.*;
import app.view.swing.LogsWindow;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
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

        for (File file : paths) {
            try {
                if (file.isDirectory()) {
                    Files.walk(Paths.get(file.getAbsolutePath())).forEach(filePath -> {
                        m.addMedia(MediaPlayerFactory.createMedia(filePath.toString()));
                    });
                } else if (Files.isRegularFile(Paths.get(file.getAbsolutePath())))
                    m.addMedia(MediaPlayerFactory.createMedia(file.toString()));
            } catch (Exception e) {
                LogsWindow.createInstance().update(e.getMessage());
            }
        }

        LogsWindow.createInstance().update("FILES FOUND : \n" + m.toString());
        System.out.println(m);
        MediaPlayerFactory.initMediaLoader(m);

        return m;
    }


    private static void initMediaLoader(MediaPlayer m) {
        MediaLoader loader = MediaLoader.getInstance();
        int size = loader.size();
        size = size/2;
        for(int i = -size; i <= size; i++)
            loader.add(m.get(i));
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

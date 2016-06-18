package app.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Yann on 18/06/2016.
 */
public class SortingManager {

    public final static int ACCEPT = 0;
    public final static int REJECT = 1;
    private static SortingManager manager;

    private String rootDir;
    private MediaPlayer m;
    private Path acceptedDir;
    private Path rejectedDir;

    private SortingManager(String dirPath, MediaPlayer m) {
        this.rootDir = dirPath;
        this.m = m;
        this.initDirs();
    }

    private void initDirs() {
        this.acceptedDir = Paths.get(this.rootDir, "accepted");
        this.rejectedDir = Paths.get(this.rootDir, "rejected");
        try {
            Files.createDirectories(this.acceptedDir);
            Files.createDirectories(this.rejectedDir);
        } catch (IOException e) {  e.printStackTrace();  }
    }

    public void acceptMedia() {
        try {
            Files.move(Paths.get(this.m.current().getPath()), this.acceptedDir.resolve(Paths.get(this.m.current().getPath()).getFileName()));
            this.m.removeCurrent();
        } catch (IOException e) {  e.printStackTrace();  }
    }

    public void rejectMedia() {
        try {
            Files.move(Paths.get(this.m.current().getPath()), this.rejectedDir.resolve(Paths.get(this.m.current().getPath()).getFileName()));
        } catch (IOException e) {  e.printStackTrace();  }
    }


    public static SortingManager getInstance(String dest, MediaPlayer m) {
        if(SortingManager.manager == null || dest != null)
            SortingManager.manager = new SortingManager(dest, m);

        return SortingManager.manager;
    }
}

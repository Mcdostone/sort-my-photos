package app.model;

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
    private String workingDir;
    private MediaPlayer m;
    private Path acceptedDir;
    private Path rejectedDir;
    private boolean dirsCreated;

    public SortingManager(MediaPlayer m, String acceptedDir, String rejectedDir) {
        this.m = m;
        this.dirsCreated = false;
        this.workingDir = this.m.getWorkingDirectory();
        this.acceptedDir = Paths.get(this.workingDir, acceptedDir);
        this.rejectedDir = Paths.get(this.workingDir, rejectedDir);
    }

    public void acceptMedia() {
        if(!this.m.isEmpty()) {
            if(!dirsCreated)
                this.createDirectories();
            Path newPath = this.acceptedDir.resolve(Paths.get(this.m.current().getPath()).getFileName());
            try {
                Files.move(Paths.get(this.m.current().getPath()), newPath);
                this.m.removeCurrent();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void rejectMedia() {
        if(!this.m.isEmpty()) {
            if(!dirsCreated)
                this.createDirectories();
            Path newPath = this.rejectedDir.resolve(Paths.get(this.m.current().getPath()).getFileName());
            try {
                Files.move(Paths.get(this.m.current().getPath()), newPath);
                this.m.removeCurrent();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createDirectories() {
        try {
                Files.createDirectories(this.acceptedDir);
                Files.createDirectories(this.rejectedDir);
                this.dirsCreated = true;
        }
        catch (IOException e) {  e.printStackTrace();  }
    }
}

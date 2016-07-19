package app.model;

import app.util.DoublyLinkedCircularList;
import app.util.MyDoublyLinkedCircularList;
import app.util.Node;

import java.util.Observable;
import java.util.logging.Logger;

/**
 * MediaPlayer enables to play all media, one by one, thanks to a circular List
 *
 * @author Mcdostone
 */
public class MediaPlayer extends Observable {

    private DoublyLinkedCircularList<Media> playlist;
    private Media current;
    private String workingDir;

    public MediaPlayer(String workDir) {
        this.workingDir = workDir;
        this.playlist = new MyDoublyLinkedCircularList<>();
    }

    public void addMedia(Media m) {
        if(m != null) {
            this.playlist.add(m);
            this.setChanged();
            this.notifyObservers();
        }
    }

    public void removeCurrent() {
        this.current = this.playlist.removeCurrent();
        this.setChanged();
        this.notifyObservers();
    }

    public Media next() {
        this.current = (this.isEmpty()) ? null: this.playlist.next();
        this.setChanged();
        this.notifyObservers();
        return this.current;
    }

    public Media previous() {
        this.current = (this.isEmpty()) ? null: this.playlist.previous();
        this.setChanged();
        this.notifyObservers();
        return this.current;
    }

    public Media firstMedia() {
        this.current = this.playlist.firstValue();
        return this.current;
    }

    public Media current() {  return this.current;  }

    public Media get(int index) {  return this.playlist.get(index);  }

    public String toString() {
        return this.playlist.toString();
    }

    public boolean isEmpty() {  return this.playlist.isEmpty();  }

    public String getWorkingDirectory() {  return this.workingDir;  }

}

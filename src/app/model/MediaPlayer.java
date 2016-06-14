package app.model;

import app.util.DoublyLinkedCircularList;
import app.util.MyDoublyLinkedCircularList;

import java.util.Observable;

/**
 * MediaPlayer enables to play all media, one by one, thanks to a circular List
 *
 * @author Mcdostone
 */
public class MediaPlayer extends Observable {

    private DoublyLinkedCircularList<Media> playlist;

    public MediaPlayer() {
        this.playlist = new MyDoublyLinkedCircularList<>();
    }

    public void addMedia(Media m) {
        if(m != null) {
            this.playlist.add(m);
            this.setChanged();
            this.notifyObservers();
        }
    }

    public void removeCurrent() {  this.playlist.removeCurrent();
        this.setChanged();
        this.notifyObservers();
    }

    public Media next() {
        this.setChanged();
        this.notifyObservers();
        return this.playlist.next();
    }

    public Media previous() {
        this.setChanged();
        this.notifyObservers();
        return this.playlist.previous();
    }

}

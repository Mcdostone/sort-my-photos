package app.model;

import app.util.DoublyLinkedCircularList;
import app.util.MyDoublyLinkedCircularList;
import app.util.Node;

import java.util.Observable;

/**
 * MediaPlayer enables to play all media, one by one, thanks to a circular List
 *
 * @author Mcdostone
 */
public class MediaPlayer extends Observable {

    private DoublyLinkedCircularList<Media> playlist;
    private Media current;

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
        Media toLoad = this.playlist.NthMediaAfterCurrent((MediaLoader.getInstance().size()/2) + 1);
        Media toRemove = this.playlist.NthMediaBeforeCurrent(MediaLoader.getInstance().size()/2);
        MediaLoader.getInstance().nextMedia(toRemove, toLoad);
        this.current = this.playlist.next();
        return this.current;
    }


    public Media previous() {
        this.setChanged();
        this.notifyObservers();
        Media toLoad = this.playlist.NthMediaAfterCurrent((MediaLoader.getInstance().size()/2) + 1);
        Media toRemove = this.playlist.NthMediaAfterCurrent(MediaLoader.getInstance().size()/2);
        MediaLoader.getInstance().previousMedia(toRemove, toLoad);
        this.current = this.playlist.previous();
        return this.current;
    }

    public Media firstMedia() {
        this.current = this.playlist.firstValue();
        return this.current;
    }

    public Media get(int index) {  return this.playlist.get(index);  }

    public String toString() {
        return this.playlist.toString();
    }

}

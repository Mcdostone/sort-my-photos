package app.util;

import app.model.Media;

import java.util.Iterator;

/**
 * Circular List for watching medias infinite
 */
public class MyMediaList implements DoublyLinkedCircularList<Media> {

    private Node current;

    @Override
    public Media next() {
        return null;
    }

    @Override
    public Media previous() {
        return null;
    }

    @Override
    public void add(Media element) {

    }

    @Override
    public void remove(Media element) {

    }

    @Override
    public boolean contains(Media element) {
        return false;
    }

    @Override
    public Iterator<Media> iterator() {
        return null;
    }
}

package app.util;

/**
 * Special data structure for the media player
 *
 * @author Mcdostone
 */
public interface DoublyLinkedCircularList<E> extends Iterable<E> {

    E next();
    E previous();
    boolean isEmpty();
    void add(E element);
    void remove(E element);
    void removeCurrent();
    boolean contains(E element);
}

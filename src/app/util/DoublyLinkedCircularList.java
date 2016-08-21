package app.util;

/**
 * Interface for the special data structure for the media player.
 *
 * @author Mcdostone
 */
public interface DoublyLinkedCircularList<E> extends Iterable<E> {

    /** @return The next element in the collection */
    E next();

    /** @return The previous element in the collection */
    E previous();

    /** @return True if there is no values in the collection */
    boolean isEmpty();

    /** Add an element in the collection */
    void add(E element);

    void add(int index, E element);

    /** Remove a given element in the collection */
    void remove(E element);

    /** Remove the current element in the collection */
    E removeCurrent();

    boolean contains(E element);

    E get(int index);

    int size();

    E firstValue();

}

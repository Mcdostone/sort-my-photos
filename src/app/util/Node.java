package app.util;

/**
 * A node in a circular listr
 *
 * @author Mcdostone
 */
public class Node<E> {

    private Node previous;
    private Node next;
    private E value;

    public Node (E value) {  this.value = value;  }

    public Node previous() {  return this.previous;  }

    public Node next() {  return this.next;  }

}

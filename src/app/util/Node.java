package app.util;

/**
 * A node in a circular listr
 *
 * @author Mcdostone
 */
public class Node<E> {

    private Node<E> previous;
    private Node<E> next;
    private E value;

    public Node (E value) {  this.value = value;  }

    public Node<E> previous() {  return this.previous;  }
    public Node<E> next() {  return this.next;  }
    public E value() {  return this.value;  }

    public void setPrevious(Node<E> n) {  this.previous = n;  }
    public void setNext(Node<E> n) {  this.next  = n;  }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return value != null ? value.equals(node.value) : node.value == null;
    }

    public String toString() {
        return this.value.toString();
    }

}

package app.util;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Circular List for watching medias infinite
 */
public class MyDoublyLinkedCircularList<E> implements DoublyLinkedCircularList<E> {

    private Node<E> first;
    private Node<E> last;
    private Node<E> current;

    @Override
    public E next() {
        if(!isEmpty()) {
            if (current == null) current = first;
            else current = current.next();
            return current.value();
        }
        else return null;
    }

    @Override
    public E previous() {
        if(!isEmpty()) {
            if(current==null)   current=first;
            else   current=current.previous();
            return current.value();
        }
        else  return null;
    }

    @Override
    public boolean isEmpty() {  return this.first == null;  }

    @Override
    public void add(E element) {
        if(this.first == null) {
            this.first = new Node<>(element);
            this.first.setPrevious(this.first);
            this.first.setNext(this.first);
            this.last = this.first;
        }
        else {
            Node<E> tmp = new Node<>(element);
            tmp.setNext(this.first);
            this.last.setNext(tmp);
            tmp.setPrevious(this.last);
            this.last = tmp;
            this.first.setPrevious(this.last);
        }
    }

    @Override
    public void remove(E element) {
    }

    @Override
    public void removeCurrent() {
        this.current.previous().setNext(this.current.next());
        this.current.next().setPrevious(this.current.previous());
        this.current  = this.current.previous();
    }

    @Override
    public boolean contains(E element) {
        Node tmp = this.first;
        if(tmp.value().equals(element))  return true;
        else {
            tmp = tmp.next();
            while(!tmp.equals(this.first)) {
                if(tmp.value().equals(element))
                    return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        ArrayList<E> list = new ArrayList<>();
        Node<E> tmp = this.first;
        list.add(tmp.value());
        tmp = tmp.next();
        while(!tmp.equals(this.first)) {
            list.add(tmp.value());
            tmp = tmp.next();
        }
        return list.iterator();
    }

}

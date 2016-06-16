package app.util;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Circular List for watching medias like a playlist (loop)
 * For this, I decided to implement a doubly linked circular list (get the next, the previous infinite...)
 *
 * @author Mcdostone
 */
public class MyDoublyLinkedCircularList<E> implements DoublyLinkedCircularList<E> {

    /** First Node of the circular list */
    private Node<E> first;
    /** Last Node of the circular list */
    private Node<E> last;
    /** Current Node of the circular list */
    private Node<E> current;

    @Override
    public E next() {
        if(!isEmpty()) {
            this.current = this.current.next();
            return this.current.value();
        }
        else return null;
    }

    @Override
    public E previous() {
        if(!isEmpty()) {
            this.current = this.current.previous();
            return this.current.value();
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
            this.current = this.first;
        }
        else {
            // Updates next and previous nodes ...
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
        if(contains(element)) {
            Node<E> tmp = this.first;
            if(tmp.value().equals(element)) {
                this.current = tmp;
                this.removeCurrent();
            }
            else {
                tmp = tmp.next();
                while(!tmp.equals(this.first)) {
                    if(tmp.value().equals(element)) {
                        this.current = tmp;
                        this.removeCurrent();
                        break;
                    }
                    else
                        tmp = tmp.next();
                }
            }
        }
    }

    @Override
    public void removeCurrent() {
        if(this.last.equals(this.first)) {
            this.last = null;
            this.first = null;
            this.current = null;
        }
        else {
            if(this.current.equals(this.last))
                this.last = this.current.previous();

            this.current.previous().setNext(this.current.next());
            this.current.next().setPrevious(this.current.previous());
            this.current  = this.current.previous();
        }
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
                else
                    tmp = tmp.next();
            }
        }
        return false;
    }

    @Override
    public E get(int index) {
        Node<E> tmp = this.first;
        for(int i = 0; i < Math.abs(index); i++) {
            tmp = (index < 0) ? tmp.previous() : tmp.next();
        }

        return tmp.value();
    }

    @Override
    public int size() {
        int size = (this.first != null) ? 1 : 0;
        Node tmp = this.first;
        while(tmp != this.last) {
            size++;
            tmp = tmp.next();
        }
        return size;
    }

    @Override
    public E firstValue() {
        return this.first.value();
    }

    @Override
    public Iterator<E> iterator() {
        ArrayList<E> list = new ArrayList<>();
        Node<E> tmp = this.first;
        if(tmp != null) {
            list.add(tmp.value());
            tmp = tmp.next();
            while(!tmp.equals(this.first)) {
                list.add(tmp.value());
                tmp = tmp.next();
            }
        }
        return list.iterator();
    }

    public String toString() {
        String s = "";
        int count = 0;
        for (E elem : this) {
            s += "[" + count + "] " + elem + "\n";
            count++;
        }
        return s;
    }

}


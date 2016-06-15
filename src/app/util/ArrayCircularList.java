package app.util;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * CircularList with an array.
 *
 * @author Mcdostone
 */
public class ArrayCircularList<E> implements DoublyLinkedCircularList<E> {

    private static final int SIZE = 5;
    private E[] list;
    private int index;

    public ArrayCircularList() {
        this.list = (E[]) new Object[ArrayCircularList.SIZE];
        this.index = -1;

    }

    @Override
    public E next() {
        if(this.index == -1)
            this.index = this.list.length / 2;
        else
            this.nextIndex();
        return this.list[this.index];
    }

    private void nextIndex() {
        this.index = (this.index == this.list.length - 1) ? 0 : this.index + 1;
    }

    private void previousIndex() {
        this.index = (this.index == 0) ? this.list.length - 1 : this.index  - 1;
    }

    @Override
    public E previous() {
        if(this.index == -1)
            this.index = this.list.length / 2;
        else
            this.previousIndex();
        return this.list[this.index];
    }

    @Override
    public boolean isEmpty() {
        return this.index == -1;
    }

    @Override
    public void add(E element) {
        this.nextIndex();
        this.list[this.index] = element;
    }

    @Override
    public void remove(E element) {
        if(this.contains(element))
            for(int i = 0; i < this.list.length; i++) {
                if(this.list[i] != null && this.list[i].equals(element))
                    this.list[i] = null;
            }
    }

    @Override
    public void removeCurrent() {
        this.list[this.index] = null;
    }

    @Override
    public boolean contains(E element) {
        for(int i = 0; i < this.list.length; i++) {
            if(this.list[i].equals(element))
                return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        ArrayList<E> l = new ArrayList<E>();
        for(E elem: this.list) {
            l.add(elem);
        }

        return l.iterator();
    }
}

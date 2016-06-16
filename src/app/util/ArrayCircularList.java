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
        this.index = this.nextIndex();
        return this.list[this.index];
    }

    private int nextIndex() {
        if(this.index == -1)
            return 0;
        else
            return (this.index == this.list.length - 1) ? 0 : this.index + 1;
    }

    private int previousIndex() {
        if(this.index == -1)
            return this.list.length / 2;
        else
            return (this.index == 0) ? this.list.length - 1 : this.index  - 1;
    }

    @Override
    public E previous() {
        this.index = this.previousIndex();
        return this.list[this.index];
    }

    @Override
    public boolean isEmpty() {
        return this.index == -1;
    }

    @Override
    public void add(E element) {
        this.index = this.nextIndex();
        this.list[this.index] = element;
        System.out.println("added at " + this.index);
    }

    @Override
    public void add(int index, E element) {
        this.list[index] = element;
    }

    @Override
    public void remove(E element) {
        if(this.contains(element))
            for(int i = 0; i < this.list.length; i++) {
                if(this.list[i] != null && this.list[i].equals(element))
                    this.list[i] = null;
            }
    }

    public void shiftLeft() {
        for(int i = 0; i < this.list.length -1; i++) {
            this.list[i] = this.list[i + 1];
        }
        this.list[this.list.length - 1] = null;
    }

    public void shiftRight() {
        for(int i = this.list.length - 1; i > 1; i--) {
            this.list[i] = this.list[i - 1];
        }
        this.list[0] = null;
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
    public E get(int index) {
        return (index > 0 && index < this.list.length) ? this.list[index] : null;
    }

    @Override
    public int size() {
        return this.list.length;
    }

    @Override
    public E firstValue() {
        return this.list[0];
    }

    @Override
    public E NthMediaAfterCurrent(int n) {
        int tmp = this.index;
        for(int i = 0; i < n; i++) {
            tmp = this.nextIndex();
        }
        return this.list[tmp];

    }

    @Override
    public E NthMediaBeforeCurrent(int n) {
        int tmp = this.index;
        for(int i = 0; i < n; i++) {
            tmp = this.previousIndex();
        }
        return this.list[tmp];
    }

    @Override
    public Iterator<E> iterator() {
        ArrayList<E> l = new ArrayList<E>();
        for(E elem: this.list) {
            l.add(elem);
        }

        return l.iterator();
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

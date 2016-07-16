package app.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
    /** Size of the data structure */
    private int size = 0;

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
    public boolean isEmpty() {  return size == 0;  }
    
    private void addBetweenNodes(Node<E> before, Node<E> between, Node<E> after){
    	between.setNext(after);
        between.setPrevious(before);
        before.setNext(between);
        after.setPrevious(between);
    }

    @Override
    public void add(E element) {
        if(this.first == null) {
            Node<E> tmp = new Node<>(element);
            tmp.setPrevious(tmp);
            tmp.setNext(tmp);
            this.first = tmp;
            this.last = tmp;
            this.current = tmp;
        }
        else {
            Node<E> tmp = new Node<>(element);
            addBetweenNodes(this.last, tmp, this.first);
            this.last = tmp;
        }
        size++;
    }

    @Override
    public void add(int index, E element) throws IndexOutOfBoundsException {
    	if(index > size)
    		throw new IndexOutOfBoundsException();
        Node<E> tmp = new Node<>(element);
        if(index == 0)
            add(element);
        else {
            Node<E> curr = this.first;
            for(int i = 0; i < index - 1; i++)
                curr = curr.next();
            addBetweenNodes(curr, tmp, curr.next());
        }
        size++;
    }

    @Override
    public void remove(E element) throws NoSuchElementException {
        if(!contains(element))
        	throw new NoSuchElementException();
        Node<E> tmp = this.first;
        while(!tmp.equals(this.last) && !tmp.value().equals(element))
            tmp = tmp.next();
        if(tmp.value().equals(element))
            this.removeNode(tmp);
    }
    
    private void removeNode(Node<E> node){
        if(node.equals(this.first) && node.equals(this.last)) {
            this.last = null;
            this.first = null;
            this.current = null;
        }
        else{        
	        if(node.equals(this.last))
	            this.last = node.previous();
	        if(node.equals(this.first))
	            this.first = node.next();
	
	        node.previous().setNext(node.next());
	        node.next().setPrevious(node.previous());
	        node = node.next();
        }
        
        size--;
    }

    @Override
    public E removeCurrent() {
        Node<E> removedNode = this.current;
        E returnedValue = removedNode.value();
        this.current = this.current.next();
        this.removeNode(removedNode);
        return returnedValue;
    }

    @Override
    public boolean contains(E element) {
        Node<E> tmp = this.first;
        while(!tmp.value().equals(element) && !tmp.equals(this.last))
            tmp = tmp.next();
        return tmp.value().equals(element);
    }
    
    @Override
    public E get(int index){
        Node<E> tmp = this.first;
        for(int i = 0; i < Math.abs(index); i++) {
            tmp = (index < 0) ? tmp.previous() : tmp.next();
        }

        return tmp.value();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public E firstValue() {
        return this.first.value();
    }

    @Override
    public E NthMediaAfterCurrent(int n) {
        if(!this.isEmpty()) {
            Node<E> tmp = this.current;
            for(int i = 0; i < n; i++) {
                tmp = tmp.next();
            }

            return tmp.value();
        }
        return null;
    }

    @Override
    public E NthMediaBeforeCurrent(int n) {
        if(!this.isEmpty()) {
            Node<E> tmp = this.current;
            for(int i = 0; i < n; i++) {
                tmp = tmp.previous();
            }

            return tmp.value();
        }
        return null;
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


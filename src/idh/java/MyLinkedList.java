package idh.java;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyLinkedList<T> implements List<T> {

    /**
     * Helper class for the list elements
     */
    private class ListElement {
	T payload;
	ListElement next;

	ListElement(T value) {
	    this.payload = value;
	}
    }

    /**
     * We only need to store the very first element of our list, because it will know
     * whether there is a next element.
     */
    ListElement first;

    @Override
    public int size() {
//	This should do the trick, right?
	return this.size();
//    int size = 0; 
//    if(first.equals(null)) {
//    	return -1;
//    }
//    if(first.next == null) {
//    	size = 1;
//    } else {
//    	
//    }
//    return size;
//    int last = this.lastIndexOf(first);
//    return last;
    }

    @Override
    public boolean contains(Object o) {
	
    boolean contained = false;
    for(Object ob : this) {
    	if(ob.equals(o)) {
    		contained = true;
    	} else {
    		contained = false;
    	}
    }
    return contained;
//	return this.contains(o); //this line should already be enough, but I suppose this is not the intended way
    }

    @Override
    public boolean remove(Object o) {
	// TODO: Implement
//    for(Object ob : this) {
//    	if(ob.equals(o)) {
//    		this.remove(o);
//    	}
//    }
	return this.remove(o);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
	// TODO Implement!
    return this.addAll(this);
    }

    @Override
    public T set(int index, T element) {
	// TODO: Implement
	return this.set(index, element);
    }

    @Override
    public void add(int index, T element) {
	// TODO: Implement
    this.add(index, element);
    }

    @Override
    public T remove(int index) {
	// TODO: Implement
	return this.remove(index);
    }

    @Override
    public boolean isEmpty() {
	return first == null;
    }

    @Override
    public Iterator<T> iterator() {
	return new Iterator<T>() {
	    ListElement next = first;

	    @Override
	    public boolean hasNext() {
		return next != null;
	    }

	    @Override
	    public T next() {
		T ret = next.payload;
		next = next.next;
		return ret;
	    }

	};
    }

    @Override
    public Object[] toArray() {
	return this.toArray(new Object[this.size()]);
    }

    @Override
    public <E> E[] toArray(E[] a) {
	if (a.length < size()) {
	    a = (E[]) new Object[size()];
	}
	int i = 0;
	for (T t : this) {
	    a[i++] = (E) t;
	}
	return a;
    }

    @Override
    public boolean add(T e) {
	ListElement newListElement = new ListElement(e);
	if (first == null)
	    first = newListElement;
	else
	    last().next = newListElement;
	return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
	for (Object o : c)
	    if (!contains(o))
		return false;
	return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
	for (T t : c)
	    this.add(t);
	return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
	boolean r = true;
	for (Object o : c)
	    r = r || this.remove(o);
	return r;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
	throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
	first = null;
    }

    @Override
    public T get(int index) {
	return getElement(index).payload;
    }

    @Override
    public int indexOf(Object o) {
	if (isEmpty())
	    return -1;
	ListElement current = first;
	int index = 0;
	while (current != null) {
	    if (current.payload.equals(o))
		return index;
	    index++;
	    current = current.next;
	}
	return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
	if (isEmpty())
	    return -1;
	ListElement current = first;
	int index = 0;
	int lastFoundIndex = -1;
	while (current != null) {
	    if (current.payload.equals(o))
		lastFoundIndex = index;
	    index++;
	    current = current.next;
	}
	return lastFoundIndex;
    }

    @Override
    public ListIterator<T> listIterator() {
	return new ListIterator<T>() {

	    ListElement previous = null;
	    ListElement next = first;
	    int index;

	    @Override
	    public boolean hasNext() {
		return next != null;
	    }

	    @Override
	    public T next() {
		previous = next;
		T ret = next.payload;
		next = next.next;
		index++;
		return ret;
	    }

	    @Override
	    public boolean hasPrevious() {
		return false;
	    }

	    @Override
	    public T previous() {
		throw new UnsupportedOperationException();
	    }

	    @Override
	    public int nextIndex() {
		return index + 1;
	    }

	    @Override
	    public int previousIndex() {
		return index - 1;
	    }

	    @Override
	    public void remove() {
		previous.next = next.next;
	    }

	    @Override
	    public void set(T e) {
		next.payload = e;
	    }

	    @Override
	    public void add(T e) {
		throw new UnsupportedOperationException();
	    }

	};
    }

    @Override
    public ListIterator<T> listIterator(int index) {
	throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
	throw new UnsupportedOperationException();
    }

    /**
     * Internal method that iterates over the list, returning the last element
     * (i.e., the one whose next field is null)
     * 
     * @return
     */
    private ListElement last() {
	if (first == null)
	    return null;
	ListElement current = first;

	while (current.next != null) {
	    current = current.next;
	}
	return current;
    }

    /**
     * Internal method to get the list element (not the value) of the list at the
     * specified index position.
     * 
     * @param index
     * @return
     */
    private ListElement getElement(int index) {
	if (isEmpty())
	    return null;
	ListElement current = first;
	while (current != null) {
	    if (index == 0)
		return current;
	    index--;
	    current = current.next;
	}
	return null;
    }

    public static void main(String[] args) {
	MyLinkedList<String> ll = new MyLinkedList<String>();
	ll.add("Hallo");
	ll.add("Welt");
	ll.add("Welt");
	System.out.println(ll.get(0));
	for (String s : ll) {
	    System.out.println(s);
	}

    }
}

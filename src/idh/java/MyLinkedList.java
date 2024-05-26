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
     * We only need to store the very first element of our list, because it will now
     * whether there is a next element.
     */
    ListElement first;

    @Override
    public int size() {
    	int size = 0;
        ListElement current = first;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    @Override
    public boolean contains(Object o) {
    	 ListElement current = first;
         while (current != null) {
             if (current.payload.equals(o)) {
                 return true;
             }
             current = current.next;
         }
         return false;
     }

    @Override
    public boolean remove(Object o) {
    	if (first == null) {
            return false;
        }
        if (first.payload.equals(o)) {
            first = first.next;
            return true;
        }
        ListElement current = first;
        while (current.next != null) {
            if (current.next.payload.equals(o)) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
	return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
    	if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        ListElement current = first;
        ListElement prev = null;
        for (int i = 0; i < index; i++) {
            prev = current;
            current = current.next;
        }
        for (T element : c) {
            ListElement newListElement = new ListElement(element);
            if (prev == null) {
                newListElement.next = first;
                first = newListElement;
            } else {
                newListElement.next = prev.next;
                prev.next = newListElement;
            }
            prev = newListElement;
        }
	return true;
    }

    @Override
    public T set(int index, T element) {
    	ListElement current = getElement(index);
        if (current == null) {
            throw new IndexOutOfBoundsException();
        }
        T oldPayload = current.payload;
        current.payload = element;
        return oldPayload;
    }

    @Override
    public void add(int index, T element) {
    	if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        ListElement newListElement = new ListElement(element);
        if (index == 0) {
            newListElement.next = first;
            first = newListElement;
        } else {
            ListElement prev = getElement(index - 1);
            newListElement.next = prev.next;
            prev.next = newListElement;
        }
    }

    @Override
    public T remove(int index) {
    	if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            T payload = first.payload;
            first = first.next;
            return payload;
        } else {
            ListElement prev = getElement(index - 1);
            T payload = prev.next.payload;
            prev.next = prev.next.next;
            return payload;
        }
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
    	Object[] array = new Object[size()];
        int i = 0;
        for (T t : this) {
            array[i++] = t;
        }
        return array;
    }

    @Override
    public <E> E[] toArray(E[] a) {
        if (a.length < size()) {
            a = (E[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size());
        }
        int i = 0;
        Object[] result = a;
        for (T t : this) {
            result[i++] = t;
        }
        if (a.length > size()) {
            a[size()] = null;
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
	boolean r = false;
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
    	ListElement element = getElement(index);
        if (element == null) {
            throw new IndexOutOfBoundsException();
        }
        return element.payload;
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
	ll.get(0);
	for (String s : ll) {
	    System.out.println(s);
	}

    }
}

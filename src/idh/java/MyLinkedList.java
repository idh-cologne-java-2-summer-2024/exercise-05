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
	int count = 0;
	ListElement current = first;
	while (current != null) {
		count++;
		current = current.next;
	}
	return count;
    }

    @Override
    public boolean contains(Object o) {
    	ListElement current = first;
    	while (current != null) {
    		if ((o == null && current.payload == null) || (o != null && o.equals(current.payload))){
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
    	if ((o == null && first.payload == null) || (o != null && o.equals(first.payload))) {
            first = first.next; // Entfernt das erste Element, wenn es übereinstimmt
            return true;
        }
    	
    	ListElement current = first;
        while (current.next != null) { // Iteriert durch die Liste
            if ((o == null && current.next.payload == null) || (o != null && o.equals(current.next.payload))) {
                current.next = current.next.next; // Entfernt das übereinstimmende Element
                return true;
            }
            current = current.next; // Geht zum nächsten Element über
        }

        return false;
    }
    	

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
    	if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size()); // Überprüft den Indexbereich
        }
        boolean modified = false; // Flag, um Änderungen zu verfolgen
        for (T element : c) { // Fügt jedes Element der Sammlung an der angegebenen Position hinzu
            add(index++, element); // Erhöht den Index nach jedem Hinzufügen
            modified = true;
        }
        return modified; // Gibt true zurück, wenn die Liste geändert wurde
    }
    

    @Override
    public T set(int index, T element) {
    	ListElement current = getElement(index); // Holt das Element an der angegebenen Position
        if (current == null) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size()); // Überprüft den Indexbereich
        }
        T oldPayload = current.payload; // Speichert den alten Wert
        current.payload = element; // Setzt den neuen Wert
        return oldPayload; // Gibt den alten Wert zurück
    }

    @Override
    public void add(int index, T element) {
    	if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size()); // Überprüft den Indexbereich
        }
        if (index == 0) {
            ListElement newElement = new ListElement(element); // Erstellt ein neues Element
            newElement.next = first; // Verbindet das neue Element mit dem ersten Element
            first = newElement; // Setzt das neue Element als erstes Element
        } else {
            ListElement prev = getElement(index - 1); // Holt das vorherige Element
            ListElement newElement = new ListElement(element); // Erstellt ein neues Element
            newElement.next = prev.next; // Verbindet das neue Element mit dem nachfolgenden Element
            prev.next = newElement; // Verbindet das vorherige Element mit dem neuen Element
        }
    }

    @Override
    public T remove(int index) {
    	if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size()); // Überprüft den Indexbereich
        }
        if (index == 0) {
            T removedPayload = first.payload; // Speichert den zu entfernenden Wert
            first = first.next; // Entfernt das erste Element
            return removedPayload; // Gibt den entfernten Wert zurück
        } else {
            ListElement prev = getElement(index - 1); // Holt das vorherige Element
            T removedPayload = prev.next.payload; // Speichert den zu entfernenden Wert
            prev.next = prev.next.next; // Entfernt das Element
            return removedPayload; // Gibt den entfernten Wert zurück
        }
    }

    @Override
    public boolean isEmpty() {
	return first == null; // Gibt true zurück, wenn die Liste leer ist
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
    	Object[] array = new Object[size()]; // Erstellt ein Array der passenden Größe
        int i = 0; // Initialisiert einen Index
        for (T t : this) { // Iteriert durch die Liste
            array[i++] = t; // Fügt jedes Element dem Array hinzu
        }
        return array; // Gibt das Array zurück
    }

    @Override
    public <E> E[] toArray(E[] a) {
	if (a.length < size()) {
	    a = (E[]) new Object[size()];
	}
	int i = 0;
	Object[] result = a; // Verwendet das übergebene Array oder das neue Array
	for (T t : this) {
		result[i++] = t;
	}
	if (a.length > size) {
        a[size] = null; // Setzt das erste überflüssige Element auf null
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
    	boolean modified = false; // um Veränderungen zu verfolgen
		for (T t : c) {
			if (add(t)) {
	            modified = true; // Fügt jedes Element der Liste hinzu
	        }
	    }
	    return modified; // Gibt true zurück, wenn die Liste geändert wurde
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
	ll.get(0);
	for (String s : ll) {
	    System.out.println(s);
	}

    }
}

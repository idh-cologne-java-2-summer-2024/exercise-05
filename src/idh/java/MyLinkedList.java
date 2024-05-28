package idh.java;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyLinkedList<T> implements List<T> {

    private class ListElement {
        T payload;
        ListElement next;

        ListElement(T value) {
            this.payload = value;
        }
    }

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
            if (current.payload.equals(o)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (isEmpty()) {
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
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        boolean modified = false;
        ListElement current = (index == 0) ? null : getElement(index - 1);
        for (T element : c) {
            ListElement newElement = new ListElement(element);
            if (current == null) {
                newElement.next = first;
                first = newElement;
                current = first;
            } else {
                newElement.next = current.next;
                current.next = newElement;
                current = newElement;
            }
            modified = true;
        }
        return modified;
    }

    @Override
    public T set(int index, T element) {
        ListElement current = getElement(index);
        T oldPayload = current.payload;
        current.payload = element;
        return oldPayload;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        ListElement newElement = new ListElement(element);
        if (index == 0) {
            newElement.next = first;
            first = newElement;
        } else {
            ListElement current = getElement(index - 1);
            newElement.next = current.next;
            current.next = newElement;
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        if (index == 0) {
            T payload = first.payload;
            first = first.next;
            return payload;
        } else {
            ListElement current = getElement(index - 1);
            T payload = current.next.payload;
            current.next = current.next.next;
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
        int size = size();
        if (a.length < size) {
        	@SuppressWarnings("unchecked")
        	E[] newArray = (E[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        	a = newArray;

        }
        int i = 0;
        Object[] result = a;
        for (T t : this) {
            result[i++] = t;
        }
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public boolean add(T e) {
        ListElement newElement = new ListElement(e);
        if (first == null)
            first = newElement;
        else
            last().next = newElement;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T t : c) {
            this.add(t);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object o : c) {
            modified = modified || remove(o);
        }
        return modified;
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
        ListElement current = getElement(index);
        return current.payload;
    }

    @Override
    public int indexOf(Object o) {
        ListElement current = first;
        int index = 0;
        while (current != null) {
            if (current.payload.equals(o)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        ListElement current = first;
        int index = 0;
        int lastFoundIndex = -1;
        while (current != null) {
            if (current.payload.equals(o)) {
                lastFoundIndex = index;
            }
            current = current.next;
            index++;
        }
        return lastFoundIndex;
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    private ListElement last() {
        if (first == null)
            return null;
        ListElement current = first;
        while (current.next != null) {
            current = current.next;
        }
        return current;
    }

    private ListElement getElement(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        ListElement current = first;
        while (current != null && index > 0) {
            current = current.next;
            index--;
        }
        return current;
    }

    public static void main(String[] args) {
        MyLinkedList<String> ll = new MyLinkedList<>();
        ll.add("Hallo");
        ll.add("Welt");
        ll.add("Welt");
        System.out.println(ll.get(0)); // Should print "Hallo"
        System.out.println(ll.get(1)); // Should print "Welt"
        System.out.println(ll.get(2)); // Should print "Welt"

        for (String s : ll) {
            System.out.println(s);
        }
    }
}


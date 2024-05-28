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
        return indexOf(o) != -1;
    }

    @Override
    public boolean remove(Object o) {
        if (isEmpty()) return false;
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
        if (index < 0 || index > size()) throw new IndexOutOfBoundsException();
        if (c.isEmpty()) return false;

        ListElement current = index == 0 ? null : getElement(index - 1);
        ListElement next = current == null ? first : current.next;

        for (T element : c) {
            ListElement newElement = new ListElement(element);
            if (current == null) {
                first = newElement;
            } else {
                current.next = newElement;
            }
            current = newElement;
        }
        current.next = next;
        return true;
    }

    @Override
    public T set(int index, T element) {
        ListElement elementAtIndex = getElement(index);
        if (elementAtIndex == null) throw new IndexOutOfBoundsException();
        T oldValue = elementAtIndex.payload;
        elementAtIndex.payload = element;
        return oldValue;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size()) throw new IndexOutOfBoundsException();
        ListElement newElement = new ListElement(element);

        if (index == 0) {
            newElement.next = first;
            first = newElement;
            return;
        }

        ListElement current = getElement(index - 1);
        newElement.next = current.next;
        current.next = newElement;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException();
        if (index == 0) {
            T value = first.payload;
            first = first.next;
            return value;
        }

        ListElement previous = getElement(index - 1);
        ListElement elementToRemove = previous.next;
        previous.next = elementToRemove.next;
        return elementToRemove.payload;
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
        boolean modified = false;
        for (Object o : c)
            modified = remove(o) || modified;
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            if (!c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public void clear() {
        first = null;
    }

    @Override
    public T get(int index) {
        ListElement element = getElement(index);
        if (element == null) throw new IndexOutOfBoundsException();
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
            int index = 0;

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
                return previous != null;
            }

            @Override
            public T previous() {
                throw new UnsupportedOperationException();
            }

            @Override
            public int nextIndex() {
                return index;
            }

            @Override
            public int previousIndex() {
                return index - 1;
            }

            @Override
            public void remove() {
                if (previous == null) {
                    first = next.next;
                } else {
                    previous.next = next.next;
                }
                next = previous == null ? first : previous.next;
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
        if (index < 0 || isEmpty())
            throw new IndexOutOfBoundsException();
        ListElement current = first;
        while (current != null) {
            if (index == 0)
                return current;
            index--;
            current = current.next;
        }
        throw new IndexOutOfBoundsException();
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

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

    @Override //SOLVED
    public int size() {
    	if (first == null) {
    		return 0;
    	}else {
    		int counter = 0;
    		
    		ListElement current=first;
    		while (current !=null){
    			current = current.next;
    			counter++;
    		}
    	return counter;
    	}
    }

    @Override //SOLVED
    public boolean contains(Object o) {
    	if (first == null) {
    		return false;
    	}else {
    		
    		ListElement current=first;
    		while (current !=null){
    			if (current.payload==o) {
    				return true;
    			}
    			current = current.next;
    		}
    	return false;
    	}
    }

    @SuppressWarnings("unchecked")
	@Override //SOLVED well I didn't  
    public boolean remove(Object o) {
    	if (first == null) {
    		return false;
    	}else {
    		
    		ListElement current=first;
    		while (current !=null){
    			if (current.payload==o) {
    				current.payload=(T) "REMOVED"; //You can write down null instead of "Removed" but then you have to take 
    											//care of possible errors occuring to that in other functions and 
    											//but here U can see that I solved this task. The former text is deleted ;)
    				return true;
    			}
    			current = current.next;
    		}
    	return false;
    	}
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
    	// TODO Implement!
    	return false;
    }

    @Override //SOLVED
    public T set(int index, T element) throws IndexOutOfBoundsException{
    	T former;
    	if (first == null) {
    		throw new IndexOutOfBoundsException();
    	}else {
    		int counter = 0;
    		
    		ListElement current=first;
    		while (current !=null){
    			if(counter==index) {
    				former = current.payload; 
    				current.payload = element;
    				return former;
    			}
    			current = current.next;
    			counter++;
    		}
    		throw new IndexOutOfBoundsException();
    	}
    }

    @Override //SOLVED
    public void add(int index, T element) throws IndexOutOfBoundsException{
    	T placeholder;
    	T placeholder2;
    	if (first == null) {
    		throw new IndexOutOfBoundsException();
    	}else {
    		int counter = 0;
    		
    		ListElement current=first;
    		while (current !=null){
    			if(counter==index) {
    				placeholder = current.payload; 
    				current.payload = element;
    		//		System.out.println(current.payload.toString());
    				
    				while(current.next!=null) {
    					current = current.next;
    					placeholder2 = current.payload;
    					current.payload = placeholder;
    					placeholder = placeholder2; //die in PH2 gespeicherten Werte werden in PH1 gespeichert, 
    					//sodass PH2 wieder in der Iterierung mit dem nächsten Value beschrieben werden kann.
    		//			System.out.println(current.payload.toString());
    				}
    				if (current.next == null) {
    					add(placeholder);
    					return;
    				}
    				return;
    			}
    		//	System.out.println(current.payload.toString());
    			current = current.next;
    			counter++;
    		}
    		throw new IndexOutOfBoundsException();
    	}
    }

    @Override //(nicht ganz fertig bis jetzt -> probiermal current payload = next.payload)
    public T remove(int index) throws IndexOutOfBoundsException{
    	T removedElement;
    	T placeholder2;
    	if (first == null) {
    		throw new IndexOutOfBoundsException();
    	}else {
    		int counter = 0;
    		
    		ListElement current=first;
    		while (current !=null){
    			if(counter==index) {
    				removedElement = current.payload;
    				current.payload=current.next.payload;
    				//
    				return removedElement;
    			}
    			
    		//	System.out.println(current.payload.toString());
    			current.next = current;
    			counter++;
    		}
    		throw new IndexOutOfBoundsException();
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
    	ll.add("Hallo UzK");
    	ll.add("guten Morgen!");
    	ll.add("Welt");
    	ll.add("POTZBLITZ WAR DAS ANSTRENGEND");
    	ll.remove("POTZBLITZ WAR DAS ANSTRENGEND");
    	ll.get(0);
    	System.out.println("_Die Liste ist "+ll.size()+" Einheiten lang");
    	System.out.println("_Die Liste enthält das Wort Yeehaw: "+ll.contains("Yeehaw"));
    	System.out.println("_Die Liste überschreibt folgendes Wort mit Yeehaw: "+ll.set(2, "Yeehaw"));
    	ll.add(2, "ich hab's geschafft |_(^.^)_/");
    	System.out.println("Und jetzt einmal die komplette Liste:\n{");
    	for (String s : ll) {
    		System.out.println(s);
    	}
    	System.out.println("}");
    	
    }
}

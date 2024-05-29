package idh.java;

import java.util.Iterator;

/**
 * Solution for optional task 2
 * 
 * @param <T>
 */
public class SkipIterator<T> implements Iterator<T> {

    /**
     * The base iterator from which we skip some elements
     */
    Iterator<T> baseIterator;

    /**
     * the number of elements to skip in between each step (set to 0 to disable
     * skipping)
     */
    int skip;

    /**
     * We need a special variable to deal with the first element
     */
    boolean first = true;

    public SkipIterator(Iterator<T> baseIterator, int skip) {
	super();
	this.baseIterator = baseIterator;
	this.skip = skip;
    }

    /**
     * More complicated than before, because we need to look ahead a few elements.
     * When calling this method, we move the iterator by skip elements and see if
     * there is anything after that. If so, we return true, otherwise false.
     * 
     * Note: This implementation is not perfect, because it assumes that hasNext()
     * is called only once. If it's called twice (for whatever reason), we move the
     * base iterator too far.
     */
    @Override
    public boolean hasNext() {
	int i = 0;
	if (first && baseIterator.hasNext())
	    return true;
	while (baseIterator.hasNext() && i < skip) {
	    baseIterator.next();
	    i++;
	}
	return baseIterator.hasNext();
    }

    @Override
    public T next() {
	if (first) {
	    first = false;
	}
	return baseIterator.next();
    }

}

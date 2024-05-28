package idh.java;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SkipIterator<T> implements Iterator<T> {
    private final Iterator<T> baseIterator;
    private final int skipCount;
    private boolean firstElementProcessed;

    public SkipIterator(Iterator<T> baseIterator, int skipCount) {
        this.baseIterator = baseIterator;
        this.skipCount = skipCount;
        this.firstElementProcessed = false;
    }

    @Override
    public boolean hasNext() {
        // Check if there are more elements in the base iterator
        if (!firstElementProcessed) {
            processNext();
        }
        return baseIterator.hasNext();
    }

    @Override
    public T next() {
        if (!firstElementProcessed) {
            processNext();
        }
        if (!baseIterator.hasNext()) {
            throw new NoSuchElementException();
        }
        T nextElement = baseIterator.next();
        processNext();
        return nextElement;
    }

    private void processNext() {
        for (int i = 0; i < skipCount; i++) {
            if (baseIterator.hasNext()) {
                baseIterator.next();
            }
        }
        firstElementProcessed = true;
    }

    public static void main(String[] args) {
        // Test with an example
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("by");
        list.add(" ");
        list.add("Bram");
        list.add("[Illustration:");
        list.add("GROSSET");
        list.add("YORK");
        list.add("Publishers");
        list.add("DUNLAP");
        list.add("in");
        list.add("1897,");
        list.add("America,");

        SkipIterator<String> skipIterator = new SkipIterator<>(list.iterator(), 2);
        while (skipIterator.hasNext()) {
            System.out.println(skipIterator.next());
        }
    }
}

package ua.edu.ucu.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IntIterable implements Iterable<Integer> {
    private int[] values;

    public IntIterable(int... values) {
        this.values = values;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < values.length;
            }

            @Override
            public Integer next() {
                if (hasNext()) {
                    return values[currentIndex++];
                } else {
                    throw new NoSuchElementException();
                }
            }


        };
    }
}

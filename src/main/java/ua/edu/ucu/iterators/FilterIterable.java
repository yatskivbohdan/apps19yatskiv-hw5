package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntPredicate;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FilterIterable implements Iterable<Integer> {
    private Iterable<Integer> iterable;
    private IntPredicate predicate;

    public FilterIterable(Iterable<Integer> iterable, IntPredicate predicate) {
        this.iterable = iterable;
        this.predicate = predicate;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int nextVal;
            private Iterator<Integer> iterator = iterable.iterator();

            @Override
            public boolean hasNext() {
                while (iterator.hasNext()) {
                    nextVal = iterator.next();
                    if (predicate.test(nextVal)) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public Integer next() {
                return nextVal;
            }
        };
    }
}
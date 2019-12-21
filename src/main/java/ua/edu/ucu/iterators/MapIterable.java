package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntUnaryOperator;

import java.util.Iterator;

public class MapIterable implements Iterable<Integer> {
    private Iterable<Integer> iterable;
    private IntUnaryOperator mapper;

    public MapIterable(Iterable<Integer> iterable, IntUnaryOperator mapper) {
        this.iterable = iterable;
        this.mapper = mapper;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private Iterator<Integer> iterator = iterable.iterator();

            @Override
            public boolean hasNext() {
                if (iterator.hasNext()){
                    iterator.next();
                    return true;
                }
                return false;
            }

            @Override
            public Integer next() {
                return mapper.apply(iterator.next());
            }
        };
    }
}
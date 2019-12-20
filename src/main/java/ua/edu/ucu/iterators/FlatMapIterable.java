package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.stream.AsIntStream;

import java.util.Iterator;

public class FlatMapIterable implements Iterable<Integer>{
    private Iterable<Integer> iterable;
    private IntToIntStreamFunction func;

    public FlatMapIterable(Iterable<Integer> iterable, IntToIntStreamFunction func) {
        this.iterable = iterable;
        this.func = func;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private Iterator<Integer> iterator = iterable.iterator();
            private Iterator<Integer> subIterator;
            @Override
            public boolean hasNext() {
               if (subIterator != null && subIterator.hasNext()){
                   return true;
               }
               while (iterator.hasNext()){
                   subIterator = ((AsIntStream) func.applyAsIntStream(iterator.next())).getIterable().iterator();
                   return true;
               }
               return false;
            }

            @Override
            public Integer next () {
                return subIterator.next();
            }
        };
    }
}

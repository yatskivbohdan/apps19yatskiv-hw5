package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;
import ua.edu.ucu.iterators.FilterIterable;
import ua.edu.ucu.iterators.FlatMapIterable;
import ua.edu.ucu.iterators.IntIterable;
import ua.edu.ucu.iterators.MapIterable;

import java.util.ArrayList;
import java.util.Iterator;

public class AsIntStream implements IntStream {
    private Iterable<Integer> valuesIterable;

    private AsIntStream(Iterable<Integer> values) {
        valuesIterable = values;
    }

    public static IntStream of(int... values) {
        return new AsIntStream(new IntIterable(values));
    }

    private void emptyCheck() {
        if (count() == 0) {
            throw new IllegalArgumentException();
        }
    }

    public Iterable<Integer> getIterable() {
        return valuesIterable;
    }

    @Override
    public Double average() {
        emptyCheck();
        return (double) sum() / (count());
    }

    @Override
    public Integer max() {
        emptyCheck();
        int max = Integer.MIN_VALUE;
        for (int el : valuesIterable) {
            if (el > max) {
                max = el;
            }
        }
        return max;
    }

    @Override
    public Integer min() {
        emptyCheck();
        int min = Integer.MAX_VALUE;
        for (int el : valuesIterable) {
            if (el < min) {
                min = el;
            }
        }
        return min;
    }

    @Override
    public long count() {
        long size = 0;
        for (Integer integer : valuesIterable) {
            size++;
        }
        return size;
    }

    @Override
    public Integer sum() {
        emptyCheck();
        int sum = 0;
        for (int el : valuesIterable) {
            sum += el;
        }
        return sum;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        return new AsIntStream(new FilterIterable(valuesIterable, predicate));
    }

    @Override
    public void forEach(IntConsumer action) {
        for (int el : valuesIterable) {
            action.accept(el);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        return new AsIntStream(new MapIterable(valuesIterable, mapper));
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        return new AsIntStream(new FlatMapIterable(valuesIterable, func));
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int toReturn = identity;
        for (Integer el : valuesIterable) {
            toReturn = op.apply(toReturn, el);
        }
        return toReturn;
    }

    @Override
    public int[] toArray() {
        int[] toReturn = new int[(int) count()];
        int i = 0;
        for (int el : valuesIterable) {
            toReturn[i] = el;
            i++;
        }
        return toReturn;
    }

}

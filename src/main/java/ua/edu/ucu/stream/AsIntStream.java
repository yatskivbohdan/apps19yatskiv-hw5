package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;

import java.util.ArrayList;

public class AsIntStream implements IntStream {
    private ArrayList<Integer> values;
    private boolean closed;

    private AsIntStream(int[] values) {
        closed = false;
        this.values = new ArrayList<>();
        for (int el : values) {
            this.values.add(el);
        }
    }

    public static IntStream of(int... values) {
        return new AsIntStream(values);
    }

    private void emptyCheck() {
        if (values.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    private void closedCheck() {
        if (closed) {
            throw new RuntimeException("Stream is closed");
        }
    }

    private void setClosed() {
        closed = true;
    }

    @Override
    public Double average() {
        emptyCheck();
        closedCheck();
        setClosed();
        int sum = 0;
        for (int el : values) {
            sum += el;
        }
        return (double) sum / (values.size());
    }

    @Override
    public Integer max() {
        emptyCheck();
        closedCheck();
        setClosed();
        int max = Integer.MIN_VALUE;
        for (int el : values) {
            if (el > max) {
                max = el;
            }
        }
        return max;
    }

    @Override
    public Integer min() {
        emptyCheck();
        closedCheck();
        setClosed();
        int min = Integer.MAX_VALUE;
        for (int el : values) {
            if (el < min) {
                min = el;
            }
        }
        return min;
    }

    @Override
    public long count() {
        closedCheck();
        setClosed();
        return values.size();
    }

    @Override
    public Integer sum() {
        emptyCheck();
        closedCheck();
        setClosed();
        int sum = 0;
        for (int el : values) {
            sum += el;
        }
        return sum;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        for (int i=0; i < values.size(); i++) {
            if (!predicate.test(values.get(i))) {
                values.remove(i);
            }
        }
        return this;
    }

    @Override
    public void forEach(IntConsumer action) {
        closedCheck();
        setClosed();
        for (int el : values) {
            action.accept(el);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        for (int i = 0; i < values.size(); i++) {
            values.set(i, mapper.apply(values.get(i)));
        }
        return this;
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        ArrayList <Integer> newValues = new ArrayList<>();
        for (Integer integer : values) {
            int[] subStreamArray = func.applyAsIntStream(integer).toArray();
            for (int value : subStreamArray) {
                newValues.add(value);
            }
        }
        values = newValues;
        return this;
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        closedCheck();
        setClosed();
        int toReturn = identity;
        for (Integer el : values){
            toReturn = op.apply(toReturn, el);
        }
        return toReturn;
    }

    @Override
    public int[] toArray() {
        closedCheck();
        setClosed();
        int[] toReturn = new int[values.size()];
        for (int i = 0; i < values.size(); i++) {
            toReturn[i] = values.get(i);
        }
        return toReturn;
    }

}

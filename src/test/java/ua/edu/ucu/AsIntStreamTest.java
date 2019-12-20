package ua.edu.ucu;

import org.junit.Before;
import org.junit.Test;
import ua.edu.ucu.stream.AsIntStream;
import ua.edu.ucu.stream.IntStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class AsIntStreamTest {

    private IntStream intStream;
    private IntStream oneValueStream;
    private IntStream emptyStream;

    @Before
    public void init() {
        int[] intArr = {-1, 0, 1, 2, 3};
        int[] empty = {};
        int[] one = {5};
        intStream = AsIntStream.of(intArr);
        emptyStream = AsIntStream.of(empty);
        oneValueStream = AsIntStream.of(one);

    }

    @Test
    public void testAverage() {
        double expectedNorm = 1.0;
        assertEquals(expectedNorm, intStream.average(), 0.001);
        double expectedOne = 5.0;
        assertEquals(expectedOne, oneValueStream.average(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageEmpty() {
        emptyStream.average();

    }

    @Test
    public void testMax() {
        double expectedNorm = 3.0;
        assertEquals(expectedNorm, intStream.max(), 0.001);
        double expectedOne = 5.0;
        assertEquals(expectedOne, oneValueStream.max(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxEmpty() {
        emptyStream.max();

    }

    @Test
    public void testMin() {
        double expectedNorm = -1.0;
        assertEquals(expectedNorm, intStream.min(), 0.001);
        double expectedOne = 5.0;
        assertEquals(expectedOne, oneValueStream.min(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinEmpty() {
        emptyStream.min();

    }

    @Test
    public void testCount() {
        long expectedNorm = 5;
        assertEquals(expectedNorm, intStream.count());
        long expectedOne = 1;
        assertEquals(expectedOne, oneValueStream.count());
        long expectedEmpty = 0;
        assertEquals(expectedEmpty, emptyStream.count());
    }


    @Test
    public void testSum() {
        int expectedNorm = 5;
        assertEquals(expectedNorm, intStream.sum().intValue());
        int expectedOne = 5;
        assertEquals(expectedOne, oneValueStream.sum().intValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSumEmpty() {
        emptyStream.sum();

    }

    @Test
    public void testToArray() {
        int[] expectedNorm = {-1, 0, 1, 2, 3};
        assertArrayEquals(expectedNorm, intStream.toArray());
        int[] expectedOne = {5};
        assertArrayEquals(expectedOne, oneValueStream.toArray());
        int[] expectedEmpty = {};
        assertArrayEquals(expectedEmpty, emptyStream.toArray());
    }

}

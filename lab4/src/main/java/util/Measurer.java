package util;

public class Measurer {

    public static long measure(Measurable measurable) {
        long start = System.nanoTime();
        measurable.execute();
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        return timeElapsed / 1000;
    }

}

package util;

import query.QueryType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LongSummaryStatistics;

public class Counter {
    private final HashMap<QueryType, ArrayList<Long>> map;

    public Counter() {
        map = new HashMap<>();
        map.put(QueryType.SELECT, new ArrayList<>());
        map.put(QueryType.CHANGE, new ArrayList<>());
        map.put(QueryType.DELETE, new ArrayList<>());
    }

    public void append(QueryType type, Long value) {
        map.get(type).add(value);
    }

    public void printStatistics(QueryType type) {
        ArrayList<Long> list = map.get(type);
        System.out.printf("%s (%d):\n", type, list.size());
        LongSummaryStatistics lss = list.stream().mapToLong(a -> a).summaryStatistics();
        System.out.printf("\tAVG = %.0f\n\tMIN = %d\n\tMAX = %d\n%n", lss.getAverage(), lss.getMin(), lss.getMax());
    }

    public void printStatistics() {
        printStatistics(QueryType.SELECT);
        printStatistics(QueryType.CHANGE);
        printStatistics(QueryType.DELETE);
    }
}

package query.executor;

import cache.LRUCache;
import query.Query;

import java.sql.ResultSet;

public class CachedQueryExecutor extends QueryExecutor {

    private final LRUCache<String, ResultSet> cache;

    public CachedQueryExecutor(int capacity) {
        cache = new LRUCache<>(capacity);
    }

    @Override
    void executeSelect(Query query) {
        String key = cache.getKey(query.table, query.text);
        ResultSet rs;
        if (!cache.contains(key)) {
            rs = lc.executeResult(query.text);
            cache.put(key, rs);
        } else {
            rs = cache.get(key);
        }
    }

    @Override
    void executeChange(Query query) {
        cache.removeContains(query.table);
        lc.executeUpdate(query.text);
    }

}

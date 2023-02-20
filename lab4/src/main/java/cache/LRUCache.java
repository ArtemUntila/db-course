package cache;

import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class LRUCache<K, V> {

    private final Deque<K> deque;

    private final HashMap<K, V> map;

    private final int CACHE_SIZE;

    public LRUCache(int capacity) {
        deque = new LinkedList<>();
        map = new HashMap<>();
        CACHE_SIZE = capacity;
    }

    public V get(K key) {
        deque.remove(key);
        deque.push(key);
        return map.get(key);
    }

    public boolean contains(K key) {
        return deque.contains(key);
    }

    public void put(K key, V value) {
        if (deque.size() == CACHE_SIZE) {
            K last = deque.removeLast();
            map.remove(last);
        }
        deque.push(key);
        map.put(key, value);
    }

    public void removeContains(String prefix) {
        Iterator<K> iterator = deque.iterator();
        while (iterator.hasNext()) {
            K key = iterator.next();
            String s = key.toString();
            if (s.contains(prefix)) {
                iterator.remove();
                map.remove(key);
            }
        }
    }

    public void remove(K key) {
        deque.remove(key);
        map.remove(key);
    }

    public void printState() {
        System.out.println(deque);
    }

    public String getKey(String prefix, String value) {
        return String.format("%s_%d", prefix, value.hashCode());
    }
}

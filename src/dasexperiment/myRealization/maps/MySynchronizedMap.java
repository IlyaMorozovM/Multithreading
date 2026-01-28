package dasexperiment.myRealization.maps;

import java.util.*;

public class MySynchronizedMap<K, V> extends HashMap<K, V> {
    private final Object lock = new Object();

    @Override
    public V put(K key, V value) {
        synchronized (lock) {
            return super.put(key, value);
        }
    }

    @Override
    public V get(Object key) {
        synchronized (lock) {
            return super.get(key);
        }
    }

    @Override
    public Collection<V> values() {
        synchronized (lock) {
            return new ArrayList<>(super.values());
        }
    }
}

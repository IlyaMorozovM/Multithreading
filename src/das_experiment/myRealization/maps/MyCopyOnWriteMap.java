package das_experiment.myRealization.maps;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class MyCopyOnWriteMap<K, V> {
    private final AtomicReference<Map<K, V>> ref = new AtomicReference<>(new HashMap<>());

    public void put(K key, V value) {
        while (true) {
            Map<K, V> oldMap = ref.get();
            Map<K, V> newMap = new HashMap<>(oldMap);
            newMap.put(key, value);
            if (ref.compareAndSet(oldMap, newMap)) break;
        }
    }

    public V get(K key) {
        return ref.get().get(key);
    }

    public Collection<V> values() {
        return ref.get().values();
    }
}

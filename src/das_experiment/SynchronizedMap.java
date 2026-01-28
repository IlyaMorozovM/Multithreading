package das_experiment;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SynchronizedMap {

    public static void main(String[] args) {
        Map<Integer, Integer> map = Collections.synchronizedMap(new HashMap<>());
        Thread writer = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                map.put(i, i);
                try { Thread.sleep(1); } catch (InterruptedException ignored) {}
            }
        });

        Thread reader = new Thread(() -> {
            while (true) {
                int sum = 0;
                synchronized (map) {
                    for (int value : map.values()) {
                        sum += value;
                    }
                }
            }
        });

        writer.start();
        reader.start();
    }

}

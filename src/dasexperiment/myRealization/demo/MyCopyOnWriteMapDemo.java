package dasexperiment.myRealization.demo;

import dasexperiment.myRealization.maps.MyCopyOnWriteMap;

public class MyCopyOnWriteMapDemo {

    public static void main(String[] args) {

        MyCopyOnWriteMap<Integer, Integer> map = new MyCopyOnWriteMap<>();

        Thread writer = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                map.put(i, i);
                try { Thread.sleep(1); } catch (InterruptedException ignored) {}
            }
        });

        Thread reader = new Thread(() -> {
            while (true) {
                int sum = 0;
                for (int value : map.values()) {
                    sum += value;
                }
                System.out.println("Sum: " + sum);
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
            }
        });

        writer.start();
        reader.start();
    }
}

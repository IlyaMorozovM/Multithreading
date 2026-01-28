package myRealization.demo;

import myRealization.maps.MySynchronizedMap;

import java.util.Map;

public class MySynchronizedMapDemo {

    public static void main(String[] args) {

        Map<Integer, Integer> map = new MySynchronizedMap<>();

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

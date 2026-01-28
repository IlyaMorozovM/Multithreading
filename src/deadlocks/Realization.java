package deadlocks;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Realization {
    private final List<Integer> numbers = new ArrayList<>();
    private final Object lock = new Object();

    public static void main(String[] args) {
        Realization task = new Realization();
        task.startThreads();
    }

    public void startThreads() {
        Thread writer = new Thread(() -> {
            while (true) {
                int num = ThreadLocalRandom.current().nextInt(100);
                synchronized (lock) {
                    numbers.add(num);
                }
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
            }
        });

        Thread sumPrinter = new Thread(() -> {
            while (true) {
                int sum;
                synchronized (lock) {
                    sum = numbers.stream().mapToInt(Integer::intValue).sum();
                }
                System.out.println("Sum: " + sum);
                try { Thread.sleep(300); } catch (InterruptedException ignored) {}
            }
        });

        Thread sqrtSumSquaresPrinter = new Thread(() -> {
            while (true) {
                double sqrtSumSquares;
                synchronized (lock) {
                    int sumSquares = numbers.stream().mapToInt(i -> i * i).sum();
                    sqrtSumSquares = Math.sqrt(sumSquares);
                }
                System.out.println("Sqrt of sum of squares: " + sqrtSumSquares);
                try { Thread.sleep(500); } catch (InterruptedException ignored) {}
            }
        });

        writer.start();
        sumPrinter.start();
        sqrtSumSquaresPrinter.start();
    }
}

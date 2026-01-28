package task4;

public class BlockingObjectPool {
    private final Object[] pool;
    private int count = 0;

    public BlockingObjectPool(int size) {
        if (size <= 0) throw new IllegalArgumentException("Size must be positive");
        pool = new Object[size];

        for (int i = 0; i < size; i++) {
            pool[i] = new Object();
            count++;
        }
    }

    public synchronized Object get() {
        while (count == 0) {
            try {
                wait();
            } catch (InterruptedException ignored) {}
        }

        for (int i = 0; i < pool.length; i++) {
            if (pool[i] != null) {
                Object obj = pool[i];
                pool[i] = null;
                count--;
                notifyAll();
                return obj;
            }
        }

        return null;
    }

    public synchronized void take(Object object) {
        while (count == pool.length) {
            try {
                wait();
            } catch (InterruptedException ignored) {}
        }

        for (int i = 0; i < pool.length; i++) {
            if (pool[i] == null) {
                pool[i] = object;
                count++;
                notifyAll(); // Оповестить тех, кто ждет появления объекта
                return;
            }
        }
    }
}

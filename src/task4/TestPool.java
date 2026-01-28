package task4;

public class TestPool {
    public static void main(String[] args) {
        BlockingObjectPool pool = new BlockingObjectPool(2);

        new Thread(() -> {
            while (true) {
                Object obj = pool.get();
                System.out.println("Got object: " + obj);
                try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
                pool.take(obj);
                System.out.println("Returned object: " + obj);
            }
        }).start();

        new Thread(() -> {
            while (true) {
                Object obj = pool.get();
                System.out.println("Thread 2 got: " + obj);
                try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
                pool.take(obj);
                System.out.println("Thread 2 returned: " + obj);
            }
        }).start();
    }
}

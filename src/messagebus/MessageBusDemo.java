package messagebus;

public class MessageBusDemo {
    public static void main(String[] args) {
        MessageBus bus = new MessageBus();

        new Thread(new Consumer(bus, "sports")).start();
        new Thread(new Consumer(bus, "news")).start();

        new Thread(new Producer(bus, "sports")).start();
        new Thread(new Producer(bus, "news")).start();
    }
}

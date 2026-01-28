package messagebus;

import java.util.Random;

class Producer implements Runnable {
    private final MessageBus bus;
    private final String topic;
    private final Random random = new Random();

    Producer(MessageBus bus, String topic) {
        this.bus = bus;
        this.topic = topic;
    }

    @Override
    public void run() {
        int count = 0;
        while (true) {
            String payload = "Message #" + (++count) + " for topic " + topic + " [" + random.nextInt(1000) + "]";
            Message message = new Message(topic, payload);
            bus.post(message);
            try {
                Thread.sleep(500 + random.nextInt(500));
            } catch (InterruptedException ignored) {}
        }
    }
}

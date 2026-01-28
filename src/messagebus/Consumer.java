package messagebus;

class Consumer implements Runnable {
    private final MessageBus bus;
    private final String topic;

    Consumer(MessageBus bus, String topic) {
        this.bus = bus;
        this.topic = topic;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message message = bus.take(topic);
                System.out.println("[" + topic + "] Consumed: " + message.payload);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}

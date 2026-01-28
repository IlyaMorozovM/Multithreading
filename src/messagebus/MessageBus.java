package messagebus;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class MessageBus {
    private final Map<String, LinkedList<Message>> topicQueues = new HashMap<>();

    public void post(Message message) {
        synchronized (this) {
            topicQueues.putIfAbsent(message.topic, new LinkedList<>());
            topicQueues.get(message.topic).add(message);
            this.notifyAll(); // Оповещаем всех ждущих consumer-ов
        }
    }

    public Message take(String topic) throws InterruptedException {
        synchronized (this) {
            while (!topicQueues.containsKey(topic) || topicQueues.get(topic).isEmpty()) {
                this.wait();
            }
            return topicQueues.get(topic).removeFirst();
        }
    }
}

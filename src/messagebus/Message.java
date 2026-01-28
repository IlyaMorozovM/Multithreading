package messagebus;

class Message {
    final String topic;
    final String payload;

    Message(String topic, String payload) {
        this.topic = topic;
        this.payload = payload;
    }
}

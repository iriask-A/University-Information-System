package KBTU;

public class Lesson {

    private LessonType type;
    private String topic;
    private String room;

    public Lesson() {
    }

    public Lesson(LessonType type, String topic, String room) {
        this.type = type;
        this.topic = topic;
        this.room = room;
    }

    @Override
    public String toString() {
        return "Lesson [" + type + "]: " + topic + " (Room: " + room + ")";
    }

    public LessonType getType() {
        return type;
    }

    public void setType(LessonType type) {
        this.type = type;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}

package kbtu.model.course;

import kbtu.enums.LessonType;

import java.io.Serializable;

/**
 * A single lesson within a course (lecture or practice).
 */
public class Lesson implements Serializable {
    private static final long serialVersionUID = 1L;

    private LessonType type;
    private String     topic;
    private String     room;

    public Lesson() {}

    public Lesson(LessonType type, String topic, String room) {
        this.type  = type;
        this.topic = topic;
        this.room  = room;
    }

    public LessonType getType()           { return type; }
    public void       setType(LessonType t){ this.type = t; }
    public String     getTopic()          { return topic; }
    public void       setTopic(String t)  { this.topic = t; }
    public String     getRoom()           { return room; }
    public void       setRoom(String r)   { this.room = r; }

    @Override
    public String toString() {
        return "Lesson [" + type + "]: " + topic + " (Room: " + room + ")";
    }
}

package kbtu.model.course;

import kbtu.enums.LessonType;

import java.io.Serializable;

/**
 * Represents a single academic session within a course.
 * This class defines the format of the lesson (lecture or practice),
 * the specific academic topic being covered, and the location.
 */
public class Lesson implements Serializable {
    private static final long serialVersionUID = 1L;

    /** The format of the lesson, such as LECTURE or PRACTICE. */
    private LessonType type;

    /** The specific subject or theme covered during the session. */
    private String topic;

    /** The physical room or laboratory where the lesson takes place. */
    private String room;

    public Lesson() {}

    public Lesson(LessonType type, String topic, String room) {
        this.type  = type;
        this.topic = topic;
        this.room  = room;
    }

    /**
     * Gets the type of the lesson.
     * @return The LessonType enum value.
     */
    public LessonType getType() { return type; }

    /**
     * Sets the type of the lesson.
     * @param t The LessonType to be assigned.
     */
    public void setType(LessonType t) { this.type = t; }

    /**
     * Gets the academic topic of the lesson.
     * @return A string representing the topic.
     */
    public String getTopic() { return topic; }

    /**
     * Sets the academic topic for the lesson.
     * @param t The topic name.
     */
    public void setTopic(String t) { this.topic = t; }

    /**
     * Gets the location where the lesson is held.
     * @return The room identifier.
     */
    public String getRoom() { return room; }

    /**
     * Sets the location for the lesson.
     * @param r The room or hall name.
     */
    public void setRoom(String r) { this.room = r; }

    @Override
    public String toString() {
        return "Lesson [" + type + "]: " + topic + " (Room: " + room + ")";
    }
}
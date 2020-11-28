package net.school.model.lessons;

import net.school.types.Subject;

import java.time.LocalDateTime;
import java.util.List;

public final class Lesson {
    private int id;
    private int teacherId;
    private LocalDateTime lessonTime;
    private Subject subject;
    private List<Integer> studentAttendingLesson;

    public Lesson(int id, int teacherId, LocalDateTime lessonTime, Subject subject) {
        this.id = id;
        this.teacherId = teacherId;
        this.lessonTime = lessonTime;
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public int getTeacher() {
        return teacherId;
    }

    public LocalDateTime getLessonTime() {
        return lessonTime;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTeacher(int teacherId) {
        this.teacherId = teacherId;
    }

    public void setLessonTime(LocalDateTime lessonTime) {
        this.lessonTime = lessonTime;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}

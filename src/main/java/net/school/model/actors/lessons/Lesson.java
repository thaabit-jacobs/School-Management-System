package net.school.model.actors.lessons;

import net.school.model.actors.Faculty;
import net.school.types.Subject;

import java.time.LocalDateTime;

public final class Lesson {
    private int id;
    private Faculty teacher;
    private LocalDateTime lessonTime;
    private Subject subject;

    public Lesson(int id, Faculty teacher, LocalDateTime lessonTime, Subject subject) {
        this.id = id;
        this.teacher = new Faculty(teacher);
        this.lessonTime = lessonTime;
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public Faculty getTeacher() {
        return teacher;
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

    public void setTeacher(Faculty teacher) {
        this.teacher = teacher;
    }

    public void setLessonTime(LocalDateTime lessonTime) {
        this.lessonTime = lessonTime;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}

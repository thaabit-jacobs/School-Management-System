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

    public Lesson(int id, int teacherId, Subject subject, LocalDateTime lessonTime, List<Integer> studentAttendingLesson) {
        this.id = id;
        this.teacherId = teacherId;
        this.lessonTime = lessonTime;
        this.subject = subject;
        this.studentAttendingLesson = studentAttendingLesson;
    }

    public int getId() {
        return id;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public LocalDateTime getLessonTime() {
        return lessonTime;
    }

    public Subject getSubject() {
        return subject;
    }

    public List<Integer> getStudentAttendingLesson() {
        return studentAttendingLesson;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public void setLessonTime(LocalDateTime lessonTime) {
        this.lessonTime = lessonTime;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setStudentAttendingLesson(List<Integer> studentAttendingLesson) {
        this.studentAttendingLesson = studentAttendingLesson;
    }
}

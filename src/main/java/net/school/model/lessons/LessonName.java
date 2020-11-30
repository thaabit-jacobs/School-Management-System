package net.school.model.lessons;

import net.school.types.Subject;

import java.time.LocalDateTime;
import java.util.List;

public class LessonName {
    private int id;
    private String teacherName;
    private LocalDateTime lessonTime;
    private Subject subject;
    private List<Integer> studentAttendingLesson;

    public LessonName(int id, String teacherName, Subject subject, LocalDateTime lessonTime, List<Integer> studentAttendingLesson) {
        this.id = id;
        this.teacherName = teacherName;
        this.lessonTime = lessonTime;
        this.subject = subject;
        this.studentAttendingLesson = studentAttendingLesson;
    }

    public int getId() {
        return id;
    }

    public String getTeacherName() {
        return teacherName;
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

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
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

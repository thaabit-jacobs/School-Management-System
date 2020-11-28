package net.school.model;

import net.school.types.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Faculty extends Academic{
    private boolean isTeachingLesson;

    public Faculty(){

    }

    public Faculty(int id, String firstName, String lastName, String email, String mobileNo, LocalDate dob, Role role, LocalDateTime dateCreated, List<String> registeredSubjects, boolean isTeachingLesson) {
        super(id, firstName, lastName, email, mobileNo, dob, role, dateCreated, registeredSubjects);
        this.isTeachingLesson = isTeachingLesson;
    }

    public boolean getIsTeachingLesson() {
        return isTeachingLesson;
    }

    public void setIsTeachingLesson(boolean teachingLesson) {
        isTeachingLesson = teachingLesson;
    }
}

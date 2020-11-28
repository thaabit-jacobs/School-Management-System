package net.school.model;

import net.school.types.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Student extends Academic {
    private double feesBalance;
    private boolean isAttendingLesson;
    private List<Integer> notesForLessons;

    public Student(int id, String firstName, String lastName, String email, String mobileNo, LocalDate dob, Role role, LocalDateTime dateCreated, List<String> registeredSubjects, double feesBalance,boolean isAttendingLesson, List<Integer> notesForLessons ) {
        super(id, firstName, lastName, email, mobileNo, dob, role, dateCreated, registeredSubjects);
        this.feesBalance = feesBalance;
        this.isAttendingLesson = isAttendingLesson;
        this.notesForLessons = notesForLessons;
    }

    public Student(){

    }


    public double getFeesBalance() {
        return feesBalance;
    }

    public Person setFeesBalance(double feesBalance) {
        this.feesBalance = feesBalance;
        return this;
    }

    public boolean getIsAttendingLesson() {
        return isAttendingLesson;
    }

    public Person setIsAttendingLesson(boolean attendingLesson) {
        isAttendingLesson = attendingLesson;
        return this;
    }

    public List<Integer> getNotesForLessons() {
        return notesForLessons;
    }

    public Person setNotesForLessons(List<Integer> notesForLessons) {
        this.notesForLessons = notesForLessons;

        return this;
    }

}

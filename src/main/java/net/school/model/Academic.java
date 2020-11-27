package net.school.model;

import net.school.types.Role;
import net.school.types.Subject;

import java.time.LocalDate;
import java.util.List;

public class Academic extends Person{
    private List<Subject> registeredSubjects;

    public List<Subject> getRegisteredSubjects() {
        return registeredSubjects;
    }

    public Person setRegisteredSubjects(List<Subject> registeredSubjects){
        this.registeredSubjects = registeredSubjects;
        return  this;
    }
}

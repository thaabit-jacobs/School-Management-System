package net.school.model;

import net.school.types.Role;
import net.school.types.Subject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public abstract class Academic extends Person{
    private List<String> registeredSubjects;

    public Academic(){

    }

    public Academic(int id, String firstName, String lastName, String email, String mobileNo, LocalDate dob, Role role, LocalDateTime dateCreated, List<String> registeredSubjects){
        super(id, firstName, lastName, email, mobileNo, dob, role, dateCreated);
        this.registeredSubjects = registeredSubjects;
    }

    public List<String> getRegisteredSubjects() {
        return registeredSubjects;
    }

    public Person setRegisteredSubjects(List<String> registeredSubjects){
        this.registeredSubjects = registeredSubjects;
        return  this;
    }
}

package net.school.model.actors;

import net.school.types.Subject;

import java.util.List;

public abstract class Academic extends Person{
    private List<Subject> registeredSubjects;

    public List<Subject> getRegisteredSubjects() {
        return registeredSubjects;
    }

    public Person setRegisteredSubjects(List<Subject> registeredSubjects){
        this.registeredSubjects = registeredSubjects;
        return  this;
    }
}

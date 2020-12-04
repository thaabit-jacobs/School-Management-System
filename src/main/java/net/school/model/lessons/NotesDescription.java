package net.school.model.lessons;

import net.school.types.Subject;

public class NotesDescription {
    private Subject subject;
    private String description;

    public Subject getSubject() {
        return subject;
    }

    public NotesDescription(Subject subject, String description) {
        this.subject = subject;
        this.description = description;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

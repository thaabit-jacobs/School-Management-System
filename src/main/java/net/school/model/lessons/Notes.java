package net.school.model.lessons;

public class Notes {

    private int id;
    private int lessonId;
    private String description;

    public int getId() {
        return id;
    }

    public Notes setId(int id) {
        this.id = id;

        return this;
    }

    public Notes(){}

    public Notes(int id, int lessonId, String description) {
        this.id = id;
        this.lessonId = lessonId;
        this.description = description;
    }

    public int getLessonId() {
        return lessonId;
    }

    public Notes setLessonId(int lessonId) {
        this.lessonId = lessonId;

        return this;
    }

    public String getDescription() {
        return description;
    }

    public Notes setDescription(String description) {
        this.description = description;

        return this;
    }
}

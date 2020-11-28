package net.school.model.lessons;

public enum Notes {
    ;
    private int id;
    private int lessonId;

    public int getId() {
        return id;
    }

    public Notes setId(int id) {
        this.id = id;

        return this;
    }

    public int getLessonId() {
        return lessonId;
    }

    public Notes setLessonId(int lessonId) {
        this.lessonId = lessonId;

        return this;
    }
}

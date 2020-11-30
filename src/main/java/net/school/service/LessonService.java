package net.school.service;

import net.school.doa.lesson.LessonDoa;
import net.school.doa.lesson.LessonDoaImpl;
import net.school.doa.student.StudentDoa;
import net.school.doa.student.StudentDoaImpl;
import net.school.model.Student;
import net.school.model.lessons.Lesson;
import net.school.model.lessons.LessonName;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LessonService {

    private final LessonDoa lessonDoa = new LessonDoaImpl();
    private final static LessonService instance = new LessonService();

    private LessonService(){}

    public static synchronized LessonService getInstance(){
        return instance;
    }

    public synchronized boolean insertLesson(Lesson lesson){
        return lessonDoa.insertLesson(lesson);
    }

    public synchronized Lesson selectLesson(int id){
        return lessonDoa.selectLesson(id);
    }

    public synchronized List<Lesson> selectAllLessons(){
        return lessonDoa.selectAllLessons();
    }

    public synchronized List<LessonName> selectAllLessonsTeacherName(){
        return lessonDoa.selectAllLessonsTeacherName();
    }

    public synchronized boolean deleteLesson(int id){
        return lessonDoa.deleteLesson(id);
    }

    public synchronized boolean deleteAllLessons(){
        return lessonDoa.deleteAllLessons();
    }

    public synchronized boolean updateLesson(Lesson lesson){
        return lessonDoa.updateLesson(lesson);
    }

    public int getUniqueStudentId(){
        List<Lesson> students = selectAllLessons();

        Comparator<Lesson> byId = (a,b) -> a.getId() - b.getId();

        Collections.sort(students, byId);

        if(students.size()==0)
            return 1;

        int uniqueId = students.get(students.size() - 1).getId() + 1;

        return uniqueId;
    }
}

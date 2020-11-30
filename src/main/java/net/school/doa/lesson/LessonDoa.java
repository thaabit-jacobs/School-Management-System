package net.school.doa.lesson;

import net.school.model.lessons.Lesson;
import net.school.model.lessons.LessonName;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface LessonDoa {

    @SqlUpdate("insert into lessons  (id, teacher_id, subject, lesson_time, students_attending) " +
            "values(:id, :teacherId, :subject, :lessonTime, :studentAttendingLesson)")
    boolean insertLesson(@BindBean Lesson lesson);

    @SqlQuery("select * from lessons")
    List<Lesson> selectAllLessons();

    @SqlQuery("select * from lessons where id=:id")
    Lesson selectLesson(@Bind("id") int id);

    @SqlQuery("select lessons.id, faculties.first_name, lessons.subject, lessons.lesson_time, lessons.students_attending from lessons " +
            "inner join faculties on lessons.teacher_id=faculties.id")
    List<LessonName> selectAllLessonsTeacherName();


    @SqlUpdate("update lessons set teacher_id=:teacherId, students_attending=:studentAttendingLesson")
    boolean updateLesson(@BindBean Lesson lesson);

    @SqlUpdate("delete from lessons")
    boolean deleteAllLessons();

    @SqlUpdate("delete from lessons where id=:id")
    boolean deleteLesson(@Bind("id") int id);
}

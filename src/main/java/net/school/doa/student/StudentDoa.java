package net.school.doa.student;

import net.school.model.Student;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface StudentDoa {

    @SqlUpdate("insert into students (id, first_name, last_name, email, mobile_no, dob, role, fees_balance, registered_subjects, lesson_notes, is_attending_lesson, date_created) " +
            "values(:id, :firstName, :lastName, :email, :mobileNo, :dob, :role, :feesBalance, :registeredSubjects, :notesForLessons, :isAttendingLesson, :dateCreated)")
    boolean insertStudent(@BindBean Student student);

    @SqlQuery("select * from students where id=:id")
    Student selectStudent(@Bind("id") int id);

    @SqlQuery("select * from students")
    List<Student> selectAllStudents();

    @SqlUpdate("update students set fees_balance=? where id=?")
    boolean updateStudent(double amount, int id);

    @SqlUpdate("delete from students where id=:id")
    boolean deleteStudent(@Bind("id") int id);

    @SqlUpdate("delete from students")
    boolean deleteAllStudents();

}

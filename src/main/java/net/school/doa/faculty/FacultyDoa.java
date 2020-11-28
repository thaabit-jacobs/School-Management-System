package net.school.doa.faculty;

import net.school.model.Faculty;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface FacultyDoa {
    @SqlUpdate("insert into faculties (id, first_name, last_name, email, mobile_no, dob, role, registered_subjects, is_teaching_lesson, date_created) " +
            "values(:id, :firstName, :lastName, :email, :mobileNo, :dob, :role, :registeredSubjects, :isTeachingLesson, :dateCreated)")
    boolean insertFaculty(@BindBean Faculty faculty);

    @SqlQuery("select * from faculties where id=:id")
    Faculty selectFaculty(@Bind("id") int id);

    @SqlQuery("select * from faculties")
    List<Faculty> selectAllFaculties();

    @SqlUpdate("delete from faculties where id=:id")
    boolean deleteFaculty(@Bind("id") int id);

    @SqlUpdate("delete from faculties")
    boolean deleteAllFaculties();
}

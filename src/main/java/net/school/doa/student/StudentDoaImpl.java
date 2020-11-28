package net.school.doa.student;

import net.school.mapper.AdminMapper;
import net.school.mapper.StudentMapper;
import net.school.model.Student;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.List;

public class StudentDoaImpl implements StudentDoa{

    private Jdbi jdbi = Jdbi.create("jdbc:postgresql://localhost:5432/schoolsdb", "thaabit", "1234");

    public StudentDoaImpl() {
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
        jdbi.registerRowMapper(new StudentMapper());
        jdbi.registerArrayType(Integer.class, "integer");
        jdbi.registerArrayType(String.class, "varchar");
    }

    public StudentDoaImpl(Jdbi jdbi){
        this.jdbi = jdbi;
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
        jdbi.registerRowMapper(new StudentMapper());
        jdbi.registerArrayType(Integer.class, "integer");
        jdbi.registerArrayType(String.class, "varchar");
    }

    @Override
    public boolean insertStudent(Student student){
        return jdbi.withExtension(StudentDoa.class, doa-> doa.insertStudent(student));
    }

    @Override
    public Student selectStudent(int id){
        return jdbi.withExtension(StudentDoa.class, doa-> doa.selectStudent(id));
    }

    @Override
    public List<Student> selectAllStudents(){
        return jdbi.withExtension(StudentDoa.class, doa-> doa.selectAllStudents());
    }

    @Override
    public boolean deleteStudent(int id){
        return jdbi.withExtension(StudentDoa.class, doa-> doa.deleteStudent(id));
    }

    @Override
    public boolean deleteAllStudents(){
        return jdbi.withExtension(StudentDoa.class, doa-> doa.deleteAllStudents());
    }

}

package net.school.doa.faculty;

import net.school.mapper.AdminMapper;
import net.school.mapper.FacultyMapper;
import net.school.mapper.StudentMapper;
import net.school.model.Faculty;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.List;

public class FacultyDoaImpl implements FacultyDoa{

    private Jdbi jdbi = Jdbi.create("jdbc:postgresql://localhost:5432/schoolsdb", "thaabit", "1234");

    public FacultyDoaImpl() {
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
        jdbi.registerRowMapper(new FacultyMapper());
        jdbi.registerArrayType(Integer.class, "integer");
        jdbi.registerArrayType(String.class, "varchar");
    }

    public FacultyDoaImpl(Jdbi jdbi){
        this.jdbi = jdbi;
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
        jdbi.registerRowMapper(new FacultyMapper());
        jdbi.registerArrayType(Integer.class, "integer");
        jdbi.registerArrayType(String.class, "varchar");
    }

    @Override
    public boolean insertFaculty(Faculty faculty){
        return jdbi.withExtension(FacultyDoa.class, doa -> doa.insertFaculty(faculty));
    }

    @Override
    public Faculty selectFaculty(int id){
        return jdbi.withExtension(FacultyDoa.class, doa -> doa.selectFaculty(id));
    }

    @Override
    public List<Faculty> selectAllFaculties(){
        return jdbi.withExtension(FacultyDoa.class, doa -> doa.selectAllFaculties());
    }

    @Override
    public boolean deleteFaculty(int id){
        return jdbi.withExtension(FacultyDoa.class, doa -> doa.deleteFaculty(id));
    }

    @Override
    public boolean deleteAllFaculties(){
        return jdbi.withExtension(FacultyDoa.class, doa -> doa.deleteAllFaculties());
    }
}

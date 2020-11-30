package net.school.doa.student;

import net.school.mapper.AdminMapper;
import net.school.mapper.StudentMapper;
import net.school.model.Student;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

public class StudentDoaImpl implements StudentDoa{

    private Jdbi jdbi = Jdbi.create("jdbc:postgresql://localhost:5432/schoolsdb", "thaabit", "1234");

    public StudentDoaImpl() {
/*        try {
            jdbi = getJdbiDatabaseConnection("jdbc:postgresql://localhost:5432/banking, thaabit, 1234");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
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
    public boolean updateStudent(double amount, int id){
        return jdbi.withExtension(StudentDoa.class, doa-> doa.updateStudent( amount,  id));
    }

    @Override
    public boolean updateStudent(Student student){
        return jdbi.withExtension(StudentDoa.class, doa-> doa.updateStudent(student));
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

    static Jdbi getJdbiDatabaseConnection(String defualtJdbcUrl) throws URISyntaxException, SQLException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        String database_url = processBuilder.environment().get("DATABASE_URL");
        if (database_url != null) {

            URI uri = new URI(database_url);
            String[] hostParts = uri.getUserInfo().split(":");
            String username = hostParts[0];
            String password = hostParts[1];
            String host = uri.getHost();

            int port = uri.getPort();

            String path = uri.getPath();
            String url = String.format("jdbc:postgresql://%s:%s%s", host, port, path);

            return Jdbi.create(url, username, password);
        }

        return Jdbi.create(defualtJdbcUrl);

    }
}

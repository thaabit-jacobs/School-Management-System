package net.school.doa.faculty;

import net.school.mapper.AdminMapper;
import net.school.mapper.FacultyMapper;
import net.school.mapper.StudentMapper;
import net.school.model.Faculty;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

public class FacultyDoaImpl implements FacultyDoa{

    private Jdbi jdbi = Jdbi.create("jdbc:postgresql://localhost:5432/schoolsdb", "thaabit", "1234");

    public FacultyDoaImpl() {
        try {
            jdbi = getJdbiDatabaseConnection("jdbc:postgresql://localhost:5432/banking, thaabit, 1234");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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

    @Override
    public boolean updateFaculty(Faculty faculty) {
        return jdbi.withExtension(FacultyDoa.class, doa -> doa.updateFaculty(faculty));
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

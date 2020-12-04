package net.school.doa.lesson;

import net.school.mapper.LessonMapper;
import net.school.mapper.LessonNameMapper;
import net.school.mapper.StudentMapper;
import net.school.model.lessons.Lesson;
import net.school.model.lessons.LessonName;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

public class LessonDoaImpl implements LessonDoa{

    private Jdbi jdbi = Jdbi.create("jdbc:postgresql://localhost:5432/schoolsdb", "thaabit", "1234");

    public LessonDoaImpl() {
        try {
            jdbi = getJdbiDatabaseConnection("jdbc:postgresql://localhost:5432/banking, thaabit, 1234");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
        jdbi.registerRowMapper(new LessonMapper());
        jdbi.registerRowMapper(new LessonNameMapper());
        jdbi.registerArrayType(Integer.class, "integer");
        jdbi.registerArrayType(String.class, "varchar");
    }

    public LessonDoaImpl(Jdbi jdbi){
        this.jdbi = jdbi;
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
        jdbi.registerRowMapper(new LessonMapper());
        jdbi.registerRowMapper(new LessonNameMapper());
        jdbi.registerArrayType(Integer.class, "integer");
        jdbi.registerArrayType(String.class, "varchar");
    }

    @Override
    public boolean insertLesson(Lesson lesson) {
        return jdbi.withExtension(LessonDoa.class, doa-> doa.insertLesson(lesson));
    }

    @Override
    public List<Lesson> selectAllLessons() {
        return jdbi.withExtension(LessonDoa.class, doa -> doa.selectAllLessons());
    }

    @Override
    public List<LessonName> selectAllLessonsTeacherName() {
        return jdbi.withExtension(LessonDoa.class, doa -> doa.selectAllLessonsTeacherName());
    }

    @Override
    public Lesson selectLesson(int id) {
        return jdbi.withExtension(LessonDoa.class, doa -> doa.selectLesson(id));
    }

    @Override
    public boolean updateLesson(Lesson lesson) {
        return jdbi.withExtension(LessonDoa.class, doa -> doa.updateLesson(lesson));
    }

    @Override
    public boolean deleteAllLessons() {
        return jdbi.withExtension(LessonDoa.class, doa -> doa.deleteAllLessons());
    }

    @Override
    public boolean deleteLesson(int id) {
        return jdbi.withExtension(LessonDoa.class, doa -> doa.deleteLesson(id));
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

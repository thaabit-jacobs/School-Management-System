package net.school.doa.lesson;

import net.school.mapper.LessonMapper;
import net.school.mapper.LessonNameMapper;
import net.school.mapper.NotesMapper;
import net.school.model.lessons.Notes;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

public class NotesDoaImpl implements NotesDoa{

    private Jdbi jdbi = Jdbi.create("jdbc:postgresql://localhost:5432/schoolsdb", "thaabit", "1234");

    public NotesDoaImpl() {
        try {
            jdbi = getJdbiDatabaseConnection("jdbc:postgresql://localhost:5432/banking, thaabit, 1234");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
        jdbi.registerRowMapper(new NotesMapper());
        jdbi.registerRowMapper(new NotesMapper());
        jdbi.registerArrayType(Integer.class, "integer");
        jdbi.registerArrayType(String.class, "varchar");
    }

    public NotesDoaImpl(Jdbi jdbi){
        this.jdbi = jdbi;
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
        jdbi.registerRowMapper(new NotesMapper());
        jdbi.registerRowMapper(new NotesMapper());
        jdbi.registerArrayType(Integer.class, "integer");
        jdbi.registerArrayType(String.class, "varchar");
    }


    @Override
    public boolean insertNote(Notes note) {
        return jdbi.withExtension(NotesDoa.class, doa -> doa.insertNote(note));
    }

    @Override
    public List<Notes> selectAllNotes() {
        return jdbi.withExtension(NotesDoa.class, doa -> doa.selectAllNotes());
    }

    @Override
    public Notes selectNote(int id) {
        return jdbi.withExtension(NotesDoa.class, doa -> doa.selectNote(id));
    }

    @Override
    public boolean deleteNote(int id) {
        return jdbi.withExtension(NotesDoa.class, doa -> doa.deleteNote(id));
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

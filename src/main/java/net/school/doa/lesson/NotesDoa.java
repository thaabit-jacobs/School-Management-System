package net.school.doa.lesson;

import net.school.model.lessons.Notes;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface NotesDoa {

    @SqlUpdate("insert into notes (id, lesson_id, description) values(:id, :lessonId, :description)")
    boolean insertNote(@BindBean Notes note);

    @SqlQuery("select * from notes")
    List<Notes> selectAllNotes();

    @SqlQuery("select * from notes where id=:id")
    Notes selectNote(@Bind("id") int id);

    @SqlUpdate("delete from notes where id=:id")
    boolean deleteNote(@Bind("id") int id);
}

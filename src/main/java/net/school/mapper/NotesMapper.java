package net.school.mapper;

import net.school.model.lessons.Notes;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NotesMapper implements RowMapper<Notes> {
    @Override
    public Notes map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Notes(rs.getInt("id"), rs.getInt("lesson_id"), rs.getString("description"));
    }
}

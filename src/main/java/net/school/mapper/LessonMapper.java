package net.school.mapper;

import net.school.model.lessons.Lesson;
import net.school.types.Subject;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;

public class LessonMapper implements RowMapper<Lesson> {
    @Override
    public Lesson map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Lesson(rs.getInt("id"), rs.getInt("teacher_id"), Subject.valueOf(rs.getString("subject")), new Timestamp(rs.getDate("lesson_time").getTime()).toLocalDateTime(), Arrays.asList((Integer[])rs.getArray("students_attending").getArray()));
    }
}

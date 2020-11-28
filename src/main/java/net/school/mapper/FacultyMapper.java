package net.school.mapper;

import net.school.model.Faculty;
import net.school.model.Student;
import net.school.types.Role;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;

public class FacultyMapper implements RowMapper<Faculty> {

    @Override
    public Faculty map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Faculty(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"),
                rs.getString("email"), rs.getString("mobile_no"), rs.getDate("dob").toLocalDate(), Role.valueOf(rs.getString("role")),
                new Timestamp(rs.getDate("date_created").getTime()).toLocalDateTime(),
                Arrays.asList((String[])rs.getArray("registered_subjects").getArray()), rs.getBoolean("is_teaching_lesson")
        );
    }
}

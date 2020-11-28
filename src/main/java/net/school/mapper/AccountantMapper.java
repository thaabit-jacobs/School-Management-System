package net.school.mapper;

import net.school.model.Accountant;
import net.school.model.Admin;
import net.school.types.Role;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class AccountantMapper implements RowMapper<Accountant> {

    @Override
    public Accountant map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Accountant(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"),
                rs.getString("email"), rs.getString("mobile_no"), rs.getDate("dob").toLocalDate(), Role.valueOf(rs.getString("role")),
                new Timestamp(rs.getDate("date_created").getTime()).toLocalDateTime());
    }

}

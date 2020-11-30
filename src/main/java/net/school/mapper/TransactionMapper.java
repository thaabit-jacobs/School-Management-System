package net.school.mapper;

import net.school.model.Transaction;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TransactionMapper implements RowMapper<Transaction> {
    @Override
    public Transaction map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Transaction(rs.getInt("id"), rs.getString("transac_type"), rs.getDouble("transac_amount"),
                rs.getString("transac_status"), new Timestamp(rs.getDate("date_created").getTime()).toLocalDateTime(), rs.getInt("student_id"));
    }
}

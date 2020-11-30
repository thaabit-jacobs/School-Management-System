package net.school.doa.transacs;

import net.school.model.Student;
import net.school.model.Transaction;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface TransactionDoa {
    @SqlUpdate("insert into transacs (id, transac_type, transac_amount, transac_status, date_created, student_id ) " +
            "values(:id, :transactionType, :transactionAmount, :transactionStatus, :dateCreated, :studentId)")
    boolean insertTransaction(@BindBean Transaction transaction);

    @SqlQuery("select * from transacs")
    List<Transaction> selectAllTransactions();

    @SqlQuery("select * from transacs where id=:id")
    Transaction selectTransaction(@Bind("id") int id);

    @SqlQuery("select * from transacs where student_id=:id")
    List<Transaction> selectAllTransactionsForStudent(@Bind("id") int id);

    @SqlUpdate("delete from transacs where student_id=:studentId")
    boolean deleteAllTransactionsForStudent(@Bind("studentId") int studentId);
}

package net.school.doa.transacs;

import net.school.mapper.StudentMapper;
import net.school.mapper.TransactionMapper;
import net.school.model.Transaction;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.List;

public class TransactionDoaImpl implements TransactionDoa{
    private Jdbi jdbi = Jdbi.create("jdbc:postgresql://localhost:5432/schoolsdb", "thaabit", "1234");

    public TransactionDoaImpl() {
/*        try {
            jdbi = getJdbiDatabaseConnection("jdbc:postgresql://localhost:5432/banking, thaabit, 1234");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
        jdbi.registerRowMapper(new TransactionMapper());
        jdbi.registerArrayType(Integer.class, "integer");
        jdbi.registerArrayType(String.class, "varchar");
    }

    public TransactionDoaImpl(Jdbi jdbi){
        this.jdbi = jdbi;
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
        jdbi.registerRowMapper(new TransactionMapper());
        jdbi.registerArrayType(Integer.class, "integer");
        jdbi.registerArrayType(String.class, "varchar");
    }

    @Override
    public boolean insertTransaction(Transaction transaction) {
        return jdbi.withExtension(TransactionDoa.class, doa -> doa.insertTransaction(transaction));
    }

    @Override
    public List<Transaction> selectAllTransactions() {
        return jdbi.withExtension(TransactionDoa.class, doa -> doa.selectAllTransactions());
    }

    @Override
    public Transaction selectTransaction(int id) {
         return jdbi.withExtension(TransactionDoa.class, doa -> doa.selectTransaction(id));
    }

    @Override
    public List<Transaction> selectAllTransactionsForStudent(int id) {
        return jdbi.withExtension(TransactionDoa.class, doa -> doa.selectAllTransactionsForStudent(id));
    }

    @Override
    public boolean deleteAllTransactionsForStudent(int studentId) {
        return jdbi.withExtension(TransactionDoa.class, doa -> doa.deleteAllTransactionsForStudent(studentId));
    }
}

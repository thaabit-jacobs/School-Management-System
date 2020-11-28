package net.school.doa.accountant;

import net.school.mapper.AccountantMapper;
import net.school.mapper.AdminMapper;
import net.school.model.Accountant;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.List;

public class AccountantDoaImpl implements AccountantDoa{

    private Jdbi jdbi = Jdbi.create("jdbc:postgresql://localhost:5432/schoolsdb", "thaabit", "1234");

    public AccountantDoaImpl() {
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
        jdbi.registerRowMapper(new AccountantMapper());
    }

    public AccountantDoaImpl(Jdbi jdbi){
        this.jdbi = jdbi;
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
        jdbi.registerRowMapper(new AccountantMapper());
    }

    @Override
    public boolean insertAccountant(Accountant accountant){
        return jdbi.withExtension(AccountantDoa.class, doa -> doa.insertAccountant(accountant));
    }

    @Override
    public Accountant selectAccountant(int id){
        return jdbi.withExtension(AccountantDoa.class, doa -> doa.selectAccountant(id));
    }

    @Override
    public List<Accountant> selectAllAccountants(){
        return jdbi.withExtension(AccountantDoa.class, doa -> doa.selectAllAccountants());
    }

    @Override
    public boolean deleteAccountant(int id){
        return jdbi.withExtension(AccountantDoa.class, doa -> doa.deleteAccountant(id));
    }
}

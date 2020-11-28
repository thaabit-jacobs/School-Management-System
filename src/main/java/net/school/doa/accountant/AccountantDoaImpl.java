package net.school.doa.accountant;

import net.school.mapper.AccountantMapper;
import net.school.mapper.AdminMapper;
import net.school.model.Accountant;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

public class AccountantDoaImpl implements AccountantDoa{

    private Jdbi jdbi = Jdbi.create("jdbc:postgresql://localhost:5432/schoolsdb", "thaabit", "1234");

    public AccountantDoaImpl() {
        try {
            jdbi = getJdbiDatabaseConnection("jdbc:postgresql://localhost:5432/banking, thaabit, 1234");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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

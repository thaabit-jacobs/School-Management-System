package net.school.doa.admin;

import net.school.mapper.AdminMapper;
import net.school.model.Admin;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

public class AdminDoaImpl implements AdminDoa {
    private Jdbi jdbi = Jdbi.create("jdbc:postgresql://localhost:5432/schoolsdb", "thaabit", "1234");

    public AdminDoaImpl() {
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
        jdbi.registerRowMapper(new AdminMapper());
    }

    public AdminDoaImpl(Jdbi jdbi){
        this.jdbi = jdbi;
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
        jdbi.registerRowMapper(new AdminMapper());
    }

    @Override
    public boolean insertAdmin(Admin admin){
        return jdbi.withExtension(AdminDoa.class, doa -> doa.insertAdmin(admin));
    }

    @Override
    public Admin selectAdmin(int id){
        return jdbi.withExtension(AdminDoa.class, doa -> doa.selectAdmin(id));
    }

    @Override
    public List<Admin> selectAllAdmins(){
        return jdbi.withExtension(AdminDoa.class, doa -> doa.selectAllAdmins());
    }

    @Override
    public boolean updateAdmin(Admin admin){
        return jdbi.withExtension(AdminDoa.class, doa -> doa.updateAdmin(admin));
    }

    @Override
    public boolean deleteAdmin(int id){
        return jdbi.withExtension(AdminDoa.class, doa -> doa.deleteAdmin(id));
    }

    @Override
    public boolean deleteAllAdmins(){
        return jdbi.withExtension(AdminDoa.class, doa -> doa.deleteAllAdmins());
    }
}

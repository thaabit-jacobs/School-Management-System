package net.school.doa.admin;

import net.school.model.Admin;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface AdminDoa {

    @SqlUpdate("insert into admins (id, first_name, last_name, email, mobile_no, dob, role, date_created) " +
            "values(:id, :firstName, :lastName, :email, :mobileNo, :dob, :role, :dateCreated)")
     boolean insertAdmin(@BindBean Admin admin);

    @SqlQuery("select * from admins where id=:id")
    Admin selectAdmin(@Bind("id") int id);

    @SqlQuery("select * from admins")
    List<Admin> selectAllAdmins();

    @SqlUpdate("update admins set first_name=:firstName, last_name=:lastName, email=:email, mobile_no=:mobileNo, dob=:dob, role=:role where id=:id")
    boolean updateAdmin(@BindBean Admin admin);

    @SqlUpdate("delete from admins where id=:id")
    boolean deleteAdmin(@Bind("id") int id);

    @SqlUpdate("delete from admins")
    boolean deleteAllAdmins();
}

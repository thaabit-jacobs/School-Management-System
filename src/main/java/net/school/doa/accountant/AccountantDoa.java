package net.school.doa.accountant;

import net.school.model.Accountant;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface AccountantDoa {
    @SqlUpdate("insert into acountants (id, first_name, last_name, email, mobile_no, dob, role, date_created) values(" +
            ":id, :firstName, :lastName, :email, :mobileNo, :dob, :role, :dateCreated)")
    boolean insertAccountant(@BindBean Accountant accountant);

    @SqlQuery("select * from acountants where id=:id")
    Accountant selectAccountant(@Bind("id") int id);

    @SqlQuery("select * from acountants")
    List<Accountant> selectAllAccountants();

    @SqlUpdate("delete from acountants where id=:id")
    boolean deleteAccountant(@Bind("id") int id);
}

package doa.admin;

import net.school.doa.admin.AdminDoaImpl;
import net.school.model.Admin;
import net.school.types.Role;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class AdminDoaImplTest {

    private final AdminDoaImpl adminDoa = new AdminDoaImpl(Jdbi.create("jdbc:postgresql://localhost:5432/schoolsdbtest", "thaabit", "1234"));
    private final Admin admin = new Admin(1, "Thaabit", "Jacobs", "jacobs@gmail.com","07653698710",  LocalDate.now(), Role.ADMIN, LocalDateTime.now());

    @Test
    void returnTrueAndAddUserToDb(){
        adminDoa.deleteAllAdmins();
        assertTrue(adminDoa.insertAdmin(admin));
    }

    @Test
    void shouldReturnAdminForValidId(){
        adminDoa.deleteAllAdmins();
        adminDoa.insertAdmin(admin);
        assertEquals(1, adminDoa.selectAdmin(1).getId());
    }

    @Test
    void shouldReturnAListOfValidUsers(){
        adminDoa.deleteAllAdmins();
        assertEquals(0, adminDoa.selectAllAdmins().size());
    }
}

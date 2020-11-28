package net.school.model;

import net.school.types.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Admin  extends Person{

    public Admin(){
        super();
    }

    public Admin(int id, String firstName, String lastName, String email, String mobileNo, LocalDate dob, Role role, LocalDateTime dateCreated) {
        super(id, firstName, lastName, email, mobileNo, dob, role, dateCreated);
    }
}

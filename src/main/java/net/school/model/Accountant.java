package net.school.model;

import net.school.types.Role;
import net.school.types.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Accountant extends Person{

    public Accountant(){

    }

    public Accountant(int id, String firstName, String lastName, String email, String mobileNo, LocalDate dob, Role role, LocalDateTime dateCreated) {
        super(id, firstName, lastName, email, mobileNo, dob, role, dateCreated);
    }

    public Transaction payStudentFees(Student student, double amount){
        if(amount <= 0 || student.getFeesBalance() == 0){
            return new Transaction().setTransactionType("PAYMENT")
                    .setTransactionAmount(amount)
                    .setTransactionStatus(Status.ERROR.toString())
                    .setDateCreated(LocalDateTime.now())
                    .setStudentId(student.getId())
                    .build();
        }

        double newFeesBalance = student.getFeesBalance() - amount;
        student.setFeesBalance(newFeesBalance);

        return new Transaction().setTransactionType("PAYMENT")
                .setTransactionAmount(amount)
                .setTransactionStatus(Status.SUCCESS.toString())
                .setDateCreated(LocalDateTime.now())
                .setStudentId(student.getId())
                .build();
    }
}

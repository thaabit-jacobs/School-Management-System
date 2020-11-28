package net.school.model;

import net.school.types.Status;

import java.time.LocalDateTime;

public class Accountant extends Person{

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

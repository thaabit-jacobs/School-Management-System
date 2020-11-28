package model.actors.test;

import net.school.model.Accountant;
import net.school.model.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountantTest {

    private final Accountant accountant = new Accountant();
    private final Student student = new Student();

    @Test
    void shouldReturnTrueAndAmendAccountBalance(){
        student.setFeesBalance(500);
        assertEquals(100, accountant.payStudentFees(student, 100).getTransactionAmount());
        assertEquals(400, student.getFeesBalance());
    }

    @Test
    void shouldReturnFalsendShouldNotAmendAccountBalance(){
        student.setFeesBalance(500);
        assertEquals(-100, accountant.payStudentFees(student, -100).getTransactionAmount());
        assertEquals(500, student.getFeesBalance());
    }
}

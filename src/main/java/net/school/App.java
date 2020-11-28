package net.school;

import net.school.controller.AdminController;
import net.school.controller.StudentController;
import net.school.model.Admin;
import net.school.model.Faculty;
import net.school.model.Student;
import net.school.service.AdminService;
import net.school.service.FacultyService;
import net.school.service.StudentService;
import net.school.types.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class App {

    public static void main(String[] args)
    {
       //AdminService.getInstance().insertAdmin(new Admin(1, "Thaabit", "Jacobs", "jacobs@gmail.com","07653698710",  LocalDate.now(), Role.ADMIN, LocalDateTime.now()));
       // AdminService.getInstance().insertAdmin(new Admin(3, "Bob", "Smith", "bob@gmail.com","07653698710",  LocalDate.now(), Role.STUDENT, LocalDateTime.now()));
/*     StudentService.getInstance().insertStudent(new Student(2, "Bob", "Smith", "bob@gmail.com","07653698710",  LocalDate.now(), Role.STUDENT, LocalDateTime.now(),
                new ArrayList<String>(), 0.0, false, new ArrayList<Integer>()));*/

        //FacultyService.getInstance().insertFaculty(new Faculty(1, "James", "Smith", "james@gmail.com", "07653698710", LocalDate.now(), Role.FACULTY, LocalDateTime.now(),  new ArrayList<String>(), false));
        //FacultyService.getInstance().insertFaculty(new Faculty(2, "Bob", "Jones", "jones@gmail.com", "07653698710", LocalDate.now(), Role.FACULTY, LocalDateTime.now(),  new ArrayList<String>(), false));

        new AdminController(AdminService.getInstance(), StudentService.getInstance(), FacultyService.getInstance());
        new StudentController(StudentService.getInstance());
    }
}

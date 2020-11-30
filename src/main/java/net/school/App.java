package net.school;

import net.school.controller.AdminController;
import net.school.controller.StudentController;
import net.school.model.Accountant;
import net.school.model.Admin;
import net.school.model.Faculty;
import net.school.model.Student;
import net.school.model.lessons.Lesson;
import net.school.service.*;
import net.school.types.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

public class App {

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args)
    {

        port(getHerokuAssignedPort());
       //AdminService.getInstance().insertAdmin(new Admin(1, "Thaabit", "Jacobs", "jacobs@gmail.com","07653698710",  LocalDate.now(), Role.ADMIN, LocalDateTime.now()));
       // AdminService.getInstance().insertAdmin(new Admin(3, "Bob", "Smith", "bob@gmail.com","07653698710",  LocalDate.now(), Role.STUDENT, LocalDateTime.now()));
/*     StudentService.getInstance().insertStudent(new Student(2, "Bob", "Smith", "bob@gmail.com","07653698710",  LocalDate.now(), Role.STUDENT, LocalDateTime.now(),
                new ArrayList<String>(), 0.0, false, new ArrayList<Integer>()));*/

        //FacultyService.getInstance().insertFaculty(new Faculty(1, "James", "Smith", "james@gmail.com", "07653698710", LocalDate.now(), Role.FACULTY, LocalDateTime.now(),  new ArrayList<String>(), false));
        //FacultyService.getInstance().insertFaculty(new Faculty(2, "Bob", "Jones", "jones@gmail.com", "07653698710", LocalDate.now(), Role.FACULTY, LocalDateTime.now(),  new ArrayList<String>(), false));
/*        Lesson lea = LessonService.getInstance().selectLesson(3);
        List<Integer> lll = new ArrayList<>(lea.getStudentAttendingLesson());
        lll.add(0);
        lll.add(0);

        lea.setStudentAttendingLesson(new ArrayList<Integer>());

        LessonService.getInstance().updateLesson(lea);*/

        new AdminController(AdminService.getInstance(), StudentService.getInstance(), FacultyService.getInstance(), AccountantService.getInstance());
        //new StudentController(StudentService.getInstance());

       // AccountantService.getInstance().insertAccountant(new Accountant(1, "Bill", "Gates", "gates@micro.com", "0000000000", LocalDate.now(), Role.ACCOUNTANT, LocalDateTime.now()));
        //AccountantService.getInstance().insertAccountant(new Accountant(2, "Sarah", "Smith", "sarah@gmail.com", "0000000000", LocalDate.now(), Role.ACCOUNTANT, LocalDateTime.now()));

    }
}

package net.school.controller;

import com.google.gson.Gson;
import net.school.model.Admin;
import net.school.model.Faculty;
import net.school.model.Student;
import  net.school.service.AdminService;
import net.school.service.FacultyService;
import net.school.service.StudentService;
import net.school.types.Role;
import net.school.util.ResponseError;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.school.util.JsonUtil.*;

import static  spark.Spark.*;

public class AdminController {
    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }


    public AdminController(final AdminService adminService, final StudentService studentService, final FacultyService facultyService){

        //ADMIN VIEW
        get("/admin/:id", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            int adminId = Integer.parseInt(request.params("id"));

            Admin admin = adminService.selectAdmin(adminId);

            if(admin != null){
                int adminCount = adminService.selectAllAdmins().size();
                int studentCount = studentService.selectAllStudents().size();
                int facultiesCount = facultyService.selectAllFaculties().size();

                model.put("adminName", admin.getFirstName() + " " + admin.getLastName());
                model.put("adminCount", adminCount);
                model.put("studentCount", studentCount);
                model.put("facultiesCount", facultiesCount);
                model.put("adminId", adminId);

                return render(model, "index.hbs");
            }

            response.status(400);

            return new Gson().toJson("User not found for id " + adminId);
        }));

        //ADMIN VIEW OF ALL ADMINS

        get("/admin/:id/admins", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            int adminId = Integer.parseInt(request.params("id"));

            Admin admin = adminService.selectAdmin(adminId);

            if(admin != null){
                List<Admin> adminsList = adminService.selectAllAdmins();
                adminsList.removeIf(p -> p.getId() == adminId);

                model.put("adminsList", adminsList);
                model.put("currentAdminId", adminId);
                return render(model, "admins.hbs");
            }

            response.status(400);

            return new Gson().toJson("User not found for id " + adminId);
        }));

        get("/admin/:currentAdminId/admins/delete/:id", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int adminId = Integer.parseInt(request.params("id"));
            int currentAdminId = Integer.parseInt(request.params("currentAdminId"));

            System.out.println(adminId);

            adminService.deleteAdmin(adminId);

            List<Admin> adminsList = adminService.selectAllAdmins();
            adminsList.removeIf(p -> p.getId() == currentAdminId);

            model.put("adminsList", adminsList);
            model.put("currentAdminId", currentAdminId);

            return render(model, "admins.hbs");
        }));

        get("/admin/:id/admins/add", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int adminId = Integer.parseInt(request.params("id"));

            model.put("adminId", adminId);

            return render(model, "AddAdminForm.hbs");
        }));

        post("/admin/:id/admins/add", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int adminId = Integer.parseInt(request.params("id"));

            String firstName = request.queryParams("firstName");
            String lastName = request.queryParams("lastName");
            String email = request.queryParams("email");
            String mobileNo = request.queryParams("mobileNo");
            LocalDate dob = LocalDate.parse(request.queryParams("dob"));

            adminService.insertAdmin(new Admin(adminService.getUniquerId(), firstName, lastName, email, mobileNo, dob, Role.ADMIN, LocalDateTime.now()));

            List<Admin> adminsList = adminService.selectAllAdmins();
            adminsList.removeIf(p -> p.getId() == adminId);

            model.put("adminsList", adminsList);
            model.put("adminId", adminId);

            response.redirect("/admin/" + adminId + "/admins");
            return "";
        }));

        //ADMIN STUDENT PANEL
        get("/admin/:id/students", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            int adminId = Integer.parseInt(request.params("id"));

            Admin admin = adminService.selectAdmin(adminId);

            if(admin != null){
                List<Student> studentList = studentService.selectAllStudents();

                model.put("currentAdminId", adminId);
                model.put("studentList", studentList);

                return render(model, "students.hbs");
            }

            response.status(400);

            return new Gson().toJson("User not found for id " + adminId);
        }));

        get("/admin/:id/students/add", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            int adminId = Integer.parseInt(request.params("id"));

            model.put("adminId", adminId);

            return render(model, "addStudentForm.hbs");
        }));

        post("/admin/:id/students/add", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            int adminId = Integer.parseInt(request.params("id"));

            String firstName = request.queryParams("firstName");
            String lastName = request.queryParams("lastName");
            String email = request.queryParams("email");
            String mobileNo = request.queryParams("mobileNo");
            LocalDate dob = LocalDate.parse(request.queryParams("dob"));

            studentService.insertStudent(new Student(studentService.getUniqueStudentId(), firstName, lastName, email, mobileNo, dob, Role.STUDENT, LocalDateTime.now(),
                    new ArrayList<String>(), 0, false, new ArrayList<Integer>()));

            List<Student> studentList = studentService.selectAllStudents();

            model.put("adminsList", studentList);
            model.put("adminId", adminId);

            response.redirect("/admin/" + adminId + "/students");
            return "";
        }));

        get("/admin/:currentAdminId/students/delete/:id", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int studentId = Integer.parseInt(request.params("id"));
            int currentAdminId = Integer.parseInt(request.params("currentAdminId"));

            studentService.deleteStudent(studentId);

            List<Student> studentList = studentService.selectAllStudents();

            model.put("studentList", studentList);
            model.put("currentAdminId", currentAdminId);

            return render(model, "students.hbs");
        }));

        ///////////////////////////
        get("/admin/:id/faculties", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            int adminId = Integer.parseInt(request.params("id"));

            Admin admin = adminService.selectAdmin(adminId);

            if(admin != null){
                List<Faculty> facultyList = facultyService.selectAllFaculties();

                model.put("currentAdminId", adminId);
                model.put("facultyList", facultyList);

                return render(model, "faculties.hbs");
            }

            response.status(400);

            return new Gson().toJson("User not found for id " + adminId);
        }));

        get("/admin/:id/faculties/add", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            int adminId = Integer.parseInt(request.params("id"));

            model.put("adminId", adminId);

            return render(model, "addFacultyForm.hbs");
        }));

        post("/admin/:id/faculties/add", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            int adminId = Integer.parseInt(request.params("id"));

            String firstName = request.queryParams("firstName");
            String lastName = request.queryParams("lastName");
            String email = request.queryParams("email");
            String mobileNo = request.queryParams("mobileNo");
            LocalDate dob = LocalDate.parse(request.queryParams("dob"));

            facultyService.insertFaculty(new Faculty(facultyService.getUniqueId(), firstName, lastName, email, mobileNo, dob, Role.FACULTY, LocalDateTime.now(), new ArrayList<String>(), false));

            List<Faculty> facultyList = facultyService.selectAllFaculties();

            model.put("facultyList", facultyList);
            model.put("adminId", adminId);

            response.redirect("/admin/" + adminId + "/faculties");
            return "";
        }));

        get("/admin/:currentAdminId/faculties/delete/:id", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int facultyId = Integer.parseInt(request.params("id"));
            int currentAdminId = Integer.parseInt(request.params("currentAdminId"));

            facultyService.deleteFaculty(facultyId);

            List<Faculty> facultyList = facultyService.selectAllFaculties();

            model.put("facultyList", facultyList);
            model.put("currentAdminId", currentAdminId);

            return render(model, "faculties.hbs");
        }));

    }
}

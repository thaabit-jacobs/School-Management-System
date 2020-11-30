package net.school.controller;

import com.google.gson.Gson;
import net.school.model.*;
import net.school.model.lessons.Lesson;
import net.school.model.lessons.LessonName;
import net.school.service.*;
import net.school.types.Role;
import net.school.types.Subject;
import net.school.util.ResponseError;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static net.school.util.JsonUtil.*;

import static  spark.Spark.*;

public class AdminController {
    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }


    public AdminController(final AdminService adminService, final StudentService studentService, final FacultyService facultyService, final AccountantService accountantService){

        //ADMIN

        //ADMIN LANDING
        get("/admin/:id", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            int adminId = Integer.parseInt(request.params("id"));

            Admin admin = adminService.selectAdmin(adminId);

            if(admin != null){
                int adminCount = adminService.selectAllAdmins().size();
                int studentCount = studentService.selectAllStudents().size();
                int facultiesCount = facultyService.selectAllFaculties().size();
                int accountantsCount = accountantService.selectAllAccountants().size();

                model.put("adminName", admin.getFirstName() + " " + admin.getLastName());
                model.put("adminCount", adminCount);
                model.put("studentCount", studentCount);
                model.put("facultiesCount", facultiesCount);
                model.put("accountantsCount", accountantsCount);
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

        //DELETE ADMIN
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

        //ADD ADMIN
        get("/admin/:id/admins/add", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int adminId = Integer.parseInt(request.params("id"));

            model.put("adminId", adminId);

            return render(model, "AddAdminForm.hbs");
        }));

        //ADD ADMIN
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

        //ADMIN STUDENT VIEW
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

        //ADNMIN ADD STUDENT

        get("/admin/:id/students/add", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            int adminId = Integer.parseInt(request.params("id"));

            model.put("adminId", adminId);

            return render(model, "addStudentForm.hbs");
        }));

        //ADMIN ADD STUDENT
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

        //DELETE STUDENT
        get("/admin/:currentAdminId/students/delete/:id", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int studentId = Integer.parseInt(request.params("id"));
            int currentAdminId = Integer.parseInt(request.params("currentAdminId"));

            TransactionService.getInstance().deleteAllTransactionsForStudent(studentId);
            studentService.deleteStudent(studentId);

            List<Student> studentList = studentService.selectAllStudents();

            model.put("studentList", studentList);
            model.put("currentAdminId", currentAdminId);

            return render(model, "students.hbs");
        }));

        get("/admin/:id/students/fees/:studentId", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            int adminId = Integer.parseInt(request.params("id"));
            int studentId = Integer.parseInt(request.params("studentId"));

            List<Transaction> transactionsList = TransactionService.getInstance().selectAllTransactionsForStudents(studentId);
            Student student  = studentService.selectStudent(studentId);

            double feesBalance = student.getFeesBalance();

            model.put("adminId", adminId);
            model.put("feesBalance", "R" + feesBalance);
            model.put("transactionsList", transactionsList);
            model.put("studentId", studentId);

            return render(model, "studentFees.hbs");
        }));

        post("/admin/:id/students/fees/:studentId", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            int adminId = Integer.parseInt(request.params("id"));
            int studentId = Integer.parseInt(request.params("studentId"));
            double amount = Double.parseDouble(request.queryParams("amount"));

            Student student = studentService.selectStudent(studentId);

            Transaction transaction = Transaction.performTransaction(student, amount);
            TransactionService.getInstance().insertTransaction(transaction);

            studentService.updateStudent(student.getFeesBalance(), studentId);

            response.redirect("/admin/" + adminId + "/students/fees/" + studentId);

            return "";
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

        get("/admin/:id/faculties/add/lesson", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            int adminId = Integer.parseInt(request.params("id"));

            List<Faculty> faculties = facultyService.selectAllFaculties();
            List<Subject> subjectsList = Arrays.asList(Subject.values());
            List<String> facultiesList = new ArrayList<>();

            faculties.forEach(a -> facultiesList.add(a.getFirstName() + " " + a.getLastName() + " " + a.getId()));

            model.put("adminId", adminId);
            model.put("facultiesList", facultiesList);
            model.put("subjectsList", subjectsList);

            return render(model, "addLessonForm.hbs");
        }));

        post("/admin/:id/faculties/add/lesson", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            int adminId = Integer.parseInt(request.params("id"));
            System.out.println(adminId);

            String selectedTeacher = request.queryParams("selectedTeacher");
            int selectedTeacherId = Integer.parseInt(Character.valueOf(selectedTeacher.charAt(selectedTeacher.length() -1)).toString());

            String selectedSubject = request.queryParams("selectedSubject");
            Subject subject = Subject.valueOf(selectedSubject);

            String selectDateTime = request.queryParams("selectedTime");
            LocalDateTime dateTime = LocalDateTime.parse(selectDateTime);

            Lesson lesson = new Lesson(LessonService.getInstance().getUniqueStudentId(), selectedTeacherId, subject, dateTime, new ArrayList<Integer>());

            LessonService.getInstance().insertLesson(lesson);

            response.redirect("/admin/" + adminId + "/faculties");

            return"";
        }));

        ///////////////////////
        get("/admin/:id/accountants", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            int adminId = Integer.parseInt(request.params("id"));

            Admin admin = adminService.selectAdmin(adminId);

            if(admin != null){
                List<Accountant> accountantList = accountantService.selectAllAccountants();

                model.put("currentAdminId", adminId);
                model.put("accountantList", accountantList);

                return render(model, "accountants.hbs");
            }

            response.status(400);

            return new Gson().toJson("User not found for id " + adminId);
        }));

        get("/admin/:id/accountants/add", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            int adminId = Integer.parseInt(request.params("id"));

            model.put("adminId", adminId);

            return render(model, "addAccountantForm.hbs");
        }));

        post("/admin/:id/accountants/add", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            int adminId = Integer.parseInt(request.params("id"));

            String firstName = request.queryParams("firstName");
            String lastName = request.queryParams("lastName");
            String email = request.queryParams("email");
            String mobileNo = request.queryParams("mobileNo");
            LocalDate dob = LocalDate.parse(request.queryParams("dob"));

           accountantService.insertAccountant(new Accountant(accountantService.getUniquerId(), firstName, lastName, email, mobileNo, dob, Role.ACCOUNTANT, LocalDateTime.now()));

            List<Accountant> accountantList = accountantService.selectAllAccountants();

            model.put("accountantList", accountantList);
            model.put("adminId", adminId);

            response.redirect("/admin/" + adminId + "/accountants");
            return "";
        }));

        get("/admin/:currentAdminId/accountants/delete/:id", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int accountantId = Integer.parseInt(request.params("id"));
            int currentAdminId = Integer.parseInt(request.params("currentAdminId"));

            accountantService.deleteAccountant(accountantId);

            List<Accountant> accountantList = accountantService.selectAllAccountants();

            model.put("accountantList", accountantList);
            model.put("currentAdminId", currentAdminId);

            return render(model, "accountants.hbs");
        }));

        get("/admin/:id/accountants/fees", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            int adminId = Integer.parseInt(request.params("id"));

            List<Student> studentsList = studentService.selectAllStudents();

            model.put("adminId", adminId);
            model.put("studentsList", studentsList);

            return render(model, "AccountantView.hbs");
        }));

        get("/admin/:adminId/accountants/fees/:id", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            int adminId = Integer.parseInt(request.params("adminId"));
            int studentId = Integer.parseInt(request.params("id"));

            Student student = studentService.selectStudent(studentId);

            String studentName = student.getFirstName() + " " + student.getLastName();
            double feesBalance = student.getFeesBalance();

            List<Transaction> transactionList = TransactionService.getInstance().selectAllTransactionsForStudents(studentId);

            model.put("adminId", adminId);
            model.put("transactionsList", transactionList);
            model.put("studentName", studentName);
            model.put("feesBalance", feesBalance);

            return render(model, "transactionViewForStudent.hbs");
        }));

        //Faculty view

        get("/admin/:id/faculties/attendance", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            int adminId = Integer.parseInt(request.params("id"));

            List<LessonName> lessonsList= LessonService.getInstance().selectAllLessonsTeacherName();

            model.put("lessonsList", lessonsList);
            model.put("adminId", adminId);

            return render(model, "adminFacultiesAttendance.hbs");
        }));

        get("/admin/:adminId/faculties/attendance/lesson/:lessonId", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int adminId = Integer.parseInt(request.params("adminId"));
            int lessonId = Integer.parseInt(request.params("lessonId"));

            Lesson lesson = LessonService.getInstance().selectLesson(lessonId);
            List<Integer> listStudentIds = lesson.getStudentAttendingLesson();

            List<Student> studentsList = new ArrayList<>();

            listStudentIds.forEach(a -> {
                Student student = studentService.selectStudent(a);

                if(student != null)
                    studentsList.add(student);
            });

            int studentCount = studentsList.size();

            model.put("studentCount", studentCount);
            model.put("studentsList", studentsList);

            return render(model, "adminFacultiesLessonView.hbs");
        }));
    }
}

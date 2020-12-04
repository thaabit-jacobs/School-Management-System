package net.school.controller;

import com.google.gson.Gson;
import net.school.model.Admin;
import net.school.model.Student;
import net.school.model.Transaction;
import net.school.service.AdminService;
import net.school.service.StudentService;
import net.school.service.TransactionService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class StudentController {

        public static String render(Map<String, Object> model, String templatePath) {
            return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
        }


        public StudentController(final StudentService studentService){

            //STUDENT LANDING PAGE
            get("/student/:id", ((request, response) -> {
                Map<String, Object> model = new HashMap<>();

                int studentId = Integer.parseInt(request.params("id"));

                Student student = studentService.selectStudent(studentId);

                if(student != null){
                    double currentBalance = student.getFeesBalance();

                    model.put("id", studentId);
                    model.put("currentBalance", currentBalance);

                    return render(model, "studentLandingView.hbs");
                }

                response.status(400);

                return new Gson().toJson("User not found for id " + studentId);
            }));

            get("/student/:id/fees", ((request, response) -> {
                Map<String, Object> model = new HashMap<>();

                int studentId = Integer.parseInt(request.params("id"));

                Student student = studentService.selectStudent(studentId);

                double currentBalance = student.getFeesBalance();

                List<Transaction> transactionsList = TransactionService.getInstance().selectAllTransactionsForStudents(studentId);


                model.put("currentBalance", currentBalance);
                model.put("studentId", studentId);
                model.put("transactionsList", transactionsList);

                return render(model, "studentFeesView.hbs");
            }));
        }
}

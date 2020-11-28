package net.school.controller;

import com.google.gson.Gson;
import net.school.model.Admin;
import net.school.model.Student;
import net.school.service.AdminService;
import net.school.service.StudentService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class StudentController {

        public static String render(Map<String, Object> model, String templatePath) {
            return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
        }


        public StudentController(final StudentService studentService){
            get("/student/:id", ((request, response) -> {
                response.type("application/json");

                int studentId = Integer.parseInt(request.params("id"));

                Student student = studentService.selectStudent(studentId);

                if(student != null){
                    return new Gson().toJson(student);
                }

                response.status(400);

                return new Gson().toJson("User not found for id " + studentId);
            }));
        }
}

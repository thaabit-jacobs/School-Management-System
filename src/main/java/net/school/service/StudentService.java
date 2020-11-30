package net.school.service;

import net.school.doa.admin.AdminDoa;
import net.school.doa.admin.AdminDoaImpl;
import net.school.doa.student.StudentDoa;
import net.school.doa.student.StudentDoaImpl;
import net.school.model.Student;

import java.util.Collections;
import java.util.List;

public class StudentService {

    private final StudentDoa studentDoa = new StudentDoaImpl();
    private final static StudentService instance = new StudentService();

    private StudentService(){}

    public static synchronized StudentService getInstance(){
        return instance;
    }

    public synchronized boolean insertStudent(Student student){
        return studentDoa.insertStudent(student);
    }

    public synchronized Student selectStudent(int id){
        return studentDoa.selectStudent(id);
    }

    public synchronized List<Student> selectAllStudents(){
        return studentDoa.selectAllStudents();
    }

    public synchronized boolean deleteStudent(int id){
        return studentDoa.deleteStudent(id);
    }

    public synchronized boolean deleteAllStudent(){
        return studentDoa.deleteAllStudents();
    }

    public synchronized boolean updateStudent(double amount, int id){
        return studentDoa.updateStudent(amount, id);
    }

    public synchronized boolean updateStudent(Student student){
        return studentDoa.updateStudent(student);
    }

    public int getUniqueStudentId(){
        List<Student> students = selectAllStudents();

        Collections.sort(students);

        if(students.size()==0)
            return 1;

        int uniqueId = students.get(students.size() - 1).getId() + 1;

        return uniqueId;
    }
}

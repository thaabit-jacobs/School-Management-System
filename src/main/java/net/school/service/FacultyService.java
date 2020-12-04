package net.school.service;

import net.school.doa.faculty.FacultyDoa;
import net.school.doa.faculty.FacultyDoaImpl;
import net.school.doa.student.StudentDoa;
import net.school.doa.student.StudentDoaImpl;
import net.school.model.Faculty;
import net.school.model.Student;

import java.util.Collections;
import java.util.List;

public class FacultyService {
    private final FacultyDoa facultyDoa = new FacultyDoaImpl();
    private final static FacultyService instance = new FacultyService();

    private FacultyService(){}

    public static synchronized FacultyService getInstance(){
        return instance;
    }

    public synchronized boolean insertFaculty(Faculty faculty){
        return facultyDoa.insertFaculty(faculty);
    }

    public synchronized Faculty selectFaculty(int id){
        return facultyDoa.selectFaculty(id);
    }

    public synchronized List<Faculty> selectAllFaculties(){
        return facultyDoa.selectAllFaculties();
    }

    public synchronized boolean deleteFaculty(int id){
        return facultyDoa.deleteFaculty(id);
    }

    public synchronized boolean deleteAllFaculties(){
        return facultyDoa.deleteAllFaculties();
    }

    public synchronized boolean updateFaculty(Faculty faculty){
        return facultyDoa.updateFaculty(faculty);
    }

    public int getUniqueId(){
        List<Faculty> faculties = selectAllFaculties();

        Collections.sort(faculties);

        if(faculties.size()==0)
            return 1;

        int uniqueId = faculties.get(faculties.size() - 1).getId() + 1;

        return uniqueId;
    }
}

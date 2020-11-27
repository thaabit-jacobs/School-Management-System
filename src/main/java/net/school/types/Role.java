package net.school.types;

public enum Role {

    STUDENT{
        public String description(){
            return "Students can check his fees details paid or pending, mark sheets according to the class, attendance details and merit list of the school students.";
        }
    },

    ACCOUNTANT{
        public String description(){
            return "Accountants can perform all the activities related to the finance department. Like fees paid by any student or the remaining fees of any student.";
        }
    },

    FACULTY{
        public String description(){
            return "Faculty can check the Marks, Marks sheet details and manage student attendance.";
        }
    },

    ADMIN{
        public String description(){
            return "Admin can manage Other actors like Accountant, Students, and Faculty.";
        }
    };

    public abstract  String description();


}

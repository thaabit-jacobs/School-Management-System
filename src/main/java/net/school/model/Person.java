package net.school.model;

import net.school.types.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Person  implements Comparable<Person>{
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNo;
    private LocalDate dob;
    private Role role;
    private LocalDateTime dateCreated;

    public Person(){

    }

    public Person(int id, String firstName, String lastName, String email, String mobileNo, LocalDate dob, Role role, LocalDateTime dateCreated) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNo = mobileNo;
        this.dob = dob;
        this.role = role;
        this.dateCreated = dateCreated;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Role getRole() {
        return role;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public Person setId(int id) {
        this.id = id;
        return this;
    }

    public Person setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Person setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Person setEmail(String email) {
        this.email = email;
        return this;
    }

    public Person setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
        return this;
    }

    public Person setDob(LocalDate dob) {
        this.dob = dob;
        return this;
    }

    public Person setRole(Role role) {
        this.role = role;
        return this;
    }

    public Person setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    @Override
    public int compareTo(Person person){
        return id - person.id;
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Person)) return false;

        Person person = (Person)obj;
        return this.id == person.id;
    }

    @Override
    public int hashCode(){
        return id;
    }

    @Override
    public String toString(){
        return "\n{\n" +
                "firstname: " + firstName + "," +
                "\nfirstname: " + lastName + "," +
                "\nemail: " + email + "," +
                "\nmobile: " + mobileNo + "," +
                "\ndob: " + dob + "," +
                "\nrole: " + role +
                "\n}";
    }
}

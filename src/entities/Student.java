/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import common.StudentStatus;
import java.time.LocalDate;
import java.util.Objects;

public class Student {

    private int id;
    private String fullname;
    private boolean gender;
    private LocalDate dateOfBirth;
    private String phone;
    private String email;
    private String flink;
    private String workplace;
    private String address;
    private String description;
    private StudentStatus status;

    public Student() {
    }

    public Student(int id, String fullname, boolean gender, LocalDate dateOfBirth,
            String phone, String email, String flink, String workplace, String address,
            String description, StudentStatus status) {
        this.id = id;
        this.fullname = fullname;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.email = email;
        this.flink = flink;
        this.workplace = workplace;
        this.address = address;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFlink() {
        return flink;
    }

    public void setFlink(String flink) {
        this.flink = flink;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public void setStatus(StudentStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || !(o instanceof Student)) {
            return false;
        }
        final Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", fullname=" + fullname + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", phone=" + phone + ", email=" + email + ", flink=" + flink + ", workplace=" + workplace + ", address=" + address + ", description=" + description + ", status=" + status + '}';
    }

}
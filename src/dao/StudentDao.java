/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import entities.Student;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author NHI
 */
public interface StudentDao {
    List<Student> getAllStudents();
    
    int save(Student student);

    public Student getStudent(int studentId);

    public int update(Student student);
}

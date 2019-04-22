/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.StudentDao;
import entities.Student;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author NHI
 */
public interface StudentService {
    List<Student> getAllStudent();

    public int save(Student student);

    public Student getStudent(int id);

    public int update(Student student);
}

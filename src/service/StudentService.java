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
public class StudentService {
    private final StudentDao dao;

    public StudentService() {
        this.dao = new StudentDao();
    }
    public List<Student> getStudent(){
        final List<Student> students = dao.getStudent();
        if(!students.isEmpty()){
            return students;
        }
        return Collections.EMPTY_LIST;
    }
    
}

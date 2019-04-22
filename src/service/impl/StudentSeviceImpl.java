/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import dao.StudentDao;
import dao.impl.StudentDaoImpl;
import entities.Student;
import java.util.Collections;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import service.StudentService;

/**
 *
 * @author NHI
 */
public class StudentSeviceImpl implements StudentService{
    private final StudentDao studentDao;

    public StudentSeviceImpl() {
        this.studentDao = new StudentDaoImpl();
    };

    @Override
    public List<Student> getAllStudent() {
        final List<Student> students = studentDao.getAllStudents();
        if(!CollectionUtils.isEmpty(students)){
            return students;
        }
        return Collections.EMPTY_LIST;
    }
    @Override
    public Student getStudent(int studentId) {
        return studentDao.getStudent(studentId);
    }

    @Override
    public int save(Student student) {
        return studentDao.save(student);
    }

    @Override
    public int update(Student student) {
        return studentDao.update(student);
    }
    
}

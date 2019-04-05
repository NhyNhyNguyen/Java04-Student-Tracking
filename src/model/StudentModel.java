/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Student;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import jdk.nashorn.api.scripting.AbstractJSObject;
import service.StudentService;

/**
 *
 * @author NHI
 */
public class StudentModel extends AbstractTableModel {
    private List<Student> students;
    private final StudentService studentService;
   private String[] cols = {"STT", "Họ tên", "Ngày sinh", "Giới tính", "Số điện thoại", "Nơi công tác", "Tình trạng"};
    public StudentModel() {
        this.studentService = new StudentService();
        students = studentService.getStudent();
    }

    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public String getColumnName(int index) {
        return cols[index];
    }

    @Override
    public Class<?> getColumnClass(int cindex) {
        if (cindex == 3) {
            return Boolean.class;
        }
        return super.getColumnClass(cindex);
    }

    @Override
    public Object getValueAt(int row, int column) {
        Object object = null;
        final Student student = students.get(row);
        switch (column) {
            case 0:
                object = student.getId();
                break;
            case 1:
                object = student.getFullname();
                break;
            case 2:
                object = student.getDateOfBirth();
                break;
            case 3:
                object = student.isGender();
                break;
            case 4:
                object = student.getPhone();
                break;
            case 5:
                object = student.getWorkplace();
                break;
            case 6:
                object = student.getStatus().getValue();
                break;
        }
        return object;
    }

    
}

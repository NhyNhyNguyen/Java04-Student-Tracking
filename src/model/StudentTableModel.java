/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Student;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import service.StudentService;
import service.impl.StudentSeviceImpl;

/**
 *
 * @author NHI
 */
public class StudentTableModel extends AbstractTableModel {
    private List<Student> students;
    private final StudentService studentService;
   private String[] cols = {"STT", "Họ tên", "Ngày sinh", "Giới tính", "Số điện thoại", "Nơi công tác", "Tình trạng","Edit"};
    public StudentTableModel() {
        this.studentService = new StudentSeviceImpl();
        students = studentService.getAllStudent();
        System.err.println(students.size());
    }

    @Override
    public int getRowCount() {
        return students.size() ;
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
        if (cindex == 0) {
            return Integer.class;
        }
        if(cindex ==7) return JButton.class;
        return super.getColumnClass(cindex);
    }

    @Override
    public Object getValueAt(int row, int col) {
        Object object = null;
        final Student student = students.get(row);
        switch (col) {
            case 0:
                object = student.getId();
                break;
            case 1:
                object = student.getFullname();
                break;
            case 2:
                final LocalDate lDate = student.getDateOfBirth();
                
                object = lDate !=null ? lDate : "__/__/__";
                
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
            case 7: 
                object = new JButton();
        }
        return object;
    }

    public void firedTargeted(Student student) {
        students.add(student);
        fireTableDataChanged();
    }

    public void fireTargetEdit(int selectedRow, Student student, JTable tbStudent) {
        int rowModel = tbStudent.convertRowIndexToModel(selectedRow);
        students.set(rowModel, student);
        fireTableRowsUpdated(rowModel, rowModel);
    }

    
}

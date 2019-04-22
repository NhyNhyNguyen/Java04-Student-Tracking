/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.sub;

import com.toedter.calendar.JDateChooser;
import common.CrudEnum;
import common.StudentStatus;
import entities.Student;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.Month;
import java.util.Enumeration;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.StudentTableModel;
import service.StudentService;
import service.impl.StudentSeviceImpl;
import utils.DateUtils;
import view.search.FrStudentInfo;

/**
 * Controller for availble FrStudentAdd
 *
 * @author NHI
 */
public class StudentWindowController {

    private final JLabel lbStudentEventInfo;
    private final JTable tbStudent;
    private final StudentTableModel model;
    private final JFrame frStudentAdd;
    private final StudentService studentService;
    private final JButton btSubmit;
    private final JTextField tfStudentId;
    private final JTextField tfStudentName;
    private final JDateChooser birth;
    private final JTextField tfPhoneNumber;
    private final ButtonGroup buttonGroup;
    private final JTextField tfFlink;
    private final JTextArea taDescrition;
    private final JTextArea taAddress;
    private final JTextField tfWorkPlace;
    private final JLabel lbMessage;
    private int selectedRow;

    public StudentWindowController(JButton btSubmit, JTextField tfStudentId, JTextField tfStudentName, JDateChooser lbdateOfBirth, JTextField tfPhoneNumber, ButtonGroup buttonGroup, JTextField tfFlink, JTextArea taAddress, JTextArea taDescrition, JTextField tfWorkPlace, JLabel lbMessage, FrStudentInfo frStudentAdd, JLabel lbStudentEventInfo, StudentTableModel model, JTable tbStudent) {
        this.btSubmit = btSubmit;
        this.tfStudentId = tfStudentId;
        this.tfStudentName = tfStudentName;
        this.birth = lbdateOfBirth;
        this.tfPhoneNumber = tfPhoneNumber;
        this.buttonGroup = buttonGroup;
        this.tfFlink = tfFlink;
        this.taDescrition = taDescrition;
        this.taAddress = taAddress;
        this.tfWorkPlace = tfWorkPlace;
        this.lbMessage = lbMessage;
        studentService = new StudentSeviceImpl();
        this.frStudentAdd = frStudentAdd;
        this.tbStudent = tbStudent;
        this.model = model;
        this.lbStudentEventInfo = lbStudentEventInfo;
    }

    public void setEvent(JTable tbStudent, CrudEnum crudEnum, int selectedRow) {
        this.selectedRow = selectedRow;
        disableMessage(tfStudentName, tfPhoneNumber, birth, tfFlink);
        btSubmitActionListener(crudEnum);
    }

    private void btSubmitActionListener(CrudEnum crudEnum) {
        btSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                final Student student = getStudentInfo();
                if (!isValidate(student)) {
                    lbMessage.setVisible(true);
                } else {
                    switch (crudEnum) {
                        case ADD:
                            addNewStudent(student);
                            break;
                        case EDIT:
                            final int studentId = Integer.parseInt(tbStudent.getValueAt(selectedRow, 0).toString());
                            student.setId(studentId);
                            updateStudent(student);

                            break;
                    }

                }
            }
        });
    }

    private void addNewStudent(Student student) {
        int lastStudentId = studentService.save(student);
        if (lastStudentId > 0) {
            lbStudentEventInfo.setText("Thêm mới sinh viên thành công");
            frStudentAdd.setVisible(false);
            model.firedTargeted(student);
        }
    }

    private void updateStudent(Student student) {
        int result = studentService.update(student);
        if (result > 0) {
            lbStudentEventInfo.setText("Cập nhật sinh viên thành công!");
            // refresh Table Model
            setStudentInfo(student);
            model.fireTargetEdit(selectedRow, student, tbStudent);
            frStudentAdd.setVisible(false);
        }
    }

    private void disableMessage(Component... components) {
        for (Component comp : components) {
            comp.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent ke) {
                    if (lbMessage.isVisible()) {
                        lbMessage.setVisible(false);
                    }
                }
            });
        }
    }

    private boolean isValidate(Student student) {
        if (student.getFullname().isEmpty()) {
            tfStudentName.requestFocus();
            return false;
        }
        if (student.getDateOfBirth() == null) {
            birth.requestFocus();
            return false;
        }
        if (student.getPhone().isEmpty()) {
            tfPhoneNumber.requestFocus();
            return false;
        }
        if (student.getFlink().isEmpty()) {
            tfFlink.requestFocus();
            return false;
        }
        return true;
    }

    private Student getStudentInfo() {
        Student student = new Student();
        student.setFullname(tfStudentName.getText());
        student.setPhone(tfPhoneNumber.getText());
        student.setDateOfBirth(DateUtils.convertUdatatoLDate(birth.getDate()));
        student.setGender(getStudentGender());
        student.setFlink(tfFlink.getText());
        student.setAddress(taAddress.getText());
        student.setDescription(taDescrition.getText());
        student.setWorkplace(tfWorkPlace.getText());
        student.setStatus(StudentStatus.UNDEFINE);
        return student;
    }

    private boolean getStudentGender() {
        Enumeration<AbstractButton> elements = buttonGroup.getElements();

        while (elements.hasMoreElements()) {
            AbstractButton button = elements.nextElement();
            if (button.isSelected()) {
                return button.getText().equals("Nam");
            }
        }
        return true;

    }
 private void setStudentGender(boolean  gender) {
        Enumeration<AbstractButton> elements = buttonGroup.getElements();

        while (elements.hasMoreElements()) {
            AbstractButton button = elements.nextElement();
            if (button.getText().equals("Nam")) {
                if(gender){
                    button.setSelected(true);
                }
            }
            else{
                if (button.getText().equals("Nữ")) {
                if(!gender){
                    button.setSelected(true);
                }
            } 
            }
           
        }

    }

    public void setStudentInfo(Student studentInfo) {
        tfStudentId.setText(String.valueOf(studentInfo.getId()));
        tfStudentName.setText(studentInfo.getFullname());
        birth.setDate(DateUtils.convertLdatatoUDate(studentInfo.getDateOfBirth()));
        tfPhoneNumber.setText(studentInfo.getPhone());
        tfFlink.setText(studentInfo.getFlink());
        taAddress.setText(studentInfo.getAddress());
        taDescrition.setText(studentInfo.getDescription());
        tfWorkPlace.setText(studentInfo.getWorkplace());
        setStudentGender(studentInfo.isGender());
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import common.StudentStatus;
import entities.Student;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import libs.LibDate;
import libs.LibDbConnect;

/**
 *
 * @author NHI
 */
public class StudentDao {

    public Connection con = LibDbConnect.getConnectMySQL();
    private Statement st;
    private ResultSet rs;

    public List<Student> getStudent() {
        final List<Student> students = new ArrayList<>();
        String query = "Select* from student";
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                Student student = new Student(rs.getInt("STUDENT_ID"), rs.getString("FULLNAME"),
                        rs.getBoolean("GENDER"), LibDate.getLocalDate(rs.getDate("DATE_OF_BIRTH")),
                        rs.getString("PHONE"), rs.getString("EMAIL"),
                        rs.getString("FLINK"), rs.getString("WORK_PLACE"),
                        rs.getString("ADDRESS"), rs.getString("DESCRIPTION"),
                        getStatus(rs.getString("STATUS")));
                students.add(student);

            }
        } catch (SQLException e) {

        }
        return students;
    }
    public StudentStatus getStatus(String status) {
        StudentStatus studentStatus = null;
        switch (status) {
            case "Đang theo học":
               studentStatus = StudentStatus.INPROGRESS;
                break;
            case "Đợi khóa sau":
                studentStatus = StudentStatus.WAITING;
                break;
            case "Đã hoàn thành":
                studentStatus = StudentStatus.DONE;
                break;
            case "Tạm dừng":
                studentStatus = StudentStatus.DISCARD;
                break;

        }
        return studentStatus;
    }

}

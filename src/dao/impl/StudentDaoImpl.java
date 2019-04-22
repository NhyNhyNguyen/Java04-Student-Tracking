/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import common.StudentStatus;
import dao.StudentDao;
import entities.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.ConnectionProvider;
import jdbc.JdbcConnectionProvider;

import utils.DateUtils;

/**
 *
 * @author NHI
 */
public class StudentDaoImpl implements StudentDao {

    public final Connection con;
    private final ConnectionProvider connectionProvider = new JdbcConnectionProvider();
    private Statement st;
    private ResultSet rs;
    private PreparedStatement pst;

    public StudentDaoImpl() {
        this.con = connectionProvider.getConnection();
    }

    public List<Student> getAllStudents() {
        final List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM student";

        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("STUDENT_ID"));
                student.setFullname(rs.getString("FULLNAME"));
                //student.setDateOfBirth(DateUtils.convertSdatatoLDate(rs.getDate("DATE_OF_BIRTH")));
                student.setDateOfBirth(null);
                student.setGender(rs.getBoolean("GENDER"));
                student.setPhone(rs.getString("PHONE"));
                student.setWorkplace(rs.getString("WORK_PLACE"));
                student.setStatus(StudentStatus.getNameFromValue(rs.getString("STATUS")));
                students.add(student);
            }
        } catch (SQLException e) {

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        return students;
    }

    @Override
    public Student getStudent(int studentId) {
        Student student = null;
        String sql = "SELECT * FROM student WHERE STUDENT_ID = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, studentId);
            rs = pst.executeQuery();
            if (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("STUDENT_ID"));
                student.setFullname(rs.getString("FULLNAME"));
                student.setDateOfBirth(DateUtils.convertSdatatoLDate(rs.getDate("DATE_OF_BIRTH")));
                student.setGender(rs.getBoolean("GENDER"));
                student.setPhone(rs.getString("PHONE"));
                student.setEmail(rs.getString("EMAIL"));
                student.setFlink(rs.getString("FLINK"));
                student.setWorkplace(rs.getString("WORK_PLACE"));
                student.setAddress(rs.getString("ADDRESS"));
                student.setDescription(rs.getString("DESCRIPTION"));
                student.setStatus(StudentStatus.getNameFromValue(rs.getString("STATUS")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
            }
        }

        return student;
    }

    @Override
    public int save(Student student) {
        int lastStudentId = 0;
        final String sql = "INSERT INTO Student(FULLNAME, GENDER,"
                + "PHONE, EMAIL, FLINK, WORK_PLACE, ADDRESS, DESCRIPTION, STATUS, DATE_OF_BIRTH)\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            con.setAutoCommit(true);
            pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, student.getFullname());
            pst.setBoolean(2, student.isGender());
            pst.setString(3, student.getPhone());
            pst.setString(4, student.getEmail());
            pst.setString(5, student.getFlink());
            pst.setString(6, student.getWorkplace());
            pst.setString(7, student.getAddress());
            pst.setString(8, student.getDescription());
            pst.setString(9, student.getStatus().getValue());
            pst.setDate(10, DateUtils.convertLDateToSDate(student.getDateOfBirth()));
            int save = pst.executeUpdate();
            if (save > 0) {
                rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    lastStudentId = rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
            }
        }
        return lastStudentId;
    }

    @Override
    public int update(Student student) {
        int result = 0;
        final String sql = "UPDATE student \n"
                + "SET FULLNAME=?, \n"
                + "GENDER=?, \n"
                + "DATE_OF_BIRTH=?, \n"
                + "PHONE=?, \n"
                + "EMAIL=?, \n"
                + "FLINK=?, \n"
                + "WORK_PLACE=?, \n"
                + "ADDRESS=?, \n"
                + "DESCRIPTION=? \n"
                // + "STATUS=? \n"
                + "WHERE STUDENT_ID=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, student.getFullname());
            pst.setBoolean(2, student.isGender());
            pst.setDate(3, DateUtils.convertLDateToSDate(student.getDateOfBirth()));
            pst.setString(4, student.getPhone());
            pst.setString(5, student.getEmail());
            pst.setString(6, student.getFlink());
            pst.setString(7, student.getWorkplace());
            pst.setString(8, student.getAddress());
            pst.setString(9, student.getDescription());
            pst.setInt(10, student.getId());
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {}
        }
        return result;
    }
}

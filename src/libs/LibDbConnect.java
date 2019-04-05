/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author NHI
 */
public class LibDbConnect {
    public static Connection conn = null;
    private static final String url = "jdbc:mysql://localhost:3306/student_tracking?useUnicode=true&characterEncoding=UTF-8";
    private static String user = "root";
    private static String pass = "";
    public static Connection getConnectMySQL() {
       
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
        }
        return conn;
    }
      public static void main(String[] args) {
		System.out.println("Connect: " + getConnectMySQL());
      }
}

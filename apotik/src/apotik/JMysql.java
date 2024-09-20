/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apotik;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class JMysql {

    public static Connection getkoneksi() {
        Connection cn = null;
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/apotik_db", "root", "");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Koneksi Gagal: " + e);
        }
        return cn;
    }

    public static void closekoneksi(Connection cn, PreparedStatement pr, ResultSet rs) {
        try {
            if (cn != null) {
                cn.close();
            }
            if (pr != null) {
                pr.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Disconnect error " + e);
        }
    }
}

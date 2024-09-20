/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package apotik;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

public class apotik_login extends javax.swing.JFrame {

    public apotik_login() {
        initComponents();
        setLocationRelativeTo(null);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background21 = new apotik.background2();
        jLabel1 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        masuk = new javax.swing.JButton();
        background22 = new apotik.background2();
        password = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        jLabel1.setText("Apotik Jesse");
        background21.add(jLabel1);
        jLabel1.setBounds(150, 60, 260, 50);

        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });
        background21.add(username);
        username.setBounds(170, 140, 210, 38);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Username");
        background21.add(jLabel2);
        jLabel2.setBounds(70, 140, 100, 25);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Password");
        background21.add(jLabel3);
        jLabel3.setBounds(70, 190, 100, 25);

        masuk.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        masuk.setText("Masuk");
        masuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masukActionPerformed(evt);
            }
        });
        background21.add(masuk);
        masuk.setBounds(200, 260, 150, 45);
        background21.add(background22);
        background22.setBounds(10, 20, 0, 0);
        background21.add(password);
        password.setBounds(170, 190, 210, 40);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background21, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

    private void masukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masukActionPerformed
        Connection con = JMysql.getkoneksi();
        PreparedStatement pre = null;
        ResultSet res = null;
        String us = username.getText();
        String ps = password.getText();
        try {
            String sql = "select id from kasir where id = '" + us + "'";
            pre = con.prepareStatement(sql);

            res = pre.executeQuery(sql);
            if (res.next()) {
                if (us.equals(res.getString("id")) && ps.equals("zan")) {    /*ini passwordnya masih anomanli*/
                    new apotik_menu_utama().setVisible(true);
                    this.dispose();
                } else {
                    username.setText(null);
                    password.setText(null);
                    username.requestFocus();
                    JOptionPane.showMessageDialog(null, "Password Salah");
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "id tidak terdaftar");
                username.setText(null);
                password.setText(null);
                username.requestFocus();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Kesalahan saat mengakses database : " + e);
            masuk.setEnabled(false);
        }
    }//GEN-LAST:event_masukActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info
                    : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {

            java.util.logging.Logger.getLogger(apotik_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex
            );
        } catch (InstantiationException ex) {

        } catch (IllegalAccessException ex) {
            Logger.getLogger(apotik_login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(apotik_login.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new apotik_login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private apotik.background2 background21;
    private apotik.background2 background22;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public javax.swing.JButton masuk;
    public javax.swing.JPasswordField password;
    public javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}

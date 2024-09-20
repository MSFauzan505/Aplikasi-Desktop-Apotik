/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package apotik;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author sqlnf
 */
public class apotik_laporan extends javax.swing.JFrame {

    /**
     * Creates new form apotik_laporan
     */
    public apotik_laporan() {
        initComponents();
        setLocationRelativeTo(null);
        jButton4.setEnabled(false);
        transaksi_itemtotal();
        hitungTotal();
        transaksi();
    }

    public void transaksi() {
        DefaultTableModel tblMode = (DefaultTableModel) tabel_trans.getModel();
        tblMode.getDataVector().removeAllElements();

        Connection cn = JMysql.getkoneksi();
        PreparedStatement pr;
        ResultSet rs;
        String sql = "select *from transaksi";
        try {
            pr = cn.prepareStatement(sql);
            rs = pr.executeQuery();
            int row = 0;

            while (rs.next()) {
                tblMode.addRow(new Object[]{"", "", ""});
                String kd = rs.getString("no_transaksi");
                String nm = rs.getString("tgl");
                String st = rs.getString("kasir");
                tabel_trans.setValueAt(kd, row, 1);
                tabel_trans.setValueAt(nm, row, 2);
                tabel_trans.setValueAt(st, row, 3);
                row++;
            }
            JMysql.closekoneksi(cn, pr, rs);
            int i = tabel_trans.getRowCount();
            for (int a = 0; a < i; a++) {
                String no = String.valueOf(a + 1);
                tabel_trans.setValueAt(no + ".", a, 0);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal koneksi " + e);
        }
    }

    public void transaksi_item() {
        DefaultTableModel tblMode = (DefaultTableModel) tabel_trans_item.getModel();
        tblMode.getDataVector().removeAllElements();

        int ii = tabel_trans.getSelectedRow();
        String ss = String.valueOf(tabel_trans.getValueAt(ii, 1));

        Connection cn = JMysql.getkoneksi();
        PreparedStatement pr;
        ResultSet rs;
        String sql = "select *from trans_item where no_transaksi like '%" + ss + "%'";
        try {
            pr = cn.prepareStatement(sql);

            rs = pr.executeQuery();
            int row = 0;

            while (rs.next()) {
                tblMode.addRow(new Object[]{"", "", "", ""});

                String kd = rs.getString("kd_produk");
                String no = rs.getString("no_transaksi");
                String qt = rs.getString("qty");
                String hrg = rs.getString("harga");

                tabel_trans_item.setValueAt(kd, row, 1);
                tabel_trans_item.setValueAt(no, row, 2);
                tabel_trans_item.setValueAt(qt, row, 3);
                tabel_trans_item.setValueAt(hrg, row, 4);
                row++;
            }
            JMysql.closekoneksi(cn, pr, rs);
            int i = tabel_trans_item.getRowCount();
            for (int a = 0; a < i; a++) {
                String no = String.valueOf(a + 1);
                tabel_trans_item.setValueAt(no + ".", a, 0);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal koneksi " + e);
        }
    }

    public void transaksi_itemtotal() {
        DefaultTableModel tblMode = (DefaultTableModel) tabelbeli.getModel();
        tblMode.getDataVector().removeAllElements();

        Connection cn = JMysql.getkoneksi();
        PreparedStatement pr;
        ResultSet rs;
        String sql = "select *from trans_item";
        try {
            pr = cn.prepareStatement(sql);

            rs = pr.executeQuery();
            int row = 0;
            while (rs.next()) {
                tblMode.addRow(new Object[]{"", "", "", ""});

                String kd = rs.getString("kd_produk");
                String no = rs.getString("no_transaksi");
                String qt = rs.getString("qty");
                String hrg = rs.getString("harga");

                tabelbeli.setValueAt(kd, row, 1);
                tabelbeli.setValueAt(no, row, 2);
                tabelbeli.setValueAt(qt, row, 3);
                tabelbeli.setValueAt(hrg, row, 4);
                row++;
            }
            JMysql.closekoneksi(cn, pr, rs);
            int i = tabelbeli.getRowCount();
            for (int a = 0; a < i; a++) {
                String no = String.valueOf(a + 1);
                tabelbeli.setValueAt(no + ".", a, 0);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal koneksi " + e);
        }
    }

    public void hitungTotal() {
        int jumlahBaris = tabelbeli.getRowCount();
        int totalBiaya = 0;
        int totalqty = 0;
        int hargaBarang;
        int jqty;
        String t;
        try {
            TableModel tabelModel;
            tabelModel = tabelbeli.getModel();
            for (int i = 0; i < jumlahBaris; i++) {

                hargaBarang = Integer.parseInt(tabelModel.getValueAt(i, 4).toString());
                totalBiaya = totalBiaya + (hargaBarang);
            }
            Grand.setText(String.valueOf(totalBiaya));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error" + e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        background1 = new apotik.background();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelbeli = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_trans = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabel_trans_item = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        Grand = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenuItem1.setText("jMenuItem1");

        jMenuItem5.setText("jMenuItem5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Pengaturan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        background1.add(jButton1);
        jButton1.setBounds(590, 10, 140, 60);

        jButton2.setText("Penjualan");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        background1.add(jButton2);
        jButton2.setBounds(110, 10, 140, 60);

        jButton3.setText("Daftar produk");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        background1.add(jButton3);
        jButton3.setBounds(270, 10, 140, 60);

        jButton4.setText("Laporan Transaksi");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        background1.add(jButton4);
        jButton4.setBounds(430, 10, 140, 60);

        tabelbeli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Kode", "Nama", "Kasir", "qty", "subtotal"
            }
        ));
        jScrollPane1.setViewportView(tabelbeli);

        jTabbedPane1.addTab("Pendapatan", jScrollPane1);

        tabel_trans.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "No Transaksi", "Tanggal", "Kasir"
            }
        ));
        tabel_trans.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_transMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel_trans);

        tabel_trans_item.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Kode produk", "No Transaksi", "qty", "Harga"
            }
        ));
        jScrollPane3.setViewportView(tabel_trans_item);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Transaksi", jPanel1);

        background1.add(jTabbedPane1);
        jTabbedPane1.setBounds(10, 120, 790, 470);
        background1.add(jSeparator1);
        jSeparator1.setBounds(0, 80, 820, 10);

        Grand.setText("GrandTotal");
        background1.add(Grand);
        Grand.setBounds(10, 40, 110, 50);

        jMenu3.setText("Menu");

        jMenuItem2.setText("Penjualan");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuItem3.setText("Daftar Barang");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem4.setText("Buku Transaksi");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem6.setText("Keluar");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuBar2.add(jMenu3);

        jMenu4.setText("Pengaturan");
        jMenu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu4ActionPerformed(evt);
            }
        });
        jMenuBar2.add(jMenu4);

        jMenu5.setText("Bantuan");
        jMenu5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu5ActionPerformed(evt);
            }
        });
        jMenuBar2.add(jMenu5);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, 816, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new apotik_penjualan().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new apotik_daftarProduk().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new apotik_pengaturan().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new apotik_penjualan().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        new apotik_daftarProduk().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        new apotik_laporan().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        new notif().setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu4ActionPerformed
        new apotik_pengaturan().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenu4ActionPerformed

    private void jMenu5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu5ActionPerformed
        new tentang().setVisible(true);
    }//GEN-LAST:event_jMenu5ActionPerformed

    private void tabel_transMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_transMouseClicked
        try {
            transaksi_item();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(apotik_laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(apotik_laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(apotik_laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(apotik_laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */

    }//GEN-LAST:event_tabel_transMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(apotik_laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(apotik_laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(apotik_laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(apotik_laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new apotik_laporan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Grand;
    private apotik.background background1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tabel_trans;
    private javax.swing.JTable tabel_trans_item;
    private javax.swing.JTable tabelbeli;
    // End of variables declaration//GEN-END:variables
}

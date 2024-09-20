/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package apotik;

import apotik.JMysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sqlnf
 */
public class apotik_daftarProduk extends javax.swing.JFrame {

    public apotik_daftarProduk() {
        initComponents();
        produk();
        daftar_produk.setEnabled(false);
        setLocationRelativeTo(null);
    }

    public void produk() {
        DefaultTableModel tblMode = (DefaultTableModel) tabel_produk.getModel();
        tblMode.getDataVector().removeAllElements();
        Connection cn = JMysql.getkoneksi();
        PreparedStatement pr;
        ResultSet rs;
        String sql = "select *from produk";
        try {
            pr = cn.prepareStatement(sql);
            rs = pr.executeQuery();
            int row = 0;
            while (rs.next()) {
                tblMode.addRow(new Object[]{"", "", "", ""});
                String kd = rs.getString("kd_produk");
                String nm = rs.getString("nama");
                String st = rs.getString("satuan");
                String hg = rs.getString("harga");
                tabel_produk.setValueAt(kd, row, 1);
                tabel_produk.setValueAt(nm, row, 2);
                tabel_produk.setValueAt(st, row, 3);
                tabel_produk.setValueAt(hg, row, 4);
                row++;
            }
            JMysql.closekoneksi(cn, pr, rs);
            int i = tabel_produk.getRowCount();
            for (int a = 0; a < i; a++) {
                String no = String.valueOf(a + 1);
                tabel_produk.setValueAt(no + ".", a, 0);
            }
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(this, "Gagal koneksi " + e);
        }
    }

    public void cariproduk() {
        DefaultTableModel tblMode = (DefaultTableModel) tabel_produk.getModel();
        tblMode.getDataVector().removeAllElements();

        Connection cn = JMysql.getkoneksi();
        PreparedStatement pr;
        ResultSet rs;
        String cr = cari.getText();
        String sql = "select *from produk where nama like '%" + cr + "%'";
        try {
            pr = cn.prepareStatement(sql);
            rs = pr.executeQuery();
            int row = 0;
            while (rs.next()) {
                tblMode.addRow(new Object[]{"", "", "", ""});
                String kd = rs.getString("kd_produk");
                String nm = rs.getString("nama");
                String st = rs.getString("satuan");
                String hg = rs.getString("harga");
                tabel_produk.setValueAt(kd, row, 1);
                tabel_produk.setValueAt(nm, row, 2);
                tabel_produk.setValueAt(st, row, 3);
                tabel_produk.setValueAt(hg, row, 4);
                row++;
            }
            JMysql.closekoneksi(cn, pr, rs);
            int i = tabel_produk.getRowCount();
            for (int a = 0; a < i; a++) {
                String no = String.valueOf(a + 1);
                tabel_produk.setValueAt(no + ".", a, 0);
            }
            if (i == 0) {
                is.setText("Data tidak ditemukan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal koneksi " + e);
        }
    }

    @SuppressWarnings("unchecked")


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background1 = new apotik.background();
        btn_pengaturan = new javax.swing.JButton();
        penjualan = new javax.swing.JButton();
        daftar_produk = new javax.swing.JButton();
        laporan_transaksi = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane = new javax.swing.JScrollPane();
        tabel_produk = new javax.swing.JTable();
        cari = new javax.swing.JTextField();
        is = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menu_utama = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        daftar_barang = new javax.swing.JMenuItem();
        buku_transaksi = new javax.swing.JMenuItem();
        keluar = new javax.swing.JMenuItem();
        pengaturan = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_pengaturan.setText("pengaturan");
        btn_pengaturan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pengaturanActionPerformed(evt);
            }
        });
        background1.add(btn_pengaturan);
        btn_pengaturan.setBounds(540, 20, 130, 50);

        penjualan.setText("Penjualan");
        penjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                penjualanActionPerformed(evt);
            }
        });
        background1.add(penjualan);
        penjualan.setBounds(120, 20, 130, 50);

        daftar_produk.setText("Daftar Produk");
        daftar_produk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                daftar_produkActionPerformed(evt);
            }
        });
        background1.add(daftar_produk);
        daftar_produk.setBounds(260, 20, 130, 50);

        laporan_transaksi.setText("Laporan Transaksi");
        laporan_transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laporan_transaksiActionPerformed(evt);
            }
        });
        background1.add(laporan_transaksi);
        laporan_transaksi.setBounds(400, 20, 130, 50);
        background1.add(jSeparator1);
        jSeparator1.setBounds(-10, 80, 780, 3);

        tabel_produk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No", "kode Produk", "Nama produk", "Satuan", "Harga"
            }
        ));
        tabel_produk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tabel_produkMouseEntered(evt);
            }
        });
        jScrollPane.setViewportView(tabel_produk);

        background1.add(jScrollPane);
        jScrollPane.setBounds(0, 170, 770, 350);

        cari.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                cariCaretUpdate(evt);
            }
        });
        cari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cariMouseEntered(evt);
            }
        });
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });
        background1.add(cari);
        cari.setBounds(320, 102, 120, 30);

        is.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        is.setText("Cari");
        background1.add(is);
        is.setBounds(290, 110, 37, 20);

        menu_utama.setText("Menu");

        jMenuItem1.setText("penjualan");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menu_utama.add(jMenuItem1);

        daftar_barang.setText("daftar barang");
        daftar_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                daftar_barangActionPerformed(evt);
            }
        });
        menu_utama.add(daftar_barang);

        buku_transaksi.setText("buku transaksi");
        buku_transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buku_transaksiActionPerformed(evt);
            }
        });
        menu_utama.add(buku_transaksi);

        keluar.setText("keluar");
        keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluarActionPerformed(evt);
            }
        });
        menu_utama.add(keluar);

        jMenuBar1.add(menu_utama);

        pengaturan.setText("Pengaturan");
        pengaturan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pengaturanActionPerformed(evt);
            }
        });
        jMenuBar1.add(pengaturan);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cariActionPerformed

    private void daftar_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_daftar_barangActionPerformed
        new apotik_daftarProduk().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_daftar_barangActionPerformed

    private void cariCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_cariCaretUpdate
        cariproduk();
    }//GEN-LAST:event_cariCaretUpdate

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new apotik_penjualan().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void buku_transaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buku_transaksiActionPerformed
        new apotik_laporan().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_buku_transaksiActionPerformed

    private void keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluarActionPerformed
        new notif().setVisible(true);
    }//GEN-LAST:event_keluarActionPerformed

    private void pengaturanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pengaturanActionPerformed
        new apotik_pengaturan().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_pengaturanActionPerformed

    private void cariMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cariMouseEntered
        is.setText("");
    }//GEN-LAST:event_cariMouseEntered

    private void tabel_produkMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_produkMouseEntered
        is.setText("");
    }//GEN-LAST:event_tabel_produkMouseEntered

    private void daftar_produkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_daftar_produkActionPerformed
        new apotik_daftarProduk().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_daftar_produkActionPerformed

    private void penjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_penjualanActionPerformed
        new apotik_penjualan().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_penjualanActionPerformed

    private void laporan_transaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laporan_transaksiActionPerformed
        new apotik_laporan().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_laporan_transaksiActionPerformed

    private void btn_pengaturanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pengaturanActionPerformed
        new apotik_pengaturan().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_pengaturanActionPerformed

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
            java.util.logging.Logger.getLogger(apotik_daftarProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex
            );
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(apotik_daftarProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex
            );
        } catch (IllegalAccessException ex) {

            java.util.logging.Logger.getLogger(apotik_daftarProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex
            );
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {

            java.util.logging.Logger.getLogger(apotik_daftarProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex
            );
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new apotik_daftarProduk().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private apotik.background background1;
    public javax.swing.JButton btn_pengaturan;
    public javax.swing.JMenuItem buku_transaksi;
    public javax.swing.JTextField cari;
    public javax.swing.JMenuItem daftar_barang;
    public javax.swing.JButton daftar_produk;
    private javax.swing.JLabel is;
    private javax.swing.JMenuBar jMenuBar1;
    public javax.swing.JMenuItem jMenuItem1;
    public javax.swing.JScrollPane jScrollPane;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JMenuItem keluar;
    public javax.swing.JButton laporan_transaksi;
    public javax.swing.JMenu menu_utama;
    public javax.swing.JMenu pengaturan;
    public javax.swing.JButton penjualan;
    private javax.swing.JTable tabel_produk;
    // End of variables declaration//GEN-END:variables
}

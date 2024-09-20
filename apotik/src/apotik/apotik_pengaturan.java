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
import javax.swing.table.DefaultTableModel;

public class apotik_pengaturan extends javax.swing.JFrame {

    public apotik_pengaturan() {
        initComponents();
        btn_pengaturan.setEnabled(false);
        produk();
        karyawan();
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

    public void tambahproduk() {
        Connection cn = JMysql.getkoneksi();
        PreparedStatement pr = null;
        String a = kkode.getText();
        String b = namaprod.getText();
        String c = satuan.getText();
        String d = harga.getText();
        String sql = "insert into produk values(?,?,?,?)";

        try {
            pr = cn.prepareStatement(sql);
            pr.setString(1, a);
            pr.setString(2, b);
            pr.setString(3, c);
            pr.setString(4, d);
            pr.executeUpdate();
            JMysql.closekoneksi(cn, pr, null);
            produk();
            kkode.setText("");
            namaprod.setText("");
            satuan.setText("");
            harga.setText("");
            JOptionPane.showMessageDialog(this, "Produk Berhasil Ditambahkan");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal : " + e);
        }
    }

    public void pindahproduk() {
        int i = tabel_produk.getSelectedRow();
        String kod = String.valueOf(tabel_produk.getValueAt(i, 1));
        String nm = String.valueOf(tabel_produk.getValueAt(i, 2));
        String sat = String.valueOf(tabel_produk.getValueAt(i, 3));
        String har = String.valueOf(tabel_produk.getValueAt(i, 4));
        kkode.setText(kod);
        namaprod.setText(nm);
        satuan.setText(sat);
        harga.setText(har);
    }

    public void hapusproduk() {
        Connection cn = JMysql.getkoneksi();
        PreparedStatement pr = null;
        int baris = tabel_produk.getSelectedRow();
        String id = String.valueOf(tabel_produk.getValueAt(baris, 1));
        String sql = "delete from produk where kd_produk =?";
        try {
            pr = cn.prepareStatement(sql);
            pr.setString(1, id);
            pr.executeUpdate();
            produk();
            cn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal Dihapus : " + e);
        }
    }

    public void updateproduk() {
        Connection cn = JMysql.getkoneksi();
        PreparedStatement pr = null;
        String nm, hg, id;
        int baris = tabel_produk.getSelectedRow();
        id = String.valueOf(tabel_produk.getValueAt(baris, 1));

        String a = kkode.getText();
        String b = namaprod.getText();
        String c = satuan.getText();
        String d = harga.getText();
        String sql = "update produk set kd_produk=?,nama=?, satuan=?, harga=? where kd_produk=?";
        try {
            pr = cn.prepareStatement(sql);
            pr.setString(1, a);
            pr.setString(2, b);
            pr.setString(3, c);
            pr.setString(4, d);
            pr.setString(5, id);
            pr.executeUpdate();
            JMysql.closekoneksi(cn, pr, null);
            produk();
            JOptionPane.showMessageDialog(this, "Produk Berhasil diupdate");
            kkode.setText("");
            namaprod.setText("");
            satuan.setText("");
            harga.setText("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Data gagal Disimpan : " + e);
        }
    }

    public void karyawan() {
        DefaultTableModel tblMode = (DefaultTableModel) tabel_karyawan.getModel();
        tblMode.getDataVector().removeAllElements();

        Connection cn = JMysql.getkoneksi();
        PreparedStatement pr;
        ResultSet rs;
        String sql = "select *from kasir";
        try {
            pr = cn.prepareStatement(sql);
            rs = pr.executeQuery();
            int row = 0;
            while (rs.next()) {
                tblMode.addRow(new Object[]{"", "", ""});
                String id = rs.getString("id");
                String nm = rs.getString("nama");
                String al = rs.getString("alamat");
                tabel_karyawan.setValueAt(id, row, 1);
                tabel_karyawan.setValueAt(nm, row, 2);
                tabel_karyawan.setValueAt(al, row, 3);
                row++;
            }
            JMysql.closekoneksi(cn, pr, rs);

            int i = tabel_karyawan.getRowCount();
            for (int a = 0; a < i; a++) {
                String no = String.valueOf(a + 1);
                tabel_karyawan.setValueAt(no + ".", a, 0);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal koneksi " + e);
        }
    }

    public void tambahkaryawan() {
        Connection cn = JMysql.getkoneksi();
        PreparedStatement pr = null;
        String b = namakar.getText();
        String c = alamat.getText();
        String sql = "insert into kasir values(?,?,?)";
        try {
            pr = cn.prepareStatement(sql);
            pr.setString(1, "0");
            pr.setString(2, b);
            pr.setString(3, c);
            pr.executeUpdate();
            JMysql.closekoneksi(cn, pr, null);
            karyawan();

            idkar.setText("");
            namakar.setText("");
            alamat.setText("");
            JOptionPane.showMessageDialog(this, "Karyawan Berhasil Ditambahkan");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal : " + e);
        }
    }

    public void updatekaryawan() {
        Connection cn = JMysql.getkoneksi();
        PreparedStatement pr = null;
        String nm, hg, id;
        int baris = tabel_karyawan.getSelectedRow();
        id = String.valueOf(tabel_karyawan.getValueAt(baris, 1));
        String a = namakar.getText();
        String b = alamat.getText();
        String sql = "update kasir set nama=?,alamat=? where id=?";
        try {
            pr = cn.prepareStatement(sql);
            pr.setString(1, a);
            pr.setString(2, b);
            pr.setString(3, id);
            pr.executeUpdate();
            JMysql.closekoneksi(cn, pr, null);
            karyawan();
            JOptionPane.showMessageDialog(this, "Karyawan Berhasil diupdate");
            idkar.setText("");
            namakar.setText("");
            alamat.setText("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Data gagal Disimpan : " + e);
        }
    }

    public void pindahkaryawan() {
        int i = tabel_karyawan.getSelectedRow();
        String id = String.valueOf(tabel_karyawan.getValueAt(i, 1));
        String nm = String.valueOf(tabel_karyawan.getValueAt(i, 2));
        String al = String.valueOf(tabel_karyawan.getValueAt(i, 3));
        idkar.setText(id);
        namakar.setText(nm);
        alamat.setText(al);
    }

    public void hapuskaryawan() {
        Connection cn = JMysql.getkoneksi();
        PreparedStatement pr = null;
        int baris = tabel_karyawan.getSelectedRow();
        String id = String.valueOf(tabel_karyawan.getValueAt(baris, 1));
        String sql = "delete from kasir where id =?";
        try {
            pr = cn.prepareStatement(sql);
            pr.setString(1, id);
            pr.executeUpdate();
            karyawan();
            cn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal Dihapus : " + e);
        }
    }

    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background1 = new apotik.background();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        kkode = new javax.swing.JTextField();
        namaprod = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        satuan = new javax.swing.JTextField();
        harga = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        idkar = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        namakar = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        alamat = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_produk = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabel_karyawan = new javax.swing.JTable();
        penjualan = new javax.swing.JButton();
        daftar_produk = new javax.swing.JButton();
        laporan_transaksi = new javax.swing.JButton();
        btn_pengaturan = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Produk");

        jLabel2.setText("Kode");

        jLabel3.setText("Nama");

        namaprod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaprodActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Karyawan");

        jButton1.setText("delete");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Save");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel5.setText("Satuan");

        jLabel6.setText("harga");

        jLabel7.setText("ID");

        idkar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idkarActionPerformed(evt);
            }
        });

        jLabel8.setText("Nama");

        namakar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namakarActionPerformed(evt);
            }
        });

        jLabel9.setText("Alamat");

        alamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alamatActionPerformed(evt);
            }
        });

        jButton5.setText("delete");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Save");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("update");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(satuan, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(kkode, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(namaprod, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(harga, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(alamat, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                                    .addComponent(namakar)
                                    .addComponent(idkar)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton5)
                                .addGap(15, 15, 15)
                                .addComponent(jButton7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton6))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(jLabel4)))
                .addContainerGap(111, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(kkode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(namaprod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(satuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(idkar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(namakar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(alamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        background1.add(jPanel1);
        jPanel1.setBounds(10, 20, 390, 550);

        tabel_produk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No", "Kode", "Nama", "Satuan", "harga"
            }
        ));
        tabel_produk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_produkMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel_produk);

        jScrollPane1.setViewportView(jScrollPane2);

        background1.add(jScrollPane1);
        jScrollPane1.setBounds(450, 20, 370, 260);

        tabel_karyawan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "no", "id", "nama", "alamat"
            }
        ));
        tabel_karyawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_karyawanMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabel_karyawan);

        background1.add(jScrollPane3);
        jScrollPane3.setBounds(450, 290, 370, 280);

        penjualan.setText("Penjualan");
        penjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                penjualanActionPerformed(evt);
            }
        });

        daftar_produk.setText("Daftar Produk");
        daftar_produk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                daftar_produkActionPerformed(evt);
            }
        });

        laporan_transaksi.setText("Laporan Transaksi");
        laporan_transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laporan_transaksiActionPerformed(evt);
            }
        });

        btn_pengaturan.setText("pengaturan");
        btn_pengaturan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pengaturanActionPerformed(evt);
            }
        });

        jMenu1.setText("menu ");

        jMenuItem1.setText("penjualan");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("daftar barang");
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("buku transaksi");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("keluar");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("pengaturan");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(penjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(daftar_produk, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(laporan_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btn_pengaturan, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(penjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(daftar_produk, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(laporan_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pengaturan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background1, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new apotik_penjualan().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void penjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_penjualanActionPerformed
        new apotik_penjualan().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_penjualanActionPerformed

    private void daftar_produkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_daftar_produkActionPerformed
        new apotik_daftarProduk().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_daftar_produkActionPerformed

    private void laporan_transaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laporan_transaksiActionPerformed
        new apotik_laporan().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_laporan_transaksiActionPerformed

    private void tabel_produkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_produkMouseClicked
        pindahproduk();
    }//GEN-LAST:event_tabel_produkMouseClicked

    private void tabel_karyawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_karyawanMouseClicked
        pindahkaryawan();
    }//GEN-LAST:event_tabel_karyawanMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            hapusproduk();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Select Data");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (kkode.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Please fill the blank field");
            return;
        } else {
            tambahproduk();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            updateproduk();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Select data");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            hapuskaryawan();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Select data");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if (namakar.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Please fill the blank field");
            return;
        } else {
            tambahkaryawan();
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        new apotik_laporan().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        new notif().setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
        new apotik_pengaturan().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenu2ActionPerformed

    private void idkarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idkarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idkarActionPerformed

    private void btn_pengaturanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pengaturanActionPerformed
        new apotik_pengaturan().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_pengaturanActionPerformed

    private void alamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_alamatActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            updatekaryawan();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Select data");
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void namakarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namakarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namakarActionPerformed

    private void namaprodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaprodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaprodActionPerformed

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
            Logger.getLogger(apotik_pengaturan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(apotik_pengaturan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(apotik_pengaturan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(apotik_pengaturan.class.getName()).log(Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new apotik_pengaturan().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField alamat;
    private apotik.background background1;
    public javax.swing.JButton btn_pengaturan;
    public javax.swing.JButton daftar_produk;
    private javax.swing.JTextField harga;
    public javax.swing.JTextField idkar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField kkode;
    public javax.swing.JButton laporan_transaksi;
    private javax.swing.JTextField namakar;
    private javax.swing.JTextField namaprod;
    public javax.swing.JButton penjualan;
    private javax.swing.JTextField satuan;
    private javax.swing.JTable tabel_karyawan;
    private javax.swing.JTable tabel_produk;
    // End of variables declaration//GEN-END:variables
}

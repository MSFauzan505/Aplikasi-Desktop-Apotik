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
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class apotik_penjualan extends javax.swing.JFrame {

    /**
     * Creates new form apotik_penjualan
     */
    public apotik_penjualan() {
        initComponents();
        tgl();
        kasir();
        produk();
        notrans();
        penjualan.setEnabled(false);
        setLocationRelativeTo(null);
    }

    public void tgl() {
        Date skrg = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
        String tgl = format.format(skrg);
        tanggal.setText(format.format(skrg));
    }

    public void kasir() {
        Connection cn = JMysql.getkoneksi();
        PreparedStatement pr;
        ResultSet rs;
        String sql = "select *from kasir";
        try {
            pr = cn.prepareStatement(sql);
            rs = pr.executeQuery();
            while (rs.next()) {
                ckasir
                        .addItem(rs.getString("nama"));
            }
            JMysql.closekoneksi(cn, pr, rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal update query combobox : " + e);
        }
    }

    public void produk() {
        DefaultTableModel tblMode = (DefaultTableModel) tabelproduk.getModel();
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
                tabelproduk.setValueAt(kd, row, 1);
                tabelproduk.setValueAt(nm, row, 2);
                tabelproduk.setValueAt(st, row, 3);
                tabelproduk.setValueAt(hg, row, 4);
                row++;
            }
            JMysql.closekoneksi(cn, pr, rs);
            int i = tabelproduk.getRowCount();
            for (int a = 0; a < i; a++) {
                String no = String.valueOf(a + 1);
                tabelproduk.setValueAt(no + ".", a, 0);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal koneksi " + e);
        }
    }

    public void cariproduk() {
        DefaultTableModel tblMode = (DefaultTableModel) tabelproduk.getModel();
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
                tabelproduk.setValueAt(kd, row, 1);
                tabelproduk.setValueAt(nm, row, 2);
                tabelproduk.setValueAt(st, row, 3);
                tabelproduk.setValueAt(hg, row, 4);
                row++;
            }
            JMysql.closekoneksi(cn, pr, rs);
            int i = tabelproduk.getRowCount();
            for (int a = 0; a < i; a++) {
                String no = String.valueOf(a + 1);
                tabelproduk.setValueAt(no + ".", a, 0);
            }
            if (i == 0) {
                is.setText("Data tidak ditemukan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal koneksi " + e);
        }
    }

    public void tambahtrans() {
       Connection cn = JMysql.getkoneksi();
        PreparedStatement pr = null;
        String a = String.valueOf(ckasir.getSelectedItem());
        String b = tanggal.getText();
        String sql = "insert into transaksi values(?,?,?)";
        try {
            pr = cn.prepareStatement(sql);
            pr.setString(1, "0");
            pr.setString(2, b);
            pr.setString(3, a);
            pr.executeUpdate();
            JMysql.closekoneksi(cn, pr, null);
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(this, "Transaksi Gagal : " + e1);
        }
    }

    public void tambahtransItem() {
        Connection cn = JMysql.getkoneksi();
        PreparedStatement pr = null;
        int baris = tabelbeli.getRowCount();
        TableModel tabelModel;
        tabelModel = tabelbeli.getModel();
        for (int i = 0; i < baris; i++) {
            String nomor = notrans.getText();
            String kd = String.valueOf(tabelbeli.getValueAt(i, 1));
            String qt = String.valueOf(tabelbeli.getValueAt(i, 3));
            String hrg = String.valueOf(tabelbeli.getValueAt(i, 4));
            String sql = "insert into trans_item values(?,?,?,?)";
            try {
                pr = cn.prepareStatement(sql);
                pr.setString(1, kd);
                pr.setString(2, nomor);
                pr.setString(3, qt);
                pr.setString(4, hrg);
                pr.executeUpdate();
                notrans();
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(this, "Transaksi Gagal : " + e1);
            }
        }
        JMysql.closekoneksi(cn, pr, null);
        JOptionPane.showMessageDialog(this, "Transaksi item Berhasil Disimpan");
        DefaultTableModel tblMode = (DefaultTableModel) tabelproduk.getModel();

        tblMode.getDataVector().removeAllElements();
    }

    public void tambahkeranjangbelanja() {
       int baris = tabelproduk.getSelectedRow();

        String kodepr = String.valueOf(tabelproduk.getValueAt(baris, 1));
        String namapr = String.valueOf(tabelproduk.getValueAt(baris, 2));
        String hargapr = String.valueOf(tabelproduk.getValueAt(baris, 4));
        int hargapr2 = Integer.valueOf(hargapr);
        int Quan = Integer.valueOf(qty.getText());
        Integer sub = hargapr2 * Quan;

        DefaultTableModel tableModel = (DefaultTableModel) tabelbeli.getModel();
        String[] data = new String[6];
        data[0] = String.valueOf(1 + tabelbeli.getRowCount());
        data[1] = kodepr;
        data[2] = namapr;
        data[3] = qty.getText();
        data[4] = String.valueOf(sub);
        tableModel.addRow(data);
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

                jqty = Integer.parseInt(tabelModel.getValueAt(i, 3).toString());
                totalqty = totalqty + (jqty);
            }
            grandtotal.setText(String.valueOf(totalBiaya));
            qty2.setText(String.valueOf(totalqty));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error" + e);
        }
    }

    public void notrans() {
        Connection cn = JMysql.getkoneksi();
        PreparedStatement pr;
        ResultSet rs;
        String sql = "select no_transaksi from transaksi ORDER BY no_transaksi DESC LIMIT 1; ";
        try {
            pr = cn.prepareStatement(sql);
            rs = pr.executeQuery();
            while (rs.next()) {
                notrans.setText(rs.getString("no_transaksi"));
            }
            JMysql.closekoneksi(cn, pr, rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal update query combobox : " + e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        background1 = new apotik.background();
        jButton1 = new javax.swing.JButton();
        penjualan = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        is = new javax.swing.JLabel();
        cari = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        notrans = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelproduk = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelbeli = new javax.swing.JTable();
        tanggal = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        ckasir = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        namaproduk = new javax.swing.JTextField();
        hargaproduk = new javax.swing.JTextField();
        qty = new javax.swing.JTextField();
        jlabel = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        grandtotal = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        qty2 = new javax.swing.JTextField();
        bayar = new javax.swing.JTextField();
        kembali = new javax.swing.JTextField();
        jkembali = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        info = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("pengaturan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        background1.add(jButton1);
        jButton1.setBounds(560, 20, 130, 40);

        penjualan.setText("penjualan");
        penjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                penjualanActionPerformed(evt);
            }
        });
        background1.add(penjualan);
        penjualan.setBounds(140, 20, 130, 40);

        jButton3.setText("daftar produk");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        background1.add(jButton3);
        jButton3.setBounds(280, 20, 130, 40);

        jButton4.setText("laporan transaksi");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        background1.add(jButton4);
        jButton4.setBounds(420, 20, 130, 40);

        jInternalFrame1.setVisible(true);

        is.setText("cari");

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

        jLabel2.setText("no transaksi");

        tabelproduk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "no", "kode", "nama", "satuan", "harga"
            }
        ));
        tabelproduk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelprodukMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tabelprodukMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tabelproduk);

        jLabel4.setText("keranjang belanja");

        tabelbeli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "no", "kode", "nama", "QTY", "sub total"
            }
        ));
        jScrollPane3.setViewportView(tabelbeli);

        tanggal.setText("tanggal");

        jLabel3.setText("kasir");

        jLabel5.setText("item");

        hargaproduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargaprodukActionPerformed(evt);
            }
        });

        qty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qtyActionPerformed(evt);
            }
        });

        jlabel.setText("QTY");

        jButton5.setText("tambah keranjang");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(hargaproduk, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(namaproduk))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 43, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(ckasir, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 19, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ckasir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(namaproduk, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hargaproduk, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlabel)
                    .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel7.setText("checkout");

        jLabel8.setText("QTY");

        jLabel9.setText("bayar");

        qty2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qty2ActionPerformed(evt);
            }
        });

        bayar.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                bayarCaretUpdate(evt);
            }
        });
        bayar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bayarMouseEntered(evt);
            }
        });
        bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bayarActionPerformed(evt);
            }
        });

        kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembaliActionPerformed(evt);
            }
        });

        jkembali.setText("kembali");

        jButton6.setText("pembayaran");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        info.setText("info");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jkembali, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(qty2)
                            .addComponent(bayar)
                            .addComponent(kembali)))
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(info, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(316, 316, 316))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(grandtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(info))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grandtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(qty2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(bayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jkembali)
                    .addComponent(kembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addComponent(is, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(114, 114, 114)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(notrans, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3))
                        .addGap(61, 61, 61)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(195, 195, 195)
                        .addComponent(tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tanggal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(is)
                            .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(notrans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(21, 21, 21))))
        );

        background1.add(jInternalFrame1);
        jInternalFrame1.setBounds(0, 60, 970, 550);

        jMenu3.setText("menu");

        jMenuItem1.setText("penjualan");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem2.setText("daftar barang");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuItem3.setText("buku transaksi");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem4.setText("keluar");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Pengaturan");
        jMenu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu4ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.PREFERRED_SIZE, 970, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new apotik_pengaturan().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void qtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_qtyActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new apotik_daftarProduk().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        new apotik_laporan().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new apotik_penjualan().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new apotik_daftarProduk().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        new apotik_laporan().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        new notif().setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu4ActionPerformed
        new apotik_pengaturan().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenu4ActionPerformed

    private void cariCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_cariCaretUpdate
        cariproduk();
    }//GEN-LAST:event_cariCaretUpdate

    private void cariMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cariMouseEntered
        is.setText("");
    }//GEN-LAST:event_cariMouseEntered

    private void tabelprodukMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelprodukMouseEntered
        is.setText("");
    }//GEN-LAST:event_tabelprodukMouseEntered

    private void tabelprodukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelprodukMouseClicked
        int baris = tabelproduk.getSelectedRow();
        String namapr = String.valueOf(tabelproduk.getValueAt(baris, 2));
        String hargapr = String.valueOf(tabelproduk.getValueAt(baris, 4));
        namaproduk.setText(namapr);
        hargaproduk.setText(hargapr);
    }//GEN-LAST:event_tabelprodukMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (qty.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Masukkan jumlah pembelian");
            return;
        } else {

            tambahkeranjangbelanja();
            hitungTotal();
            qty.setText("");

        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if (bayar.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Masukkan pembayaran");
        } else {
            try {
                tambahtrans();
                notrans();
                tambahtransItem();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }
        }


    }//GEN-LAST:event_jButton6ActionPerformed

    private void bayarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_bayarCaretUpdate
        try {
            int jlh = Integer.valueOf(grandtotal.getText());
            int bayarint = Integer.valueOf(bayar.getText());
            String kembalian;
            try {
                int kembalint = bayarint - jlh;
                kembalian = String.valueOf(kembalint);
                kembali.setText(kembalian);
            } catch (Exception e) {
                info.setText("periksa penulisan");
            }
        } catch (Exception e) {
            info.setText("periksa penulisan");
        }

    }//GEN-LAST:event_bayarCaretUpdate

    private void bayarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bayarMouseEntered
        info.setText("");
    }//GEN-LAST:event_bayarMouseEntered

    private void penjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_penjualanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_penjualanActionPerformed

    private void hargaprodukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargaprodukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hargaprodukActionPerformed

    private void qty2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qty2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_qty2ActionPerformed

    private void kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembaliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kembaliActionPerformed

    private void bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bayarActionPerformed

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
            java.util.logging.Logger.getLogger(apotik_penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {

            java.util.logging.Logger.getLogger(apotik_penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {

            java.util.logging.Logger.getLogger(apotik_penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {

            java.util.logging.Logger.getLogger(apotik_penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new apotik_penjualan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private apotik.background background1;
    private javax.swing.JTextField bayar;
    private javax.swing.JTextField cari;
    private javax.swing.JComboBox<String> ckasir;
    private javax.swing.JTextField grandtotal;
    private javax.swing.JTextField hargaproduk;
    private javax.swing.JLabel info;
    private javax.swing.JLabel is;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel jkembali;
    private javax.swing.JLabel jlabel;
    private javax.swing.JTextField kembali;
    private javax.swing.JTextField namaproduk;
    private javax.swing.JTextField notrans;
    private javax.swing.JButton penjualan;
    private javax.swing.JTextField qty;
    private javax.swing.JTextField qty2;
    private javax.swing.JTable tabelbeli;
    private javax.swing.JTable tabelproduk;
    private javax.swing.JLabel tanggal;
    // End of variables declaration//GEN-END:variables
}

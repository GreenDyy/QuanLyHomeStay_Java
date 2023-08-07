/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package MDI;

import DAO.ConnectionDB;
import DAO.PhieuDatDAO;
import GUI.DatPhong;
import POJO.PhieuDat;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Vector;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author GreenDuy
 */
public class QLPhieuDatPhong extends javax.swing.JInternalFrame {

    /**
     * Creates new form QLPhieuDatPhong
     */
    Vector tblDataPD = new Vector();
    Vector tblTitlePD = new Vector();
    DefaultTableModel tblModePD;
    
    String action;
    
    static ArrayList<PhieuDat> dspd = PhieuDatDAO.getDSPD();
    public QLPhieuDatPhong() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        BasicInternalFrameUI bui = (BasicInternalFrameUI) this.getUI();
        bui.setNorthPane(null);
        
        //mac dinh
        dateNgayDat.setDateFormatString("yyyy-MM-dd");
        dateNgayTra.setDateFormatString("yyyy-MM-dd");
        setControl();

        tblPhieuDat.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tblPhieuDat.getTableHeader().setBackground(new Color(0, 153, 51));
        tblPhieuDat.getTableHeader().setForeground(new Color(0, 153, 51));
        tblPhieuDat.setRowHeight(25);

        //set up
        bangnhapdien();
        loadDataPhieuNhat(dspd);
        loadCBMaPhong();
        cbMaPhong.setSelectedIndex(-1);
        loadCBMaKH();
        cbMakH.setSelectedIndex(-1);
        
        //format date jchooser
        dateNgayDat.setDateFormatString("yyyy-MM-dd");
        dateNgayTra.setDateFormatString("yyyy-MM-dd");
    }
  
    public final void setControl()
    {
        txtMaPD.setEnabled(false);
        cbMaPhong.setEnabled(false);
        cbMakH.setEnabled(false);
        spnSoNguoi.setEnabled(false);
        dateNgayDat.setEnabled(false);
        dateNgayTra.setEnabled(false);

        btnSave.setEnabled(false);
    }
    public final void bangnhapdien(){
        tblTitlePD.add("Mã phiếu");
        tblTitlePD.add("Mã phòng");
        tblTitlePD.add("Mã KH");
        tblTitlePD.add("Ngày đặt");
        tblTitlePD.add("Ngày ra");
        tblTitlePD.add("Số người");       
    }
    
    public final void loadDataPhieuNhat(ArrayList<PhieuDat> dspd){
        tblDataPD.removeAllElements();
        for(PhieuDat pd : dspd){
            Vector v = new Vector();
            v.add(pd.getMapd()); 
            v.add(pd.getMaphong()); 
            v.add(pd.getMakh()); 
            v.add(pd.getNgaydat());
            v.add(pd.getNgaytra());
            v.add(pd.getSonguoi());
            tblDataPD.add(v);
        }
        tblModePD = new DefaultTableModel(tblDataPD, tblTitlePD);
        tblPhieuDat.setModel(tblModePD);
    }
    public final void loadCBMaPhong()
    {
        cbMaPhong.removeAllItems();
        String sql = "SELECT maphong FROM phong ORDER BY maphong";
        ConnectionDB conn = new ConnectionDB();
        conn.getCn();
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) 
            {
                 String maphong = rs.getString(1).trim();
                cbMaPhong.addItem(maphong);
            }
        }
            
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public final void loadCBMaKH()
    {
        cbMakH.removeAllItems();
        String sql = "SELECT makh FROM KHACHHANG ORDER BY makh";
        ConnectionDB conn = new ConnectionDB();
        conn.getCn();
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) 
            {
                cbMakH.addItem(rs.getString(1).trim());
            }
        }
            
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    public void setEnabledEdit()
    {
        if(action.equals("them"))
        {
            txtMaPD.setEnabled(true);
            cbMaPhong.setEnabled(true);
            cbMakH.setEnabled(true);
            spnSoNguoi.setEnabled(true);
            dateNgayDat.setEnabled(true);
            dateNgayTra.setEnabled(true);
            
            btnSua.setEnabled(false);
            btnXoa.setEnabled(false);
            btnSave.setEnabled(true);
        }
        else if(action.equals("xoa"))
        {
            txtMaPD.setEnabled(false);
            cbMaPhong.setEnabled(false);
            cbMakH.setEnabled(false);
            spnSoNguoi.setEnabled(false);
            dateNgayDat.setEnabled(false);
            dateNgayTra.setEnabled(false);
            
            btnSua.setEnabled(false);
            btnThem.setEnabled(false);
            btnSave.setEnabled(true);
        }
        else if(action.equals("sua"))
        {
            txtMaPD.setEnabled(true);
            cbMaPhong.setEnabled(true);
            cbMakH.setEnabled(true);
            spnSoNguoi.setEnabled(true);
            dateNgayDat.setEnabled(true);
            dateNgayTra.setEnabled(true);
            
            btnSua.setEnabled(false);
            btnXoa.setEnabled(false);
            btnSave.setEnabled(true);
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

        jLabel3 = new javax.swing.JLabel();
        panelRound2 = new CUSTOM.PanelRound();
        panelRound1 = new CUSTOM.PanelRound();
        dateNgayTra = new com.toedter.calendar.JDateChooser();
        dateNgayDat = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbMaPhong = new javax.swing.JComboBox<>();
        txtMaPD = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbMakH = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        spnSoNguoi = new javax.swing.JSpinner();
        btnDatPhong = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        panelRound3 = new CUSTOM.PanelRound();
        btnSave = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txt_TimKiemPhieu = new javax.swing.JTextField();
        btn_TimPhieu = new javax.swing.JButton();
        panelRound4 = new CUSTOM.PanelRound();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhieuDat = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("QUẢN LÝ PHIẾU ĐẶT PHÒNG");

        panelRound2.setBackground(new java.awt.Color(255, 255, 255));
        panelRound2.setRoundBottomLeft(20);
        panelRound2.setRoundBottomRight(20);
        panelRound2.setRoundTopLeft(20);
        panelRound2.setRoundTopRight(20);

        panelRound1.setRoundBottomLeft(20);
        panelRound1.setRoundBottomRight(20);
        panelRound1.setRoundTopLeft(20);
        panelRound1.setRoundTopRight(20);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/date-20.png"))); // NOI18N
        jLabel12.setText("Ngày đặt phòng:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/date-20.png"))); // NOI18N
        jLabel9.setText("Ngày trả phòng:");

        cbMaPhong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbMaPhong.setSelectedIndex(-1);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/sigup.png"))); // NOI18N
        jLabel8.setText(" Mã phiếu đặt:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/room_black.png"))); // NOI18N
        jLabel7.setText("Mã phòng:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/id-card-24.png"))); // NOI18N
        jLabel10.setText("Mã khách hàng:");

        cbMakH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbMakH.setSelectedIndex(-1);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/people-24.png"))); // NOI18N
        jLabel6.setText("Số người:");

        btnDatPhong.setBackground(new java.awt.Color(61, 168, 82));
        btnDatPhong.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnDatPhong.setForeground(new java.awt.Color(255, 255, 255));
        btnDatPhong.setText("Đặt phòng");
        btnDatPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatPhongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spnSoNguoi, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cbMakH, javax.swing.GroupLayout.Alignment.TRAILING, 0, 277, Short.MAX_VALUE)
                        .addComponent(cbMaPhong, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtMaPD, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateNgayDat, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateNgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(218, 218, 218)
                        .addComponent(btnDatPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(564, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtMaPD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cbMaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(dateNgayDat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(jLabel9)
                        .addGap(2, 2, 2))
                    .addComponent(dateNgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(cbMakH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spnSoNguoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnDatPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 153, 51));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Thông tin chi tiết");

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel14)
                .addGap(10, 10, 10)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        panelRound3.setBackground(new java.awt.Color(255, 255, 255));
        panelRound3.setRoundBottomLeft(20);
        panelRound3.setRoundBottomRight(20);
        panelRound3.setRoundTopLeft(20);
        panelRound3.setRoundTopRight(20);

        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save-24.png"))); // NOI18N
        btnSave.setText("Lưu");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnRefresh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh.png"))); // NOI18N
        btnRefresh.setText("Làm mới");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        btnXoa.setText("Xoá");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/edit.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Từ khoá:");

        btn_TimPhieu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_TimPhieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        btn_TimPhieu.setText("Search");
        btn_TimPhieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimPhieuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnSave)
                .addGap(50, 50, 50)
                .addComponent(jLabel4)
                .addGap(10, 10, 10)
                .addComponent(txt_TimKiemPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(btn_TimPhieu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txt_TimKiemPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_TimPhieu))
                    .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );

        panelRound4.setBackground(new java.awt.Color(255, 255, 255));
        panelRound4.setRoundBottomLeft(20);
        panelRound4.setRoundBottomRight(20);
        panelRound4.setRoundTopLeft(20);
        panelRound4.setRoundTopRight(20);

        tblPhieuDat.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tblPhieuDat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblPhieuDat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblPhieuDat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhieuDatMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPhieuDat);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 153, 51));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Danh sách phiếu đặt phòng");

        javax.swing.GroupLayout panelRound4Layout = new javax.swing.GroupLayout(panelRound4);
        panelRound4.setLayout(panelRound4Layout);
        panelRound4Layout.setHorizontalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1551, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound4Layout.setVerticalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel15)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel3)
                .addGap(20, 20, 20)
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        action = "them";
        setEnabledEdit();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        action = "sua";
        setEnabledEdit();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here
        action = "xoa";
        //tạo 1 cái model tạm để lưu từ model nhân viên
        DefaultTableModel tblModelTam = (DefaultTableModel) tblPhieuDat.getModel();
        if(tblPhieuDat.getSelectedRowCount() == 1)
        {
            if(JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa không?", "Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            {
                tblModelTam.removeRow(tblPhieuDat.getSelectedRow());
            }
        }
        else
        {
            if(tblPhieuDat.getRowCount() == 0)
            {
                JOptionPane.showMessageDialog(rootPane, "Bảng rỗng");
            }
            else
            {
                JOptionPane.showMessageDialog(rootPane, "Hãy chọn dòng cần xoá!");
            }
        }
      
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        //mac dinh
        txtMaPD.setEnabled(false);
        cbMaPhong.setEnabled(false);
        cbMakH.setEnabled(false);
        spnSoNguoi.setEnabled(false);
        dateNgayDat.setEnabled(false);
        dateNgayTra.setEnabled(false);

        btnThem.setEnabled(true);
        btnXoa.setEnabled(true);
        btnSua.setEnabled(true);
        btnSave.setEnabled(false);
        
        dspd = PhieuDatDAO.getDSPD();
        loadDataPhieuNhat(dspd);
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        String ngaydat = ((JTextField)dateNgayDat.getDateEditor().getUiComponent()).getText();
        String ngaytra = ((JTextField)dateNgayTra.getDateEditor().getUiComponent()).getText();
        String songuoi = spnSoNguoi.getValue().toString();
        if(action.equals("them"))
        {
            if(txtMaPD.getText().equals("") || txtMaPD.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Thông báo", 2);
            }
            else
            {
                try
                {
                    String lenhsql = "insert into PhieuDat(MaPD, MAPHONG, MAKH, NGAYDATPHONG,  NGAYTRAPHONG, SONGUOI) " +
                    "values ('"+txtMaPD.getText()+"', N'"+cbMaPhong.getSelectedItem().toString().trim()+"', 'N"+cbMakH.getSelectedItem().toString().trim()+"', '"+ngaydat+"'"
                    + ", '"+ngaytra+"', '"+songuoi+"')";
                    int kq=PhieuDatDAO.ThemXoaSuaPhieuDat(lenhsql);
                    if(kq!=-1)
                    {
                        dspd = PhieuDatDAO.getDSPD();
                        loadDataPhieuNhat(dspd);
                        JOptionPane.showMessageDialog(this, "Thêm thành công!", "Thông báo", 2);
                    }
                    else
                    JOptionPane.showMessageDialog(this, "Mã nhân viên này đã tồn tại, không thể thêm", "Thông báo", 2);
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
            }

        }
        else if(action.equals("xoa"))
        {
            String sql = "delete from PhieuDat where MaPD = '"+txtMaPD.getText().trim()+"'";
            if(PhieuDatDAO.ThemXoaSuaPhieuDat(sql)!=-1)
            {
                dspd = PhieuDatDAO.getDSPD();
                loadDataPhieuNhat(dspd);
                JOptionPane.showMessageDialog(this, "Xoá thành công!", "Thông báo", 2);
            }
            else
            JOptionPane.showMessageDialog(this, "Dữ liệu này đang được sử dụng, không thể xóa", "Thông báo", 2);
        }
        else if(action.equals("sua"))
        {
            try{
                String sql = "UPDATE DATPHONG SET MAPHONG = '"+cbMaPhong.getSelectedItem().toString().trim()+"', MAKH = '"+cbMakH.getSelectedItem().toString().trim()
                        +"', NGAYDATPHONG = '"+ngaydat+"', NGAYTRAPHONG = '"+ngaytra+"', SONGUOI = '"+songuoi+"' WHERE MADP = '"+txtMaPD.getText().trim()+"'";
                PhieuDatDAO.ThemXoaSuaPhieuDat(sql);
                dspd = PhieuDatDAO.getDSPD();
                loadDataPhieuNhat(dspd);
                JOptionPane.showMessageDialog(this, "Sửa thành công!", "Thông báo", 2);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        //set lại mặc định
        txtMaPD.setEnabled(false);
        cbMaPhong.setEnabled(false);
        cbMakH.setEnabled(false);
        spnSoNguoi.setEnabled(false);
        dateNgayDat.setEnabled(false);
        dateNgayTra.setEnabled(false);

        btnSave.setEnabled(false);
        btnThem.setEnabled(true);
        btnXoa.setEnabled(true);
        btnSua.setEnabled(true);
    }//GEN-LAST:event_btnSaveActionPerformed

    private void tblPhieuDatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuDatMouseClicked
        try {
            // TODO add your handling code here:
            int i = tblPhieuDat.getSelectedRow();
            String mapd = tblPhieuDat.getValueAt(i, 0).toString().trim();
            String maphong = tblPhieuDat.getValueAt(i, 1).toString().trim();
            String makh = tblPhieuDat.getValueAt(i, 2).toString().trim();
            String ngaydat = tblPhieuDat.getValueAt(i, 3).toString().trim();
            String ngaytra = tblPhieuDat.getValueAt(i, 4).toString().trim();
            String songuoi = tblPhieuDat.getValueAt(i, 5).toString().trim();
            int sn = Integer.parseInt(songuoi);
            
            txtMaPD.setText(mapd);
            spnSoNguoi.setValue(sn);
            cbMakH.setSelectedItem(makh.trim());
            cbMaPhong.setSelectedItem(maphong.trim());
            //xử lý binding ngày
            Date datengaydat = new SimpleDateFormat("yyyy-MM-dd").parse(ngaydat);
            dateNgayDat.setDate(datengaydat);
            Date datengaytra = new SimpleDateFormat("yyyy-MM-dd").parse(ngaytra);
            dateNgayTra.setDate(datengaytra);
//            "yyyy-MM-dd"
//             "dd-MM-yyy"
            
        } catch (ParseException ex) {
            Logger.getLogger(QLPhieuDatPhong.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_tblPhieuDatMouseClicked

    private void btnDatPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatPhongActionPerformed
        // TODO add your handling code here:
        DatPhong f = new DatPhong();
        f.setLocationRelativeTo(null); // this method display the JFrame to center position of a screen
        f.setVisible(true);
    }//GEN-LAST:event_btnDatPhongActionPerformed

    private void btn_TimPhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimPhieuActionPerformed
        ArrayList<PhieuDat> timphieu= new  ArrayList<PhieuDat>();
        timphieu = PhieuDatDAO.TimDSPhieu(txt_TimKiemPhieu.getText());
        loadDataPhieuNhat(timphieu);
    }//GEN-LAST:event_btn_TimPhieuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDatPhong;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btn_TimPhieu;
    private javax.swing.JComboBox<String> cbMaPhong;
    private javax.swing.JComboBox<String> cbMakH;
    private com.toedter.calendar.JDateChooser dateNgayDat;
    private com.toedter.calendar.JDateChooser dateNgayTra;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private CUSTOM.PanelRound panelRound1;
    private CUSTOM.PanelRound panelRound2;
    private CUSTOM.PanelRound panelRound3;
    private CUSTOM.PanelRound panelRound4;
    private javax.swing.JSpinner spnSoNguoi;
    private javax.swing.JTable tblPhieuDat;
    private javax.swing.JTextField txtMaPD;
    private javax.swing.JTextField txt_TimKiemPhieu;
    // End of variables declaration//GEN-END:variables
}

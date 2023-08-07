/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package MDI;

import DAO.ConnectionDB;
import DAO.HoaDonDAO;
import GUI.ThongTinChiTietPhong;
import POJO.HoaDon;
import POJO.HoaDon;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author GreenDuy
 */
public class QLHoaDon extends javax.swing.JInternalFrame {

    /**
     * Creates new form QLHoaDon
     */
    Vector tblDataHoaDon = new Vector();
    Vector tblTitleHoaDon = new Vector();
    DefaultTableModel tblModeHoaDon;
    
    String action;
    
    static ArrayList<HoaDon> dshd = HoaDonDAO.getDSHD();
    
    public QLHoaDon() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        BasicInternalFrameUI bui = (BasicInternalFrameUI) this.getUI();
        bui.setNorthPane(null);
        
        tblHoaDon.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tblHoaDon.getTableHeader().setBackground(new Color(0, 153, 51));
        tblHoaDon.getTableHeader().setForeground(new Color(0, 153, 51));
        tblHoaDon.setRowHeight(25);
        
        dateNgapLap.setDateFormatString("yyyy-MM-dd");
        bangHoaDon();
        laydulieuHoaDon(dshd);
        
        loadCBMaKH();
        loadCBMaNV();
        loadCBMaDP();
        loadCBMaPhong();
        
        cbMaDP.setSelectedIndex(-1);
        cbMaKH.setSelectedIndex(-1);
        cbMaNV.setSelectedIndex(-1);
        cbMaPhong.setSelectedIndex(-1);
        
        setControls();
    }

    public final void setControls()
    {
        txtMaHD.setEnabled(false);
        cbMaDP.setEnabled(false);
        cbMaKH.setEnabled(false);
        cbMaPhong.setEnabled(false);
        cbMaNV.setEnabled(false);
        dateNgapLap.setEnabled(false);
        txtThanhTien.setEnabled(false);
        
        btnSave.setEnabled(false);
    }
    public final void bangHoaDon(){
        tblTitleHoaDon.add("Mã HD");
        tblTitleHoaDon.add("Mã ĐP");
        tblTitleHoaDon.add("Mã KH");
        tblTitleHoaDon.add("Mã Phòng");
        tblTitleHoaDon.add("Ngày Lập");
        tblTitleHoaDon.add("Tổng Tiền");
        tblTitleHoaDon.add("Mã NV");
    }
    
    public final void laydulieuHoaDon(ArrayList<HoaDon> dsp){
        tblDataHoaDon.removeAllElements();
        for(HoaDon hd : dshd){
            Vector v = new Vector();
            v.add(hd.getMahd()); 
            v.add(hd.getMadp());
            v.add(hd.getMakh());
            v.add(hd.getMaphong());
            v.add(hd.getNgaylaphd());
            v.add(hd.getTongtienhd());
            v.add(hd.getManv());
            tblDataHoaDon.add(v);
        }
        tblModeHoaDon = new DefaultTableModel(tblDataHoaDon, tblTitleHoaDon);
        tblHoaDon.setModel(tblModeHoaDon);
    }
    public final void loadCBMaKH()
    {
        cbMaKH.removeAllItems();
        String sql = "SELECT MAKH FROM KHACHHANG";
        ConnectionDB conn = new ConnectionDB();
        conn.getCn();
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) 
            {
                String a = rs.getString(1).trim();
                cbMaKH.addItem(a);
            }
        }
            
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    public final void loadCBMaNV()
    {
        cbMaNV.removeAllItems();
        String sql = "SELECT MANV FROM NHANVIEN";
        ConnectionDB conn = new ConnectionDB();
        conn.getCn();
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) 
            {
                String a = rs.getString(1).trim();
                cbMaNV.addItem(a);
            }
        }
            
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    public final void loadCBMaDP()
    {
        cbMaDP.removeAllItems();
        String sql = "SELECT MADP FROM DATPHONG";
        ConnectionDB conn = new ConnectionDB();
        conn.getCn();
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) 
            {
                String a = rs.getString(1).trim();
                cbMaDP.addItem(a);
            }
        }
            
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    public final void loadCBMaPhong()
    {
        cbMaPhong.removeAllItems();
        String sql = "SELECT MAPHONG FROM PHONG";
        ConnectionDB conn = new ConnectionDB();
        conn.getCn();
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) 
            {
                String a = rs.getString(1).trim();
                cbMaPhong.addItem(a);
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
        cbMaDP.setEnabled(true);
        cbMaKH.setEnabled(true);
        cbMaPhong.setEnabled(true);
        cbMaNV.setEnabled(true);
        dateNgapLap.setEnabled(true);
        txtThanhTien.setEnabled(true);
            
            btnSave.setEnabled(true);
            btnSua.setEnabled(false);
            btnXoa.setEnabled(false);

        }
        else if(action.equals("xoa"))
        {
        cbMaDP.setEnabled(false);
        cbMaKH.setEnabled(false);
        cbMaPhong.setEnabled(false);
        cbMaNV.setEnabled(false);
        dateNgapLap.setEnabled(false);
        txtThanhTien.setEnabled(false);

            btnSave.setEnabled(true);
            btnSua.setEnabled(false);
            btnThem.setEnabled(false);

        }
        else if(action.equals("sua"))
        {
        cbMaDP.setEnabled(true);
        cbMaKH.setEnabled(true);
        cbMaPhong.setEnabled(true);
        cbMaNV.setEnabled(true);
        dateNgapLap.setEnabled(true);
        txtThanhTien.setEnabled(true);
            
            btnSave.setEnabled(true);
            btnSua.setEnabled(false);
            btnXoa.setEnabled(false);
        }
        
    }
    public void inHoaDon()
    {
        try {
            Hashtable map = new Hashtable();
            JasperReport rpt = JasperCompileManager.compileReport("src/REPORT/rptHoaDon2.jrxml");
            int mahd = Integer.parseInt(txtMaHD.getText().trim());
            map.put("rptmahd", mahd);
            ConnectionDB conn = new ConnectionDB();

            JasperPrint p = JasperFillManager.fillReport(rpt, map, conn.getCn());
            JasperViewer.viewReport(p, false);
        } catch (JRException ex) {
            Logger.getLogger(ThongTinChiTietPhong.class.getName()).log(Level.SEVERE, null, ex);
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

        jLabel1 = new javax.swing.JLabel();
        panelRound2 = new CUSTOM.PanelRound();
        panelRound1 = new CUSTOM.PanelRound();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbMaPhong = new javax.swing.JComboBox<>();
        cbMaKH = new javax.swing.JComboBox<>();
        cbMaDP = new javax.swing.JComboBox<>();
        txtMaHD = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cbMaNV = new javax.swing.JComboBox<>();
        dateNgapLap = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtThanhTien = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        panelRound3 = new CUSTOM.PanelRound();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        panelRound4 = new CUSTOM.PanelRound();
        btnXoa = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txt_TimKiemHoaDon = new javax.swing.JTextField();
        btn_TimHoaDon = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 51));
        jLabel1.setText("QUẢN LÝ HÓA ĐƠN");

        panelRound2.setBackground(new java.awt.Color(255, 255, 255));
        panelRound2.setRoundBottomLeft(20);
        panelRound2.setRoundBottomRight(20);
        panelRound2.setRoundTopLeft(20);
        panelRound2.setRoundTopRight(20);

        panelRound1.setRoundBottomLeft(20);
        panelRound1.setRoundBottomRight(20);
        panelRound1.setRoundTopLeft(20);
        panelRound1.setRoundTopRight(20);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bill-24.png"))); // NOI18N
        jLabel2.setText("Mã hóa đơn:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/id-card-24.png"))); // NOI18N
        jLabel5.setText("Mã đặt phòng:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/id-card-24.png"))); // NOI18N
        jLabel10.setText("Mã khách hàng:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/id-card-24.png"))); // NOI18N
        jLabel12.setText("Mã phòng:");

        cbMaPhong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbMaPhong.setSelectedIndex(-1);

        cbMaKH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbMaKH.setSelectedIndex(-1);

        cbMaDP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbMaDP.setSelectedIndex(-1);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/id-card-24.png"))); // NOI18N
        jLabel9.setText("Mã nhân viên:");

        cbMaNV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbMaNV.setSelectedIndex(-1);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/date-20.png"))); // NOI18N
        jLabel7.setText(" Ngày lập : ");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/price-24.png"))); // NOI18N
        jLabel8.setText("Thành tiền :");

        txtThanhTien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtThanhTienKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel10)
                    .addComponent(jLabel2)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaHD)
                    .addComponent(cbMaDP, 0, 240, Short.MAX_VALUE)
                    .addComponent(cbMaKH, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbMaPhong, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(141, 141, 141)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dateNgapLap, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(695, 695, 695))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(cbMaDP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(12, 12, 12)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(cbMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(cbMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(dateNgapLap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cbMaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Thông tin chi tiết");

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel3)
                .addGap(10, 10, 10)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        panelRound3.setBackground(new java.awt.Color(255, 255, 255));
        panelRound3.setRoundBottomLeft(20);
        panelRound3.setRoundBottomRight(20);
        panelRound3.setRoundTopLeft(20);
        panelRound3.setRoundTopRight(20);

        tblHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 51));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Danh sách hóa đơn");

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1515, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel6)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        panelRound4.setBackground(new java.awt.Color(255, 255, 255));
        panelRound4.setRoundBottomLeft(20);
        panelRound4.setRoundBottomRight(20);
        panelRound4.setRoundTopLeft(20);
        panelRound4.setRoundTopRight(20);

        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        btnXoa.setText("Xoá");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
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

        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/edit.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
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

        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save-24.png"))); // NOI18N
        btnSave.setText("Lưu");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("Từ khoá:");

        btn_TimHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_TimHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        btn_TimHoaDon.setText("Search");
        btn_TimHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimHoaDonActionPerformed(evt);
            }
        });

        btnPrint.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/print-24.png"))); // NOI18N
        btnPrint.setText("In hoá đơn");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound4Layout = new javax.swing.GroupLayout(panelRound4);
        panelRound4.setLayout(panelRound4Layout);
        panelRound4Layout.setHorizontalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnRefresh)
                .addGap(30, 30, 30)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jLabel14)
                .addGap(10, 10, 10)
                .addComponent(txt_TimKiemHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_TimHoaDon)
                .addGap(30, 30, 30)
                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelRound4Layout.setVerticalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_TimHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)
                        .addComponent(txt_TimKiemHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1)
                        .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelRound4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        try {
            int i = tblHoaDon.getSelectedRow();
            TableModel model = tblHoaDon.getModel();
            
            txtMaHD.setText(model.getValueAt(i, 0).toString());
            
            String madp = model.getValueAt(i, 1).toString();
            cbMaDP.setSelectedItem(madp.trim());
            
            String makh = model.getValueAt(i, 2).toString();
            cbMaKH.setSelectedItem(makh.trim());
            
            String maphong = model.getValueAt(i, 3).toString();
            cbMaPhong.setSelectedItem(maphong.trim());
            
            String ngaylap = model.getValueAt(i, 4).toString().trim();
            Date datengaylap = new SimpleDateFormat("yyyy-MM-dd").parse(ngaylap);
            dateNgapLap.setDate(datengaylap);
            
            txtThanhTien.setText(model.getValueAt(i, 5).toString());
            
            String manv = model.getValueAt(i, 6).toString();
            cbMaNV.setSelectedItem(manv.trim());
        } catch (ParseException ex) {
            Logger.getLogger(QLHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

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
        //tạo 1 cái model tạm để lưu từ model HoaDon
        DefaultTableModel tblModelTam = (DefaultTableModel) tblHoaDon.getModel();
        if(tblHoaDon.getSelectedRowCount() == 1)
        {
            if(JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa không?", "Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            {
                tblModelTam.removeRow(tblHoaDon.getSelectedRow());
            }
        }
        else
        {
            if(tblHoaDon.getRowCount() == 0)
            {
                JOptionPane.showMessageDialog(rootPane, "Bảng rỗng");
            }
            else
            {
                JOptionPane.showMessageDialog(rootPane, "Hãy chọn dòng cần xoá!");
            }
        }
        setEnabledEdit();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        //mac dinh

        cbMaDP.setEnabled(false);
        cbMaKH.setEnabled(false);
        cbMaPhong.setEnabled(false);
        cbMaNV.setEnabled(false);
        dateNgapLap.setEnabled(false);
        txtThanhTien.setEnabled(false);

        btnThem.setEnabled(true);
        btnXoa.setEnabled(true);
        btnSua.setEnabled(true);
        btnSave.setEnabled(false);

        laydulieuHoaDon(dshd);
        txtMaHD.setText("");
        cbMaDP.setSelectedIndex(-1);
        cbMaKH.setSelectedIndex(-1);
        cbMaNV.setSelectedIndex(-1);
        cbMaPhong.setSelectedIndex(-1);
        txtThanhTien.setText("");
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        if(action.equals("them"))
        {
            if(txtThanhTien.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Thông báo", 2);
            }
            else
            {
                try
                {
                    String madp = cbMaDP.getSelectedItem().toString().trim();
                    String makh = cbMaKH.getSelectedItem().toString().trim();
                    String maphong = cbMaPhong.getSelectedItem().toString().trim();
                    String manv = cbMaNV.getSelectedItem().toString().trim();
                    String ngaylap = ((JTextField)dateNgapLap.getDateEditor().getUiComponent()).getText();
                    String tongtien = txtThanhTien.getText().trim();
                    
                    String sql = "SET DATEFORMAT DMY INSERT INTO HOADON(MADP, MAKH, MAPHONG, NGAYLAPHD, TONGTIENHD, MANV) "
                            + "VALUES('"+madp.trim()+"', '"+makh.trim()+"', '"+maphong.trim()+"', '"+ngaylap.trim()+"', "+tongtien+", '"+manv.trim()+"')";
                    int kq=HoaDonDAO.ThemXoaSuaHoaDon(sql);
                    if(kq!=-1)
                    {
                        dshd = HoaDonDAO.getDSHD();
                        laydulieuHoaDon(dshd);
                        JOptionPane.showMessageDialog(this, "Thêm thành công!", "Thông báo", 2);
                    }
                    else
                    JOptionPane.showMessageDialog(this, sql, "Thông báo", 2);
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
            }

        }
        else if(action.equals("xoa"))
        {
            String sql = "delete from HOADON where MAHD = '"+txtMaHD.getText().trim()+"'";
            if(HoaDonDAO.ThemXoaSuaHoaDon(sql)!=-1)
            {
                dshd = HoaDonDAO.getDSHD();
                laydulieuHoaDon(dshd);
                JOptionPane.showMessageDialog(this, "Xoá thành công!", "Thông báo", 2);
            }
            else
            JOptionPane.showMessageDialog(this, "Dữ liệu này đang được sử dụng, không thể xóa", "Thông báo", 2);
        }
        else if(action.equals("sua"))
        {
            String madp = cbMaDP.getSelectedItem().toString().trim();
                    String makh = cbMaKH.getSelectedItem().toString().trim();
                    String maphong = cbMaPhong.getSelectedItem().toString().trim();
                    String manv = cbMaNV.getSelectedItem().toString().trim();
                    String ngaylap = ((JTextField)dateNgapLap.getDateEditor().getUiComponent()).getText();
                    String tongtien = txtThanhTien.getText().trim();
                    String mahd = txtMaHD.getText().trim();
                    
                    
                    
                    String sql = "UPDATE HOADON SET MADP = '"+madp+"', MAKH = '"+makh+"', MAPHONG = '"+maphong+"', NGAYLAPHD = '"+ngaylap.trim()+"', TONGTIENHD = "+tongtien+", MANV = '"+manv+"' WHERE MAHD = "+mahd+"";
            try{
                
                HoaDonDAO.ThemXoaSuaHoaDon(sql);
                dshd = HoaDonDAO.getDSHD();
                laydulieuHoaDon(dshd);
                JOptionPane.showMessageDialog(this, "Sửa thành công!", "Thông báo", 2);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, sql, "Thông báo", 2);
                System.out.println(e);
            }
        }

        //set lại mặc định
        cbMaDP.setEnabled(false);
        cbMaKH.setEnabled(false);
        cbMaPhong.setEnabled(false);
        cbMaNV.setEnabled(false);
        dateNgapLap.setEnabled(false);
        txtThanhTien.setEnabled(false);

        btnSave.setEnabled(false);
        btnThem.setEnabled(true);
        btnXoa.setEnabled(true);
        btnSua.setEnabled(true);
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btn_TimHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimHoaDonActionPerformed
        ArrayList<HoaDon> timhoadon= new  ArrayList<HoaDon>();
        timhoadon = HoaDonDAO.TimDSHoaDon(txt_TimKiemHoaDon.getText());
        laydulieuHoaDon(timhoadon);
    }//GEN-LAST:event_btn_TimHoaDonActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
        inHoaDon();
    }//GEN-LAST:event_btnPrintActionPerformed

    private void txtThanhTienKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtThanhTienKeyTyped
   String sdt = txtThanhTien.getText();
        if (sdt.matches(".*[a-zA-Z].*")) 
        {
            JOptionPane.showMessageDialog(this, "Thành tiền không chứa ký tự");
            txtThanhTien.setText("");
        
        }
    }//GEN-LAST:event_txtThanhTienKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btn_TimHoaDon;
    private javax.swing.JComboBox<String> cbMaDP;
    private javax.swing.JComboBox<String> cbMaKH;
    private javax.swing.JComboBox<String> cbMaNV;
    private javax.swing.JComboBox<String> cbMaPhong;
    private com.toedter.calendar.JDateChooser dateNgapLap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private CUSTOM.PanelRound panelRound1;
    private CUSTOM.PanelRound panelRound2;
    private CUSTOM.PanelRound panelRound3;
    private CUSTOM.PanelRound panelRound4;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txt_TimKiemHoaDon;
    // End of variables declaration//GEN-END:variables
}

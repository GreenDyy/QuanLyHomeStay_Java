/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import DAO.ConnectionDB;
import DAO.NhanVienDAO;
import DAO.PhongDAO;
import POJO.TrungGian;
import java.awt.geom.RoundRectangle2D;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
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
public class ThongTinChiTietPhong extends javax.swing.JFrame {
    Vector tblDataDV = new Vector();
    Vector tblTitleDV = new Vector();
    DefaultTableModel tblModeDV;
    
    
    String maphong = TrungGian.getMaPhong();
    String tennv = TrungGian.getTenNV();
    String makh = "";
    
    double giaphong = 0;
    int songuoi = 0;
    int songay = 0;
    
    //
    double tienphong = 0;
    double tiendichvu = 0;
    

    //thông tin để add vào hoá đơn
    String ngayvao = "";
    String ngayra = "";
    String madp = "";
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
    LocalDateTime now = LocalDateTime.now();  
    String ngaylap = dtf.format(now);
    String manv = "";
    double tongtien = 0;
    
    /**
     * Creates new form ThongTinChiTietPhong
     */
    public ThongTinChiTietPhong() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // this method display the JFrame to center position of a screen
        setShape(new RoundRectangle2D.Double(0 , 0, getWidth(), getHeight(), 50 ,50));
        
        
        loadChiTietPhong(maphong);
        loadDichVuPhongDangSuDung(maphong);
        
        duLieuTinhTienPhong(); //tien phong cap nhat tai day
        duLieuTinhTienDichVu(); //tien dichvu cap nhat tai day
        getMaDatPhong();
        getMaNV();

        tongtien = tienphong + tiendichvu; 
    }
    public final void loadChiTietPhong(String maphong)
    {
        ConnectionDB conn = new ConnectionDB();
        conn.getCn();
        String sql = "select TENPHONG, TENKH, SONGUOI, GIAPHONG, NGAYDATPHONG, NGAYTRAPHONG, KHACHHANG.MAKH from DATPHONG, KHACHHANG, PHONG "
                + "where DATPHONG.MAKH = KHACHHANG.MAKH and DATPHONG.MAPHONG = PHONG.MAPHONG and PHONG.MAPHONG = '"+maphong+"'";
        ResultSet rs = conn.executeQuery(sql);
        try {
                while (rs.next()) {
                    lbRoomDetail.setText(maphong.trim() + " - " + rs.getString(1));
                    lbKhachHang.setText(rs.getString(2));
                    lbSoNguoi.setText(rs.getString(3));
                    lbGiaPhong.setText(rs.getString(4));
                    lbNgayVao.setText(rs.getString(5));
                    lbNgayRa.setText(rs.getString(6));
                    makh = rs.getString(7);
                    
                }
            }
            catch (Exception e) 
            {
                e.printStackTrace();
            }
    }
  
    public final void loadDichVuPhongDangSuDung(String maphong){
        tblTitleDV.add("Tên dịch vụ");
        tblTitleDV.add("Số lượng");
        tblTitleDV.add("Đơn giá");
        
        tblDataDV.removeAllElements();
        ConnectionDB conn = new ConnectionDB();
        conn.getCn();
        String sql = "SELECT TENDV, SL, GIADV FROM PHUCVU, DICHVU WHERE PHUCVU.MADV = DICHVU.MADV AND MAPHONG = '"+maphong.trim()+"'";
        ResultSet rs = conn.executeQuery(sql);
        
        try {
                while (rs.next()) {
                    Vector v = new Vector();
                    v.add(rs.getString(1));
                    v.add(rs.getString(2));
                    v.add(rs.getString(3));
                    tblDataDV.add(v);
                }
                tblModeDV = new DefaultTableModel(tblDataDV, tblTitleDV);
                tblDichVu.setModel(tblModeDV);
            }
            catch (Exception e) 
            {
                e.printStackTrace();
            }
    }
    //tinh tiền
    public final void duLieuTinhTienPhong()
    {
        String sql = "select PHONG.MAPHONG, GIAPHONG, SONGUOI, DATEDIFF(DAY, NGAYDATPHONG, NGAYTRAPHONG) AS SONGAY from PHONG, DATPHONG where PHONG.MAPHONG = DATPHONG.MAPHONG AND PHONG.MAPHONG = '"+maphong+"'";
        ConnectionDB conn = new ConnectionDB();
        conn.getCn();
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) 
            {
                maphong = rs.getString(1);
                giaphong = rs.getDouble(2);
                songuoi = rs.getInt(3);
                songay = rs.getInt(4);
                
                tienphong = (giaphong * songuoi) * (int)songay;
            }
        }  
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public final void duLieuTinhTienDichVu()
    {
        String sql = "SELECT PV.MAPHONG,DV.MADV, DV.TENDV, DV.GIADV,SUM(PV.SL) AS TONGSLDV\n" +
        "FROM DICHVU DV, PHUCVU PV \n" +
        "WHERE PV.MADV = DV.MADV AND PV.MAPHONG = '"+maphong+"'\n" +
        "GROUP BY PV.MAPHONG,DV.MADV, DV.TENDV, DV.GIADV";
        ConnectionDB conn = new ConnectionDB();
        conn.getCn();
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) 
            {
                double giadv = rs.getDouble(4);
                int soluong = rs.getInt(5);
                tiendichvu = tiendichvu + (giadv * soluong);
            }
        }  
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    public final void getMaNV()
    {
        String sql = "SELECT MANV FROM NHANVIEN WHERE TENNV = N'"+tennv+"' ";
        ConnectionDB conn = new ConnectionDB();
        conn.getCn();
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) 
            {
                manv = rs.getString(1);
            }
        }  
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    public final void getMaDatPhong()
    {
        String sql = "SELECT MADP from DATPHONG where MAKH = '"+makh+"'";
        ConnectionDB conn = new ConnectionDB();
        conn.getCn();
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) 
            {
                madp = rs.getString(1);
            }
        }  
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    public void inHoaDon()
    {
        try {
            Hashtable map = new Hashtable();
            JasperReport rpt = JasperCompileManager.compileReport("src/REPORT/rptHoaDon2.jrxml");
            int mahd = getMaHDLastInsert();
            map.put("rptmahd", mahd);
            ConnectionDB conn = new ConnectionDB();

            JasperPrint p = JasperFillManager.fillReport(rpt, map, conn.getCn());
            JasperViewer.viewReport(p, false);
        } catch (JRException ex) {
            Logger.getLogger(ThongTinChiTietPhong.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void nghiepVuTraPhong() //đầu tiên thêm hoá đơn, nếu thêm dc thì in ra hoá đơn rồi cập nhật trạng thái phòng
    {
        String lenhsql = "SET DATEFORMAT DMY INSERT INTO HOADON(MADP, MAKH, MAPHONG, NGAYLAPHD, TONGTIENHD, MANV) "
                            + "VALUES('"+madp.trim()+"', '"+makh.trim()+"', '"+maphong.trim()+"', '"+ngaylap.trim()+"', "+tongtien+", '"+manv.trim()+"')";
        try
        {
            int kq=NhanVienDAO.ThemXoaSuaNhanVien(lenhsql);
            if(kq!=-1)
            {
                capNhatTrangThaiPhong();
                JOptionPane.showMessageDialog(this, "Thanh toán thành công!", "Thông báo", 1);
                inHoaDon();
                
                this.dispose();
//                JFrame frame = TrungGian.getFrame();
////                frame.setVisible(false);
////                frame.setVisible(true);
//                frame.repaint();

            }
            else
                JOptionPane.showMessageDialog(this, "Đăng nhập đi ba, không đăng nhập sao lấy được mã nv?", "Thông báo", 2);
            }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, lenhsql, "Thông báo", 2);
        }
    }
    public int getMaHDLastInsert()
    {
        int mahd = 0;
        ConnectionDB conn = new ConnectionDB();
        conn.getCn();
        String sql = "select TOP 1 MAHD from HOADON ORDER BY MAHD DESC";
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) 
            {
                mahd = rs.getInt(1);
            }
        }  
            catch(Exception e)
            {
                System.out.println(e);
            }
        return mahd;
    }
    public void capNhatTrangThaiPhong()
    {
        try{
                String sql = "UPDATE PHONG SET TRANGTHAI = N'CÒN TRỐNG' WHERE MAPHONG = '"+maphong+"'";
                PhongDAO.ThemXoaSuaPhong(sql);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
    }
    public void xoaThongTinDatPhongCuaPhong()
    {
        try{
                String sql = "DELETE DATPHONG WHERE MAPHONG = '"+maphong+"'";
                PhongDAO.ThemXoaSuaPhong(sql);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
    }
    public void xoaCacPhucVuCuaPhong()
    {
        try{
                String sql = "DELETE PHUCVU WHERE MAPHONG = '"+maphong+"'";
                PhongDAO.ThemXoaSuaPhong(sql);
            }
            catch(Exception e)
            {
                System.out.println(e);
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

        jPanel1 = new javax.swing.JPanel();
        lbRoomDetail = new javax.swing.JLabel();
        btnClose = new javax.swing.JLabel();
        panelRound1 = new CUSTOM.PanelRound();
        lbdichvu = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDichVu = new javax.swing.JTable();
        btnThemDichVu = new javax.swing.JButton();
        panelRound2 = new CUSTOM.PanelRound();
        lbSoNguoi = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbKhachHang = new javax.swing.JLabel();
        lbRoomDetail2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbGiaPhong = new javax.swing.JLabel();
        lbNgayVao = new javax.swing.JLabel();
        lbNgayRa = new javax.swing.JLabel();
        btnTraPhong = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbRoomDetail.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbRoomDetail.setForeground(new java.awt.Color(0, 153, 0));
        lbRoomDetail.setText("LOẠI PHÒNG");

        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/close2.png"))); // NOI18N
        btnClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCloseMouseClicked(evt);
            }
        });

        panelRound1.setRoundBottomLeft(50);
        panelRound1.setRoundBottomRight(50);
        panelRound1.setRoundTopLeft(50);
        panelRound1.setRoundTopRight(50);

        lbdichvu.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbdichvu.setForeground(new java.awt.Color(0, 153, 0));
        lbdichvu.setText("Dịch vụ đang sử dụng");

        tblDichVu.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblDichVu);

        btnThemDichVu.setBackground(new java.awt.Color(0, 153, 0));
        btnThemDichVu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnThemDichVu.setForeground(new java.awt.Color(255, 255, 255));
        btnThemDichVu.setText("Thêm dịch vụ");
        btnThemDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDichVuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(lbdichvu))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(btnThemDichVu)))
                .addGap(74, 74, 74))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lbdichvu)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnThemDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelRound2.setRoundBottomLeft(50);
        panelRound2.setRoundBottomRight(50);
        panelRound2.setRoundTopLeft(50);
        panelRound2.setRoundTopRight(50);

        lbSoNguoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbSoNguoi.setText("...");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/date-20.png"))); // NOI18N
        jLabel5.setText(" Ngày ra:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/date-20.png"))); // NOI18N
        jLabel4.setText(" Ngày vào:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/people-24.png"))); // NOI18N
        jLabel6.setText("Số người:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/name-24.png"))); // NOI18N
        jLabel3.setText(" Khách hàng: ");

        lbKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbKhachHang.setText("...");

        lbRoomDetail2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbRoomDetail2.setForeground(new java.awt.Color(0, 153, 0));
        lbRoomDetail2.setText("Thông tin phòng");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/price-24.png"))); // NOI18N
        jLabel7.setText("Giá phòng:");

        lbGiaPhong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbGiaPhong.setForeground(new java.awt.Color(255, 0, 51));
        lbGiaPhong.setText("...");

        lbNgayVao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbNgayVao.setText("...");

        lbNgayRa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbNgayRa.setText("...");

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(lbRoomDetail2)
                .addContainerGap())
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(27, 27, 27)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound2Layout.createSequentialGroup()
                        .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbSoNguoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelRound2Layout.createSequentialGroup()
                                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbGiaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(47, 47, 47))
                    .addGroup(panelRound2Layout.createSequentialGroup()
                        .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNgayRa, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNgayVao, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lbRoomDetail2)
                .addGap(41, 41, 41)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lbKhachHang))
                .addGap(20, 20, 20)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(lbSoNguoi, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lbGiaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lbNgayVao))
                .addGap(20, 20, 20)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lbNgayRa))
                .addGap(21, 21, 21))
        );

        btnTraPhong.setBackground(new java.awt.Color(0, 153, 0));
        btnTraPhong.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnTraPhong.setForeground(new java.awt.Color(255, 255, 255));
        btnTraPhong.setText("Thanh toán");
        btnTraPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraPhongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(424, 424, 424)
                .addComponent(lbRoomDetail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnClose)
                .addGap(17, 17, 17))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(338, 338, 338)
                        .addComponent(btnTraPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClose)
                    .addComponent(lbRoomDetail))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnTraPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCloseMouseClicked

    private void btnThemDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDichVuActionPerformed
        // TODO add your handling code here:
        this.dispose();
        ThemDichVu f = new ThemDichVu();
        f.setVisible(true);
    }//GEN-LAST:event_btnThemDichVuActionPerformed

    private void btnTraPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraPhongActionPerformed
        
            // TODO add your handling code here:
        nghiepVuTraPhong();
        
//        xoaThongTinDatPhongCuaPhong();
//        xoaCacPhucVuCuaPhong();
    }//GEN-LAST:event_btnTraPhongActionPerformed

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
            java.util.logging.Logger.getLogger(ThongTinChiTietPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongTinChiTietPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongTinChiTietPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongTinChiTietPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongTinChiTietPhong().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnClose;
    private javax.swing.JButton btnThemDichVu;
    private javax.swing.JButton btnTraPhong;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbGiaPhong;
    private javax.swing.JLabel lbKhachHang;
    private javax.swing.JLabel lbNgayRa;
    private javax.swing.JLabel lbNgayVao;
    private javax.swing.JLabel lbRoomDetail;
    private javax.swing.JLabel lbRoomDetail2;
    private javax.swing.JLabel lbSoNguoi;
    private javax.swing.JLabel lbdichvu;
    private CUSTOM.PanelRound panelRound1;
    private CUSTOM.PanelRound panelRound2;
    private javax.swing.JTable tblDichVu;
    // End of variables declaration//GEN-END:variables
}

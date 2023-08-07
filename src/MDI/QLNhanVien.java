/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package MDI;
import DAO.NhanVienDAO;
import POJO.NhanVien;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author GreenDuy
 */
public class QLNhanVien extends javax.swing.JInternalFrame {
    
    Vector tblDataNV = new Vector();
    Vector tblTitleNV = new Vector();
    DefaultTableModel tblModeNV;
    
    String action;
    
    static ArrayList<NhanVien> dsnv = NhanVienDAO.getDSNV();

    /**
     * Creates new form Test
     */
    public QLNhanVien() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        BasicInternalFrameUI bui = (BasicInternalFrameUI) this.getUI();
        bui.setNorthPane(null);
        
        //mac dinh
        dateNgaySinh.setDateFormatString("yyyy-MM-dd");
        setControl();

        tblNhanVien.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tblNhanVien.getTableHeader().setBackground(new Color(0, 153, 51));
        tblNhanVien.getTableHeader().setForeground(new Color(0, 153, 51));
        tblNhanVien.setRowHeight(25);

        //set up
        bangnhanvien();
        laydulieunhanvien(dsnv);
        
        cbGioiTinh.removeAllItems();
        cbGioiTinh.addItem("NAM");
        cbGioiTinh.addItem("NỮ");
        cbGioiTinh.setSelectedIndex(-1);
        
        cbQuyen.removeAllItems();
        cbQuyen.addItem("NHÂN VIÊN");
        cbQuyen.addItem("QUẢN LÝ");
        cbQuyen.setSelectedIndex(-1);
  
    }
    public final void setControl()
    {
        txtMaNV.setEnabled(false);
        txtTenNV.setEnabled(false);
        cbGioiTinh.setEnabled(false);
        txtSDT.setEnabled(false);
        txtAnh.setEnabled(false);
        txtDiaChi.setEnabled(false);
        txtReMK.setEnabled(false);
        txtMatKhau.setEnabled(false);
        txtTaiKhoan.setEnabled(false);
        btnSave.setEnabled(false);
        btnBrowser.setEnabled(false);
        cbQuyen.setEnabled(false);
        dateNgaySinh.setEnabled(false);
    }
    public final void bangnhanvien(){
        tblTitleNV.add("Mã NV");
        tblTitleNV.add("Tên NV");
        tblTitleNV.add("Giới tính");
        tblTitleNV.add("Ngày sinh");
        tblTitleNV.add("Địa chỉ");
        tblTitleNV.add("SĐT");       
        tblTitleNV.add("Tài khoản");
        tblTitleNV.add("Mật khẩu");
        tblTitleNV.add("Quyền");
        tblTitleNV.add("Hình ảnh");
    }
    
    public final void laydulieunhanvien(ArrayList<NhanVien> dsnv){
        tblDataNV.removeAllElements();
        for(NhanVien nv : dsnv){
            Vector v = new Vector();
            v.add(nv.getManv()); 
            v.add(nv.getTennv()); 
            v.add(nv.getGioitinh()); 
            v.add(nv.getNgaysinh()); 
            v.add(nv.getDiachi()); 
            v.add(nv.getSdt()); 
            v.add(nv.getTaikhoan()); 
            v.add(nv.getMatkhau()); 
            v.add(nv.getQuyen()); 
            v.add(nv.getAvatar());
            tblDataNV.add(v);
        }
        tblModeNV = new DefaultTableModel(tblDataNV, tblTitleNV);
        tblNhanVien.setModel(tblModeNV);
    }
    
    public void setEnabledEdit()
    {
        if(action.equals("them"))
        {
            txtMaNV.setEnabled(true);
            txtTenNV.setEnabled(true);
            cbGioiTinh.setEnabled(true);
            cbQuyen.setEnabled(true);
            dateNgaySinh.setEnabled(true);
            txtSDT.setEnabled(true);
            txtAnh.setEnabled(true);
            txtDiaChi.setEnabled(true);
            txtReMK.setEnabled(true);
            txtMatKhau.setEnabled(true);
            txtTaiKhoan.setEnabled(true);
            
            btnSua.setEnabled(false);
            btnXoa.setEnabled(false);
            btnSave.setEnabled(true);
            btnBrowser.setEnabled(true);
        }
        else if(action.equals("xoa"))
        {
            txtMaNV.setEnabled(false);
            txtTenNV.setEnabled(false);
            cbGioiTinh.setEnabled(false);
            cbQuyen.setEnabled(false);
            dateNgaySinh.setEnabled(false);
            txtSDT.setEnabled(false);
            txtAnh.setEnabled(false);
            txtDiaChi.setEnabled(false);
            txtReMK.setEnabled(false);
            txtMatKhau.setEnabled(false);
            txtTaiKhoan.setEnabled(false);
            
            btnSua.setEnabled(false);
            btnThem.setEnabled(false);
            btnSave.setEnabled(true);
            btnBrowser.setEnabled(false);

        }
        else if(action.equals("sua"))
        {
            txtMaNV.setEnabled(true);
            txtTenNV.setEnabled(true);
            cbGioiTinh.setEnabled(true);
            cbQuyen.setEnabled(true);
            dateNgaySinh.setEnabled(true);
            txtSDT.setEnabled(true);
            txtAnh.setEnabled(true);
            txtDiaChi.setEnabled(true);
            txtReMK.setEnabled(true);
            txtMatKhau.setEnabled(true);
            txtTaiKhoan.setEnabled(true);
            
            btnSua.setEnabled(false);
            btnXoa.setEnabled(false);
            btnSave.setEnabled(true);
            btnBrowser.setEnabled(true);

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
        panelRound1 = new CUSTOM.PanelRound();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        panelRound2 = new CUSTOM.PanelRound();
        panelRound3 = new CUSTOM.PanelRound();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        cbGioiTinh = new javax.swing.JComboBox<>();
        dateNgaySinh = new com.toedter.calendar.JDateChooser();
        txtTenNV = new javax.swing.JTextField();
        txtMaNV = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnBrowser = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTaiKhoan = new javax.swing.JTextField();
        txtMatKhau = new javax.swing.JTextField();
        txtReMK = new javax.swing.JTextField();
        cbQuyen = new javax.swing.JComboBox<>();
        txtAnh = new javax.swing.JTextField();
        panelAvatar = new javax.swing.JPanel();
        avt = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        panelRound4 = new CUSTOM.PanelRound();
        btnSave = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        txt_TimKiemNhanVien = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        btn_TimNhanVien3 = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setTitle("Nhân Viên");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/staff_black.png"))); // NOI18N
        setMinimumSize(new java.awt.Dimension(1000, 1000));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("QUẢN LÝ NHÂN VIÊN");

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));
        panelRound1.setRoundBottomLeft(20);
        panelRound1.setRoundBottomRight(20);
        panelRound1.setRoundTopLeft(20);
        panelRound1.setRoundTopRight(20);

        tblNhanVien.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tblNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
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
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 153, 51));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Danh sách nhân viên");

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1542, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel15)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        panelRound2.setBackground(new java.awt.Color(255, 255, 255));
        panelRound2.setRoundBottomLeft(20);
        panelRound2.setRoundBottomRight(20);
        panelRound2.setRoundTopLeft(20);
        panelRound2.setRoundTopRight(20);

        panelRound3.setRoundBottomLeft(20);
        panelRound3.setRoundBottomRight(20);
        panelRound3.setRoundTopLeft(20);
        panelRound3.setRoundTopRight(20);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/gender-24.png"))); // NOI18N
        jLabel12.setText("Giới tính:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/phone-20.png"))); // NOI18N
        jLabel9.setText(" Số điện thoại:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/address-20.png"))); // NOI18N
        jLabel6.setText(" Địa chỉ:");

        txtSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSDTKeyTyped(evt);
            }
        });

        cbGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbGioiTinh.setSelectedIndex(-1);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/id-card-24.png"))); // NOI18N
        jLabel8.setText("Mã nhân viên:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/name-24.png"))); // NOI18N
        jLabel7.setText(" Tên nhân viên:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/date-20.png"))); // NOI18N
        jLabel10.setText(" Ngày sinh:");

        btnBrowser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBrowser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/find.png"))); // NOI18N
        btnBrowser.setText("Browser");
        btnBrowser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBrowser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowserActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Ảnh:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user-24.png"))); // NOI18N
        jLabel13.setText("Quyền:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/password.png"))); // NOI18N
        jLabel4.setText("Xác nhận mật khẩu:");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/password.png"))); // NOI18N
        jLabel1.setText("Mật khẩu:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/account.png"))); // NOI18N
        jLabel2.setText("Tài khoản:");

        cbQuyen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbQuyen.setSelectedIndex(-1);

        panelAvatar.setPreferredSize(new java.awt.Dimension(160, 160));
        panelAvatar.setLayout(new java.awt.BorderLayout());

        avt.setPreferredSize(new java.awt.Dimension(500, 500));
        panelAvatar.add(avt, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTenNV, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dateNgaySinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                            .addComponent(cbGioiTinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDiaChi)))
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(82, 82, 82)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                            .addComponent(txtReMK)
                            .addComponent(txtTaiKhoan)))
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBrowser)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbQuyen, 0, 267, Short.MAX_VALUE)
                            .addComponent(txtAnh))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 153, Short.MAX_VALUE)
                .addComponent(panelAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(120, 120, 120))
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRound3Layout.createSequentialGroup()
                                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(dateNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(cbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(17, 17, 17)
                                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelRound3Layout.createSequentialGroup()
                                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(panelRound3Layout.createSequentialGroup()
                                        .addComponent(txtReMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel13)
                                            .addComponent(cbQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(17, 17, 17)
                                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel11)
                                            .addComponent(txtAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBrowser, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(panelAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 153, 51));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Thông tin chi tiết");

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel14)
                .addGap(10, 10, 10)
                .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        panelRound4.setBackground(new java.awt.Color(255, 255, 255));
        panelRound4.setRoundBottomLeft(20);
        panelRound4.setRoundBottomRight(20);
        panelRound4.setRoundTopLeft(20);
        panelRound4.setRoundTopRight(20);

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

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setText("Từ khoá:");

        btn_TimNhanVien3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_TimNhanVien3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        btn_TimNhanVien3.setText("Search");
        btn_TimNhanVien3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimNhanVien3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound4Layout = new javax.swing.GroupLayout(panelRound4);
        panelRound4.setLayout(panelRound4Layout);
        panelRound4Layout.setHorizontalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnRefresh)
                .addGap(40, 40, 40)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_TimKiemNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btn_TimNhanVien3)
                .addContainerGap(465, Short.MAX_VALUE))
        );
        panelRound4Layout.setVerticalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txt_TimKiemNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btn_TimNhanVien3))
                    .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel3)
                .addGap(20, 20, 20)
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        action = "them";
        setEnabledEdit();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here
        action = "xoa";
        //tạo 1 cái model tạm để lưu từ model nhân viên
        DefaultTableModel tblModelTam = (DefaultTableModel) tblNhanVien.getModel();
        if(tblNhanVien.getSelectedRowCount() == 1)
        {
            if(JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa không?", "Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            {
                tblModelTam.removeRow(tblNhanVien.getSelectedRow());
            }
        }
        else
        {
            if(tblNhanVien.getRowCount() == 0)
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

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        action = "sua";
        setEnabledEdit();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        //mac dinh
        txtMaNV.setEnabled(false);
        txtTenNV.setEnabled(false);
        cbGioiTinh.setEnabled(false);
        cbQuyen.setEnabled(false);
        dateNgaySinh.setEnabled(false);
        txtSDT.setEnabled(false);
        txtAnh.setEnabled(false);
        txtDiaChi.setEnabled(false);
        txtReMK.setEnabled(false);
        txtMatKhau.setEnabled(false);
        txtTaiKhoan.setEnabled(false);

        btnThem.setEnabled(true);
        btnXoa.setEnabled(true);
        btnSua.setEnabled(true);
        btnSave.setEnabled(false);
        btnBrowser.setEnabled(false);

        laydulieunhanvien(dsnv);
        txtAnh.setText("");
        txtMaNV.setText("");
        txtTenNV.setText("");
        txtDiaChi.setText("");
        txtMatKhau.setText("");
        txtTaiKhoan.setText("");
        txtSDT.setText("");
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        try {
            // TODO add your handling code here:
            int i = tblNhanVien.getSelectedRow();
            String manv = tblNhanVien.getValueAt(i, 0).toString().trim();
            String tennv = tblNhanVien.getValueAt(i, 1).toString().trim();
            String gt = tblNhanVien.getValueAt(i, 2).toString().trim();
            String ngaysinh = tblNhanVien.getValueAt(i, 3).toString().trim();
            String dc = tblNhanVien.getValueAt(i, 4).toString().trim();
            String sdt = tblNhanVien.getValueAt(i, 5).toString().trim();
            String tk = tblNhanVien.getValueAt(i, 6).toString().trim();
            String mk = tblNhanVien.getValueAt(i, 7).toString().trim();
            String quyen = tblNhanVien.getValueAt(i, 8).toString().trim();
            String anh = tblNhanVien.getValueAt(i, 9).toString().trim();
            
            txtMaNV.setText(manv);
            txtTenNV.setText(tennv);
            cbGioiTinh.setSelectedItem(gt.trim());
            cbQuyen.setSelectedItem(quyen.trim());
            txtDiaChi.setText(dc);
            txtSDT.setText(sdt);
            txtAnh.setText(anh);
            txtTaiKhoan.setText(tk);
            txtMatKhau.setText(mk);
            Date datengaysinh = new SimpleDateFormat("yyyy-MM-dd").parse(ngaysinh);
            dateNgaySinh.setDate(datengaysinh);
            
            //cach 3
            ImageIcon icon = new ImageIcon(getClass().getResource("/Image/" + anh));
            Image img = icon.getImage().getScaledInstance(avt.getWidth(), avt.getHeight(), Image.SCALE_SMOOTH);
            avt.setIcon(new ImageIcon(img));
        } catch (ParseException ex) {
            Logger.getLogger(QLNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        String ngaysinh = ((JTextField)dateNgaySinh.getDateEditor().getUiComponent()).getText();
        if(action.equals("them"))
        {
            if(txtMaNV.getText().equals("") || txtTenNV.getText().equals("") || txtSDT.getText().equals("") || txtAnh.getText().equals("") || txtTaiKhoan.getText().equals("") || txtMatKhau.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Thông báo", 2);
            }
            else
            {
                try
                {
                    String lenhsql = "insert into NhanVien(MaNV, TenNV, GioiTinh, NgaySinh,  DiaChi, SDT, TaiKhoan, MatKhau, Quyen, Avatar) " +
                    "values ('"+txtMaNV.getText()+"', N'"+txtTenNV.getText()+"', N'"+cbGioiTinh.getSelectedItem().toString().trim()+"', N'"+ngaysinh+"'"
                    + ", N'"+txtDiaChi.getText()+"', '"+txtSDT.getText()+"','"+txtTaiKhoan.getText()+"',"
                    + " '"+txtMatKhau.getText()+"','N"+cbQuyen.getSelectedItem().toString().trim()+"', '"+txtAnh.getText()+"')";
                    int kq=NhanVienDAO.ThemXoaSuaNhanVien(lenhsql);
                    if(kq!=-1)
                    {
                        dsnv = NhanVienDAO.getDSNV();
                        laydulieunhanvien(dsnv);
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
            String sql = "delete from NhanVien where MaNV = '"+txtMaNV.getText().trim()+"'";
            if(NhanVienDAO.ThemXoaSuaNhanVien(sql)!=-1)
            {
                dsnv = NhanVienDAO.getDSNV();
                laydulieunhanvien(dsnv);
                JOptionPane.showMessageDialog(this, "Xoá thành công!", "Thông báo", 2);
            }
            else
            JOptionPane.showMessageDialog(this, "Dữ liệu này đang được sử dụng, không thể xóa", "Thông báo", 2);
        }
        else if(action.equals("sua"))
        {
            try{
                String sql = "UPDATE NhanVien SET TenNV = N'"+txtTenNV.getText()+"', NgaySinh = '"+ngaysinh+"', GioiTinh = N'"+cbGioiTinh.getSelectedItem()+"', "
                + "DiaChi = N'"+txtDiaChi.getText()+"', SDT = '"+txtSDT.getText()+"', Avatar = '"+txtAnh.getText()+"', TaiKhoan = '"+txtTaiKhoan.getText()+"', "
                + "MatKhau = '"+txtMatKhau.getText()+"', Quyen = N'"+cbQuyen.getSelectedItem().toString().trim()+"' where MANV='"+txtMaNV.getText()+"'";
                NhanVienDAO.ThemXoaSuaNhanVien(sql);
                dsnv = NhanVienDAO.getDSNV();
                laydulieunhanvien(dsnv);
                JOptionPane.showMessageDialog(this, "Sửa thành công!", "Thông báo", 2);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }

        //set lại mặc định
        txtMaNV.setEnabled(false);
        txtTenNV.setEnabled(false);
        cbGioiTinh.setEnabled(false);
        cbQuyen.setEnabled(false);
        dateNgaySinh.setEnabled(false);
        txtSDT.setEnabled(false);
        txtAnh.setEnabled(false);
        txtDiaChi.setEnabled(false);
        txtReMK.setEnabled(false);
        txtMatKhau.setEnabled(false);
        txtTaiKhoan.setEnabled(false);

        btnBrowser.setEnabled(false);
        btnSave.setEnabled(false);
        btnThem.setEnabled(true);
        btnXoa.setEnabled(true);
        btnSua.setEnabled(true);
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnBrowserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowserActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser("src/Image");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnValue = fileChooser.showOpenDialog(this);
        if(returnValue == JFileChooser.APPROVE_OPTION)
        {
            File file = fileChooser.getSelectedFile();
            //lay duog dan file de luu vao csdl
            String path = file.getAbsolutePath();
            //String tenanh = path.substring(-1);

            //lấy 9 ký tự cuối
            String nameanh = path.substring(Math.max(0, path.length() - 9));

            //mọi thứ sau dấu / cuối cùng
            String tenanh = path.substring(path.lastIndexOf('\\') + 1);

            txtAnh.setText(tenanh);
            try {
                avt.setIcon(new ImageIcon(tenanh));
            }catch (Exception e) {
                System.out.println("Chưa chọn anh");
            }
        }
    }//GEN-LAST:event_btnBrowserActionPerformed

    private void btn_TimNhanVien3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimNhanVien3ActionPerformed
        ArrayList<NhanVien> timnv= new  ArrayList<NhanVien>();
        timnv = NhanVienDAO.TimDSNhanVien(txt_TimKiemNhanVien.getText(),txt_TimKiemNhanVien.getText());
        //kqtim=  BanDAO.Timtenban(maban)

        laydulieunhanvien(timnv);
    }//GEN-LAST:event_btn_TimNhanVien3ActionPerformed

    private void txtSDTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyTyped
       String sdt = txtSDT.getText();
        if (sdt.matches(".*[a-zA-Z].*")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không chứa ký tự");
            txtSDT.setText("");
        } else if (sdt.length() > 10) {
            JOptionPane.showMessageDialog(this, "Số điện thoại chỉ được 10 số");
            txtSDT.setText("");
        }
    }//GEN-LAST:event_txtSDTKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel avt;
    private javax.swing.JButton btnBrowser;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btn_TimNhanVien3;
    private javax.swing.JComboBox<String> cbGioiTinh;
    private javax.swing.JComboBox<String> cbQuyen;
    private com.toedter.calendar.JDateChooser dateNgaySinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelAvatar;
    private CUSTOM.PanelRound panelRound1;
    private CUSTOM.PanelRound panelRound2;
    private CUSTOM.PanelRound panelRound3;
    private CUSTOM.PanelRound panelRound4;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtAnh;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextField txtReMK;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTaiKhoan;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txt_TimKiemNhanVien;
    // End of variables declaration//GEN-END:variables
}

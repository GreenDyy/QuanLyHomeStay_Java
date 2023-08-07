/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package MDI;
import DAO.ConnectionDB;
import DAO.PhongDAO;
import DAO.PhongDAO;
import static MDI.QLPhong.dsp;
import POJO.Phong;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
/**
 *
 * @author GreenDuy
 */
public class QLPhong extends javax.swing.JInternalFrame {
    Vector tblDataPhong = new Vector();
    Vector tblTitlePhong = new Vector();
    DefaultTableModel tblModePhong;
    
    String action;
    
    static ArrayList<Phong> dsp = PhongDAO.getDSP();

    /**
     * Creates new form QLPhong
     */
    public QLPhong() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        BasicInternalFrameUI bui = (BasicInternalFrameUI) this.getUI();
        bui.setNorthPane(null);
        
        tblPhong.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tblPhong.getTableHeader().setBackground(new Color(0, 153, 51));
        tblPhong.getTableHeader().setForeground(new Color(0, 153, 51));
        tblPhong.setRowHeight(25);
        
        bangPhong();
        laydulieuPhong(dsp);
        cbTrangThai.removeAllItems();
        cbTrangThai.addItem("ĐÃ ĐƯỢC ĐẶT");
        cbTrangThai.addItem("CÒN TRỐNG");
        cbTrangThai.setSelectedIndex(-1);
        loadLoaiPhong();
        cbLoaiPhong.setSelectedIndex(-1);
        
        setControls();
    }
    public final void setControls()
    {
        txt_MaPhong.setEnabled(false);
        txt_TenPhong.setEnabled(false);
        cbLoaiPhong.setEnabled(false);
        txt_GiaPhong.setEnabled(false);
        txt_TienNghi.setEnabled(false);
        cbTrangThai.setEnabled(false);
        
        btnSave.setEnabled(false);
    }
    public final void bangPhong(){
        tblTitlePhong.add("Mã Phòng");
        tblTitlePhong.add("Tên Phòng");
        tblTitlePhong.add("Loại Phòng");
        tblTitlePhong.add("Giá Phòng");
        tblTitlePhong.add("Tiện Nghi");
        tblTitlePhong.add("Trạng Thái");
    }
    
    public final void laydulieuPhong(ArrayList<Phong> dsp){
        tblDataPhong.removeAllElements();
        for(Phong p : dsp){
            Vector v = new Vector();
            v.add(p.getMaphong()); 
            v.add(p.getTenphong()); 
            v.add(p.getLoaiphong()); 
            v.add(p.getGiaphong()); 
            v.add(p.getTiennghi()); 
            v.add(p.getTrangthai());  
            tblDataPhong.add(v);
        }
        tblModePhong = new DefaultTableModel(tblDataPhong, tblTitlePhong);
        tblPhong.setModel(tblModePhong);
    }
    public final void loadLoaiPhong()
    {
        cbLoaiPhong.removeAllItems();
        cbLoaiPhong.addItem("PHÒNG CỔ ĐIỂN");
        cbLoaiPhong.addItem("PHÒNG CHILL");
        cbLoaiPhong.addItem("PHÒNG VIP");
    }
    public void setEnabledEdit()
    {
        if(action.equals("them"))
        {
            txt_MaPhong.setEnabled(true);
            txt_TenPhong.setEnabled(true);
            cbLoaiPhong.setEnabled(true);
            txt_GiaPhong.setEnabled(true);
            txt_TienNghi.setEnabled(true);
            cbTrangThai.setEnabled(true);
            
            btnSave.setEnabled(true);
            btnSua.setEnabled(false);
            btnXoa.setEnabled(false);

        }
        else if(action.equals("xoa"))
        {
            txt_MaPhong.setEnabled(false);
            txt_TenPhong.setEnabled(false);
            cbLoaiPhong.setEnabled(false);
            txt_GiaPhong.setEnabled(false);
            txt_TienNghi.setEnabled(false);
            cbTrangThai.setEnabled(false);

            btnSave.setEnabled(true);
            btnSua.setEnabled(false);
            btnThem.setEnabled(false);

        }
        else if(action.equals("sua"))
        {
            txt_MaPhong.setEnabled(true);
            txt_TenPhong.setEnabled(true);
            cbLoaiPhong.setEnabled(true);
            txt_GiaPhong.setEnabled(true);
            txt_TienNghi.setEnabled(true);
            cbTrangThai.setEnabled(true);
            
            btnSave.setEnabled(true);
            btnSua.setEnabled(false);
            btnXoa.setEnabled(false);
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
        txt_MaPhong = new javax.swing.JTextField();
        txt_TenPhong = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbLoaiPhong = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbTrangThai = new javax.swing.JComboBox<>();
        txt_TienNghi = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_GiaPhong = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        panelRound3 = new CUSTOM.PanelRound();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btn_Tim = new javax.swing.JButton();
        txt_TimKiemPhong = new javax.swing.JTextField();
        panelRound4 = new CUSTOM.PanelRound();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhong = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setTitle("Phòng");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/room_black.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 0));
        jLabel1.setText("QUẢN LÝ PHÒNG");

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
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/id-card-24.png"))); // NOI18N
        jLabel2.setText("Mã phòng :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/name-24.png"))); // NOI18N
        jLabel5.setText(" Tên phòng :");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/room_black.png"))); // NOI18N
        jLabel7.setText("Loại phòng :");

        cbLoaiPhong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbLoaiPhong.setSelectedIndex(-1);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/battery-24.png"))); // NOI18N
        jLabel6.setText(" Trạng thái :");

        cbTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbTrangThai.setSelectedIndex(-1);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/thumbs-up-24.png"))); // NOI18N
        jLabel9.setText(" Tiện nghi :");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/price-24.png"))); // NOI18N
        jLabel8.setText(" Giá phòng :");

        txt_GiaPhong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_GiaPhongKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(cbLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addGap(24, 24, 24)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_TenPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                            .addComponent(txt_MaPhong))))
                .addGap(36, 36, 36)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(cbTrangThai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_TienNghi, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_GiaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(833, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8)
                    .addComponent(txt_MaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_GiaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel9)
                    .addComponent(txt_TenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_TienNghi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(cbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
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
                    .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        panelRound3.setBackground(new java.awt.Color(255, 255, 255));
        panelRound3.setRoundBottomLeft(20);
        panelRound3.setRoundBottomRight(20);
        panelRound3.setRoundTopLeft(20);
        panelRound3.setRoundTopRight(20);

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

        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        btnXoa.setText("Xoá");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
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

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Từ khoá:");

        btn_Tim.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_Tim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        btn_Tim.setText("Search");
        btn_Tim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnRefresh)
                .addGap(40, 40, 40)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jLabel3)
                .addGap(10, 10, 10)
                .addComponent(txt_TimKiemPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(btn_Tim)
                .addContainerGap(589, Short.MAX_VALUE))
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound3Layout.createSequentialGroup()
                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound3Layout.createSequentialGroup()
                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_TimKiemPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_Tim, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelRound4.setBackground(new java.awt.Color(255, 255, 255));
        panelRound4.setRoundBottomLeft(20);
        panelRound4.setRoundBottomRight(20);
        panelRound4.setRoundTopLeft(20);
        panelRound4.setRoundTopRight(20);

        tblPhong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblPhong.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhongMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPhong);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 153, 51));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Danh sách phòng");

        javax.swing.GroupLayout panelRound4Layout = new javax.swing.GroupLayout(panelRound4);
        panelRound4.setLayout(panelRound4Layout);
        panelRound4Layout.setHorizontalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1502, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound4Layout.setVerticalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound4Layout.createSequentialGroup()
                .addComponent(jLabel15)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(panelRound4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblPhongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhongMouseClicked
        // TODO add your handling code here:
        int i = tblPhong.getSelectedRow();
        TableModel model = tblPhong.getModel();
        
        txt_MaPhong.setText(model.getValueAt(i, 0).toString().trim());
        txt_TenPhong.setText(model.getValueAt(i, 1).toString().trim());
        String loaiphong = model.getValueAt(i, 2).toString().trim();
        cbLoaiPhong.setSelectedItem(loaiphong.trim());
        txt_GiaPhong.setText(model.getValueAt(i, 3).toString());
        txt_TienNghi.setText(model.getValueAt(i, 4).toString());
        String trangthai = model.getValueAt(i, 5).toString().trim();
        cbTrangThai.setSelectedItem(trangthai.trim());
    }//GEN-LAST:event_tblPhongMouseClicked

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
        //tạo 1 cái model tạm để lưu từ model Phong
        DefaultTableModel tblModelTam = (DefaultTableModel) tblPhong.getModel();
        if(tblPhong.getSelectedRowCount() == 1)
        {
            if(JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa không?", "Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            {
                tblModelTam.removeRow(tblPhong.getSelectedRow());
            }
        }
        else
        {
            if(tblPhong.getRowCount() == 0)
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
        txt_MaPhong.setEnabled(false);
        txt_TenPhong.setEnabled(false);
        cbLoaiPhong.setEnabled(false);
        txt_GiaPhong.setEnabled(false);
        txt_TienNghi.setEnabled(false);
        cbTrangThai.setEnabled(false);

        btnThem.setEnabled(true);
        btnXoa.setEnabled(true);
        btnSua.setEnabled(true);
        btnSave.setEnabled(false);

        laydulieuPhong(dsp);
        txt_MaPhong.setText("");
        txt_TenPhong.setText("");
        cbLoaiPhong.setSelectedIndex(-1); 
        txt_GiaPhong.setText("");
        txt_TienNghi.setText("");
        cbTrangThai.setSelectedIndex(-1);
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        if(action.equals("them"))
        {
            if(txt_MaPhong.getText().equals("") || txt_TenPhong.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Thông báo", 2);
            }
            else
            {
                try
                {
                    String lenhsql = "INSERT INTO PHONG(MAPHONG, TENPHONG, LOAIPHONG, GIAPHONG, TIENNGHI, TRANGTHAI)"
                + "VALUES ('" + txt_MaPhong.getText() + "', N'" + txt_TenPhong.getText() + "', N'" + cbLoaiPhong.getSelectedItem().toString().trim() + "','" + txt_GiaPhong.getText() + "', N'" + txt_TienNghi.getText() + "', N'CÒN TRỐNG')";
                    int kq=PhongDAO.ThemXoaSuaPhong(lenhsql);
                    if(kq!=-1)
                    {
                        dsp = PhongDAO.getDSP();
                        laydulieuPhong(dsp);
                        JOptionPane.showMessageDialog(this, "Thêm thành công!", "Thông báo", 2);
                    }
                    else
                    JOptionPane.showMessageDialog(this, "Mã phong này đã tồn tại, không thể thêm", "Thông báo", 2);
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
            }

        }
        else if(action.equals("xoa"))
        {
            String sql = "delete from Phong where MAPHONG = '"+txt_MaPhong.getText().trim()+"'";
            if(PhongDAO.ThemXoaSuaPhong(sql)!=-1)
            {
                dsp = PhongDAO.getDSP();
                laydulieuPhong(dsp);
                JOptionPane.showMessageDialog(this, "Xoá thành công!", "Thông báo", 2);
            }
            else
            JOptionPane.showMessageDialog(this, "Dữ liệu này đang được sử dụng, không thể xóa", "Thông báo", 2);
        }
        else if(action.equals("sua"))
        {
            try{
                String sql = "update PHONG set TENPHONG=N'"+txt_TenPhong.getText().trim()+"',LOAIPHONG=N'"+cbLoaiPhong.getSelectedItem().toString().trim()
                        +"',GIAPHONG=N"+txt_GiaPhong.getText()+",TIENNGHI=N'"+txt_TienNghi.getText()+"',TRANGTHAI=N'"
                        +cbTrangThai.getSelectedItem().toString().trim()+"'where MAPHONG='"+txt_MaPhong.getText().trim()+"'";
                PhongDAO.ThemXoaSuaPhong(sql);
                dsp = PhongDAO.getDSP();
                laydulieuPhong(dsp);
                JOptionPane.showMessageDialog(this, "Sửa thành công!", "Thông báo", 2);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }

        //set lại mặc định
        txt_MaPhong.setEnabled(false);
        txt_TenPhong.setEnabled(false);
        cbLoaiPhong.setEnabled(false);
        txt_GiaPhong.setEnabled(false);
        txt_TienNghi.setEnabled(false);
        cbTrangThai.setEnabled(false);
        
        btnSave.setEnabled(false);
        btnThem.setEnabled(true);
        btnXoa.setEnabled(true);
        btnSua.setEnabled(true);
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btn_TimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimActionPerformed
        ArrayList<Phong> timphong= new  ArrayList<Phong>();
        timphong = PhongDAO.TimDSPhong(txt_TimKiemPhong.getText(),txt_TimKiemPhong.getText());
        laydulieuPhong(timphong);
    }//GEN-LAST:event_btn_TimActionPerformed

    private void txt_GiaPhongKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_GiaPhongKeyTyped
       String sdt = txt_GiaPhong.getText();
        if (sdt.matches(".*[a-zA-Z].*")) {
            JOptionPane.showMessageDialog(this, "Giá phòng không chứa ký tự");
            txt_GiaPhong.setText("");
        } 
    }//GEN-LAST:event_txt_GiaPhongKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btn_Tim;
    private javax.swing.JComboBox<String> cbLoaiPhong;
    private javax.swing.JComboBox<String> cbTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JTable tblPhong;
    private javax.swing.JTextField txt_GiaPhong;
    private javax.swing.JTextField txt_MaPhong;
    private javax.swing.JTextField txt_TenPhong;
    private javax.swing.JTextField txt_TienNghi;
    private javax.swing.JTextField txt_TimKiemPhong;
    // End of variables declaration//GEN-END:variables
}

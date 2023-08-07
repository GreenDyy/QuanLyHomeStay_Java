/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */

package MDI;

import CUSTOM.PanelRound;
import DAO.ConnectionDB;
import GUI.ThongTinChiTietPhong;
import POJO.TrungGian;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author GreenDuy
 */
public class CheckPhong extends javax.swing.JInternalFrame {

    /** Creates new form CheckPhong */
    public CheckPhong() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        BasicInternalFrameUI bui = (BasicInternalFrameUI) this.getUI();
        bui.setNorthPane(null);
       
        loadDataPhong();
    }
     public final void loadDataPhong()
    {
        panelAllRoom.setLayout(new GridLayout(4, 4, 20, 20));
        String sql = "SELECT maphong, tenphong, trangthai FROM phong";
        ConnectionDB conn = new ConnectionDB();
        conn.getCn();
        ResultSet rs = conn.executeQuery(sql);
            try {
                while (rs.next()) {
                    String maphong = rs.getString(1);
                    String tenphong = rs.getString(2);
                    String trangthai = rs.getString(3);
                    
                    ImageIcon icon = new ImageIcon(getClass().getResource("/icons/room.png"));
                    Image img = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                   
                 
                    JLabel p01 = new JLabel("<html>" + " "+ maphong + "  " + "<br><br>"+ "</html>", JLabel.LEADING);
                    p01.setFont(new Font("Arial", Font.BOLD, 16));
                    p01.setForeground(Color.WHITE);
                    
                    JLabel trangthaip = new JLabel(" " + trangthai + "   ");
                    trangthaip.setFont(new Font("Arial", Font.BOLD, 20));
                    trangthaip.setForeground(Color.WHITE);
                 
                    
                    JLabel name = new JLabel("<html>" + "<br>" + tenphong + " " + "<br><br>" + "</html>", JLabel.RIGHT);
                    name.setFont(new Font("Arial", Font.BOLD, 16));
                    name.setForeground(Color.WHITE);
                    name.setIcon(new ImageIcon(img));
           
                    //panell---------------------------------------------------------------------
                    PanelRound phong = new PanelRound();
                    phong.setRoundBottomLeft(50);
                    phong.setRoundBottomRight(50);
                    phong.setRoundTopRight(50);
                    phong.setRoundTopLeft(50);
                    phong.setBackground(new Color(80, 200, 120));
                    phong.setLayout(new BoxLayout(phong, BoxLayout.Y_AXIS));
                    phong.setBorder(new EmptyBorder(new Insets(20, 20, 10, 50)));
                  
                    
                    phong.add(p01);
                    phong.add(name);
                    phong.add(trangthaip);
                    phong.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    if(trangthai.equals("CÒN TRỐNG"))
                    {
                        phong.setBackground(new Color(109, 149, 208));
                    }
                    panelAllRoom.add(phong);
                    
                    phong.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if(trangthai.equals("CÒN TRỐNG"))
                            {
                                JOptionPane.showMessageDialog(phong, "Phòng trống, vui lòng đặt phòng!", "Thông báo", 1);
                            }
                            else
                            {
                                TrungGian.setMaPhong(maphong);
                                ThongTinChiTietPhong f = new ThongTinChiTietPhong();
                                f.setVisible(true);
                            }
                        }
                        
                    });
                }
            }
            catch (Exception e) 
            {
                e.printStackTrace();
            }
    }
     
//    public final void loadDataPhongTheoTinhTrang(String tinhtrang)
//    {
//        panelAllRoom.setLayout(new GridLayout(4, 4, 20, 20));
//        tinhtrang = cbPhanLoai.getSelectedItem().toString();
//        String sql = "";
//        ConnectionDB conn = new ConnectionDB();
//        conn.getCn();
//        if(cbPhanLoai.getSelectedItem().toString() == "2")
//        {
//            sql = "SELECT maphong, tenphong, trangthai FROM phong";
//        }
//        else if(tinhtrang.equals("Phòng trống"))
//        {
//            sql = "SELECT maphong, tenphong, trangthai FROM phong where TRANGTHAI = N'CÒN TRỐNG'";
//        }
//        else
//        {
//            sql = "SELECT maphong, tenphong, trangthai FROM phong where TRANGTHAI = N'ĐÃ ĐƯỢC ĐẶT'";
//        }
//        
//        ResultSet rs = conn.executeQuery(sql);
//            try {
//                while (rs.next()) {
//                    String maphong = rs.getString(1);
//                    String tenphong = rs.getString(2);
//                    String trangthai = rs.getString(3);
//                    
//                    ImageIcon icon = new ImageIcon(getClass().getResource("/icons/hotel-24.png"));
//                    Image img = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
//                   
//                 
//                    JLabel p01 = new JLabel("<html>" + " "+ maphong + "  " + "<br><br>"+ "</html>", JLabel.LEADING);
//                    p01.setFont(new Font("Arial", Font.BOLD, 16));
//                    p01.setForeground(Color.WHITE);
//                    
//                    JLabel trangthaip = new JLabel(" " + trangthai + "   ");
//                    trangthaip.setFont(new Font("Arial", Font.BOLD, 20));
//                    trangthaip.setForeground(Color.WHITE);
//                 
//                    
//                    JLabel name = new JLabel("<html>" + "<br>" + tenphong + " " + "<br><br>" + "</html>", JLabel.RIGHT);
//                    name.setFont(new Font("Arial", Font.BOLD, 16));
//                    name.setForeground(Color.WHITE);
//                    name.setIcon(new ImageIcon(img));
//           
//                    //panell---------------------------------------------------------------------
//                    PanelRound phong = new PanelRound();
//                    phong.setRoundBottomLeft(50);
//                    phong.setRoundBottomRight(50);
//                    phong.setRoundTopRight(50);
//                    phong.setRoundTopLeft(50);
//                    phong.setBackground(new Color(104, 206, 121));
//                    phong.setLayout(new BoxLayout(phong, BoxLayout.Y_AXIS));
//                    phong.setBorder(new EmptyBorder(new Insets(20, 20, 10, 50)));
//                  
//                    
//                    phong.add(p01);
//                    phong.add(name);
//                    phong.add(trangthaip);
//                    phong.setCursor(new Cursor(Cursor.HAND_CURSOR));
//                    if(trangthai.equals("CÒN TRỐNG"))
//                    {
//                        phong.setBackground(new Color(109, 149, 208));
//                    }
//                    panelAllRoom.add(phong);
//                    
//                    phong.addMouseListener(new MouseAdapter() {
//                        @Override
//                        public void mouseClicked(MouseEvent e) {
//                            if(trangthai.equals("CÒN TRỐNG"))
//                            {
//                                JOptionPane.showMessageDialog(phong, "Phòng trống, vui lòng đặt phòng!", "Thông báo", 1);
//                            }
//                            else
//                            {
//                                TrungGian.setMaPhong(maphong);
//                                ThongTinChiTietPhong f = new ThongTinChiTietPhong();
//                                f.setVisible(true);
//                            }
//                        }
//                        
//                    });
//                }
//            }
//            catch (Exception e) 
//            {
//                e.printStackTrace();
//            }
//    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelAllRoom = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelAllRoomLayout = new javax.swing.GroupLayout(panelAllRoom);
        panelAllRoom.setLayout(panelAllRoomLayout);
        panelAllRoomLayout.setHorizontalGroup(
            panelAllRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1304, Short.MAX_VALUE)
        );
        panelAllRoomLayout.setVerticalGroup(
            panelAllRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 718, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 51));
        jLabel1.setText("DANH SÁCH PHÒNG");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(panelAllRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelAllRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelAllRoom;
    // End of variables declaration//GEN-END:variables

}

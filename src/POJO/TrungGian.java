/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

import javax.swing.JFrame;

/**
 *
 * @author GreenDuy
 */
public class TrungGian {
    public static String tenNV = "Huỳnh Khánh Duy";
    public static String maphong = "P001";
    public static String avatar = "avatar1.png";
    public static String quyen = "Nhân Viên";
//    public static JFrame frame;
    
    public TrungGian()
    {
    }

    public static String getTenNV() {
        return tenNV;
    }

    public static void setTenNV(String tenNV) {
        TrungGian.tenNV = tenNV;
    }
    public static String getMaPhong() {
        return maphong;
    }

    public static void setMaPhong(String maphong) {
        TrungGian.maphong = maphong;
    }
    
    public static String getAvatar() {
        return avatar;
    }

    public static void setAvatar(String avatar) {
        TrungGian.avatar = avatar;
    }
    
    public static String getQuyen() {
            return quyen;
    }

    public static void setQuyen(String quyen) {
        TrungGian.quyen = quyen;
    }
    
//    public static JFrame getFrame() {
//            return frame;
//    }
//
//    public static void setFrame(JFrame frame) {
//        TrungGian.frame = frame;
//    }
}

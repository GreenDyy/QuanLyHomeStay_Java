/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.ResultSet;
/**
 *
 * @author Admin
 */
public class DangNhapDAO {
    public static String getPass(String tk){
        String pass = "";
        try {
            String sql = "select MatKhau from NhanVien where TaiKhoan = '"+tk+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                 pass = rs.getString("MatKhau");
            }
            System.out.println(pass);
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return pass;
    }
    public static String checkQuyen(String tk)
    {
        String quyen = "";
        try {
            String sql = "Select Quyen from NhanVien where TaiKhoan = '"+tk+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                 quyen = rs.getString("Quyen");
            }
            System.out.println(quyen);
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return quyen;
    }
    
    public static String getTenQuanLy(String tk)
    {
        String ten = "";
        try {
            String sql = "Select TenNV from NhanVien where TaiKhoan = '"+tk+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                 ten = rs.getString("TenNV");
            }
            System.out.println(ten);
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return ten;
    }
    public static String getAvatar(String tk)
    {
        String avatar = "";
        try {
            String sql = "Select AVATAR from NhanVien where TaiKhoan = '"+tk+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                 avatar = rs.getString(1);
            }
            System.out.println(avatar);
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return avatar;
    }
}

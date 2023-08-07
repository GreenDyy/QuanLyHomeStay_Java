/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.Phong;
import POJO.PhongTrong;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author vungu
 */
public class PhongDAO {
    public static ArrayList<Phong> getDSP(){
        ArrayList<Phong> dsp = new ArrayList<>();
        try {
            String sql = "select * from Phong";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                Phong p = new Phong(
                        rs.getString("Maphong").trim(), 
                        rs.getString("Tenphong").trim(), 
                        rs.getString("Loaiphong").trim(), 
                        rs.getString("TienNghi").trim(),
                        rs.getString("TrangThai").trim(),
                         rs.getString("GiaPhong").trim());
                dsp.add(p);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return dsp;
    }
    
    public static ArrayList<PhongTrong> layDSPhongTrong(){
        ArrayList<PhongTrong> dsptrong = new ArrayList<>();
        try {
            String sql = "select MAPHONG, LOAIPHONG, TRANGTHAI from Phong where TRANGTHAI = N'CÒN TRỐNG'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                PhongTrong p = new PhongTrong(
                        rs.getString(1), 
                        rs.getString(2), 
                        rs.getString(3));
     
                dsptrong.add(p);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return dsptrong;
    }
    public static int ThemXoaSuaPhong(String sql){
        int i=0;
        try {
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
             i = cn.executeUpdate(sql);
            cn.close();
            System.out.println("Thêm/xóa/sửa thành công");
            
        } catch (Exception e) {
            System.out.println("Thêm/xóa/sửa không thành công");
        }
        return i;
    }
    public static ArrayList<Phong> TimDSPhong(String MaPhong,String TenPhong){
        ArrayList<Phong> dsp = new ArrayList<Phong>();
        try {
            String sql = "select * from PHONG where MAPHONG='"+MaPhong+"'or TENPHONG='"+TenPhong+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
             Phong p = new Phong(rs.getString("MAPHONG").trim()
                     , rs.getString("TENPHONG").trim()
                     , rs.getString("LOAIPHONG").trim()
                     , rs.getString("TIENNGHI").trim()
                     , rs.getString("TRANGTHAI").trim()
                     , rs.getString("GIAPHONG").trim());
                     dsp.add(p);          
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu bàn");
        }
        return dsp;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.NhanVien;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author GreenDuy
 */
public class NhanVienDAO {
    public static ArrayList<NhanVien> getDSNV(){
        ArrayList<NhanVien> dsnv = new ArrayList<>();
        try {
            String sql = "select * from NhanVien";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                NhanVien nv = new NhanVien(rs.getString("manv").trim(),
                        rs.getString("tennv").trim(),
                        rs.getString("gioitinh").trim() ,
                        rs.getString("ngaysinh"),
                        rs.getString("diachi").trim(),
                        rs.getString("sdt").trim(),
                        rs.getString("taikhoan").trim(),
                        rs.getString("matkhau").trim(),
                        rs.getString("quyen").trim(),
                        rs.getString("avatar"));
                dsnv.add(nv);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return dsnv;
    }
    public static int ThemXoaSuaNhanVien(String sql){
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
    public static ArrayList<NhanVien> TimDSNhanVien(String MaNV,String TenNV)
    {
        ArrayList<NhanVien>dsNV= new ArrayList<NhanVien>();
        try 
        {
            String sql="select * from NHANVIEN where MANV like '"+MaNV+"'or TENNV like N'%"+TenNV+"%'";
            ConnectionDB cn= new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next())
            {
                 NhanVien nv = new NhanVien(rs.getString("manv").trim(),
                        rs.getString("tennv").trim(),
                        rs.getString("gioitinh").trim() ,
                        rs.getString("ngaysinh"),
                        rs.getString("diachi").trim(),
                        rs.getString("sdt").trim(),
                        rs.getString("taikhoan").trim(),
                        rs.getString("matkhau").trim(),
                        rs.getString("quyen").trim(),
                        rs.getString("avatar"));
                dsNV.add(nv);
            }
            cn.close();
        }
        catch (Exception e)
        {
                 System.out.println(e);
        }
        return dsNV;
    }
 
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.HoaDon;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author GreenDuy
 */
public class HoaDonDAO {
    public static ArrayList<HoaDon> getDSHD(){
        ArrayList<HoaDon> dshd = new ArrayList<>();
        try {
            String sql = "select MAHD,MADP,MAKH,MAPHONG,NGAYLAPHD,TONGTIENHD,MANV from HOADON";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                HoaDon hd = new HoaDon(
                        rs.getInt("MAHD"),
                        rs.getString("MADP").trim(),
                        rs.getString("MAKH").trim(),
                        rs.getString("MAPHONG").trim(),
                        rs.getDate("NGAYLAPHD"),
                        rs.getDouble("TONGTIENHD"),
                        rs.getString("MANV").trim()
                );
                        
                dshd.add(hd);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return dshd;
    }
    public static int ThemXoaSuaHoaDon(String sql){
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
    public static ArrayList<HoaDon> TimDSHoaDon(String tukhoa)
    {
        ArrayList<HoaDon>dsHD= new ArrayList<HoaDon>();
        try 
        {
             String sql = "select MAHD,MADP,MAKH,MAPHONG,NGAYLAPHD,TONGTIENHD,MANV from HOADON where MADP like '"+tukhoa+"' or MAPHONG like '"+tukhoa+"' or MAKH like '"+tukhoa+"'or MAHD like '"+tukhoa+"'or MANV like '"+tukhoa+"'";
            ConnectionDB cn= new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next())
            {
                 HoaDon hoadon = new HoaDon(
                       rs.getInt("MAHD"),
                        rs.getString("MADP").trim(),
                        rs.getString("MAKH").trim(),
                        rs.getString("MAPHONG").trim(),
                        rs.getDate("NGAYLAPHD"),
                        rs.getDouble("TONGTIENHD"),
                        rs.getString("MANV").trim()
                );
                        
                dsHD.add(hoadon);
            }
            cn.close();
        }
        catch (Exception e)
        {
                 System.out.println(e);
        }
        return dsHD;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.PhieuDat;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author vungu
 */
public class PhieuDatDAO {
   
    public static ArrayList<PhieuDat> getDSPD(){
        ArrayList<PhieuDat> dspd = new ArrayList<>();
        try {
            String sql = "select MADP,MAPHONG,MAKH,NGAYDATPHONG,NGAYTRAPHONG,SONGUOI from DATPHONG";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                PhieuDat pd = new PhieuDat(
                        rs.getString("MADP").trim(), 
                        rs.getString("MAPHONG").trim(), 
                        rs.getString("MAKH").trim(), 
                        rs.getDate("NGAYDATPHONG"),
                        rs.getDate("NGAYTRAPHONG"),
                        rs.getInt("SONGUOI"));
           
                dspd.add(pd);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return dspd;
    }
    
    public static int ThemXoaSuaPhieuDat(String sql)
    {
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
    public static ArrayList<PhieuDat> TimDSPhieu(String tukhoa)
    {
        ArrayList<PhieuDat> dsphieu = new ArrayList<PhieuDat>();
        try {
            String sql="select MADP,MAPHONG,MAKH,NGAYDATPHONG,NGAYTRAPHONG,SONGUOI from DATPHONG where MADP like '"+tukhoa+"' or MAPHONG like '"+tukhoa+"' or MAKH like '"+tukhoa+"'";
            //String sql = "select * from DATPHONG where MADP like '"+tukhoa+"' or MAPHONG like '"+tukhoa+"' or MAKH like '"+tukhoa+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
             while(rs.next())
             {
                 PhieuDat phieu = new PhieuDat(
                        rs.getString("MADP").trim(), 
                        rs.getString("MAPHONG").trim(), 
                        rs.getString("MAKH").trim(), 
                        rs.getDate("NGAYDATPHONG"),
                        rs.getDate("NGAYTRAPHONG"),
                        rs.getInt("SONGUOI"));
                dsphieu.add(phieu);
            }  
            
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu bàn");
        }
        return dsphieu;
    }
}

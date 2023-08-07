/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.DichVu;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author vungu
 */
public class DichVuDAO {
    public static ArrayList<DichVu> getDSDV(){
        ArrayList<DichVu> dsdv = new ArrayList<>();
        try {
            String sql = "select MADV, TENDV, TENLOAIDV, GIADV from DichVu, LOAIDV where DICHVU.MALDV = LOAIDV.MALDV";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                DichVu dv = new DichVu(
                        rs.getString("madv").trim(), 
                        rs.getString("tendv").trim(), 
                        rs.getString("tenloaidv").trim(), 
                        rs.getDouble("giadv"));           
                dsdv.add(dv);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return dsdv;
    }
    public static ArrayList<String> getLoaiDV(){
        ArrayList<String> dsLoaiDV = new ArrayList<>();
        try {
            String sql = "select TENLOAIDV from LOAIDV";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                dsLoaiDV.add(rs.getString(1));
            }
            cn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return dsLoaiDV;
    }
    public static String getMaLoaiDV(String tenloaidv){
        String maLoaiDV = "";
        try {
            String sql = "select MALDV from LOAIDV where TENLOAIDV = N'"+tenloaidv+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                maLoaiDV = rs.getString(1);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return maLoaiDV;
    }
    
    public static int ThemXoaSuaDichVu(String sql){
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
    public static ArrayList<DichVu> TimDSDichVu(String tukhoa)
    {
        ArrayList<DichVu> dsDV= new ArrayList<DichVu>();
        try 
        {
            String sql="select MADV, TENDV, TENLOAIDV, GIADV from DichVu, LOAIDV  where DICHVU.MALDV = LOAIDV.MALDV and (MADV like '"+tukhoa+"' or TenDV like N'%"+tukhoa+"%'  or LOAIDV.TENLOAIDV like '"+tukhoa+"')";
            ConnectionDB cn= new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next())
            {
                 DichVu dv = new DichVu(
                        rs.getString("madv").trim(), 
                        rs.getString("tendv").trim(), 
                        rs.getString("tenloaidv").trim(), 
                        rs.getDouble("giadv"));           
                dsDV.add(dv);
            }
            cn.close();
        }
        catch (Exception e)
        {
                 System.out.println(e);
        }
        return dsDV;
    }
}

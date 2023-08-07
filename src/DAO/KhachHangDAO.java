/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import POJO.KhachHang;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class KhachHangDAO 
{
    public static int ThemXoaSuaKhachHang(String sql)
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
    public static ArrayList<KhachHang> getDSKH(){
        ArrayList<KhachHang> dskh = new ArrayList<KhachHang>();
        try {
            String sql = "select * from KHACHHANG";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next())
            {
               KhachHang kh = new KhachHang(rs.getString("MAKH").trim()
                                            , rs.getString("TENKH").trim()
                                            , rs.getString("GIOITINH").trim()
                                            , rs.getString("NGAYSINH").trim()
                                            , rs.getString("DIACHI").trim()
                                            ,rs.getString( "SDT").trim());
                                         
               dskh.add(kh);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return dskh;
    }
    public static ArrayList<KhachHang> TimDSKhachHang(String MaKH,String TenKH)
    {
        ArrayList<KhachHang>dsKH= new ArrayList<KhachHang>();
        try {
            String sql = "select * from KHACHHANG where MAKH like '"+MaKH+"'or TENKH like N'%"+TenKH+"%'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next())
            {
               KhachHang kh = new KhachHang(rs.getString("MAKH").trim()
                                            , rs.getString("TENKH").trim()
                                            , rs.getString("GIOITINH").trim()
                                            , rs.getString("NGAYSINH").trim()
                                            , rs.getString("DIACHI").trim()
                                            ,rs.getString( "SDT").trim());
                                         
               dsKH.add(kh);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return dsKH;
    }
       
}

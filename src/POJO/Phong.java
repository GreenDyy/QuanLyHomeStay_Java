/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author vungu
 */
public class Phong {
    private String maphong, tenphong, loaiphong, tiennghi, trangthai, giaphong;
    //get
    public String getMaphong()
    {
        return maphong;
    }
    public String getTenphong()
    {
        return tenphong;
    }
    public String getLoaiphong()
    {
        return loaiphong;
    }
    public String getTiennghi()
    {
        return tiennghi;
    }
    public String getTrangthai()
    {
        return trangthai;
    }
    public String getGiaphong()
    {
        return giaphong;
    }
    // set
    public void setMaPhong(String maphong)
    {
        this.maphong = maphong;
    }
    public void setTenPhong(String tenphong)
    {
        this.tenphong = tenphong;
    }
    public void setLoaiPhong(String loaiphong)
    {
        this.loaiphong = loaiphong;
    }
    public void setTiennghi(String tiennghi)
    {
        this.tiennghi = tiennghi;
    }
    public void setTrangthai(String trangthai)
    {
        this.trangthai = trangthai;
    }
    public void setGiaphong(String giaphong)
    {
        this.giaphong = giaphong;
    }
    
    public Phong(String maphong, String tenphong, String loaiphong, String tiennghi, String trangthai, String giaphong)
    {
        this.maphong = maphong;
        this.tenphong = tenphong;
        this.loaiphong = loaiphong;
        this.tiennghi = tiennghi;
        this.trangthai = trangthai;
        this.giaphong = giaphong;
    }
    
    
}

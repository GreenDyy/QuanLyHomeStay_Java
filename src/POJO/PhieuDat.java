/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

import java.util.Date;

/**
 *
 * @author GreenDuy
 */
public class PhieuDat {
    private String mapd, maphong, makh;
    private Date ngaydat, ngaytra;
    private int songuoi;

    public PhieuDat(String mapd, String maphong, String makh, Date ngaydat, Date ngaytra, int songuoi) {
        this.mapd = mapd;
        this.maphong = maphong;
        this.makh = makh;
        this.ngaydat = ngaydat;
        this.ngaytra = ngaytra;
        this.songuoi = songuoi;
    }

    public String getMapd() {
        return mapd;
    }

    public void setMapd(String mapd) {
        this.mapd = mapd;
    }

    public String getMaphong() {
        return maphong;
    }

    public void setMaphong(String maphong) {
        this.maphong = maphong;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public Date getNgaydat() {
        return ngaydat;
    }

    public void setNgaydat(Date ngaydat) {
        this.ngaydat = ngaydat;
    }

    public Date getNgaytra() {
        return ngaytra;
    }

    public void setNgaytra(Date ngaytra) {
        this.ngaytra = ngaytra;
    }

    public int getSonguoi() {
        return songuoi;
    }

    public void setSonguoi(int songuoi) {
        this.songuoi = songuoi;
    }

    
}

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
public class HoaDon {
    int mahd;
    private String madp, makh, maphong;
    private Date ngaylaphd;
    private double tongtienhd;
    private String manv;

    public HoaDon(int mahd, String madp, String makh, String maphong, Date ngaylaphd, double tongtienhd, String manv) {
        this.mahd = mahd;
        this.madp = madp;
        this.makh = makh;
        this.maphong = maphong;
        this.ngaylaphd = ngaylaphd;
        this.tongtienhd = tongtienhd;
        this.manv = manv;
    }

    public int getMahd() {
        return mahd;
    }

    public void setMahd(int mahd) {
        this.mahd = mahd;
    }

    public String getMadp() {
        return madp;
    }

    public void setMadp(String madp) {
        this.madp = madp;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getMaphong() {
        return maphong;
    }

    public void setMaphong(String maphong) {
        this.maphong = maphong;
    }

    public Date getNgaylaphd() {
        return ngaylaphd;
    }

    public void setNgaylaphd(Date ngaylaphd) {
        this.ngaylaphd = ngaylaphd;
    }

    public double getTongtienhd() {
        return tongtienhd;
    }

    public void setTongtienhd(double tongtienhd) {
        this.tongtienhd = tongtienhd;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }
    
    
    
}

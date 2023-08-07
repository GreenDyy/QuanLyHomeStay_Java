/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author GreenDuy
 */
public class TienDichVu {
    private String maphong, madv, tendv;
    private double giadv;
    private int tongsl;

    public TienDichVu(String maphong, String madv, String tendv, double giadv, int tongsl) {
        this.maphong = maphong;
        this.madv = madv;
        this.tendv = tendv;
        this.giadv = giadv;
        this.tongsl = tongsl;
    }

    public String getMaphong() {
        return maphong;
    }

    public void setMaphong(String maphong) {
        this.maphong = maphong;
    }

    public String getMadv() {
        return madv;
    }

    public void setMadv(String madv) {
        this.madv = madv;
    }

    public String getTendv() {
        return tendv;
    }

    public void setTendv(String tendv) {
        this.tendv = tendv;
    }

    public double getGiadv() {
        return giadv;
    }

    public void setGiadv(double giadv) {
        this.giadv = giadv;
    }

    public int getTongsl() {
        return tongsl;
    }

    public void setTongsl(int tongsl) {
        this.tongsl = tongsl;
    }
    
    
    
}

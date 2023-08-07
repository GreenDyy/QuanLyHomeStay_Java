/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author GreenDuy
 */
public class DichVu {
    private String madv, tendv, maldv;
    private double giadv;

    public DichVu(String madv, String tendv, String maldv, double giadv) {
        this.madv = madv;
        this.tendv = tendv;
        this.maldv = maldv;
        this.giadv = giadv;
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

    public String getLoaidv() {
        return maldv;
    }

    public void setLoaidv(String maldv) {
        this.maldv = maldv;
    }

    public double getGiadv() {
        return giadv;
    }

    public void setGiadv(double giadv) {
        this.giadv = giadv;
    }
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;



/**
 *
 * @author Admin
 */
public class KhachHang 
{
    private String MAKH;
    private String TENKH;
    private String GIOITINH;
    private String NGAYSINH;
    private String DIACHI;
    private String SDT;

    public KhachHang(String MAKH, String TENKH, String GIOITINH, String NGAYSINH, String DIACHI, String SDT) {
        this.MAKH = MAKH;
        this.TENKH = TENKH;
        this.GIOITINH = GIOITINH;
        this.NGAYSINH = NGAYSINH;
        this.DIACHI = DIACHI;
        this.SDT = SDT;
    }

    public String getMAKH() {
        return MAKH;
    }

    public void setMAKH(String MAKH) {
        this.MAKH = MAKH;
    }

    public String getTENKH() {
        return TENKH;
    }

    public void setTENKH(String TENKH) {
        this.TENKH = TENKH;
    }

    public String getGIOITINH() {
        return GIOITINH;
    }

    public void setGIOITINH(String GIOITINH) {
        this.GIOITINH = GIOITINH;
    }

    public String getNGAYSINH() {
        return NGAYSINH;
    }

    public void setNGAYSINH(String NGAYSINH) {
        this.NGAYSINH = NGAYSINH;
    }

    public String getDIACHI() {
        return DIACHI;
    }

    public void setDIACHI(String DIACHI) {
        this.DIACHI = DIACHI;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    
}

package com.example.megas.apporderfood.DTO;

public class NhanVienDTO {
    int MANV;
    String TENDN, MATKHAU, GIOITINH, NGAYSINH,CMND;

    public void setMANV(int MANV) {
        this.MANV = MANV;
    }

    public void setTENDN(String TENDN) {
        this.TENDN = TENDN;
    }

    public void setMATKHAU(String MATKHAU) {
        this.MATKHAU = MATKHAU;
    }

    public void setGIOITINH(String GIOITINH) {
        this.GIOITINH = GIOITINH;
    }

    public void setNGAYSINH(String NGAYSINH) {
        this.NGAYSINH = NGAYSINH;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public int getMANV() {

        return MANV;
    }

    public String getTENDN() {
        return TENDN;
    }

    public String getMATKHAU() {
        return MATKHAU;
    }

    public String getGIOITINH() {
        return GIOITINH;
    }

    public String getNGAYSINH() {
        return NGAYSINH;
    }

    public String getCMND() {
        return CMND;
    }
}

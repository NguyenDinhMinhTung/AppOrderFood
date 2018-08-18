package com.example.megas.apporderfood.DTO;

public class MonAnDTO {
    int maMonAn, maLoai;
    String tenMonAn;
    String giaTien;

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getHinhAnh() {

        return hinhAnh;
    }

    String hinhAnh;

    public void setMaMonAn(int maMonAn) {
        this.maMonAn = maMonAn;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public void setGiaTien(String giaTien) {
        this.giaTien = giaTien;
    }

    public int getMaMonAn() {
        return maMonAn;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public String getGiaTien() {
        return giaTien;
    }
}

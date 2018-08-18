package com.example.megas.apporderfood.DTO;

public class BanAnDTO {
    int maBan;
    String tenBan;
    boolean duocChon;

    public BanAnDTO(int maBan, String tenBan) {
        this.maBan = maBan;
        this.tenBan = tenBan;
    }

    public void setDuocChon(boolean duocChon) {
        this.duocChon = duocChon;
    }

    public boolean isDuocChon() {

        return duocChon;
    }

    public int getMaBan() {
        return maBan;
    }

    public String getTenBan() {
        return tenBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }
}

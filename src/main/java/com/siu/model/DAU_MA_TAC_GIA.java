package com.siu.model;

public class DAU_MA_TAC_GIA {

    private String maDauSach;
    private String maTacGia;

    public DAU_MA_TAC_GIA() {
    }

    public DAU_MA_TAC_GIA(String maDauSach, String maTacGia) {
        this.maDauSach = maDauSach;
        this.maTacGia = maTacGia;
    }

    public String getMaDauSach() {
        return maDauSach;
    }

    public void setMaDauSach(String maDauSach) {
        this.maDauSach = maDauSach;
    }

    public String getMaTacGia() {
        return maTacGia;
    }

    public void setMaTacGia(String maTacGia) {
        this.maTacGia = maTacGia;
    }
}

package com.siu.model;

public class DAU_SACH {

    private String maDauSach;
    private String tenSach;
    private String maTacGia;
    private String mSNXB;
    private String maLoaiSach;
    private int namXB;
    private int lanXB;
    private String tomTat;

    public DAU_SACH() {
    }

    public DAU_SACH(String maDauSach, String tenSach, String maTacGia, String mSNXB, String maLoaiSach, int namXB, int lanXB, String tomTat) {
        this.maDauSach = maDauSach;
        this.tenSach = tenSach;
        this.maTacGia = maTacGia;
        this.mSNXB = mSNXB;
        this.maLoaiSach = maLoaiSach;
        this.namXB = namXB;
        this.lanXB = lanXB;
        this.tomTat = tomTat;
    }

    public String getMaDauSach() {
        return maDauSach;
    }

    public void setMaDauSach(String maDauSach) {
        this.maDauSach = maDauSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getMaTacGia() {
        return maTacGia;
    }

    public void setMaTacGia(String maTacGia) {
        this.maTacGia = maTacGia;
    }

    public String getmSNXB() {
        return mSNXB;
    }

    public void setmSNXB(String mSNXB) {
        this.mSNXB = mSNXB;
    }

    public String getMaLoaiSach() {
        return maLoaiSach;
    }

    public void setMaLoaiSach(String maLoaiSach) {
        this.maLoaiSach = maLoaiSach;
    }

    public int getNamXB() {
        return namXB;
    }

    public void setNamXB(int namXB) {
        this.namXB = namXB;
    }

    public int getLanXB() {
        return lanXB;
    }

    public void setLanXB(int lanXB) {
        this.lanXB = lanXB;
    }

    public String getTomTat() {
        return tomTat;
    }

    public void setTomTat(String tomTat) {
        this.tomTat = tomTat;
    }

}

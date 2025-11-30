package com.siu.model;

public class LOAI_SACH {

    private String maLoaiSach;
    private String tenLoai;

    public LOAI_SACH() {
    }

    public LOAI_SACH(String maLoaiSach, String tenLoai) {
        this.maLoaiSach = maLoaiSach;
        this.tenLoai = tenLoai;
    }

    public String getMaLoaiSach() {
        return maLoaiSach;
    }

    public void setMaLoaiSach(String maLoaiSach) {
        this.maLoaiSach = maLoaiSach;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}

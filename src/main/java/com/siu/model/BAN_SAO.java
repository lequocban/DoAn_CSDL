package com.siu.model;

public class BAN_SAO {

    private String maBanSao;
    private String maDauSach;
    private String tinhTrang;
    private String viTri;

    public BAN_SAO() {
    }

    public BAN_SAO(String maBanSao, String maDauSach, String tinhTrang, String viTri) {
        this.maBanSao = maBanSao;
        this.maDauSach = maDauSach;
        this.tinhTrang = tinhTrang;
        this.viTri = viTri;
    }

    public String getMaBanSao() {
        return maBanSao;
    }

    public void setMaBanSao(String maBanSao) {
        this.maBanSao = maBanSao;
    }

    public String getMaDauSach() {
        return maDauSach;
    }

    public void setMaDauSach(String maDauSach) {
        this.maDauSach = maDauSach;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }
}

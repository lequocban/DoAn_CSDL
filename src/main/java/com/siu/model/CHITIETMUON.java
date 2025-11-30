/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.siu.model;

/**
 *
 * @author ADMIN
 */
public class CHITIETMUON {
    private String soPhieuMuon ;
    private String maBanSao ;

    public CHITIETMUON() {
    }

    public CHITIETMUON(String soPhieuMuon, String maBanSao) {
        this.soPhieuMuon = soPhieuMuon;
        this.maBanSao = maBanSao;
    }

    public String getSoPhieuMuon() {
        return soPhieuMuon;
    }

    public void setSoPhieuMuon(String soPhieuMuon) {
        this.soPhieuMuon = soPhieuMuon;
    }

    public String getMaBanSao() {
        return maBanSao;
    }

    public void setMaBanSao(String maBanSao) {
        this.maBanSao = maBanSao;
    }

    @Override
    public String toString() {
        return "CHITIETMUON{" + "soPhieuMuon=" + soPhieuMuon + ", maBanSao=" + maBanSao + '}';
    }
    
    
}

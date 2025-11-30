/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.siu.model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class TRASACH {
   private String soPhieuMuon ;
   private String maBanSao ;
   private String msnv ;
   private Date ngayTra ;

    public TRASACH() {
    }

    public TRASACH(String soPhieuMuon, String maBanSao, String msnv, Date ngayTra) {
        this.soPhieuMuon = soPhieuMuon;
        this.maBanSao = maBanSao;
        this.msnv = msnv;
        this.ngayTra = ngayTra;
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

    public String getMsnv() {
        return msnv;
    }

    public void setMsnv(String msnv) {
        this.msnv = msnv;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    @Override
    public String toString() {
        return "TRASACH{" + "soPhieuMuon=" + soPhieuMuon + ", maBanSao=" + maBanSao + ", msnv=" + msnv + '}';
    }
   
   
}

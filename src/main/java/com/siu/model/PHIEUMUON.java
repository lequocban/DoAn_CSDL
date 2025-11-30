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
public class PHIEUMUON {
    private String soPhieuMuon ; 
    private String MSDG ;
    private String MSNV ;
    private Date ngayMuon ;
    private Date hanTra ;
    private String trangThai ;

    public PHIEUMUON() {
    }

    public PHIEUMUON(String soPhieuMuon, String MSDG, String MSNV, Date ngayMuon, Date hanTra, String trangThai) {
        this.soPhieuMuon = soPhieuMuon;
        this.MSDG = MSDG;
        this.MSNV = MSNV;
        this.ngayMuon = ngayMuon;
        this.hanTra = hanTra;
        this.trangThai = trangThai;
    }

    public Date getHanTra() {
        return hanTra;
    }

    public void setHanTra(Date hanTra) {
        this.hanTra = hanTra;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    

    public String getSoPhieuMuon() {
        return soPhieuMuon;
    }

    public void setSoPhieuMuon(String soPhieuMuon) {
        this.soPhieuMuon = soPhieuMuon;
    }

    public String getMSDG() {
        return MSDG;
    }

    public void setMSDG(String MSDG) {
        this.MSDG = MSDG;
    }

    public String getMSNV() {
        return MSNV;
    }

    public void setMSNV(String MSNV) {
        this.MSNV = MSNV;
    }

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    @Override
    public String toString() {
        return "PHIEUMUON{" + "soPhieuMuon=" + soPhieuMuon + ", MSDG=" + MSDG + ", MSNV=" + MSNV + '}';
    }
    
    
}

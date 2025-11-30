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
public class PHAT {
    private String soPhieuPhat ;
    private String soPhieuMuon ;
    private String maBanSao ;
    private String msnv ; 
    private Date ngayPhat ;
    private String lyDoPhat ;

    public PHAT() {
    }

    public PHAT(String soPhieuPhat, String soPhieuMuon, String maBanSao, String msnv, Date ngayPhat, String lyDoPhat) {
        this.soPhieuPhat = soPhieuPhat;
        this.soPhieuMuon = soPhieuMuon;
        this.maBanSao = maBanSao;
        this.msnv = msnv;
        this.ngayPhat = ngayPhat;
        this.lyDoPhat = lyDoPhat;
    }

    public String getSoPhieuPhat() {
        return soPhieuPhat;
    }

    public void setSoPhieuPhat(String soPhieuPhat) {
        this.soPhieuPhat = soPhieuPhat;
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

    public Date getNgayPhat() {
        return ngayPhat;
    }

    public void setNgayPhat(Date ngayPhat) {
        this.ngayPhat = ngayPhat;
    }

    public String getLyDoPhat() {
        return lyDoPhat;
    }

    public void setLyDoPhat(String lyDoPhat) {
        this.lyDoPhat = lyDoPhat;
    }

    @Override
    public String toString() {
        return "PHAT{" + "soPhieuPhat=" + soPhieuPhat + ", soPhieuMuon=" + soPhieuMuon + ", maBanSao=" + maBanSao + '}';
    }
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.siu.model;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author ADMIN
 */
public class NHANVIEN {
    private String MSNV;
    private String hoTenNV;
    private String diaChiNV;
    private Date ngaySinhNV;
    private String gioiTinh;
    private String sdtNV;
    private String emailNV;
    private Date ngayVaoLam;
    private String chucVu;

    public NHANVIEN() {
    }

    public NHANVIEN(String MSNV, String hoTenNV, String diaChiNV, Date ngaySinhNV, String gioiTinh, String sdtNV, String emailNV, Date ngayVaoLam, String chucVu) {
        this.MSNV = MSNV;
        this.hoTenNV = hoTenNV;
        this.diaChiNV = diaChiNV;
        this.ngaySinhNV = ngaySinhNV;
        this.gioiTinh = gioiTinh;
        this.sdtNV = sdtNV;
        this.emailNV = emailNV;
        this.ngayVaoLam = ngayVaoLam;
        this.chucVu = chucVu;
    }

    

    public String getMSNV() {
        return MSNV;
    }

    public void setMSNV(String MSNV) {
        this.MSNV = MSNV;
    }

    public String getHoTenNV() {
        return hoTenNV;
    }

    public void setHoTenNV(String hoTenNV) {
        this.hoTenNV = hoTenNV;
    }

    public String getDiaChiNV() {
        return diaChiNV;
    }

    public void setDiaChiNV(String diaChiNV) {
        this.diaChiNV = diaChiNV;
    }

    public Date getNgaySinhNV() {
        return ngaySinhNV;
    }

    public void setNgaySinhNV(Date ngaySinhNV) {
        this.ngaySinhNV = ngaySinhNV;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSdtNV() {
        return sdtNV;
    }

    public void setSdtNV(String sdtNV) {
        this.sdtNV = sdtNV;
    }

    public String getEmailNV() {
        return emailNV;
    }

    public void setEmailNV(String emailNV) {
        this.emailNV = emailNV;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public Date getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setNgayVaoLam(Date ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    @Override
    public String toString() {
        return "MSNV=" + MSNV + ", hoTenNV=" + hoTenNV;
    }
    
    public void out(){
        System.out.println(MSNV);
        System.out.println(hoTenNV);
        System.out.println(diaChiNV);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(sdf.format(ngaySinhNV));
        System.out.println(gioiTinh);
        System.out.println(sdtNV);
        System.out.println(emailNV);
        System.out.println(sdf.format(ngayVaoLam));
    }
    
}

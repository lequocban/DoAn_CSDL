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
public class DOCGIA {
    private String MSDG ;
    private String tenDG ;
    private String diaChi ;
    private Date NgaySinh ;
    private String email ;
    private String gioiTinh ;
    private Date ngayDK;
    private String thongTinKhac ;

    public DOCGIA() {
    }

    public DOCGIA(String MSDG, String tenDG, String diaChi, Date NgaySinh, String email, String gioiTinh, Date ngayDK, String thongTinKhac) {
        this.MSDG = MSDG;
        this.tenDG = tenDG;
        this.diaChi = diaChi;
        this.NgaySinh = NgaySinh;
        this.email = email;
        this.gioiTinh = gioiTinh;
        this.ngayDK = ngayDK;
        this.thongTinKhac = thongTinKhac;
    }

    

    public String getMSDG() {
        return MSDG;
    }

    public void setMSDG(String MSDG) {
        this.MSDG = MSDG;
    }

    public String getTenDG() {
        return tenDG;
    }

    public void setTenDG(String tenDG) {
        this.tenDG = tenDG;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgayDK() {
        return ngayDK;
    }

    public void setNgayDK(Date ngayDK) {
        this.ngayDK = ngayDK;
    }
    

    public String getThongTinKhac() {
        return thongTinKhac;
    }

    public void setThongTinKhac(String thongTinKhac) {
        this.thongTinKhac = thongTinKhac;
    }

    
    
    @Override
    public String toString() {
        return "MSDG=" + MSDG + ", tenDG=" + tenDG ;
    }
    
    public void out(){
        System.out.println(MSDG);
        System.out.println(tenDG);
        System.out.println(diaChi);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(sdf.format(NgaySinh));
        System.out.println(email);
        System.out.println(gioiTinh);
        System.out.println(thongTinKhac);
    }


}

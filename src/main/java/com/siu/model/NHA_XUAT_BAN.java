package com.siu.model;

public class NHA_XUAT_BAN {

    private String mSNXB;
    private String tenNXB;
    private String diaChiNXB;
    private String websiteNXB;
    private String thongTinKhac;

    public NHA_XUAT_BAN() {
    }

    public NHA_XUAT_BAN(String mSNXB, String tenNXB, String diaChiNXB, String websiteNXB, String thongTinKhac) {
        this.mSNXB = mSNXB;
        this.tenNXB = tenNXB;
        this.diaChiNXB = diaChiNXB;
        this.websiteNXB = websiteNXB;
        this.thongTinKhac = thongTinKhac;
    }

    public String getmSNXB() {
        return mSNXB;
    }

    public void setmSNXB(String mSNXB) {
        this.mSNXB = mSNXB;
    }

    public String getTenNXB() {
        return tenNXB;
    }

    public void setTenNXB(String tenNXB) {
        this.tenNXB = tenNXB;
    }

    public String getDiaChiNXB() {
        return diaChiNXB;
    }

    public void setDiaChiNXB(String diaChiNXB) {
        this.diaChiNXB = diaChiNXB;
    }

    public String getWebsiteNXB() {
        return websiteNXB;
    }

    public void setWebsiteNXB(String websiteNXB) {
        this.websiteNXB = websiteNXB;
    }

    public String getThongTinKhac() {
        return thongTinKhac;
    }

    public void setThongTinKhac(String thongTinKhac) {
        this.thongTinKhac = thongTinKhac;
    }
}

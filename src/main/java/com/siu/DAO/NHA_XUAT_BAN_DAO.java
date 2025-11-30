package com.siu.DAO;

import com.siu.helper.DatabaseHelper;
import com.siu.model.NHA_XUAT_BAN;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NHA_XUAT_BAN_DAO {

    public List<NHA_XUAT_BAN> getAll() throws Exception {
        List<NHA_XUAT_BAN> lstNXBs = new ArrayList<>();
        String sql = "SELECT * FROM NHA_XUAT_BAN";

        try (Connection con = DatabaseHelper.openConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                NHA_XUAT_BAN nxb = new NHA_XUAT_BAN();
                nxb.setmSNXB(rs.getString("MSNXB"));
                nxb.setTenNXB(rs.getString("TenNXB"));
                nxb.setDiaChiNXB(rs.getString("DiaChiNXB"));
                nxb.setWebsiteNXB(rs.getString("WebsiteNXB"));
                nxb.setThongTinKhac(rs.getString("ThongTinKhac"));
                lstNXBs.add(nxb);
            }
        }
        return lstNXBs;
    }

    public NHA_XUAT_BAN getByMS(String ms) {
        NHA_XUAT_BAN nxb = null;
        String sql = "SELECT * FROM NHA_XUAT_BAN WHERE MSNXB = ?"; 
        try (Connection conn = DatabaseHelper.openConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ms);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                nxb = new NHA_XUAT_BAN();
                nxb.setmSNXB(rs.getString("MSNXB"));
                nxb.setTenNXB(rs.getString("TenNXB"));
                nxb.setDiaChiNXB(rs.getString("DiaChiNXB"));
                nxb.setWebsiteNXB(rs.getString("WebsiteNXB"));
                nxb.setThongTinKhac(rs.getString("ThongTinKhac"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nxb;
    }

    public boolean insert(NHA_XUAT_BAN nxb) throws Exception {
        String sql = "INSERT INTO NHA_XUAT_BAN (MSNXB, TenNXB, DiaChiNXB, WebsiteNXB, ThongTinKhac) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DatabaseHelper.openConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, nxb.getmSNXB());
            pstmt.setString(2, nxb.getTenNXB());
            pstmt.setString(3, nxb.getDiaChiNXB());
            pstmt.setString(4, nxb.getWebsiteNXB());
            pstmt.setString(5, nxb.getThongTinKhac());

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean update(NHA_XUAT_BAN nxb) throws Exception {
        String sql = "UPDATE NHA_XUAT_BAN SET TenNXB = ?, DiaChiNXB = ?, WebsiteNXB = ?, ThongTinKhac = ? WHERE MSNXB = ?";
        try (Connection con = DatabaseHelper.openConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, nxb.getTenNXB());
            pstmt.setString(2, nxb.getDiaChiNXB());
            pstmt.setString(3, nxb.getWebsiteNXB());
            pstmt.setString(4, nxb.getThongTinKhac());
            pstmt.setString(5, nxb.getmSNXB());

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean delete(String maNXB) throws Exception {
        String sql = "DELETE FROM NHA_XUAT_BAN WHERE MSNXB = ?";
        try (Connection con = DatabaseHelper.openConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, maNXB);
            return pstmt.executeUpdate() > 0;
        }
    }

    public List<NHA_XUAT_BAN> searchByName(String tenNXB) throws Exception {
        List<NHA_XUAT_BAN> lstNXBs = new ArrayList<>();
        String sql = "SELECT * FROM NHA_XUAT_BAN WHERE TenNXB LIKE ?";

        try (Connection con = DatabaseHelper.openConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, "%" + tenNXB + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    NHA_XUAT_BAN nxb = new NHA_XUAT_BAN();
                    nxb.setmSNXB(rs.getString("MSNXB"));
                    nxb.setTenNXB(rs.getString("TenNXB"));
                    nxb.setDiaChiNXB(rs.getString("DiaChiNXB"));
                    nxb.setWebsiteNXB(rs.getString("WebsiteNXB"));
                    nxb.setThongTinKhac(rs.getString("ThongTinKhac"));
                    lstNXBs.add(nxb);
                }
            }
        }
        return lstNXBs;
    }
}

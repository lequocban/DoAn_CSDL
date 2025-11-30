package com.siu.DAO;

import com.siu.helper.DatabaseHelper;
import com.siu.model.DAU_SACH;
import com.siu.model.LOAI_SACH;
import com.siu.model.TAC_GIA;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAU_SACH_DAO {

    public List<DAU_SACH> getDauSach() throws Exception {
        List<DAU_SACH> lstDau_sachs = new ArrayList<>();
        String sql = "SELECT * FROM DAU_SACH";

        try (
                Connection con = DatabaseHelper.openConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
                DAU_SACH dausach = new DAU_SACH();
                dausach.setMaDauSach(rs.getString("MaDauSach"));
                dausach.setTenSach(rs.getString("TenSach"));
                dausach.setMaTacGia(rs.getString("MaTacGia"));
                dausach.setmSNXB(rs.getString("MSNXB"));
                dausach.setMaLoaiSach(rs.getString("MaLoaiSach"));
                dausach.setNamXB(rs.getInt("NamXB"));
                dausach.setLanXB(rs.getInt("LanXB"));
                dausach.setTomTat(rs.getString("TomTat"));

                lstDau_sachs.add(dausach);
            }
        }
        return lstDau_sachs;
    }

    public List<DAU_SACH> search(String keyword) throws Exception {
        List<DAU_SACH> list = new ArrayList<>();

        String sql = """
        SELECT * FROM DAU_SACH 
        WHERE MaDauSach LIKE ?
        OR TenSach LIKE ?
        OR MaTacGia LIKE ?
        OR MSNXB LIKE ?
        OR MaLoaiSach LIKE ?
        OR CAST(NamXB AS NVARCHAR) LIKE ?
        OR CAST(LanXB AS NVARCHAR) LIKE ?
        OR TomTat LIKE ?
    """;

        try (
                Connection con = DatabaseHelper.openConnection(); java.sql.PreparedStatement stmt = con.prepareStatement(sql);) {
            String search = "%" + keyword + "%";

            stmt.setString(1, search);
            stmt.setString(2, search);
            stmt.setString(3, search);
            stmt.setString(4, search);
            stmt.setString(5, search);
            stmt.setString(6, search);
            stmt.setString(7, search);
            stmt.setString(8, search);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                DAU_SACH d = new DAU_SACH();
                d.setMaDauSach(rs.getString("MaDauSach"));
                d.setTenSach(rs.getString("TenSach"));
                d.setMaTacGia(rs.getString("MaTacGia"));
                d.setmSNXB(rs.getString("MSNXB"));
                d.setMaLoaiSach(rs.getString("MaLoaiSach"));
                d.setNamXB(rs.getInt("NamXB"));
                d.setLanXB(rs.getInt("LanXB"));
                d.setTomTat(rs.getString("TomTat"));

                list.add(d);
            }
        }
        return list;
    }

    public boolean insert(DAU_SACH ds) throws Exception {
        String sql = "INSERT INTO DAU_SACH (MaDauSach, TenSach, MaTacGia, MSNXB, MaLoaiSach, NamXB, LanXB, TomTat) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (
                Connection con = DatabaseHelper.openConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, ds.getMaDauSach());
            pstmt.setString(2, ds.getTenSach());
            pstmt.setString(3, ds.getMaTacGia());
            pstmt.setString(4, ds.getmSNXB());
            pstmt.setString(5, ds.getMaLoaiSach());
            pstmt.setInt(6, ds.getNamXB());
            pstmt.setInt(7, ds.getLanXB());
            pstmt.setString(8, ds.getTomTat());

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean updateDauSach(DAU_SACH ds) {
        String sql = "UPDATE DAU_SACH SET TenSach=?, MaTacGia=?, MSNXB=?, MaLoaiSach=?, NamXB=?, LanXB=?, TomTat=? WHERE MaDauSach=?";
        try (Connection conn = DatabaseHelper.openConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ds.getTenSach());
            ps.setString(2, ds.getMaTacGia());
            ps.setString(3, ds.getmSNXB());
            ps.setString(4, ds.getMaLoaiSach());
            ps.setInt(5, ds.getNamXB());
            ps.setInt(6, ds.getLanXB());
            ps.setString(7, ds.getTomTat());
            ps.setString(8, ds.getMaDauSach());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String maDauSach) throws Exception {
        String sql = "DELETE FROM DAU_SACH WHERE MaDauSach = ?";
        try (Connection conn = DatabaseHelper.openConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maDauSach);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public boolean isBorrowed(String maBanSao) throws Exception {
        String sql = "SELECT COUNT(*) FROM MUON_TRA WHERE MaBanSao = ? AND TraNgay IS NULL";
        try (Connection conn = DatabaseHelper.openConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maBanSao);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    public boolean borrow(String maBanSao, String maDauSach, String maDG, String maNV, String ngayMuon, String ngayTra) throws Exception {
        String sql = "INSERT INTO MUON_TRA (MaBanSao, MaDauSach, MaDG, MaNV, NgayMuon, HanTra) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseHelper.openConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maBanSao);
            ps.setString(2, maDauSach);
            ps.setString(3, maDG);
            ps.setString(4, maNV);
            ps.setString(5, ngayMuon);
            ps.setString(6, ngayTra);
            return ps.executeUpdate() > 0;
        }
    }

    public String getAvailableCopy(String maDauSach) throws Exception {
        String sql = "SELECT TOP 1 MaBanSao FROM BAN_SAO "
                + "WHERE MaDauSach = ? "
                + "AND MaBanSao NOT IN (SELECT MaBanSao FROM MUON_TRA WHERE TraNgay IS NULL)";
        try (Connection conn = DatabaseHelper.openConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getString("MaBanSao");
            }
        }
        return null;
    }

    public DAU_SACH getByMaTacGia(String maTacGia) throws Exception {
        DAU_SACH ds = null;
        String sql = "SELECT * FROM DAU_SACH WHERE MaTacGia = ?";

        try (Connection con = DatabaseHelper.openConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, maTacGia);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ds = new DAU_SACH();
                ds.setMaDauSach(rs.getString("MaDauSach"));
                ds.setTenSach(rs.getString("TenSach"));
                ds.setMaTacGia(rs.getString("MaTacGia"));
                ds.setmSNXB(rs.getString("MSNXB"));
                ds.setMaLoaiSach(rs.getString("MaLoaiSach"));
                ds.setNamXB(rs.getInt("NamXB"));
                ds.setLanXB(rs.getInt("LanXB"));
                ds.setTomTat(rs.getString("TomTat"));
            }
        }
        return ds;
    }

    public boolean deleteNangCao(String maDauSach) throws Exception {

        String sqlCheckMuon
                = "SELECT COUNT(*) FROM BAN_SAO WHERE MaDauSach = ? AND TinhTrang = 'dang muon'";

        String sqlCheckLichSu
                = "SELECT COUNT(*) FROM CHI_TIET_MUON WHERE MaBanSao IN (SELECT MaBanSao FROM BAN_SAO WHERE MaDauSach = ?)";

        String sqlDeleteBanSao
                = "DELETE FROM BAN_SAO WHERE MaDauSach = ? AND TinhTrang = 'co san'";

        String sqlDeleteDauSach
                = "DELETE FROM DAU_SACH WHERE MaDauSach = ?";

        try (Connection con = DatabaseHelper.openConnection()) {

            // 1. Kiểm tra bản sao đang mượn
            try (PreparedStatement ps = con.prepareStatement(sqlCheckMuon)) {
                ps.setString(1, maDauSach);
                ResultSet rs = ps.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    return false;  // Có bản sao đang mượn
                }
            }

            // 2. Kiểm tra bản sao từng xuất hiện trong lịch sử mượn
            try (PreparedStatement ps = con.prepareStatement(sqlCheckLichSu)) {
                ps.setString(1, maDauSach);
                ResultSet rs = ps.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    return false; // Có bản sao từng được mượn -> không cho xóa
                }
            }

            // 3. Xóa bản sao (co san)
            try (PreparedStatement ps = con.prepareStatement(sqlDeleteBanSao)) {
                ps.setString(1, maDauSach);
                ps.executeUpdate();
            }

            // 4. Xóa đầu sách
            try (PreparedStatement ps = con.prepareStatement(sqlDeleteDauSach)) {
                ps.setString(1, maDauSach);
                return ps.executeUpdate() > 0;
            }
        }
    }

}

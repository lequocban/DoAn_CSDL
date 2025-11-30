package com.siu.DAO;

import com.siu.helper.DatabaseHelper;
import com.siu.model.LOAI_SACH;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LOAI_SACH_DAO {

    public List<LOAI_SACH> getAll() throws Exception {
        List<LOAI_SACH> lstLoaiSach = new ArrayList<>();
        String sql = "SELECT * FROM LOAI_SACH";

        try (
                Connection con = DatabaseHelper.openConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
                LOAI_SACH loai = new LOAI_SACH();
                loai.setMaLoaiSach(rs.getString("MaLoaiSach"));
                loai.setTenLoai(rs.getString("TenLoai"));
                lstLoaiSach.add(loai);
            }
        }
        return lstLoaiSach;
    }

    public List<LOAI_SACH> getByMaLoai(String maLoai) throws Exception {
        List<LOAI_SACH> list = new ArrayList<>();
        String sql = "SELECT * FROM LOAI_SACH WHERE MaLoaiSach = ?";

        try (Connection con = DatabaseHelper.openConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, maLoai);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                LOAI_SACH ls = new LOAI_SACH();
                ls.setMaLoaiSach(rs.getString("MaLoaiSach"));
                ls.setTenLoai(rs.getString("TenLoai"));
                list.add(ls);
            }
        }
        return list;
    }

    public boolean insert(LOAI_SACH loai) throws Exception {
        String sql = "INSERT INTO LOAI_SACH (MaLoaiSach, TenLoai) VALUES (?, ?)";
        try (
                Connection con = DatabaseHelper.openConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, loai.getMaLoaiSach());
            pstmt.setString(2, loai.getTenLoai());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean update(LOAI_SACH loai) throws Exception {
        String sql = "UPDATE LOAI_SACH SET TenLoai = ? WHERE MaLoaiSach = ?";
        try (
                Connection con = DatabaseHelper.openConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, loai.getTenLoai());
            pstmt.setString(2, loai.getMaLoaiSach());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean delete(String maLoaiSach) throws Exception {
        String sql = "DELETE FROM LOAI_SACH WHERE MaLoaiSach = ?";
        try (
                Connection con = DatabaseHelper.openConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, maLoaiSach);
            return pstmt.executeUpdate() > 0;
        }
    }

    public List<LOAI_SACH> searchByName(String tenLoai) throws Exception {
        List<LOAI_SACH> lstLoaiSach = new ArrayList<>();
        String sql = "SELECT * FROM LOAI_SACH WHERE TenLoai LIKE ?";
        try (
                Connection con = DatabaseHelper.openConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, "%" + tenLoai + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    LOAI_SACH loai = new LOAI_SACH();
                    loai.setMaLoaiSach(rs.getString("MaLoaiSach"));
                    loai.setTenLoai(rs.getString("TenLoai"));
                    lstLoaiSach.add(loai);
                }
            }
        }
        return lstLoaiSach;
    }
}

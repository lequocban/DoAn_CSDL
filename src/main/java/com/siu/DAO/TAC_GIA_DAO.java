package com.siu.DAO;

import com.siu.helper.DatabaseHelper;
import com.siu.model.TAC_GIA;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TAC_GIA_DAO {

    public List<TAC_GIA> getAll() throws Exception {
        List<TAC_GIA> dsTacGia = new ArrayList<>();
        String sql = "SELECT * FROM TAC_GIA";

        try (
                Connection con = DatabaseHelper.openConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                TAC_GIA tg = new TAC_GIA();
                tg.setMaTacGia(rs.getString("MaTacGia"));
                tg.setTenTacGia(rs.getString("TenTacGia"));
                dsTacGia.add(tg);
            }
        }
        return dsTacGia;
    }

    public TAC_GIA getByMaTacGia(String ma) throws Exception {
        TAC_GIA tg = null;
        String sql = "SELECT * FROM TAC_GIA WHERE MaTacGia = ?";

        try (Connection con = DatabaseHelper.openConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, ma);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                tg = new TAC_GIA();
                tg.setMaTacGia(rs.getString("MaTacGia"));
                tg.setTenTacGia(rs.getString("TenTacGia"));
            }
        }
        return tg;
    }

    public boolean insert(TAC_GIA tg) throws Exception {
        String sql = "INSERT INTO TAC_GIA (MaTacGia, TenTacGia) VALUES (?, ?)";
        try (
                Connection con = DatabaseHelper.openConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, tg.getMaTacGia());
            pstmt.setString(2, tg.getTenTacGia());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean update(TAC_GIA tg) throws Exception {
        String sql = "UPDATE TAC_GIA SET TenTacGia = ? WHERE MaTacGia = ?";
        try (
                Connection con = DatabaseHelper.openConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, tg.getTenTacGia());
            pstmt.setString(2, tg.getMaTacGia());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean delete(String maTacGia) throws Exception {
        String sql = "DELETE FROM TAC_GIA WHERE MaTacGia = ?";
        try (
                Connection con = DatabaseHelper.openConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, maTacGia);
            return pstmt.executeUpdate() > 0;
        }
    }

    public List<TAC_GIA> searchByName(String keyword) throws Exception {
        List<TAC_GIA> list = new ArrayList<>();

        String sql = "SELECT * FROM TAC_GIA WHERE TenTacGia LIKE ?";
        try (
                Connection con = DatabaseHelper.openConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, "%" + keyword + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    TAC_GIA tg = new TAC_GIA();
                    tg.setMaTacGia(rs.getString("MaTacGia"));
                    tg.setTenTacGia(rs.getString("TenTacGia"));
                    list.add(tg);
                }
            }
        }
        return list;
    }
}

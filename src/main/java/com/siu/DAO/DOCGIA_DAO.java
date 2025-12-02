/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.siu.DAO;

import com.siu.helper.DatabaseHelper;
import com.siu.model.DOCGIA;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class DOCGIA_DAO {

    public ArrayList<DOCGIA> getDSDG() throws Exception {
        ArrayList<DOCGIA> lstDocGia = new ArrayList<>();
        String sql = "select * from DOC_GIA";
        try (
                Connection con = DatabaseHelper.openConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
                DOCGIA dg = new DOCGIA();
                dg.setMSDG(rs.getString("MSDG"));
                dg.setTenDG(rs.getString("TenDG"));
                dg.setDiaChi(rs.getString("DiaChi"));
                dg.setNgaySinh(rs.getDate("NgaySinh"));
                dg.setEmail(rs.getString("Email"));
                dg.setGioiTinh(rs.getString("GioiTinh"));
                dg.setNgayDK(rs.getDate("NgayDangKy"));
                dg.setThongTinKhac(rs.getString("ThongTinKhac"));
                lstDocGia.add(dg);
            }
        }
        return lstDocGia;

    }
    

    public DOCGIA tkBangMSDG(String msdg) throws Exception {
        String sql = "select * from DOC_GIA where MSDG = ?";
        try (
                Connection con = DatabaseHelper.openConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, msdg);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {

                DOCGIA dg = new DOCGIA();
                dg.setMSDG(rs.getString("MSDG"));
                dg.setTenDG(rs.getString("TenDG"));
                dg.setDiaChi(rs.getString("DiaChi"));
                dg.setNgaySinh(rs.getDate("NgaySinh"));
                dg.setEmail(rs.getString("Email"));
                dg.setGioiTinh(rs.getString("GioiTinh"));
                dg.setNgayDK(rs.getDate("NgayDangKy"));
                dg.setThongTinKhac(rs.getString("ThongTinKhac"));
                return dg;
            }
            return null;
        }
    }

    public String themDG(DOCGIA dg) throws Exception {
        Connection con = null;
        try {
            con = DatabaseHelper.openConnection();
            con.setAutoCommit(false);

            if (dg.getMSDG() == null || dg.getMSDG().trim().isEmpty()) {
                return "Ma doc gia khong duoc đe trong!";
            }

            if (dg.getTenDG() == null || dg.getTenDG().trim().isEmpty()) {
                return "Ten doc gia khong duoc de trong!";
            }

            if (dg.getEmail() != null && !dg.getEmail().trim().isEmpty()) {
                if (!dg.getEmail().matches(".+@.+\\..+")) {
                    return "Email khong dung dinh dang!";
                }
            }

            if (dg.getNgaySinh() != null && dg.getNgayDK() != null) {
                if (dg.getNgayDK().before(dg.getNgaySinh())) {
                    return "Ngay dang ky phai sau ngay sinh!";
                }
            }

            String checkSql = "SELECT 1 FROM DOC_GIA WHERE MSDG = ?";
            try (PreparedStatement checkStmt = con.prepareStatement(checkSql)) {
                checkStmt.setString(1, dg.getMSDG());
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next()) {
                        return "Ma doc gia da ton tai!";
                    }
                }
            }

            String sql = "INSERT INTO DOC_GIA (MSDG, TenDG, DiaChi, NgaySinh, Email, GioiTinh, NgayDangKy ,ThongTinKhac) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(sql);) {
                pstmt.setString(1, dg.getMSDG());
                pstmt.setString(2, dg.getTenDG());
                pstmt.setString(3, dg.getDiaChi());
                pstmt.setDate(4, new java.sql.Date(dg.getNgaySinh().getTime()));
                pstmt.setString(5, dg.getEmail());
                pstmt.setString(6, dg.getGioiTinh());
                pstmt.setDate(7, new java.sql.Date(dg.getNgayDK().getTime()));
                pstmt.setString(8, dg.getThongTinKhac());

                int rowAffected = pstmt.executeUpdate();
                if (rowAffected > 0) {
                    con.commit();
                    return "Them doc gia thanh cong!";
                } else {
                    con.rollback();
                    return "Them doc gia that bai!";
                }
            }
        } catch (Exception e) {
            if (con != null) {
                con.rollback();
            }
            e.printStackTrace();
            return "Loi he thong: " + e.getMessage();
        } finally {
            con.setAutoCommit(true);
            con.close();
        }
    }

    public String xoaDG(String msdg) throws Exception {
        Connection con = null;
        try {
            con = DatabaseHelper.openConnection();
            con.setAutoCommit(false);

            if (msdg == null || msdg.trim().isEmpty()) {
                return "Ma doc gia khong duoc đe trong!";
            }

            String checkSql = "SELECT 1 FROM DOC_GIA WHERE MSDG = ?";
            try (PreparedStatement checkStmt = con.prepareStatement(checkSql)) {
                checkStmt.setString(1, msdg);
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (!rs.next()) {
                        return "Ma doc gia khong ton tai!";
                    }
                }
            }

            String sql = "DELETE FROM DOC_GIA WHERE MSDG = ?";
            try (PreparedStatement pstmt = con.prepareStatement(sql);) {
                pstmt.setString(1, msdg);

                int rowAffected = pstmt.executeUpdate();
                if (rowAffected > 0) {
                    con.commit();
                    return "Xoa doc gia thanh cong!";
                } else {
                    con.rollback();
                    return "Xoa doc gia that bai!";
                }
            }
        } catch (Exception e) {
            if (con != null) {
                con.rollback();
            }
            e.printStackTrace();
            return "Loi he thong: " + e.getMessage();

        } finally {
            con.setAutoCommit(true);
            con.close();
        }
    }

    public String capNhatDG(DOCGIA dg) throws Exception {
        Connection con = null;
        try {
            con = DatabaseHelper.openConnection();
            con.setAutoCommit(false);

            if (dg.getMSDG() == null || dg.getMSDG().trim().isEmpty()) {
                return "Ma doc gia khong duoc đe trong!";
            }

            String checkSql = "SELECT 1 FROM DOC_GIA WHERE MSDG = ?";
            try (PreparedStatement checkStmt = con.prepareStatement(checkSql)) {
                checkStmt.setString(1, dg.getMSDG());
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (!rs.next()) {
                        return "Ma doc gia khong ton tai!";
                    }
                }
            }
            
            String sql = "UPDATE DOC_GIA SET TenDG = ?, DiaChi = ?, NgaySinh = ?, "
                    + "Email = ?, GioiTinh = ?, NgayDangKy = ? ,ThongTinKhac = ? "
                    + "WHERE MSDG = ?";
            try (PreparedStatement pstmt = con.prepareStatement(sql);) {
                pstmt.setString(8, dg.getMSDG());
                pstmt.setString(1, dg.getTenDG());
                pstmt.setString(2, dg.getDiaChi());
                pstmt.setDate(3, new java.sql.Date(dg.getNgaySinh().getTime()));
                pstmt.setString(4, dg.getEmail());
                pstmt.setString(5, dg.getGioiTinh());
                pstmt.setDate(6, new java.sql.Date(dg.getNgayDK().getTime()));
                pstmt.setString(7, dg.getThongTinKhac());
                int rowAffected = pstmt.executeUpdate();
                if (rowAffected > 0) {
                    con.commit();
                    return "Cap nhat doc gia thanh cong!";
                } else {
                    con.rollback();
                    return "Cap nhat doc gia that bai!";
                }
            }
        } catch (Exception e) {
            if (con != null) {
                con.rollback();
            }
            e.printStackTrace();
            return "Loi he thong: " + e.getMessage();
        } finally {
            con.setAutoCommit(true);
            con.close();
        }
    }

}

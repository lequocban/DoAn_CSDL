/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.siu.DAO;

import com.siu.helper.DatabaseHelper;
import com.siu.model.NHANVIEN;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class NHANVIEN_DAO {

    public ArrayList<NHANVIEN> getDSNV() throws Exception {
        ArrayList<NHANVIEN> lstNV = new ArrayList<>();

        String sql = "select * from NHAN_VIEN";
        try (Connection con = DatabaseHelper.openConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
                NHANVIEN nv = new NHANVIEN();
                nv.setMSNV(rs.getString("MSNV"));
                nv.setHoTenNV(rs.getString("HoTenNV"));
                nv.setDiaChiNV(rs.getString("DiaChiNV"));
                java.sql.Date sqlDate = rs.getDate("NgaySinhNV");
                if (sqlDate != null) {
                    nv.setNgaySinhNV(new java.util.Date(sqlDate.getTime()));
                }

                nv.setGioiTinh(rs.getString("GioiTinhNV"));
                nv.setSdtNV(rs.getString("DienThoaiNV"));
                nv.setEmailNV(rs.getString("EmailNV"));

                java.sql.Date sqlDate1 = rs.getDate("NgayVaoLam");
                if (sqlDate != null) {
                    nv.setNgayVaoLam(new java.util.Date(sqlDate1.getTime()));
                }
                nv.setChucVu(rs.getString("ChucVu"));
                lstNV.add(nv);
            }
        }
        return lstNV;

    }

    public NHANVIEN tkBangMSNV(String msnv) throws Exception {
        String sql = "select * from NHAN_VIEN where MSNV = ?";
        try (Connection con = DatabaseHelper.openConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, msnv);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                NHANVIEN nv = new NHANVIEN();
                nv.setMSNV(rs.getString("MSNV"));
                nv.setHoTenNV(rs.getString("HoTenNV"));
                nv.setDiaChiNV(rs.getString("DiaChiNV"));
                java.sql.Date sqlDate = rs.getDate("NgaySinhNV");
                if (sqlDate != null) {
                    nv.setNgaySinhNV(new java.util.Date(sqlDate.getTime()));
                }

                nv.setGioiTinh(rs.getString("GioiTinhNV"));
                nv.setSdtNV(rs.getString("DienThoaiNV"));
                nv.setEmailNV(rs.getString("EmailNV"));

                java.sql.Date sqlDate1 = rs.getDate("NgayVaoLam");
                if (sqlDate != null) {
                    nv.setNgayVaoLam(new java.util.Date(sqlDate1.getTime()));
                }
                nv.setChucVu(rs.getString("ChucVu"));
                return nv;
            }
            return null;
        }
    }

    public String themNV(NHANVIEN nv) throws Exception {
        Connection con = null;
        try {
            con = DatabaseHelper.openConnection();
            con.setAutoCommit(false);
            // === KIỂM TRA DỮ LIỆU ĐẦU VÀO ===
            if (nv.getMSNV() == null || nv.getMSNV().trim().isEmpty()) {
                return "Ma nhan vien khong duoc đe trong!";
            }

            if (nv.getHoTenNV() == null || nv.getHoTenNV().trim().isEmpty()) {
                return "Ten nhan vien khong duoc de trong!";
            }

            if (nv.getEmailNV() != null && !nv.getEmailNV().trim().isEmpty()) {
                if (!nv.getEmailNV().matches(".+@.+\\..+")) {
                    return "Email khong dung dinh dang!";
                }
            }

            if (nv.getNgaySinhNV() != null && nv.getNgayVaoLam() != null) {
                if (nv.getNgayVaoLam().before(nv.getNgaySinhNV())) {
                    return "Ngay vao lam phai sau ngay sinh!";
                }
            }

            

            String checkSql = "SELECT 1 FROM NHAN_VIEN WHERE MSNV = ?";
            try (PreparedStatement checkStmt = con.prepareStatement(checkSql)) {
                checkStmt.setString(1, nv.getMSNV());
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next()) {
                        return "Ma nhan vien da ton tai!";
                    }
                }
            }

            String sql = "INSERT INTO NHAN_VIEN (MSNV, HoTenNV, DiaChiNV, NgaySinhNV, "
                    + "GioiTinhNV, DienThoaiNV, EmailNV, NgayVaoLam, ChucVu) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(sql);) {
                pstmt.setString(1, nv.getMSNV());
                pstmt.setString(2, nv.getHoTenNV());
                pstmt.setString(3, nv.getDiaChiNV());
                pstmt.setDate(4, new java.sql.Date(nv.getNgaySinhNV().getTime()));
                pstmt.setString(5, nv.getGioiTinh());
                pstmt.setString(6, nv.getSdtNV());
                pstmt.setString(7, nv.getEmailNV());
                pstmt.setDate(8, new java.sql.Date(nv.getNgayVaoLam().getTime()));
                pstmt.setString(9, nv.getChucVu());

                int rowAffected = pstmt.executeUpdate();
                if (rowAffected > 0) {
                    con.commit();
                    return "Them nhan vien thanh cong!";
                } else {
                    con.rollback();
                    return "Them nhan vien that bai!";
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

    public String xoaNV(String msnv) throws Exception {
        Connection con = null;
        try {
            if (msnv == null || msnv.trim().isEmpty()) {
                return "Ma nhan vien khong duoc đe trong!";
            }

            con = DatabaseHelper.openConnection();
            con.setAutoCommit(false);

            String checkSql = "SELECT 1 FROM NHAN_VIEN WHERE MSNV = ?";
            try (PreparedStatement checkStmt = con.prepareStatement(checkSql)) {
                checkStmt.setString(1, msnv);
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (!rs.next()) {
                        return "Ma nhan vien khong ton tai!";
                    }
                }
            }

            String sql = "DELETE FROM NHAN_VIEN WHERE MSNV = ?";
            try (
                    PreparedStatement pstmt = con.prepareStatement(sql);) {
                pstmt.setString(1, msnv);

                int rowAffected = pstmt.executeUpdate();
                if (rowAffected > 0) {
                    return "Xoa thanh cong!";
                } else {
                    return "Xoa that bai!";
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

    public String capNhatNV(NHANVIEN nv) throws Exception {
        Connection con = null;
        try {
            if (nv.getMSNV() == null || nv.getMSNV().trim().isEmpty()) {
                return "Ma nhan vien khong duoc đe trong!";
            }

            con = DatabaseHelper.openConnection();
            con.setAutoCommit(false);

            String checkSql = "SELECT 1 FROM NHAN_VIEN WHERE MSNV = ?";
            try (PreparedStatement checkStmt = con.prepareStatement(checkSql)) {
                checkStmt.setString(1, nv.getMSNV());
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (!rs.next()) {
                        return "Ma nhan vien khong ton tai!";
                    }
                }
            }

            String sql = "UPDATE NHAN_VIEN SET HoTenNV = ?, DiaChiNV = ?, NgaySinhNV = ?, "
                    + "GioiTinhNV = ?, DienThoaiNV = ?, EmailNV = ?, NgayVaoLam = ?, ChucVu = ? "
                    + "WHERE MSNV = ?";
            try (PreparedStatement pstmt = con.prepareStatement(sql);) {
                pstmt.setString(9, nv.getMSNV());
                pstmt.setString(1, nv.getHoTenNV());
                pstmt.setString(2, nv.getDiaChiNV());
                pstmt.setDate(3, new java.sql.Date(nv.getNgaySinhNV().getTime()));
                pstmt.setString(4, nv.getGioiTinh());
                pstmt.setString(5, nv.getSdtNV());
                pstmt.setString(6, nv.getEmailNV());
                pstmt.setDate(7, new java.sql.Date(nv.getNgayVaoLam().getTime()));
                pstmt.setString(8, nv.getChucVu());

                int rowAffected = pstmt.executeUpdate();
                if (rowAffected > 0) {
                    return "Cap nhat thanh cong!";
                } else {
                    return "Cap nhat that bai!";
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

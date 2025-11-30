/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.siu.DAO;

import com.siu.helper.DatabaseHelper;
import com.siu.model.CHITIETMUON;
import com.siu.model.PHAT;
import com.siu.model.PHIEUMUON;
import com.siu.model.TRASACH;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class DICHVU_DAO {

    public ArrayList<PHIEUMUON> getDSPM() throws Exception {
        ArrayList<PHIEUMUON> lstpm = new ArrayList<>();

        String sql = "select * from PHIEU_MUON";

        try (Connection con = DatabaseHelper.openConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
                PHIEUMUON pm = new PHIEUMUON();
                pm.setSoPhieuMuon(rs.getString("SoPhieuMuon"));
                pm.setMSDG(rs.getString("MSDG"));
                pm.setMSNV(rs.getString("MSNV"));
                pm.setNgayMuon(rs.getDate("NgayMuon"));
                pm.setHanTra(rs.getDate("HanTra"));
                pm.setTrangThai(rs.getString("TrangThai"));

                lstpm.add(pm);

            }
        }
        return lstpm;
    }

    public PHIEUMUON tkPhieumuon(String soPM) throws Exception {
        String sql = "select * from PHIEU_MUON where SoPhieuMuon = ?";
        try (
                Connection con = DatabaseHelper.openConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, soPM);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                PHIEUMUON pm = new PHIEUMUON();
                pm.setSoPhieuMuon(rs.getString("SoPhieuMuon"));
                pm.setMSDG(rs.getString("MSDG"));
                pm.setMSNV(rs.getString("MSNV"));
                pm.setNgayMuon(rs.getDate("NgayMuon"));
                pm.setHanTra(rs.getDate("HanTra"));
                pm.setTrangThai(rs.getString("TrangThai"));
                return pm;
            }
            return null;
        }

    }

    public ArrayList<TRASACH> getDSPT() throws Exception {
        ArrayList<TRASACH> lstpt = new ArrayList<>();
        String sql = "select * from TRA_SACH";

        try (Connection con = DatabaseHelper.openConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
                TRASACH tra = new TRASACH();
                tra.setSoPhieuMuon(rs.getString("SoPhieuMuon"));
                tra.setMaBanSao(rs.getString("MaBanSao"));
                tra.setMsnv(rs.getString("MSNV"));
                tra.setNgayTra(rs.getDate("NgayTra"));

                lstpt.add(tra);

            }
        }
        return lstpt;

    }

    public TRASACH tkPhieuTra(String soPM, String mbs) throws Exception {
        String sql = "select * from TRA_SACH where SoPhieuMuon = ? and MaBanSao = ?";
        try (
                Connection con = DatabaseHelper.openConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, soPM);
            pstmt.setString(2, mbs);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                TRASACH tra = new TRASACH();
                tra.setSoPhieuMuon(rs.getString("SoPhieuMuon"));
                tra.setMaBanSao(rs.getString("MaBanSao"));
                tra.setMsnv(rs.getString("MSNV"));
                tra.setNgayTra(rs.getDate("NgayTra"));

                return tra;
            }
            return null;
        }

    }

    public ArrayList<PHAT> getDSPP() throws Exception {
        ArrayList<PHAT> lstpp = new ArrayList<>();

        String sql = "select * from PHAT";

        try (Connection con = DatabaseHelper.openConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
                PHAT p = new PHAT();
                p.setSoPhieuPhat(rs.getString("SoPhieuPhat"));
                p.setSoPhieuMuon(rs.getString("SoPhieuMuon"));
                p.setMaBanSao(rs.getString("MaBanSao"));
                p.setMsnv(rs.getString("MSNV"));
                p.setNgayPhat(rs.getDate("NgayPhat"));
                p.setLyDoPhat(rs.getString("LyDoPhat"));

                lstpp.add(p);

            }
        }
        return lstpp;
    }

    public String getMBS_CS(String mds) throws Exception {
        String sql = "select MaBanSao from BAN_SAO where MaDauSach = ? and TinhTrang = 'co san'";
        try (Connection con = DatabaseHelper.openConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, mds);
            String mbs = "";
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                mbs = rs.getString("MaBanSao");
                return mbs;
            }
            return mbs;
        }
    }

    public ArrayList<String> getLstMBS_chiTiet(String spm) throws Exception {
        String sql = "select MaBanSao from CHI_TIET_MUON where SoPhieuMuon = ? ";
        try (Connection con = DatabaseHelper.openConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, spm);
            ArrayList<String> lstMBS = new ArrayList<>();
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String mbs = rs.getString("MaBanSao");
                lstMBS.add(mbs);
            }
            return lstMBS;
        }
    }

    public void updateTrangThaiQuaHan(String spm) throws Exception {
        Connection con = null;
        try {
            con = DatabaseHelper.openConnection();
            con.setAutoCommit(false);

            Date now = new Date();

            if (spm == null || spm.trim().isEmpty()) {
                con.rollback();
                return;
            }

            String getHanTraSql = "SELECT HanTra FROM PHIEU_MUON WHERE SoPhieuMuon = ?";
            Date hanTra = null;
            try (PreparedStatement getHanTraStmt = con.prepareStatement(getHanTraSql)) {
                getHanTraStmt.setString(1, spm);
                try (ResultSet rs = getHanTraStmt.executeQuery()) {
                    if (!rs.next()) {
                        con.rollback();
                        return;
                    }
                    hanTra = rs.getDate("HanTra");
                }
            }

            if (hanTra != null && hanTra.before(now)) {
                String updatePhieuSql = "UPDATE PHIEU_MUON SET TrangThai = 'Qua han' WHERE SoPhieuMuon = ?  AND TrangThai = 'Dang muon'";
                try (PreparedStatement pstmtPhieu = con.prepareStatement(updatePhieuSql)) {
                    pstmtPhieu.setString(1, spm);
                    pstmtPhieu.executeUpdate();
                }
            }
            con.commit();
            return;

        } catch (Exception e) {
            if (con != null) {
                con.rollback();
            }
            e.printStackTrace();
            return;
        } finally {
            con.setAutoCommit(true);
            con.close();
        }

    }

    public PHAT tkPhieuPhat(String soPP) throws Exception {
        String sql = "select * from PHAT where SoPhieuPhat = ?";
        try (
                Connection con = DatabaseHelper.openConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, soPP);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                PHAT p = new PHAT();
                p.setSoPhieuPhat(rs.getString("SoPhieuPhat"));
                p.setSoPhieuMuon(rs.getString("SoPhieuMuon"));
                p.setMaBanSao(rs.getString("MaBanSao"));
                p.setMsnv(rs.getString("MSNV"));
                p.setNgayPhat(rs.getDate("NgayPhat"));
                p.setLyDoPhat(rs.getString("LyDoPhat"));
                return p;
            }
        }
        return null;

    }

    public String themPhieuMuon(String SoPhieuMuon, String MSDG, String MSNV, Date NgayMuon, Date HanTra, String TrangThai) throws Exception {
        Connection con = null;
        try {
            con = DatabaseHelper.openConnection();
            con.setAutoCommit(false);

            if (SoPhieuMuon == null || SoPhieuMuon.trim().isEmpty()) {
                con.rollback();
                return "So phieu muon khong duoc de trong!";
            }

            if (MSDG == null || MSDG.trim().isEmpty()) {
                con.rollback();
                return "Ma doc gia khong duoc de trong!";
            }

            if (MSNV == null || MSNV.trim().isEmpty()) {
                con.rollback();
                return "Ma nhan vien khong duoc de trong!";
            }

            if (NgayMuon == null) {
                con.rollback();
                return "Ngay muon khong duoc de trong!";
            }
            if (HanTra == null) {
                con.rollback();
                return "Han tra khong duoc de trong!";
            }

            if (TrangThai == null || TrangThai.trim().isEmpty()) {
                con.rollback();
                return "Trang thai khong duoc de trong!";
            }

            String insertPhieuSql = "INSERT INTO PHIEU_MUON (SoPhieuMuon, MSDG, MSNV, NgayMuon, HanTra, TrangThai) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmtPhieu = con.prepareStatement(insertPhieuSql)) {
                pstmtPhieu.setString(1, SoPhieuMuon);
                pstmtPhieu.setString(2, MSDG);
                pstmtPhieu.setString(3, MSNV);
                pstmtPhieu.setDate(4, new java.sql.Date(NgayMuon.getTime()));
                pstmtPhieu.setDate(5, new java.sql.Date(HanTra.getTime()));
                pstmtPhieu.setString(6, "Dang muon");

                int rowPhieu = pstmtPhieu.executeUpdate();
                if (rowPhieu <= 0) {
                    con.rollback();
                    return "Them phieu muon that bai!";
                }
            }
            con.commit();
            return "Them phieu muon thanh cong!";

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

    public String xoaPhieuMuon(String maPhieuMuon) throws Exception {
        Connection con = null;
        try {
            con = DatabaseHelper.openConnection();
            con.setAutoCommit(false);

            if (maPhieuMuon == null || maPhieuMuon.trim().isEmpty()) {
                con.rollback();
                return "Ma phieu muon khong duoc de trong!";
            }

            // Kiểm tra tồn tại phiếu mượn
            String checkSql = "SELECT SoPhieuMuon FROM PHIEU_MUON WHERE SoPhieuMuon = ?";
            try (PreparedStatement pstmtCheck = con.prepareStatement(checkSql)) {
                pstmtCheck.setString(1, maPhieuMuon);
                ResultSet rs = pstmtCheck.executeQuery();
                if (!rs.next()) {
                    con.rollback();
                    return "Phieu muon khong ton tai!";
                }
            }
            // Xóa phiếu phạt nếu có
            String deletePPSql = "DELETE FROM PHAT WHERE SoPhieuMuon = ?";
            try (PreparedStatement pstmtCT = con.prepareStatement(deletePPSql)) {
                pstmtCT.setString(1, maPhieuMuon);
                pstmtCT.executeUpdate(); // không rollback nếu 0 dòng vì có thể phiếu không có
            }
            // Xóa chi tiết phiếu mượn trước (nếu có)
            String deleteCTSql = "DELETE FROM CHI_TIET_MUON WHERE SoPhieuMuon = ?";
            try (PreparedStatement pstmtCT = con.prepareStatement(deleteCTSql)) {
                pstmtCT.setString(1, maPhieuMuon);
                pstmtCT.executeUpdate(); // không rollback nếu 0 dòng vì có thể phiếu không có chi tiết
            }
            
            
            

            // Xóa phiếu mượn
            String deletePhieuSql = "DELETE FROM PHIEU_MUON WHERE SoPhieuMuon = ?";
            try (PreparedStatement pstmtPhieu = con.prepareStatement(deletePhieuSql)) {
                pstmtPhieu.setString(1, maPhieuMuon);
                int row = pstmtPhieu.executeUpdate();

                if (row <= 0) {
                    con.rollback();
                    return "Xoa phieu muon that bai!";
                }
            }

            con.commit();
            return "Xoa phieu muon thanh cong!";

        } catch (Exception e) {
            if (con != null) {
                con.rollback();
            }
            e.printStackTrace();
            return "Loi he thong: " + e.getMessage();
        } finally {
            if (con != null) {
                con.setAutoCommit(true);
                con.close();
            }
        }
    }

    public String muonSach(PHIEUMUON phieuMuon, ArrayList<String> lstMBS) throws Exception {
        Connection con = null;
        try {
            con = DatabaseHelper.openConnection();
            con.setAutoCommit(false);

            if (phieuMuon.getSoPhieuMuon() == null || phieuMuon.getSoPhieuMuon().trim().isEmpty()) {
                con.rollback();
                return "So phieu muon khong duoc de trong!";
            }

            if (phieuMuon.getMSDG() == null || phieuMuon.getMSDG().trim().isEmpty()) {
                con.rollback();
                return "Ma doc gia khong duoc de trong!";
            }

            if (phieuMuon.getMSNV() == null || phieuMuon.getMSNV().trim().isEmpty()) {
                con.rollback();
                return "Ma nhan vien khong duoc de trong!";
            }

            if (phieuMuon.getNgayMuon() == null) {
                con.rollback();
                return "Ngay muon khong duoc de trong!";
            }
            if (phieuMuon.getHanTra() == null) {
                con.rollback();
                return "Han tra khong duoc de trong!";
            }

            if (lstMBS == null || lstMBS.isEmpty()) {
                con.rollback();
                return "Danh sach muon khong duoc de trong!";
            }

            String checkPhieuSql = "SELECT 1 FROM PHIEU_MUON WHERE SoPhieuMuon = ?";
            try (PreparedStatement checkStmt = con.prepareStatement(checkPhieuSql)) {
                checkStmt.setString(1, phieuMuon.getSoPhieuMuon());
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (!rs.next()) {
                        con.rollback();
                        return "So phieu muon khong ton tai!";
                    }
                }
            }

            String checkDGSql = "SELECT TenDG FROM DOC_GIA WHERE MSDG = ?";
            try (PreparedStatement checkDGStmt = con.prepareStatement(checkDGSql)) {
                checkDGStmt.setString(1, phieuMuon.getMSDG());
                try (ResultSet rs = checkDGStmt.executeQuery()) {
                    if (!rs.next()) {
                        con.rollback();
                        return "Khong tim thay doc gia!";
                    }
                }
            }

            String checkNVSql = "SELECT HoTenNV FROM NHAN_VIEN WHERE MSNV = ?";
            try (PreparedStatement checkNVStmt = con.prepareStatement(checkNVSql)) {
                checkNVStmt.setString(1, phieuMuon.getMSNV());
                try (ResultSet rs = checkNVStmt.executeQuery()) {
                    if (!rs.next()) {
                        con.rollback();
                        return "Khong tim thay nhan vien!";
                    }
                }
            }

            for (String mbs : lstMBS) {
                String checkSachSql = "SELECT 1 FROM BAN_SAO WHERE MaBanSao = ? AND TinhTrang = 'co san'";
                try (PreparedStatement checkSachStmt = con.prepareStatement(checkSachSql)) {
                    checkSachStmt.setString(1, mbs);
                    try (ResultSet rs = checkSachStmt.executeQuery()) {
                        if (!rs.next()) {
                            con.rollback();
                            return "Ban sao sach " + mbs + " khong ton tai hoac da duoc muon!";
                        }
                    }
                }
            }

            /////////////////
//            String insertPhieuSql = "INSERT INTO PHIEU_MUON (SoPhieuMuon, MSDG, MSNV, NgayMuon, HanTra, TrangThai) "
//                    + "VALUES (?, ?, ?, ?, ?, ?)";
//            try (PreparedStatement pstmtPhieu = con.prepareStatement(insertPhieuSql)) {
//                pstmtPhieu.setString(1, phieuMuon.getSoPhieuMuon());
//                pstmtPhieu.setString(2, phieuMuon.getMSDG());
//                pstmtPhieu.setString(3, phieuMuon.getMSNV());
//                pstmtPhieu.setDate(4, new java.sql.Date(phieuMuon.getNgayMuon().getTime()));
//                pstmtPhieu.setDate(5, new java.sql.Date(phieuMuon.getHanTra().getTime()));
//                pstmtPhieu.setString(6, "Dang muon");
//
//                int rowPhieu = pstmtPhieu.executeUpdate();
//                if (rowPhieu <= 0) {
//                    con.rollback();
//                    return "Them phieu muon that bai!";
//                }
//            }
            String insertChiTietSql = "INSERT INTO CHI_TIET_MUON (SoPhieuMuon, MaBanSao) VALUES (?, ?)";
            try (PreparedStatement pstmtChiTiet = con.prepareStatement(insertChiTietSql)) {
                for (String mbs : lstMBS) {
                    pstmtChiTiet.setString(1, phieuMuon.getSoPhieuMuon());
                    pstmtChiTiet.setString(2, mbs);
                    pstmtChiTiet.addBatch();
                }
                pstmtChiTiet.executeBatch();
            }

            String updateSachSql = "UPDATE BAN_SAO SET TinhTrang = 'dang muon' WHERE MaBanSao = ?";
            try (PreparedStatement pstmtUpdate = con.prepareStatement(updateSachSql)) {
                for (String mbs : lstMBS) {
                    pstmtUpdate.setString(1, mbs);
                    pstmtUpdate.addBatch();
                }
                pstmtUpdate.executeBatch();
            }

            con.commit();
            return "Muon thanh cong!";

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

    public String traSach(String soPM, String msnv) throws Exception {
        Connection con = null;
        try {
            con = DatabaseHelper.openConnection();
            con.setAutoCommit(false);

            if (soPM == null || soPM.trim().isEmpty()) {
                con.rollback();
                return "So phieu muon khong duoc de trong!";
            }
            if (msnv == null || msnv.trim().isEmpty()) {
                con.rollback();
                return "Ma so nhan vien khong duoc de trong!";
            }

            String checkPhieuSql = "SELECT MSDG, TrangThai FROM PHIEU_MUON WHERE SoPhieuMuon = ?";
//            String msdg = "";
//            String trangThaiPhieu = "";

            try (PreparedStatement checkStmt = con.prepareStatement(checkPhieuSql)) {
                checkStmt.setString(1, soPM);
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (!rs.next()) {
                        con.rollback();
                        return "Khong tim thay phieu muon!";
                    }
//                    msdg = rs.getString("MSDG");
//                    trangThaiPhieu = rs.getString("TrangThai");
                }
            }
//            if (!trangThaiPhieu.equalsIgnoreCase("Dang muon") ||!trangThaiPhieu.equalsIgnoreCase("Qua han")){
//                return "Phieu muon da tra!";
//            }

            String checkNVSql = "SELECT HoTenNV FROM NHAN_VIEN WHERE MSNV = ?";
//            String tenNV = "";
            try (PreparedStatement checkNVStmt = con.prepareStatement(checkNVSql)) {
                checkNVStmt.setString(1, msnv);
                try (ResultSet rs = checkNVStmt.executeQuery()) {
                    if (!rs.next()) {
                        con.rollback();
                        return "Khong tim thay nhan vien!";
                    }
//                    tenNV = rs.getString("HoTenNV");
                }
            }

            String getHanTraSql = "SELECT HanTra FROM PHIEU_MUON WHERE SoPhieuMuon = ?";
            Date hanTra = null;
            try (PreparedStatement getHanTraStmt = con.prepareStatement(getHanTraSql)) {
                getHanTraStmt.setString(1, soPM);
                try (ResultSet rs = getHanTraStmt.executeQuery()) {
                    if (!rs.next()) {
                        con.rollback();
                        return "Khong tim thay han tra!";
                    }
                    hanTra = rs.getDate("HanTra");
                }
            }

            ArrayList<String> lstMBS = new ArrayList<>();

            //lstMBS = getLstMBS_chiTiet(soPM);
            String sqlLSTMBS = "select MaBanSao from CHI_TIET_MUON where SoPhieuMuon = ? ";
            try ( PreparedStatement pstmt = con.prepareStatement(sqlLSTMBS);) {
                pstmt.setString(1, soPM);
                
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    String mbs = rs.getString("MaBanSao");
                    lstMBS.add(mbs);
                }

            }

            if (lstMBS.isEmpty()) {
                return "Danh sach ma ban sao trong!";
            }

            String insertTraSachSql = "INSERT INTO TRA_SACH (SoPhieuMuon, MaBanSao, MSNV, NgayTra) VALUES (?, ?, ?, GETDATE())";
            try (PreparedStatement pstmtTraSach = con.prepareStatement(insertTraSachSql)) {
                for (String maBS : lstMBS) {
                    pstmtTraSach.setString(1, soPM);
                    pstmtTraSach.setString(2, maBS);
                    pstmtTraSach.setString(3, msnv);
                    pstmtTraSach.addBatch();
                }

                pstmtTraSach.executeBatch();
            } catch (BatchUpdateException e) {
                con.rollback();
                return "Loi them ban ghi tra sach: " + e.getMessage();
            }

//            
//            
//            
            String updateSachSql = "UPDATE BAN_SAO SET TinhTrang = 'co san' WHERE MaBanSao = ?";
            try (PreparedStatement pstmtUpdate = con.prepareStatement(updateSachSql)) {
                for (String maBS : lstMBS) {
                    pstmtUpdate.setString(1, maBS);
                    pstmtUpdate.addBatch();
                }
                pstmtUpdate.executeBatch();
            } catch (BatchUpdateException e) {
                con.rollback();
                return "Loi cap nhat tinh trang sach: " + e.getMessage();
            }

            String updatePhieuSql = "UPDATE PHIEU_MUON SET TrangThai = 'Da tra' WHERE SoPhieuMuon = ?";
            try (PreparedStatement pstmtPhieu = con.prepareStatement(updatePhieuSql)) {
                pstmtPhieu.setString(1, soPM);
                pstmtPhieu.executeUpdate();
            }
            
            
            
            Date ngayHienTai = new Date();
            if (ngayHienTai.after(hanTra)) {
                long diffTime = ngayHienTai.getTime() - hanTra.getTime();
                long diffDays = diffTime / (1000 * 60 * 60 * 24);

                // Tạo số phiếu phạt tự động
                String soPP = "PP" + System.currentTimeMillis();
                String lyDo = "Tra sach qua han " + diffDays + " ngay";
                
                con.commit();
                
                
                for (String mbs : lstMBS) {
                    String rsPhat = themPhieuPhat(soPP, soPM, mbs, msnv, ngayHienTai, lyDo);
                    if (!rsPhat.equals("Them phieu phat thanh cong")) {
                        //con.rollback();
                        return "Tra sach that bai do loi phat: " + rsPhat;
                    }
                }
                return "Tra sach muon, tu dong them phieu phat so: " + soPP;
            }

            con.commit();
            return "Tra sach thanh cong";

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

//    public String traAllSach(String soPM, String msnv) throws Exception {
//        Connection con = null;
//        try {
//            con = DatabaseHelper.openConnection();
//            con.setAutoCommit(false);
//
//            if (soPM == null || soPM.trim().isEmpty()) {
//                con.rollback();
//                return "So phieu muon khong duoc de trong!";
//            }
//
//            if (msnv == null || msnv.trim().isEmpty()) {
//                con.rollback();
//                return "Ma so nhan vien khong duoc de trong!";
//            }
//
//            String checkPhieuSql = "SELECT MSDG, TrangThai FROM PHIEU_MUON WHERE SoPhieuMuon = ?";
//            String msdg = "";
//            String trangThaiPhieu = "";
//
//            try (PreparedStatement checkStmt = con.prepareStatement(checkPhieuSql)) {
//                checkStmt.setString(1, soPM);
//                try (ResultSet rs = checkStmt.executeQuery()) {
//                    if (!rs.next()) {
//                        con.rollback();
//                        return "Khong tim thay phieu muon!";
//                    }
//                    msdg = rs.getString("MSDG");
//                    trangThaiPhieu = rs.getString("TrangThai");
//                }
//            }
//
//            String checkNVSql = "SELECT HoTenNV FROM NHAN_VIEN WHERE MSNV = ?";
//            String tenNV = "";
//            try (PreparedStatement checkNVStmt = con.prepareStatement(checkNVSql)) {
//                checkNVStmt.setString(1, msnv);
//                try (ResultSet rs = checkNVStmt.executeQuery()) {
//                    if (!rs.next()) {
//                        con.rollback();
//                        return "Khong tim thay nhan vien!";
//                    }
//                    tenNV = rs.getString("HoTenNV");
//                }
//            }
//
//            ArrayList<String> lstMBS = new ArrayList<>();
//            String getSachSql = "SELECT MaBanSao FROM CHI_TIET_MUON WHERE SoPhieuMuon = ? ";
//            try (PreparedStatement pstmtGetMBS = con.prepareStatement(getSachSql)) {
//                pstmtGetMBS.setString(1, soPM);
//                try (ResultSet rs = pstmtGetMBS.executeQuery()) {
//                    while (rs.next()) {
//                        lstMBS.add(rs.getString("MaBanSao"));
//                    }
//                }
//            }
//
//            if (lstMBS.isEmpty()) {
//                return "Danh sach phieu muon rong";
//            }
//
//            String getHanTraSql = "SELECT HanTra FROM PHIEU_MUON WHERE SoPhieuMuon = ?";
//            Date hanTra = null;
//            try (PreparedStatement getHanTraStmt = con.prepareStatement(getHanTraSql)) {
//                getHanTraStmt.setString(1, soPM);
//                try (ResultSet rs = getHanTraStmt.executeQuery()) {
//                    if (!rs.next()) {
//                        con.rollback();
//                        return "Khong tim thay han tra!";
//                    }
//                    hanTra = rs.getDate("HanTra");
//                }
//            }
//
//            String insertTraSachSql = "INSERT INTO TRA_SACH (SoPhieuMuon, MaBanSao, MSNV, NgayTra) VALUES (?, ?, ?, GETDATE())";
//            try (PreparedStatement pstmtTraSach = con.prepareStatement(insertTraSachSql)) {
//                for (String maBS : lstMBS) {
//                    pstmtTraSach.setString(1, soPM);
//                    pstmtTraSach.setString(2, maBS);
//                    pstmtTraSach.setString(3, msnv);
//                    pstmtTraSach.addBatch();
//                }
//
//                pstmtTraSach.executeBatch();
//            } catch (BatchUpdateException e) {
//                con.rollback();
//                return "Loi them ban ghi tra sach: " + e.getMessage();
//            }
//
////            String deleteChiTietSql = "DELETE FROM CHI_TIET_MUON WHERE SoPhieuMuon = ? ";
////            try (PreparedStatement pstmtDelete = con.prepareStatement(deleteChiTietSql)) {
////                pstmtDelete.setString(1, soPM);
////
////                int rowAffected = pstmtDelete.executeUpdate();
////                if (rowAffected <= 0) {
////                    con.rollback();
////                    return "Xoa khoi chi tiet phieu muon that bai";
////                }
////            }
//            String updateSachSql = "UPDATE BAN_SAO SET TinhTrang = 'co san' WHERE MaBanSao = ?";
//            try (PreparedStatement pstmtUpdate = con.prepareStatement(updateSachSql)) {
//                for (String maBS : lstMBS) {
//                    pstmtUpdate.setString(1, maBS);
//                    pstmtUpdate.addBatch();
//                }
//                pstmtUpdate.executeBatch();
//            } catch (BatchUpdateException e) {
//                con.rollback();
//                return "Loi cap nhat tinh trang sach: " + e.getMessage();
//            }
//
//            String checkConSachMuonSql = "SELECT COUNT(*) as SoSachConLai FROM CHI_TIET_MUON WHERE SoPhieuMuon = ?";
//            int soSachConLai = 0;
//            try (PreparedStatement checkConStmt = con.prepareStatement(checkConSachMuonSql)) {
//                checkConStmt.setString(1, soPM);
//                try (ResultSet rs = checkConStmt.executeQuery()) {
//                    if (rs.next()) {
//                        soSachConLai = rs.getInt("SoSachConLai");
//                    }
//                }
//            }
//
//            if (soSachConLai == 0) {
//                String updatePhieuSql = "UPDATE PHIEU_MUON SET TrangThai = 'Da tra' WHERE SoPhieuMuon = ?";
//                try (PreparedStatement pstmtPhieu = con.prepareStatement(updatePhieuSql)) {
//                    pstmtPhieu.setString(1, soPM);
//                    pstmtPhieu.executeUpdate();
//                }
//            }
//
//            if (hanTra != null) {
//                Date ngayHienTai = new Date();
//                if (ngayHienTai.after(hanTra)) {
//                    long diffTime = ngayHienTai.getTime() - hanTra.getTime();
//                    long diffDays = diffTime / (1000 * 60 * 60 * 24);
//
//                    // Tạo số phiếu phạt tự động
//                    String soPPBase = "PP" + System.currentTimeMillis();
//                    String lyDo = "Tra sach qua han " + diffDays + " ngay";
//
//                    int cnt = 0;
//                    for (String maBS : lstMBS) {
//                        String soPP = soPPBase + cnt++;
//                        String rsPhat = themPhieuPhat(soPP, soPM, maBS, msnv, ngayHienTai, lyDo);
//                        if (!rsPhat.equals("Them phieu phat thanh cong")) {
//                            con.rollback();
//                            return "Tra sach that bai do loi phat: " + rsPhat;
//                        }
//                    }
//                    con.commit();
//                    return "Tra sach muon ,tu dong them phieu phat voi dau so: " + soPPBase;
//                }
//
//            }
//
//            con.commit();
//            return "Tra sach thanh cong";
//
//        } catch (Exception e) {
//            if (con != null) {
//                con.rollback();
//            }
//            e.printStackTrace();
//            return "Loi he thong: " + e.getMessage();
//        } finally {
//            con.setAutoCommit(true);
//            con.close();
//        }
//    }
    public String themPhieuPhat(String soPP, String soPM, String maBS, String msnv, Date ngayPhat, String lyDo) throws Exception {
        Connection con = null;
        try {
            con = DatabaseHelper.openConnection();
            con.setAutoCommit(false);
            if (soPP == null || soPP.trim().isEmpty()) {
                con.rollback();
                return "So phieu muon khong duoc de trong!";
            }
            if (soPM == null || soPM.trim().isEmpty()) {
                con.rollback();
                return "So phieu muon khong duoc de trong!";
            }
            if (maBS == null || maBS.trim().isEmpty()) {
                con.rollback();
                return "Ma ban sao khong duoc de trong!";
            }

            if (msnv == null || msnv.trim().isEmpty()) {
                con.rollback();
                return "Ma so nhan vien khong duoc de trong!";
            }

            if (ngayPhat == null) {
                con.rollback();
                return "Ngay phat khong duoc de trong!";
            }

            if (lyDo == null || lyDo.trim().isEmpty()) {
                con.rollback();
                return "Ly do phat khong duoc de trong!";
            }

            String checkPhieuSql = "SELECT MSDG, TrangThai FROM PHIEU_MUON WHERE SoPhieuMuon = ?";
            String msdg = "";
            String trangThaiPhieu = "";

            try (PreparedStatement checkStmt = con.prepareStatement(checkPhieuSql)) {
                checkStmt.setString(1, soPM);
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (!rs.next()) {
                        con.rollback();
                        return "Khong tim thay phieu muon!";
                    }
                    msdg = rs.getString("MSDG");
                    trangThaiPhieu = rs.getString("TrangThai");
                }
            }

            String checkNVSql = "SELECT HoTenNV FROM NHAN_VIEN WHERE MSNV = ?";
            String tenNV = "";
            try (PreparedStatement checkNVStmt = con.prepareStatement(checkNVSql)) {
                checkNVStmt.setString(1, msnv);
                try (ResultSet rs = checkNVStmt.executeQuery()) {
                    if (!rs.next()) {
                        con.rollback();
                        return "Khong tim thay nhan vien!";
                    }
                    tenNV = rs.getString("HoTenNV");
                }
            }

            String insertPP = "INSERT INTO PHAT (SoPhieuPhat, SoPhieuMuon, MaBanSao, MSNV, NgayPhat, LyDoPhat) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmPhat = con.prepareStatement(insertPP)) {
                pstmPhat.setString(1, soPP);
                pstmPhat.setString(2, soPM);
                pstmPhat.setString(3, maBS);
                pstmPhat.setString(4, msnv);
                pstmPhat.setDate(5, new java.sql.Date(ngayPhat.getTime()));
                pstmPhat.setString(6, lyDo);
                int rowPhieu = pstmPhat.executeUpdate();
                if (rowPhieu <= 0) {
                    con.rollback();
                    return "Them phieu phat that bai!";
                }
            }

            String updatePhieuSql = "UPDATE PHIEU_MUON SET TrangThai = 'Bi phat' WHERE SoPhieuMuon = ?";
            try (PreparedStatement pstmtPhieu = con.prepareStatement(updatePhieuSql)) {
                pstmtPhieu.setString(1, soPM);
                pstmtPhieu.executeUpdate();
            }

            con.commit();
            return "Them phieu phat thanh cong";

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

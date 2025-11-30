package com.siu.DAO;

import com.siu.helper.DatabaseHelper;
import com.siu.model.BAN_SAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BAN_SAO_DAO {

    public List<BAN_SAO> getAll() throws Exception {
        List<BAN_SAO> list = new ArrayList<>();
        String sql = "SELECT * FROM BAN_SAO";

        try (
                Connection con = DatabaseHelper.openConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
                BAN_SAO bs = new BAN_SAO();
                bs.setMaBanSao(rs.getString("MaBanSao"));
                bs.setMaDauSach(rs.getString("MaDauSach"));
                bs.setTinhTrang(rs.getString("TinhTrang"));
                bs.setViTri(rs.getString("ViTri"));

                list.add(bs);
            }
        }
        return list;
    }

    public List<BAN_SAO> getByMaDauSach(String maDauSach) throws Exception {
        List<BAN_SAO> list = new ArrayList<>();
        String sql = "SELECT * FROM BAN_SAO WHERE MaDauSach = ?";

        try (Connection con = DatabaseHelper.openConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, maDauSach);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                BAN_SAO bs = new BAN_SAO();
                bs.setMaBanSao(rs.getString("MaBanSao"));
                bs.setMaDauSach(rs.getString("MaDauSach"));
                bs.setTinhTrang(rs.getString("TinhTrang"));
                bs.setViTri(rs.getString("ViTri"));
                list.add(bs);
            }
        }
        return list;
    }

    public boolean delete(String maBanSao) throws Exception {
        String sql = "DELETE FROM BAN_SAO WHERE MaBanSao = ?";
        try (Connection con = DatabaseHelper.openConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, maBanSao);
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean insert(BAN_SAO bs) throws Exception {
    String sql = "INSERT INTO BAN_SAO (MaBanSao, MaDauSach, TinhTrang, ViTri) VALUES (?, ?, ?, ?)";

    try (Connection con = DatabaseHelper.openConnection();
         PreparedStatement stmt = con.prepareStatement(sql)) {

        stmt.setString(1, bs.getMaBanSao());
        stmt.setString(2, bs.getMaDauSach());
        stmt.setString(3, bs.getTinhTrang());
        stmt.setString(4, bs.getViTri());

        return stmt.executeUpdate() > 0;
    }
}

}

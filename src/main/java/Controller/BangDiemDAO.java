package Controller;

import Helper.DatabaseHelper;
import Model.BangDiem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BangDiemDAO {

    public ArrayList<BangDiem> getListBangDiem() throws Exception {
        ArrayList<BangDiem> list = new ArrayList<>();
        String sql = "SELECT * FROM BangDiem";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    BangDiem bd = createBangDiem(rs);
                    list.add(bd);
                }
                return list;
            }
        }
    }

    public boolean insert(BangDiem bd) throws Exception {
        String sql = "INSERT INTO BangDiem(MaSinhVien, TiengAnh, TinHoc, GDTC)"
                + " VALUES (?,?,?,?)";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, bd.getMaSinhVien());
            pstmt.setFloat(2, bd.getTiengAnh());
            pstmt.setFloat(3, bd.getTinHoc());
            pstmt.setFloat(4, bd.getGDTC());

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean update(BangDiem bd) throws Exception {
        String sql = "UPDATE BangDiem"
                + " SET TiengAnh = ?, TinHoc = ?, GDTC = ?"
                + " WHERE MaSinhVien = ?";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setInt(5, bd.getMa());
            pstmt.setString(1, bd.getMaSinhVien());
            pstmt.setFloat(2, bd.getTiengAnh());
            pstmt.setFloat(3, bd.getTinHoc());
            pstmt.setFloat(4, bd.getGDTC());

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean deleteByMaSinhVien(String MasinhVien) throws Exception {
        String sql = "DELETE FROM BangDiem"
                + " WHERE MaSinhVien = ?";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, MasinhVien);

            return pstmt.executeUpdate() > 0;
        }
    }

    public BangDiem findByMaSinhVien(String MaSinhVien) throws Exception {
        String sql = "SELECT * FROM BangDiem WHERE MaSinhVien = ?";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, MaSinhVien);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    BangDiem bd = createBangDiem(rs);

                    return bd;
                }
            }
            return null;
        }
    }

    public ArrayList<BangDiem> findTop(int top) throws Exception {
        String sql = "SELECT top " + top + " *, (TiengAnh + TinHoc + GDTC)/3 AS DTB "
                + " FROM BangDiem ORDER BY DTB DESC";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);) {
            try (ResultSet rs = pstmt.executeQuery()) {
                ArrayList<BangDiem> list = new ArrayList<>();
                while (rs.next()) {
                    BangDiem bd = createBangDiem(rs);
                    list.add(bd);
                }
                return list;
            }
        }
    }

    private BangDiem createBangDiem(final ResultSet rs) throws SQLException {
        BangDiem bd = new BangDiem();
        bd.setMa(rs.getInt("Ma"));
        bd.setMaSinhVien(rs.getString("MaSinhVien"));
        bd.setTiengAnh(rs.getFloat("TiengAnh"));
        bd.setTinHoc(rs.getFloat("TinHoc"));
        bd.setGDTC(rs.getFloat("GDTC"));
        return bd;
    }
}

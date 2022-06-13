package Controller;

import Helper.DatabaseHelper;
import Model.SinhVien;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.rowset.serial.SerialBlob;

public class SinhVienDAO {

    public ArrayList<SinhVien> getListStudent() throws Exception {
        ArrayList<SinhVien> list = new ArrayList<>();
        String sql = "SELECT * FROM SinhVien";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    SinhVien sv = createSinhVien(rs);
                    list.add(sv);
                }
                return list;
            }
        }
    }

    private SinhVien createSinhVien(final ResultSet rs) throws SQLException {
        SinhVien sv = new SinhVien();
        sv.setMaSinhVien(rs.getString("MaSinhVien"));
        sv.setHoTen(rs.getString("HoTen"));
        sv.setEmail(rs.getString("Email"));
        sv.setSoDT(rs.getString("SoDT"));
        sv.setGioiTinh(rs.getInt("GioiTinh"));
        sv.setDiaChi(rs.getString("DiaChi"));
        Blob blob = rs.getBlob("Hinh");
        if (blob != null) {
            sv.setHinh(blob.getBytes(0, (int) blob.length()));
        }
        return sv;
    }

    public boolean insert(SinhVien sv) throws Exception {
        String sql = "INSERT INTO SinhVien(MaSinhVien, HoTen, Email, SoDT, GioiTinh, DiaChi, Hinh)"
                + " VALUES (?,?,?,?,?,?,?)";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, sv.getMaSinhVien());
            pstmt.setString(2, sv.getHoTen());
            pstmt.setString(3, sv.getEmail());
            pstmt.setString(4, sv.getSoDT());
            pstmt.setInt(5, sv.getGioiTinh());
            pstmt.setString(6, sv.getDiaChi());
            if (sv.getHinh() != null) {
                Blob Hinh = new SerialBlob(sv.getHinh());
                pstmt.setBlob(7, Hinh);
            } else {
                pstmt.setBytes(7, null);
            }
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean update(SinhVien sv) throws Exception {
        String sql = "UPDATE SinhVien"
                + " SET HoTen = ?, Email = ?, SoDT = ?, GioiTinh = ?, DiaChi = ?, Hinh = ?"
                + " WHERE MaSinhVien = ?";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);) 
        {
            pstmt.setString(7, sv.getMaSinhVien());
            pstmt.setString(1, sv.getHoTen());
            pstmt.setString(2, sv.getEmail());
            pstmt.setString(3, sv.getSoDT());
            pstmt.setInt(4, sv.getGioiTinh());
            pstmt.setString(5, sv.getDiaChi());
            if(sv.getHinh() != null){
                Blob Hinh = new SerialBlob(sv.getHinh());
                pstmt.setBlob(6, Hinh);
            }
            else{
                Blob Hinh = null;
                pstmt.setBlob(6, Hinh);
            }
            return pstmt.executeUpdate() > 0;
        }
    }

    public  boolean delete(SinhVien sv) throws Exception{
        String sql = "DELETE FROM SinhVien"
                + " WHERE MaSinhVien = ?";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);) 
        {
            pstmt.setString(1, sv.getMaSinhVien());
            return pstmt.executeUpdate() > 0;
        }
    }
    
    public SinhVien findByMaSinhVien(String MaSinhVien) throws Exception {
        String sql = "SELECT * FROM SinhVien WHERE MaSinhVien = ?";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, MaSinhVien);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    SinhVien sv = createSinhVien(rs);
                    return sv;
                }
            }
            return null;
        }
    }
}

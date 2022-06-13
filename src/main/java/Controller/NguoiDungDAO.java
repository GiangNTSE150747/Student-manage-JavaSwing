 
package Controller;
 
import Helper.DatabaseHelper;
import Model.NguoiDung;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JPasswordField;

public class NguoiDungDAO {
    public NguoiDung CheckLogin(String TenDangNhap, String MatKhau) throws Exception {
        String sql = "SELECT TenDangNhap, MatKhau FROM NguoiDung "
                + " WHERE TenDangNhap = ? AND MatKhau = ?";
        try(
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);             
           ){
                pstmt.setString(1, TenDangNhap);
                pstmt.setString(2, MatKhau);
                
                ResultSet rs = pstmt.executeQuery();
                if(rs.next()){
                    NguoiDung nd = new NguoiDung();
                    nd.setTenDangNhap(rs.getString("TenDangNhap"));
                    nd.setMatkhau(rs.getString("MatKhau"));
                    return nd;
                }
        }
        return null;
    }

    public NguoiDung CheckLogin(String text, JPasswordField txtMatKhau) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

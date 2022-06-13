 
package Model;
 
public class NguoiDung {
    private String TenDangNhap, Matkhau, Vaitro;

    public NguoiDung() {
    }

    public NguoiDung(String TenDangNhap, String Matkhau, String Vaitro) {
        this.TenDangNhap = TenDangNhap;
        this.Matkhau = Matkhau;
        this.Vaitro = Vaitro;
    }

    public String getTenDangNhap() {
        return TenDangNhap;
    }

    public void setTenDangNhap(String TenDangNhap) {
        this.TenDangNhap = TenDangNhap;
    }

    public String getMatkhau() {
        return Matkhau;
    }

    public void setMatkhau(String Matkhau) {
        this.Matkhau = Matkhau;
    }

    public String getVaitro() {
        return Vaitro;
    }

    public void setVaitro(String Vaitro) {
        this.Vaitro = Vaitro;
    }
    
}

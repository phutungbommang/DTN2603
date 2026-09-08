package entity;

public class Sach extends TaiLieu {
    private String tenTacGia;
    private int soTrang;

    public Sach(String maTaiLieu, String tenNhaXuatBan, int soBanPhatHanh, String tenTacGia, int soTrang) {
        super(maTaiLieu, tenNhaXuatBan, soBanPhatHanh);
        this.tenTacGia = tenTacGia;
        this.soTrang = soTrang;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    @Override
    public void hienThiThongTin() {
        System.out.println("====== SACH ======");
        System.out.println("Ma tai lieu    : " + getMaTaiLieu());
        System.out.println("Nha xuat ban   : " + getTenNhaXuatBan());
        System.out.println("So ban phat hanh: " + getSoBanPhatHanh());
        System.out.println("Tac gia        : " + tenTacGia);
        System.out.println("So trang       : " + soTrang);
    }
}

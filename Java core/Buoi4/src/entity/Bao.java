package entity;

import java.time.DayOfWeek;

public class Bao extends TaiLieu {
    private int ngayPhatHanh;

    public Bao(String maTaiLieu, String tenNhaXuatBan, int soBanPhatHanh, int ngayPhatHanh) {
        super(maTaiLieu, tenNhaXuatBan, soBanPhatHanh);
        this.ngayPhatHanh = ngayPhatHanh;
    }

    public int getNgayPhatHanh() {
        return ngayPhatHanh;
    }

    public void setNgayPhatHanh(int ngayPhatHanh) {
        this.ngayPhatHanh = ngayPhatHanh;
    }

    @Override
    public void hienThiThongTin() {
        System.out.println("====== BAO ======");
        System.out.println("Ma tai lieu    : " + getMaTaiLieu());
        System.out.println("Nha xuat ban   : " + getTenNhaXuatBan());
        System.out.println("So ban phat hanh: " + getSoBanPhatHanh());
        System.out.println("Ngay phat hanh : " + ngayPhatHanh);
    }
}

package entity;

import java.time.Month;
import java.util.Date;

public class TapChi extends TaiLieu {
    private int soPhatHanh;
    private Month thangPhatHanh;

    public TapChi(String maTaiLieu, String tenNhaXuatBan, int soBanPhatHanh, int soPhatHanh, Month thangPhatHanh) {
        super(maTaiLieu, tenNhaXuatBan, soBanPhatHanh);
        this.soPhatHanh = soPhatHanh;
        this.thangPhatHanh = thangPhatHanh;
    }

    public int getSoPhatHanh() {
        return soPhatHanh;
    }

    public void setSoPhatHanh(int soPhatHanh) {
        this.soPhatHanh = soPhatHanh;
    }

    public Month getThangPhatHanh() {
        return thangPhatHanh;
    }

    public void setThangPhatHanh(Month thangPhatHanh) {
        this.thangPhatHanh = thangPhatHanh;
    }
    @Override
    public void hienThiThongTin() {
        System.out.println("====== TAP CHI ======");
        System.out.println("Ma tai lieu    : " + getMaTaiLieu());
        System.out.println("Nha xuat ban   : " + getTenNhaXuatBan());
        System.out.println("So ban phat hanh: " + getSoBanPhatHanh());
        System.out.println("So phat hanh   : " + soPhatHanh);
        System.out.println("Thang phat hanh: " + thangPhatHanh);
    }
}
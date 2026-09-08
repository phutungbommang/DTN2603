package backend;

import entity.Bao;
import entity.Sach;
import entity.TaiLieu;
import entity.TapChi;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QLTV implements IQLTV {
    private List<TaiLieu> taiLieus = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    public QLTV(){
        TaiLieu bao = new Bao("ST1","Pham Quoc Anh",1,12);
        TaiLieu tapChi = new TapChi("ST2","Pham Quoc Bao",2,2, Month.JULY);
        TaiLieu sach = new Sach("ST3","Pham Quoc Cuong", 3,"Truong Thuy Kieu",167);
        taiLieus.add(bao);
        taiLieus.add(tapChi);
        taiLieus.add(sach);
    }

    @Override
    public void themMoiTaiLieu() {
        System.out.println("Chon loai tai lieu:");
        System.out.println("1. Sach");
        System.out.println("2. Tap chi");
        System.out.println("3. Bao");
        System.out.print("Chon: ");
        int chon = sc.nextInt();
        sc.nextLine();
        System.out.println("Nhap ma tai lieu: ");
        String maTaiLieu = sc.nextLine();
        for (TaiLieu lieus : taiLieus) {
            if(lieus.getMaTaiLieu().equals(maTaiLieu)) {
                System.out.println("Ma tai lieu da co!");
                return;
            }
        }
        System.out.println("Nhap ten nha xuat ban: ");
        String tenNhaXuatBan = sc.nextLine();
        System.out.println("Nhap so ban phat hanh: ");
        int soBanPhatHanh = sc.nextInt();
        sc.nextLine();
            switch (chon){
                case 1:
                    System.out.print("Ten tac gia: "); String tenTacGia = sc.nextLine();
                    System.out.print("So trang: "); int soTrang   = sc.nextInt();
                    sc.nextLine();
                    taiLieus.add(new Sach(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, tenTacGia, soTrang));
                    System.out.println("Them sach thanh cong!");
                    break;
                case 2:
                    System.out.print("So phat hanh: ");
                    int soPhatHanh  = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Thang phat hanh (1-12): ");
                    int thangPhatHanh = sc.nextInt();
                    sc.nextLine();
                    taiLieus.add(new TapChi(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, soPhatHanh, Month.of(thangPhatHanh)));
                    System.out.println("Them tap chi thanh cong!");
                    break;
                case 3:
                    System.out.print("Ngay phat hanh (1-31): ");
                    int ngayPhatHanh = sc.nextInt(); sc.nextLine();
                    taiLieus.add(new Bao(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, ngayPhatHanh));
                    System.out.println("Them bao thanh cong!");
                    break;
                default:
                    System.out.println("Chon khong hop le!");
            }
        }
    @Override
    public void xoaTaiLieuTheoMaTaiLieu() {
        System.out.println("Nhap vao ma tai lieu can xoa");
        String maTaiLieu = sc.nextLine();
        for (TaiLieu lieus : taiLieus) {
            if(lieus.getMaTaiLieu().equals(maTaiLieu)){
                taiLieus.remove(lieus);
                System.out.println("xoa thanh cong");
                return;
            }
        }
        System.out.println("Khong tim thay tai lieu");
    }
    @Override
    public void hienThiThongTinTaiLieu() {
        for (TaiLieu lieus : taiLieus) {
            lieus.hienThiThongTin();
        }
    }
    @Override
    public void timKiemTaiLieuTheoLoai() {
        System.out.println("Chon loai tai lieu:");
        System.out.println("1. Sach");
        System.out.println("2. Tap chi");
        System.out.println("3. Bao");
        System.out.print("Chon: ");
        int loai = sc.nextInt();
        sc.nextLine();
        for (TaiLieu lieus : taiLieus) {
            if(loai == 1 && lieus instanceof Sach || loai == 2 && lieus instanceof TapChi || loai == 3 && lieus instanceof Bao){
                lieus.hienThiThongTin();
            }
        }
    }



}

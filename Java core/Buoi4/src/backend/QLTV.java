package backend;

import entity.Bao;
import entity.Sach;
import entity.TaiLieu;
import entity.TapChi;

import java.sql.*;
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
        List<TaiLieu> list = new ArrayList<>();
        // b1: lấy dữ liệu từ database
        // 1.1 kết nối đến database   hostname+ port: localhost:3306    username: root     password: root   DB:qltv
        String url = "jdbc:mysql://localhost:3306/qltl";
        String username = "root";
        String password = "root";
        try{
            // kết nối
            Connection connection = DriverManager.getConnection(url, username, password);
            if (connection != null) {
                System.out.println("Kết nối DB thành công");
            } else {
                System.out.println("Kết nối DB không thành công");
            }
            // tạo viết 1 câu sql để xem toàn bộ bảng tai_lieu
            String sql = "SELECT * FROM tai_lieu;";
            // Statement đây là đối tượng hỗ trợ thực thi câu lệnh sql tĩnh và trả về kêt quá
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);// resultSet chứa dữ liệu khi chạy cau sql
            // lấy dữ liệu  từ ResultSet
            while (resultSet.next()) {
                String maTaiLieu = resultSet.getString("ma_tai_lieu");
                String tenNXB =  resultSet.getString("ten_nxb");
                int soBanPhatHanh =  resultSet.getInt("so_ban_phat_hanh");
                TaiLieu taiLieu = new TaiLieu(maTaiLieu, tenNXB, soBanPhatHanh);
                // them vao ds TaiLieu để hiển thị ra
                list.add(taiLieu);
            }
        }catch(Exception e){
        }

        // b2: hiển thị
        System.out.println("+-------------------------+-------------------------+-------------------------+");
        System.out.printf("|%25s|%25s|%25s|\n", "Mã tài liệu", "Tên NXB", "Số bản phát hành");
        System.out.println("+-------------------------+-------------------------+-------------------------+");
        if (list.size() > 0) {
            for (TaiLieu taiLieu : list) {
                System.out.printf("|%25s|%25s|%25s|\n", taiLieu.getMaTaiLieu(), taiLieu.getTenNhaXuatBan(), taiLieu.getSoBanPhatHanh());
            }
        } else {
            System.out.printf("|%77s|\n", "Không có thông tin");
        }
        System.out.println("+-------------------------+-------------------------+-------------------------+");
    }
    @Override
    public void timKiemTaiLieuTheoLoai() {
        // ds các tài liệu sẽ dc hiển thị
        List<TaiLieu> rs = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/qltv";
        String username = "root";
        String password = "root";
        String sql = "SELECT * FROM tai_lieu where loai_tai_lieu = ?";// ? là biến
        System.out.println("Chọn loại tài liệu muốn hiển thị: 1. Sách   2. Báo  Khác. Tạp chí");
        String choice = scanner.nextLine(); //,,...
        String value = "";
        switch (choice) {
            case "1":
                value = "SACH";
                break;
            case "2":
                value = "BAO";
                break;
            default:
                value = "TAP_CHI";
        }
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            //PreparedStatement đây là đối tượng hỗ trợ thực thi câu lệnh sql động và trả về kêt quá
            PreparedStatement statement = connection.prepareStatement(sql);
            // gán gtri cho dấu ? = value
            statement.setString(1, value);
            ResultSet resultSet = statement.executeQuery();// resultSet chứa dữ liệu khi chạy cau sql
            while (resultSet.next()) {
                String maTaiLieu = resultSet.getString("ma_tai_lieu");
                String tenNXB =  resultSet.getString("ten_nxb");
                int soBanPhatHanh =  resultSet.getInt("so_ban_phat_hanh");
                TaiLieu taiLieu = new TaiLieu(maTaiLieu, tenNXB, soBanPhatHanh);
                // them vao ds rs để hiển thị ra
                rs.add(taiLieu);
            }
        }catch(Exception e){
        }
        System.out.println("+-------------------------+-------------------------+-------------------------+");
        System.out.printf("|%25s|%25s|%25s|\n", "Mã tài liệu", "Tên NXB", "Số bản phát hành");
        System.out.println("+-------------------------+-------------------------+-------------------------+");
        if (rs.size() > 0) {
            for (TaiLieu taiLieu : rs) {
                System.out.printf("|%25s|%25s|%25s|\n", taiLieu.getMaTaiLieu(), taiLieu.getTenNhaXuatBan(), taiLieu.getSoBanPhatHanh());
            }
        } else {
            System.out.printf("|%77s|\n", "Không có thông tin");
        }
        System.out.println("+-------------------------+-------------------------+-------------------------+");
    }
    public static void main(String[] args) throws Exception{
        QLTV qltv = new QLTV();
        qltv.hienThiThongTinTaiLieu();
        qltv.timKiemTaiLieuTheoLoai();
    }
}

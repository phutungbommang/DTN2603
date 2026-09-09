package backend;

import entity.Position;
import entity.PositionName;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QLPosition implements IQLPosition {
    private List<Position> positions;
    private Scanner scanner;
    public QLPosition() {
        scanner = new Scanner(System.in);
        // khởi tạo các gtri cho ds tai lieu
        this.positions = new ArrayList<>();
    }
    @Override
    public void hienThiPosition() {
        List<Position> list = new ArrayList<>();
        // b1: lấy dữ liệu từ database
        // 1.1 kết nối đến database   hostname+ port: localhost:3306    username: root     password: root   DB:qltv
        String url = "jdbc:mysql://localhost:3306/buoi5";
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
            String sql = "SELECT * FROM `position`;";
            // Statement đây là đối tượng hỗ trợ thực thi câu lệnh sql tĩnh và trả về kêt quá
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);// resultSet chứa dữ liệu khi chạy cau sql
            // lấy dữ liệu  từ ResultSet
            while (resultSet.next()) {
                int positionId = resultSet.getInt("position_id");
                PositionName positionName = PositionName.valueOf(resultSet.getString("position_name"));
                Position position = new Position(positionId,positionName);
                // them vao ds TaiLieu để hiển thị ra
                list.add(position);
            }
        }catch(Exception e){
        }
        // b2: hiển thị
        System.out.println("+-------------------------+-------------------------+");
        System.out.printf("|%25s|%25s|\n", "Id chuc vu", "Tên chuc vu");
        System.out.println("+-------------------------+-------------------------+");
        if (list.size() > 0) {
            for (Position position : list) {
                System.out.printf("|%25s|%25s|\n", position.getPositionId(), position.getPositionName());
            }
        } else {
            System.out.printf("|%77s|\n", "Không có thông tin");
        }
        System.out.println("+-------------------------+-------------------------+");
    }
    public static void main(String[] args) throws SQLException {
        QLPosition position = new QLPosition();
        position.hienThiPosition();
    }
}

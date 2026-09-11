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

    @Override
    public void themPosition() {
        System.out.println("1. DEV");
        System.out.println("2. TEST");
        System.out.println("3. SCRUM_MASTER");
        System.out.println("4. PM");
        System.out.print("Chọn chức vụ: ");
        String choice = scanner.nextLine();
        PositionName positionName = null;
        switch (choice) {
            case "1":
                positionName = PositionName.DEV;
                break;
            case "2":
                positionName = PositionName.TEST;
                break;
            case "3":
                positionName = PositionName.SCRUM_MASTER;
                break;
            case "4":
                positionName = PositionName.PM;
                break;
        }
        String url = "jdbc:mysql://localhost:3306/buoi5";
        String username = "root";
        String password = "root";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO `position`(position_name) VALUES(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, positionName.name());
            int c = preparedStatement.executeUpdate();
            if (c > 0) {
                System.out.println("Thêm chức vụ thành công!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void xoaPositionTheoId() {
        System.out.print("Nhập ID Position cần xóa: ");
        Integer positionId = scanner.nextInt();
        scanner.nextLine();
        String url = "jdbc:mysql://localhost:3306/buoi5";
        String username = "root";
        String password = "root";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "DELETE FROM `position` WHERE position_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, positionId);
            int c = preparedStatement.executeUpdate();
            if (c > 0) {
                System.out.println("Xóa Position thành công!");
            } else {
                System.out.println("Không tìm thấy Position!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void suaPositionNameTheoId() {
        System.out.print("Nhập ID Position cần sửa: ");
        Integer positionId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("1. DEV");
        System.out.println("2. TEST");
        System.out.println("3. SCRUM_MASTER");
        System.out.println("4. PM");
        System.out.print("Mời chọn Position mới: ");
        String choice = scanner.nextLine();
        PositionName positionName = null;
        switch (choice) {
            case "1":
                positionName = PositionName.DEV;
                break;
            case "2":
                positionName = PositionName.TEST;
                break;
            case "3":
                positionName = PositionName.SCRUM_MASTER;
                break;
            case "4":
                positionName = PositionName.PM;
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ!");
                return;
        }
        String url = "jdbc:mysql://localhost:3306/buoi5";
        String username = "root";
        String password = "root";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "UPDATE `position` SET position_name = ? WHERE position_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, positionName.name());
            preparedStatement.setInt(2, positionId);
            int c = preparedStatement.executeUpdate();
            if (c > 0) {
                System.out.println("Cập nhật Position thành công!");
            } else {
                System.out.println("Không tìm thấy Position!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        QLPosition position = new QLPosition();
        position.hienThiPosition();
        position.themPosition();
        position.xoaPositionTheoId();
        position.suaPositionNameTheoId();
    }
}

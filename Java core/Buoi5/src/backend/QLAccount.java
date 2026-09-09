package backend;

import entity.Account;
import entity.Department;
import entity.Position;
import entity.PositionName;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QLAccount implements IQLAccount{
        private List<Account> accounts;
        private Scanner scanner;
        public QLAccount() {
            scanner = new Scanner(System.in);
            // khởi tạo các gtri cho ds tai lieu
            this.accounts = new ArrayList<>();
        }
        @Override
        public void hienThiAccount() {
            List<Account> list = new ArrayList<>();
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
                String sql = "SELECT a.account_id, a.email, a.username, a.full_name, a.create_date, " +
                        "d.department_id, d.department_name, " +
                        "p.position_id, p.position_name " +
                        "FROM account a " +
                        "INNER JOIN department d ON a.department_id = d.department_id " +
                        "INNER JOIN `position` p ON a.position_id = p.position_id";
                // Statement đây là đối tượng hỗ trợ thực thi câu lệnh sql tĩnh và trả về kêt quá
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);// resultSet chứa dữ liệu khi chạy cau sql
                // lấy dữ liệu  từ ResultSet
                while (resultSet.next()) {
                    Department dept = new Department(
                            resultSet.getInt("department_id"),
                            resultSet.getString("department_name")
                    );
                    Position pos = new Position(
                            resultSet.getInt("position_id"),
                            PositionName.valueOf(resultSet.getString("position_name"))
                    );
                    Account account = new Account(  resultSet.getInt("account_id"),
                                                resultSet.getString("email"),
                                                resultSet.getString("username"),
                                                resultSet.getString("full_name"),
                                                dept,
                                                pos,
                                                resultSet.getString("create_date"));

                    list.add(account);
                }
            }catch(Exception e){
            }
            // b2: hiển thị
            System.out.println("+----+------------------------------+------------------+--------------------+----------------------+------------------+");
            System.out.printf("|%-4s|%-30s|%-18s|%-20s|%-22s|%-18s|%n",
                    " ID", " Email", " Username", " Full Name", " Department", " Position");
            System.out.println("+----+------------------------------+------------------+--------------------+----------------------+------------------+");
            if (list.size() > 0) {
                for (Account acc : list) {
                    System.out.printf("|%-4d|%-30s|%-18s|%-20s|%-22s|%-18s|%n",
                            acc.getAccountId(),
                            acc.getEmail(),
                            acc.getUsername(),
                            acc.getFullName(),
                            acc.getDepartment() != null ? acc.getDepartment().getDepartmentName() : "N/A",
                            acc.getPosition()   != null ? acc.getPosition().getPositionName()     : "N/A");
                }
            } else {
                System.out.printf("|%77s|\n", "Không có thông tin");
            }
            System.out.println("+----+------------------------------+------------------+--------------------+----------------------+------------------+");
        }
        public static void main(String[] args) throws SQLException {
            QLAccount qlAccount = new QLAccount();
            qlAccount.hienThiAccount();
        }
}

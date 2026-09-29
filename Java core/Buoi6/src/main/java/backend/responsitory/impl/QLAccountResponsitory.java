package backend.responsitory.impl;

import backend.responsitory.IQLAccountResponsitory;
import backend.service.IQLAccountService;
import entity.Account;
import entity.Department;
import entity.Position;
import entity.PositionName;
import utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QLAccountResponsitory implements IQLAccountResponsitory {
    @Override
    public List<Account> getAccounts() {
        List<Account> list = new ArrayList<>();

        try {
            Connection connection = JDBCUtils.getConnection();

            String sql = "SELECT a.account_id, a.email, a.username, a.full_name, a.create_date, " +
                    "d.department_id, d.department_name, " +
                    "p.position_id, p.position_name " +
                    "FROM account a " +
                    "LEFT JOIN department d ON a.department_id = d.department_id " +
                    "LEFT JOIN `position` p ON a.position_id = p.position_id";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

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
            JDBCUtils.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean xoaAccountTheoId(Integer accountId) {
        try {
            // kết nối
            Connection connection = JDBCUtils.getConnection();
            String sql = "DELETE FROM `account` WHERE account_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId);

            int c = preparedStatement.executeUpdate();
            JDBCUtils.closeConnection(connection);
//            if (c > 0) {
//                return true;
//            } else {
//                return false;
//            }
            return c > 0;
        } catch (SQLException e) {
            e.printStackTrace();// show ra lỗi
        }
        return false;
    }

    @Override
    public boolean suaUserTheoId(String usernameMoi, Integer accountId) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql =  "UPDATE `account` SET username = ? WHERE account_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,usernameMoi );
            statement.setInt(2,accountId );
            int c = statement.executeUpdate();
            JDBCUtils.closeConnection(connection);
            return c > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean themAccount(String email, String username, String fullName, Integer departmentId, Integer positionId, String createDate) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "INSERT INTO `account`(email, username, full_name, department_id, position_id, create_date) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, fullName);
            preparedStatement.setInt(4, departmentId);
            preparedStatement.setInt(5, positionId);
            preparedStatement.setString(6, createDate);
            int c = preparedStatement.executeUpdate();
            JDBCUtils.closeConnection(connection);
            if (c > 0) {
                System.out.println("Thêm Account thành công!");
            } else {
                System.out.println("Thêm Account thất bại!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) return false;
        return email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    }

    @Override
    public boolean isUsernameExist(String username) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "SELECT COUNT(*) FROM `account` WHERE username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                JDBCUtils.closeConnection(connection);
                return count > 0; // true -> đã tồn tại
            }
            JDBCUtils.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isEmailExist(String email) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "SELECT COUNT(*) FROM `account` WHERE email = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                JDBCUtils.closeConnection(connection);
                return count > 0; // true -> đã tồn tại
            }
            JDBCUtils.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isAccountExist(int id) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "SELECT COUNT(*) FROM `account` WHERE account_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                JDBCUtils.closeConnection(connection);
                return count > 0; // true -> tồn tại
            }
            JDBCUtils.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void themListAccount(List<Account> accounts) {
            String sql = "INSERT INTO account " +
                    "(email, username, full_name, department_id, position_id, create_date) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            Connection con = null;

            try {

                con = JDBCUtils.getConnection();

                PreparedStatement st = con.prepareStatement(sql);

                for (Account account : accounts) {

                    st.setString(1, account.getEmail());
                    st.setString(2, account.getUsername());
                    st.setString(3, account.getFullName());
                    st.setInt(4, account.getDepartment().getDepartmentId());
                    st.setInt(5, account.getPosition().getPositionId());
                    st.setString(6, account.getCreateDate());

                    st.addBatch();
                }

                st.executeBatch();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                JDBCUtils.close(con);
            }
        }
    }


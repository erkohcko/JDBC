package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection conn;

    public UserDaoJDBCImpl() {
        conn = Util.getConnection();
    }

    public void createUsersTable() {
        final String createTableQuery = "CREATE TABLE IF NOT EXISTS users " +
                "(id MEDIUMINT NOT NULL AUTO_INCREMENT, " +
                " name VARCHAR(50), " +
                "lastname VARCHAR(50), " +
                "age TINYINT, " +
                "PRIMARY KEY (id))";
        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate(createTableQuery);
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        final String dropTableQuery = "DROP TABLE IF EXISTS users";
        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate(dropTableQuery);
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        final String insertUserQuery = "INSERT INTO users(name, lastname, age) VALUES(?,?,?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(insertUserQuery)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        final String deleteUserQuery = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(deleteUserQuery)) {
            statement.setLong(1, id);
            final int deletedRows = statement.executeUpdate();
            if (deletedRows > 0) {
                System.out.println("User удален");
            } else {
                System.out.println("User с id=" + id + " не найден");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        final String selectAllUsersQuery = "SELECT id, name, lastname, age FROM users";
        List<User> allUsers = new ArrayList<>();
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(selectAllUsersQuery)) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    public void cleanUsersTable() {
        final String deleteAllUsersQuery = "DELETE FROM users";
        try (Statement statement = conn.createStatement()) {
            final int deletedRows = statement.executeUpdate(deleteAllUsersQuery);
            if (deletedRows > 0) {
                System.out.println("ДАННЫЕ УДАЛЕНЫ");
            } else {
                System.out.println("В таблице нет данных для удаления");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            conn.close();
            System.out.println("Соединение с базой данных закрыто");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
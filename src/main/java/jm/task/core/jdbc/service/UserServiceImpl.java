package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.beans.Statement;
import java.sql.Connection;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJDBCImpl usersDao = new UserDaoJDBCImpl();

    public UserServiceImpl(Connection conn) {

    }

    public UserServiceImpl() {

    }

    public void createUsersTable() {
        usersDao.createUsersTable();

    }

    public void dropUsersTable() {
        usersDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        usersDao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        usersDao.removeUserById(id);
    }

    public List<User> getAllUsers() {

        return usersDao.getAllUsers();
    }

    public void cleanUsersTable() {
        usersDao.cleanUsersTable();
    }

    @Override
    public void saveUser(User user1) {

    }
}
package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    private static final UserService USER_SERVICE = new UserServiceImpl();
    private static final User USER_1 = new User("Vasya", "Pupkin", (byte) 10);
    private static final User USER_2 = new User("Artemii", "Chmyr", (byte) 33);
    private static final User USER_3 = new User("D'erd", "Pirda", (byte) 78);
    private static final User USER_4 = new User("Sveta", "Pivko", (byte) 21);

    public static void main(String[] args) {
        // Создание таблицы пользователей
        USER_SERVICE.createUsersTable();

        // Добавление пользователей в базу данных
        USER_SERVICE.saveUser(USER_1);
        USER_SERVICE.saveUser(USER_2);
        USER_SERVICE.saveUser(USER_3);
        USER_SERVICE.saveUser(USER_4);

        // Получение всех пользователей из базы данных
        USER_SERVICE.getAllUsers().forEach(System.out::println);

        // Очистка таблицы пользователей
        USER_SERVICE.cleanUsersTable();

        // Удаление таблицы пользователей
        USER_SERVICE.dropUsersTable();
    }
}
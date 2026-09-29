package oop;

import oop.entities.User;
import oop.repositories.UserRepository;
import oop.repositories.UsersRepositoryJdbcImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "D8a4U_8mU";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Oris_HW";

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        UserRepository userRepository = new UsersRepositoryJdbcImpl(connection);

        Scanner scanner = new Scanner(System.in);
        List<User> usersList = new ArrayList<>();

        // 1
        System.out.println("Введите количество пользователей для добавления:");
        int count = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Введите данные пользователей (Имя, Фамилия, Возраст - каждый с новой строки):");

        for (int i = 0; i < count; i++) {
            String firstName = scanner.nextLine();
            String lastName = scanner.nextLine();
            Integer age = scanner.nextInt();

            scanner.nextLine();

            usersList.add(new User(null, firstName, lastName, age));
        }

        userRepository.saveAll(usersList);
        System.out.println("Пользователи успешно добавлены!");

        // 2
        System.out.println("Введите возраст для фильтрации:");
        Integer ageLimit = scanner.nextInt();

        List<User> resultUsers = userRepository.findAllByAgeGreaterThan(ageLimit);

        System.out.println("Найденные пользователи:");
        for (User user : resultUsers) {
            System.out.println(user.getId() + " " + user.getName() + " " + user.getSurname() + " " + user.getAge());
        }
    }
}
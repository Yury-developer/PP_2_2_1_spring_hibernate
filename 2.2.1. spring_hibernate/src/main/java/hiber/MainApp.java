package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.Arrays;


public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car[] cars = {
                new Car("Mers", 111),
                new Car("Lexus", 222),
                new Car("BMW", 333),
                new Car("Lada", 444),
                new Car("Oka", 555)};

        User[] users = {
                new User("User1", "Lastname1", "user1@mail.ru", cars[0]),
                new User("User2", "Lastname2", "user2@mail.ru", cars[1]),
                new User("User3", "Lastname3", "user3@mail.ru", cars[2]),
                new User("User4", "Lastname4", "user4@mail.ru", cars[3]),
                new User("User5", "Lastname5", "user5@mail.ru", cars[4]),
                new User("User3-1", "Lastname3", "user3-1@mail.ru", cars[2]),
                new User("User3-2", "Lastname3", "user3-2@mail.ru", cars[2])
        };

        Arrays.stream(users).forEach(userService::add);

        System.out.println("\nПеречень всех пользователей с их машинами:");
        for (User user : userService.listUsers()) {
            System.out.println(user + "\n");
        }

        System.out.println("\nПеречень всех существующих машин (одна машина принадлежит одному пользователю):");
        userService.listUsers().stream().forEach(u -> System.out.println(u.getCar()));

        // Найдем пользователя по марке и серии машины:
        final String targetModel = "BMW";
        final int targetSeries = 333;
        System.out.println("\nНайдем всех владельцев автомобилей "
                + "марки   '" + targetModel + "'   и серии   '" + targetSeries
                + "'. \nВот список владельцев:");
        userService.getUsersByCarModelAndSeries(targetModel, targetSeries).forEach(System.out::println);
        context.close();
    }
}

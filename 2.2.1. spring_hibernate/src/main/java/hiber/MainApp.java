package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;


public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean((CarService.class));

        Car car1 = new Car("Mers", 111);
        Car car2 = new Car("Lexus", 222);
        Car car3 = new Car("BMW", 333);
        Car car4 = new Car("Lada", 444);
        Car car5 = new Car("Oka", 555);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru", car1);
        User user2 = new User("User2", "Lastname2", "user2@mail.ru", car2);
        User user3 = new User("User3", "Lastname3", "user3@mail.ru", car3);
        User user4 = new User("User4", "Lastname4", "user4@mail.ru", car4);
        User user5 = new User("User5", "Lastname5", "user5@mail.ru");
        user5.setCar(car5);

        carService.add(car1);
        userService.add(user1);

        carService.add(car2);
        userService.add(user2);

        carService.add(car3);
        userService.add(user3);

        carService.add(car4);
        userService.add(user4);

        carService.add(car5);
        userService.add(user5);

       System.out.println("\nПеречень всех пользователей с их машинами:");
        for (User user : userService.listUsers()) {
           System.out.println(user + "\n");
        }

       System.out.println("\nПеречень всех существующих машин (отдельно от пользователей):");
       carService.listCars().stream().forEach(System.out::println);

       // Найдем пользователя по марке и серии машины:
       final String targetModel = "BMW";
       final int targetSeries = 333;
       System.out.println("\nНиже перечислены владельцы автомобилей марки '" + targetModel
               + "' и серии '" + targetSeries + "':");
       userService.getUsersByCarModelAndSeries(targetModel, targetSeries).forEach(System.out::println);

       context.close();
    }
}

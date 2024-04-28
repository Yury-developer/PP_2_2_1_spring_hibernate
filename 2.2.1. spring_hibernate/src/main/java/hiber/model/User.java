package hiber.model;

import javax.persistence.*;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "email", length = 20)
    private String email;

    @JoinColumn(name = "fk_car_id", referencedColumnName = "id_car") //  чтобы указать столбец в базе данных, который содержит внешний ключ к таблице Car.
    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Car car;


    public User() {
    }

    public User(String firstName, String lastName, String email, Car car) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.car = car;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Id = " + id +
                "\n\tFirst Name ='" + firstName + '\'' +
                "\n\tLast Name = '" + lastName + '\'' +
                "\n\tEmail = '" + email + '\'' +
                "\n\tCar = " + car;
    }
}

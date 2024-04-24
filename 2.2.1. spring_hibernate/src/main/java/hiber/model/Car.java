package hiber.model;

import javax.persistence.*;


@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_car", updatable = false, nullable = false) // имя калонки:"id";  поле не может быть обновлено при выполнении операции обновления (UPDATE) в базе данных; не может содержать значение NULL
    private Long id;

    @Column(name = "model", length = 30, nullable = false)
    private String model;

    @Column(name = "series", length = 15)
    private int series;


    public Car() {
        // NOP
    }

    public Car(String model, int series) { //
        this.model = model;
        this.series = series;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return "{id=" + id +
                ", model='" + model + '\'' +
                ", series=" + series +
                '}';
    }
}
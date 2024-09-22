import utility.SearchAndSortUtility;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Car> cars = Arrays.asList(
                new Car(150, "Toyota", 2015),
                new Car(211, "Honda", 2018),
                new Car(203, "Honda", 2018),
                new Car(500, "Honda", 2018),
                new Car(213, "Honda", 2018),
                new Car(120, "Honda", 2018),
                new Car(50, "Honda", 2018),
                new Car(61, "Ford", 2010)
        );
        Car seachCar = new Car(200, "honda", 2018);
        SearchAndSortUtility.modShellSort(cars, Car::getPower);
        System.out.println(cars);
        SearchAndSortUtility.shellSort(cars, Car::getPower);
        System.out.println(cars);
    }
}

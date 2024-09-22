package main.java.org.example;

import main.java.org.example.classes.Car;
import main.java.org.example.input.DataInput;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            System.out.println("exit - leave the program");
            input = scanner.nextLine();
            if (input.equals("car")) {
                List<Car> cars = new DataInput<>(new Car.Builder()).fromSourceToList(1, InputType.RANDOM);
                System.out.println(cars);
            }

            if ("exit".equalsIgnoreCase(input)) {
                System.out.println("Exiting the program...");
                break;
            }

        }
    }
}
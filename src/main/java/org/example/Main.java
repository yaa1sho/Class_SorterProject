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
            while (input.equals("car")) {
                input = scanner.nextLine();
                switch (input) {
                    case "random":
                        List<Car> cars1 = new DataInput<>(new Car.Builder()).fromSourceToList(3, InputType.RANDOM);
                        System.out.println(cars1);
                        break;
                    case "console":
                        List<Car> cars2 = new DataInput<>(new Car.Builder()).fromSourceToList(3, InputType.CONSOLE);
                        System.out.println(cars2);
                        break;
                    case "file":
                        List<Car> cars3 = new DataInput<>(new Car.Builder()).fromSourceToList(3, InputType.CSV_FILE);
                        System.out.println(cars3);
                        break;
                }
                input = scanner.nextLine();
            }

            if ("exit".equalsIgnoreCase(input)) {
                System.out.println("Exiting the program...");
                break;
            }

        }
    }
}
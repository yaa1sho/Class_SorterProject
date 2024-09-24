package main.java.org.example.main_console_ui;

import main.java.org.example.classes.*;
import utility.SearchAndSortUtility;
import utility.Sortable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class ConsoleUI {

    private static final Scanner scanner = new Scanner(System.in);

    private static String getStringMainMenu() {

        return "\nГлавное меню:\n1. Ввод данных\n2. Сортировка данных\n3. Поиск данных\n4. Вывод данных\n5. Сохранение данных в файл\n6. Выход\n";
    }

    public static void printMainMenu() {

        System.out.println(getStringMainMenu());
        System.out.print("Выберите действие: ");
    }

    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Пожалуйста, введите целое число.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void inputData(List<Car> cars, List<Book> books, List<RootVegetable> vegetables) {
        System.out.println("\nВыберите способ ввода данных:");
        System.out.println("1. Ввод с консоли");
        System.out.println("2. Чтение из файла");
        System.out.println("3. Случайная генерация");
        int choice = getIntInput();

        switch (choice) {
            case 1:
                inputFromConsole(cars, books, vegetables);
                break;
            case 2:
                inputFromFile(cars, books, vegetables);
                break;
            case 3:
                generateRandomData(cars, books, vegetables);
                break;
            default:
                System.out.println("Неверный выбор.");
        }
    }

    private static void inputFromConsole(List<Car> cars, List<Book> books, List<RootVegetable> vegetables) {
        System.out.println("Выберите тип объекта для ввода:");
        System.out.println("1. Автомобиль");
        System.out.println("2. Книга");
        System.out.println("3. Корнеплод");
        int choice = getIntInput();

        switch (choice) {
            case 1:
                cars.add(new Car.Builder().buildFromConsole());
                break;
            case 2:
                books.add(new Book.Builder().buildFromConsole());
                break;
            case 3:
                vegetables.add(new RootVegetable.Builder().buildFromConsole());
                break;
            default:
                System.out.println("Неверный выбор.");
        }
    }

    private static void inputFromFile(List<Car> cars, List<Book> books, List<RootVegetable> vegetables) {
        System.out.print("Введите имя файла: ");
        String fileName = scanner.nextLine();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Car:")) {
                    cars.add(new Car.Builder().buildFromString(line.substring(4)));
                } else if (line.startsWith("Book:")) {
                    books.add(new Book.Builder().buildFromString(line.substring(5)));
                } else if (line.startsWith("RootVegetable:")) {
                    vegetables.add(new RootVegetable.Builder().buildFromString(line.substring(14)));
                }
            }
            System.out.println("Данные успешно загружены из файла.");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    private static void generateRandomData(List<Car> cars, List<Book> books, List<RootVegetable> vegetables) {
        System.out.print("Введите количество случайных объектов каждого типа: ");
        int count = getIntInput();
        for (int i = 0; i < count; i++) {
            cars.add(new Car.Builder().buildRandomObj());
            books.add(new Book.Builder().buildRandomObj());
            vegetables.add(new RootVegetable.Builder().buildRandomObj());
        }
        System.out.println("Случайные данные сгенерированы.");
    }


    private static void sortData(List<Car> cars, List<Book> books, List<RootVegetable> vegetables) {
        System.out.println("\nВыберите тип данных для сортировки:");
        System.out.println("1. Автомобили");
        System.out.println("2. Книги");
        System.out.println("3. Корнеплоды");
        int dataChoice = getIntInput();

        System.out.println("\nВыберите метод сортировки:");
        System.out.println("1. Обычная сортировка ShellSort");
        System.out.println("2. Модифицированная сортировка по четным значениям");
        int methodChoice = getIntInput();

        switch (dataChoice) {
            case 1:
                sortCars(cars, methodChoice);
                break;
            case 2:
                sortBooks(books, methodChoice);
                break;
            case 3:
                sortVegetables(vegetables, methodChoice);
                break;
            default:
                System.out.println("Неверный выбор.");
        }
    }

    private static void sortCars(List<Car> cars, int methodChoice) {
        Sortable<Car> sortable = Car::getPower;
        if (methodChoice == 1) {
            SearchAndSortUtility.shellSort(cars, sortable);
        } else if (methodChoice == 2) {
            SearchAndSortUtility.modShellSort(cars, sortable);
        }
        System.out.println("Автомобили отсортированы.");
    }

    private static void sortBooks(List<Book> books, int methodChoice) {
        Sortable<Book> sortable = Book::getPageCount;
        if (methodChoice == 1) {
            SearchAndSortUtility.shellSort(books, sortable);
        } else if (methodChoice == 2) {
            SearchAndSortUtility.modShellSort(books, sortable);
        }
        System.out.println("Книги отсортированы.");
    }

    private static void sortVegetables(List<RootVegetable> vegetables, int methodChoice) {
        Sortable<RootVegetable> sortable = RootVegetable::getWeight;
        if (methodChoice == 1) {
            SearchAndSortUtility.shellSort(vegetables, sortable);
        } else if (methodChoice == 2) {
            SearchAndSortUtility.modShellSort(vegetables, sortable);
        }
        System.out.println("Корнеплоды отсортированы.");
    }

    private static void searchData(List<Car> cars, List<Book> books, List<RootVegetable> vegetables) {
        System.out.println("\nВыберите тип данных для поиска:");
        System.out.println("1. Автомобили");
        System.out.println("2. Книги");
        System.out.println("3. Корнеплоды");
        int choice = getIntInput();

        switch (choice) {
            case 1:
                searchCars(cars);
                break;
            case 2:
                searchBooks(books);
                break;
            case 3:
                searchVegetables(vegetables);
                break;
            default:
                System.out.println("Неверный выбор.");
        }
    }

    private static void searchCars(List<Car> cars) {
        System.out.print("Введите мощность автомобиля для поиска: ");
        float power = scanner.nextFloat();
        Car key = new Car.Builder().setPower(power).build();
        int index = SearchAndSortUtility.firstIndexOf(cars, key, Car::getPower);
        if (index != -1) {
            System.out.println("Найден автомобиль: " + cars.get(index));
        } else {
            System.out.println("Автомобиль не найден.");
        }
    }

    private static void searchBooks(List<Book> books) {
        System.out.print("Введите количество страниц книги для поиска: ");
        int pageCount = getIntInput();
        Book key = new Book.Builder().setPageCount(pageCount).build();
        int index = SearchAndSortUtility.firstIndexOf(books, key, Book::getPageCount);
        if (index != -1) {
            System.out.println("Найдена книга: " + books.get(index));
        } else {
            System.out.println("Книга не найдена.");
        }
    }

    private static void searchVegetables(List<RootVegetable> vegetables) {
        System.out.print("Введите вес корнеплода для поиска: ");
        double weight = scanner.nextDouble();
        RootVegetable key = new RootVegetable.Builder().setWeight(weight).build();
        int index = SearchAndSortUtility.firstIndexOf(vegetables, key, RootVegetable::getWeight);
        if (index != -1) {
            System.out.println("Найден корнеплод: " + vegetables.get(index));
        } else {
            System.out.println("Корнеплод не найден.");
        }
    }

    private static void displayData(List<Car> cars, List<Book> books, List<RootVegetable> vegetables) {
        System.out.println("\nАвтомобили:");
        cars.forEach(System.out::println);
        System.out.println("\nКниги:");
        books.forEach(System.out::println);
        System.out.println("\nКорнеплоды:");
        vegetables.forEach(System.out::println);
    }

    private static void saveDataToFile(List<Car> cars, List<Book> books, List<RootVegetable> vegetables) {
        System.out.print("Введите имя файла для сохранения: ");
        String fileName = scanner.nextLine();
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Car car : cars) {
                writer.println("Car:" + car);
            }
            for (Book book : books) {
                writer.println("Book:" + book);
            }
            for (RootVegetable vegetable : vegetables) {
                writer.println("RootVegetable:" + vegetable);
            }
            System.out.println("Данные успешно сохранены в файл.");
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

}

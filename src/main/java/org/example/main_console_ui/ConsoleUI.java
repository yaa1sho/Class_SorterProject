package main.java.org.example.main_console_ui;

import main.java.org.example.InputType;
import main.java.org.example.classes.Car;
import main.java.org.example.classes.Book;
import main.java.org.example.classes.RootVegetable;
import main.java.org.example.input.DataInput;
import sort.SortContext;
import sort.SortingManager;
import search.BinarySearch;
import classes.CustomComparable;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class ConsoleUI {
    private final Scanner scanner;
    private List<Object> items;
    private final SortContext<Object> sortContext;

    public ConsoleUI() {
        scanner = new Scanner(System.in);
        sortContext = new SortContext<>();
    }

    public void start() {
        while (true) {
            printMainMenu();
            int choice = getIntInput();
            switch (choice) {
                case 1:
                    createList();
                    break;
                case 2:
                    if (items != null && !items.isEmpty()) {
                        sortList();
                    } else {
                        System.out.println("Сначала создайте список объектов.");
                    }
                    break;
                case 3:
                    if (items != null && !items.isEmpty()) {
                        searchInList();
                    } else {
                        System.out.println("Сначала создайте список объектов.");
                    }
                    break;
                case 4:
                    if (items != null && !items.isEmpty()) {
                        displayResults();
                    } else {
                        System.out.println("Сначала создайте список объектов.");
                    }
                    break;
                case 5:
                    if (items != null && !items.isEmpty()) {
                        saveResultsToFile();
                    } else {
                        System.out.println("Сначала создайте список объектов.");
                    }
                    break;
                case 0:
                    System.out.println("Выход из программы.");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private void printMainMenu() {
        System.out.println("\n--- Главное меню ---");
        System.out.println("1. Создать список объектов");
        System.out.println("2. Отсортировать список");
        System.out.println("3. Выполнить поиск в списке");
        System.out.println("4. Вывести результаты");
        System.out.println("5. Сохранить результаты в файл");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
    }

    private void createList() {
        System.out.println("\n--- Создание списка объектов ---");
        System.out.println("Выберите тип объектов:");
        System.out.println("1. Автомобиль");
        System.out.println("2. Книга");
        System.out.println("3. Корнеплод");
        System.out.print("Ваш выбор: ");
        int typeChoice = getIntInput();

        System.out.print("Введите количество объектов: ");
        int count = getIntInput();

        System.out.println("Выберите способ ввода данных:");
        System.out.println("1. Из файла");
        System.out.println("2. Случайная генерация");
        System.out.println("3. Ручной ввод");
        System.out.print("Ваш выбор: ");
        int inputChoice = getIntInput();

        InputType inputType;
        switch (inputChoice) {
            case 1:
                inputType = InputType.CSV_FILE;
                break;
            case 2:
                inputType = InputType.RANDOM;
                break;
            case 3:
                inputType = InputType.CONSOLE;
                break;
            default:
                System.out.println("Неверный выбор. Используется случайная генерация.");
                inputType = InputType.RANDOM;
        }

        switch (typeChoice) {
            case 1:
                DataInput<Car> carInput = new DataInput<>(new Car.Builder());
                items = (List<Object>) (List<?>) carInput.fromSourceToList(count, inputType);
                break;
            case 2:
                DataInput<Book> bookInput = new DataInput<>(new Book.Builder());
                items = (List<Object>) (List<?>) bookInput.fromSourceToList(count, inputType);
                break;
            case 3:
                DataInput<RootVegetable> vegetableInput = new DataInput<>(new RootVegetable.Builder());
                items = (List<Object>) (List<?>) vegetableInput.fromSourceToList(count, inputType);
                break;
            default:
                System.out.println("Неверный выбор типа объектов.");
                return;
        }

        System.out.println("Список объектов создан.");
    }

    private void sortList() {
        System.out.println("\n--- Сортировка списка ---");
        System.out.println("Выберите алгоритм сортировки:");
        System.out.println("1. ShellSort");
        System.out.println("2. EvenShellSort");
        System.out.print("Ваш выбор: ");
        int sortChoice = getIntInput();

        String strategyName;
        switch (sortChoice) {
            case 1:
                strategyName = "shellsort";
                break;
            case 2:
                strategyName = "evenshellsort";
                break;
            default:
                System.out.println("Неверный выбор. Используется ShellSort.");
                strategyName = "shellsort";
        }

        sortContext.setStrategy(SortingManager.getStrategy(strategyName));
        sortContext.executeStrategy(items, getComparator());
        System.out.println("Сортировка выполнена.");
    }

    private void searchInList() {
        System.out.println("\n--- Поиск в списке ---");

        CustomComparable<Object> comparator = getComparator();

        System.out.print("Введите значение для поиска: ");
        String searchValue = scanner.nextLine();

        Object searchKey;
        if (items.getFirst() instanceof Book) {
            Book.Builder builder = new Book.Builder();
            if (Objects.requireNonNull(comparator).getField(items.getFirst()) instanceof String) {
                searchKey = builder.setName(searchValue).build();
            } else {
                searchKey = builder.setPageCount(Integer.parseInt(searchValue)).build();
            }
        } else if (items.getFirst() instanceof Car) {
            Car.Builder builder = new Car.Builder();
            if (Objects.requireNonNull(comparator).getField(items.getFirst()) instanceof String) {
                searchKey = builder.setModel(searchValue).build();
            } else if (comparator.getField(items.getFirst()) instanceof Float) {
                searchKey = builder.setPower(Float.parseFloat(searchValue)).build();
            } else {
                searchKey = builder.setYear(Integer.parseInt(searchValue)).build();
            }
        } else if (items.getFirst() instanceof RootVegetable) {
            RootVegetable.Builder builder = new RootVegetable.Builder();
            if (Objects.requireNonNull(comparator).getField(items.getFirst()) instanceof String) {
                searchKey = builder.setType(searchValue).build();
            } else {
                searchKey = builder.setWeight(Double.parseDouble(searchValue)).build();
            }
        } else {
            System.out.println("Неподдерживаемый тип объекта для поиска.");
            return;
        }

        int result = BinarySearch.firstIndexOf(items, searchKey, comparator);
        if (result != -1) {
            System.out.println("Элемент найден на позиции: " + result);
        } else {
            System.out.println("Элемент не найден.");
        }
    }



    private void displayResults() {
        System.out.println("\n--- Результаты ---");
        for (Object item : items) {
            System.out.println(item);
        }
    }

    private void saveResultsToFile() {
        System.out.print("Введите имя файла для сохранения результатов: ");
        String fileName = scanner.nextLine();

        try (FileWriter writer = new FileWriter(fileName)) {
            for (Object item : items) {
                if (item instanceof Car car) {
                    writer.write(String.format(Locale.US, "%.2f,%s,%d\n", car.getPower(), car.getModel(), car.getYear()));
                } else if (item instanceof Book book) {
                    writer.write(String.format("%s,%s,%d\n", book.getName(), book.getAuthor(), book.getPageCount()));
                } else if (item instanceof RootVegetable vegetable) {
                    writer.write(String.format(Locale.US, "%s,%s,%.2f\n", vegetable.getType(), vegetable.getColor(), vegetable.getWeight()));
                }
            }
            System.out.println("Результаты сохранены в файл: " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении файла: " + e.getMessage());
        }
    }



    private int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Пожалуйста, введите целое число: ");
            }
        }
    }

    private CustomComparable<Object> getComparator() {
        if (items == null || items.isEmpty()) {
            return null;
        }

        Object firstItem = items.getFirst();

        if (firstItem instanceof Car) {
            System.out.println("Выберите поле для сортировки или поиска автомобилей:");
            System.out.println("1. Мощность");
            System.out.println("2. Модель");
            System.out.println("3. Год выпуска");
            int choice = getIntInput();
            return switch (choice) {
                case 1 -> item -> ((Car) item).getPower();
                case 2 -> item -> ((Car) item).getModel();
                case 3 -> item -> ((Car) item).getYear();
                default -> item -> ((Car) item).getPower();
            };
        } else if (firstItem instanceof Book) {
            System.out.println("Выберите поле для сортировки или поиска книг:");
            System.out.println("1. Название");
            System.out.println("2. Автор");
            System.out.println("3. Количество страниц");
            int choice = getIntInput();
            return switch (choice) {
                case 1 -> item -> ((Book) item).getName();
                case 2 -> item -> ((Book) item).getAuthor();
                case 3 -> item -> ((Book) item).getPageCount();
                default -> item -> ((Book) item).getName();
            };
        } else if (firstItem instanceof RootVegetable) {
            System.out.println("Выберите поле для сортировки или поиска корнеплодов:");
            System.out.println("1. Тип");
            System.out.println("2. Цвет");
            System.out.println("3. Вес");
            int choice = getIntInput();
            return switch (choice) {
                case 1 -> item -> ((RootVegetable) item).getType();
                case 2 -> item -> ((RootVegetable) item).getColor();
                case 3 -> item -> ((RootVegetable) item).getWeight();
                default -> item -> ((RootVegetable) item).getType();
            };
        }

        return null;
    }

}

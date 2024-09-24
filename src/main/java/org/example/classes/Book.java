package main.java.org.example.classes;

import main.java.org.example.input.IBuilder;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Book {
    private String name;
    private String author;
    private int pageCount;

    public int getPageCount() {
        return pageCount;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    private Book() {
    }

    private Book(Builder builder) {
        name = builder.name;
        author = builder.author;
        pageCount = builder.pageCount;
    }

    public static class Builder implements IBuilder<Book> {
        private String name;
        private String author;
        private int pageCount;


        public Builder() {
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAuthor(String author) {
            this.author = author;
            return this;
        }

        public Builder setPageCount(int pageCount) {
            this.pageCount = pageCount;
            return this;
        }

        public Book build() {
            return new Book(this);
        }

        @Override
        public String getDataFileRequirements() {
            return """
                      название, автор, количество страниц
                      название, автор, количество страниц
                      ...
                    """;
        }

        @Override
        public Book buildFromString(String line) {
            String[] values = line.split(",");
            setName(values[0].trim());
            setAuthor(values[1].trim());
            int pageCount = -1;
            try {
                pageCount = Integer.parseInt(values[2].trim());
            } catch (NumberFormatException ignored) {
            }
            if (pageCount > 0) {
                setPageCount(pageCount);
                return new Book(this);
            }
            return null;
        }

        @Override
        public Book buildFromConsole() {
            Scanner scanner = new Scanner(System.in);
            String input;
            do {
                System.out.println("Введите название книги или exit:");
                input = scanner.nextLine();
                if ("exit".equalsIgnoreCase(input)) {
                    return null;
                }
            } while (input.isEmpty() || input.isBlank());
            setName(input);
            do {
                System.out.println("Введите фамилию автора или exit:");
                input = scanner.nextLine();
                if ("exit".equalsIgnoreCase(input)) {
                    return null;
                }
            } while (input.isEmpty() || input.isBlank());
            setAuthor(input);
            int pageCount = -1;
            do {
                System.out.println("Введите количество страниц или exit:");
                input = scanner.nextLine();
                if ("exit".equalsIgnoreCase(input)) {
                    return null;
                }
                try {
                    pageCount = Integer.parseInt(input.trim());
                } catch (NumberFormatException exception) {
                    System.out.println("Количество страниц должно быть целым положительным числом!");
                }
            } while (pageCount < 1);
            setPageCount(pageCount);
            return new Book(this);
        }

        @Override
        public Book buildRandomObj() {
            List<String> names = List.of("Война и мир", "Преступление и наказание", "Гарри Поттер и философский камень",
                    "1984", "Убить пересмешника", "Мастер и Маргарита", "Сто лет одиночества", "Ловец в ржи",
                    "До встречи с тобой", "Анна Каренина");
            List<String> authors = List.of("Достоевский", "Толстой", "Набоков", "Пушкин", "Маяковский", "Булгаков",
                    "Тургенев", "Ахматова", "Чехов", "Гоголь");
            Random random = new Random();
            setName(names.get(random.nextInt(names.size())));
            setAuthor(authors.get(random.nextInt(authors.size())));
            setPageCount(random.nextInt(3599) + 1);
            return new Book(this);
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", pageCount=" + pageCount +
                '}';
    }
}

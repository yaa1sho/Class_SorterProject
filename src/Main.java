//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import classes.Book;
import search.BinarySearch;
import sort.ShellSort;
import sort.SortContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Book> bookList = Arrays.asList(new Book.Builder().setName("Война и Мир").setAuthor("Толстой").setPageCount(1000).build(),
                new Book.Builder().setName("Гордость и предубеждение").setAuthor("Остин").setPageCount(871).build(),
                new Book.Builder().setName("Преступление и наказание").setAuthor("Достоевский").setPageCount(531).build(),
                new Book.Builder().setName("Тихий Дон").setAuthor("Шолохов").setPageCount(862).build());
        SortContext<Book> context = new SortContext<>();
        context.setStrategy(new ShellSort<>());
        context.executeStrategy(bookList, Book::getPageCount);
        System.out.println(bookList);
    }
}
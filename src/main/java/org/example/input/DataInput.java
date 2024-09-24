package main.java.org.example.input;

import main.java.org.example.InputType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DataInput<T> {
    IBuilder<T> builder;

    public DataInput(IBuilder<T> builder) {
        this.builder = builder;
    }

    public List<T> fromSourceToList(int listSize, InputType inputType) {
        List<T> list = new ArrayList<>(listSize);
        List<String> fileStrings = new ArrayList<>();
        if (inputType == InputType.CSV_FILE) {
            do {
                System.out.println("Введите путь к файлу или exit. Файл должен содержать данные в следующем формате:\n"
                        + builder.getDataFileRequirements());
                String filePath = new Scanner(System.in).nextLine();
                if ("exit".equalsIgnoreCase(filePath)) {
                    return list;
                }
                try {
                    fileStrings = Files.readAllLines(Path.of(filePath));
                    break;
                } catch (IOException e) {
                    System.out.println("Файл недоступен");
                }
            } while (true);
        }
        for (int i = 0; i < listSize; i++) {
            T obj = null;
            switch (inputType) {
                case CSV_FILE:
                    if (i < fileStrings.size()) {
                        obj = builder.buildFromString(fileStrings.get(i));
                        break;
                    }
                case CONSOLE:
                    obj = builder.buildFromConsole();
                    break;
                case RANDOM:
                    obj = builder.buildRandomObj();
                    break;
            }
            if (obj != null) {
                list.add(obj);
            }
        }
        return list;
    }
}

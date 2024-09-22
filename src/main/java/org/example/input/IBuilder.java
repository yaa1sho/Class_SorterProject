package main.java.org.example.input;

public interface IBuilder<T> {
    T buildFromString(String line);
    T buildFromConsole();
    T buildRandomObj();
}

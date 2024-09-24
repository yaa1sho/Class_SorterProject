package main.java.org.example.input;

public interface IBuilder<T> {
    String getDataFileRequirements();
    T buildFromString(String line);
    T buildFromConsole();
    T buildRandomObj();
}

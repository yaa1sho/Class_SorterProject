package sort;

public class SortingManager {
    public static <T> SortingStrategy<T> getStrategy (String strategyName) {
        return switch (strategyName.toLowerCase()) {
            case "shellsort" -> new ShellSort<>();
            case "evenshellsort" -> new EvenShellSort<>();
            default -> throw new IllegalArgumentException("Unknown sorting strategy: " + strategyName);
        };
    }
}

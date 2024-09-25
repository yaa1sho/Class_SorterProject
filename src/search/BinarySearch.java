package search;
import classes.CustomComparable;
import utility.Utility;
import java.util.List;

public class BinarySearch<T> {
    public static <T> int firstIndexOf(List<T> items, T key, CustomComparable<T> comparable) {
        int left = 0;
        int right = items.size() - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = Utility.compare(items.get(mid), key, comparable);
            if (cmp == 0) {
                result = mid;
                right = mid - 1;
            }
            if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result; //TODO Нужно обработать результат -1 (элемент не найден)
    }
}

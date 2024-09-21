package utility;

import utility.Sortable;

import java.util.ArrayList;
import java.util.List;

public class SearchAndSortUtility<T> {
    public static <T> void shellSort(List<T> items, Sortable<T> sortable) {
        int listSize = items.size();
        for (int gap = listSize / 2; gap > 0; gap /= 2) {
            for (int currentIndex = gap; currentIndex < listSize; currentIndex++) {
                T temp = items.get(currentIndex);
                int currentIndexCopy;
                for (currentIndexCopy = currentIndex; currentIndexCopy >= gap && compare(items.get(currentIndexCopy - gap), temp, sortable) > 0; currentIndexCopy -= gap) {
                    items.set(currentIndexCopy, items.get(currentIndexCopy - gap));
                }
                items.set(currentIndexCopy, temp);
            }
        }
    }
    public static <T> void modShellSort(List<T> items, Sortable<T> sortable) {
        // Проверка, на то, что field числовой
        if (isNumber(sortable.getField(items.get(0)))) {
            List<T> evensItems = new ArrayList<>();
            //Копирование четных элементов
            for (T item :
                    items) {
                if (isEven(sortable.getField(item))) {
                    evensItems.add(item);
                }
            }
            //Сортировка четных элементов
            shellSort(evensItems, sortable);
            //Подстановка отсортированных четных элементов
            int indexOfEvenItem = 0;
            for (int index = 0; index < items.size(); index++) {
                if (isEven(sortable.getField(items.get(index)))) {
                    items.set(index, evensItems.get(indexOfEvenItem));
                    indexOfEvenItem++;
                }
            }
        }
    }

    public static <T> int firstIndexOf(List<T> items, T key, Sortable<T> sortable) {
        int left = 0;
        int right = items.size() - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = compare(items.get(mid), key, sortable);
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
        return result;
    }

    private static boolean isNumber(Object value) {
        return value instanceof Number;
    }

    private static boolean isEven(Object value) {
        if (value instanceof Integer) {
            return ((Integer) value) % 2 == 0;
        } else if (value instanceof Double) {
            return ((Double) value) % 2 == 0;
        } else if (value instanceof Float) {
            return ((Float) value) % 2 == 0;
        }
        return false;
    }

    private static <T> int compare(T a, T b, Sortable<T> sortable) {
        return sortable.getField(a).compareTo(sortable.getField(b));
    }
}

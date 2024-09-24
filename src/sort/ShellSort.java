package sort;

import classes.CustomComparable;
import utility.Utility;

import java.util.List;

public class ShellSort<T> implements SortingStrategy<T> {
    @Override
    public void sort(List<T> items, CustomComparable<T> comparable) {
        int listSize = items.size();
        for (int gap = listSize / 2; gap > 0; gap /= 2) {
            for (int currentIndex = gap; currentIndex < listSize; currentIndex++) {
                T temp = items.get(currentIndex);
                int currentIndexCopy;
                for (currentIndexCopy = currentIndex; currentIndexCopy >= gap && Utility.compare(items.get(currentIndexCopy - gap), temp, comparable) > 0; currentIndexCopy -= gap) {
                    items.set(currentIndexCopy, items.get(currentIndexCopy - gap));
                }
                items.set(currentIndexCopy, temp);
            }
        }
    }
}

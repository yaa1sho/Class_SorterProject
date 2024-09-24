package sort;
import classes.CustomComparable;

import java.util.List;

public interface SortingStrategy<T> {
     void sort(List<T> items, CustomComparable<T> comparable);
}

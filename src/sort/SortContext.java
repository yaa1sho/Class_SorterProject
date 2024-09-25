package sort;

import classes.CustomComparable;

import java.util.List;

public class SortContext<T> {
    private SortingStrategy<T> strategy;

    public void setStrategy(SortingStrategy<T> strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy(List<T> items, CustomComparable<T> comparable) {
        strategy.sort(items, comparable);
    }
}

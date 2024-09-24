package sort;
import classes.CustomComparable;
import utility.Utility;

import java.util.ArrayList;
import java.util.List;

public class EvenShellSort<T> implements SortingStrategy<T> {
    @Override
    public void sort(List<T> items, CustomComparable<T> comparable) {
        // Проверка, на то, что field числовой
        if (Utility.isNumber(comparable.getField(items.get(0)))) {
            List<T> evensItems = new ArrayList<>();
            //Копирование четных элементов
            for (T item :
                    items) {
                if (Utility.isEven(comparable.getField(item))) {
                    evensItems.add(item);
                }
            }
            //Сортировка четных элементов
            ShellSort<T> shellSort = new ShellSort<>();
            shellSort.sort(evensItems, comparable);
            //Подстановка отсортированных четных элементов
            int indexOfEvenItem = 0;
            for (int index = 0; index < items.size(); index++) {
                if (Utility.isEven(comparable.getField(items.get(index)))) {
                    items.set(index, evensItems.get(indexOfEvenItem));
                    indexOfEvenItem++;
                }
            }
        }
        else {
            System.out.println("Передано нечисловое поле");
        }
        //TODO Нужно указать пользователю, что на вход сортировки подано нечисловое поле

    }
}

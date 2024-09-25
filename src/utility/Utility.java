package utility;

import classes.CustomComparable;

public class Utility {
    public static boolean isNumber(Object value) {
        return value instanceof Number;
    }

    public static boolean isEven(Object value) {
        if (value instanceof Integer) {
            return ((Integer) value) % 2 == 0;
        } else if (value instanceof Double) {
            return ((Double) value) % 2 == 0;
        } else if (value instanceof Float) {
            return ((Float) value) % 2 == 0;
        }
        return false;
    }
    public static <T> int compare(T a, T b, CustomComparable<T> comparable) {
        return comparable.getField(b).compareTo(comparable.getField(a));
    }
}

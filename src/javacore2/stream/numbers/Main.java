package javacore2.stream.numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);
        List<Integer> sortedList = new ArrayList<>();
        for (Integer integer : integerList) {
            if (integer > 0 && integer % 2 == 0) {
                sortedList.add(integer);
            }
        }
        sortedList.sort(Comparator.naturalOrder());
        for (Integer integer :
                sortedList) {
            System.out.println(integer);
        }

    }
}

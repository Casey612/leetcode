package exec.NumberArray;

import java.util.Arrays;

public class SelectSort {

    public static void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int index = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[index]) {
                    index = j;
                }
            }
            swap(index, i, array);
        }
    }

    private static void swap(int i, int j, int[] array) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = {3, 5, 2, 1, 4};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

}

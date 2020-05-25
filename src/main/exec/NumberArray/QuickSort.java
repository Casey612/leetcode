package exec.NumberArray;

import java.util.Arrays;

public class QuickSort {

    public static void quickSort(int[] num) {
        quickSort(num, 0, num.length - 1);
    }

    private static void quickSort(int[] num, int start, int end) {
        if (num == null || start >= end) {
            return;
        }
        int partition = partition(num, start, end);
        quickSort(num, start, partition - 1);
        quickSort(num, partition + 1, end);
    }

    private static int partition(int[] num, int start, int end) {
        int temp = num[start];
        int low = start, high = end, index = low + 1;
        while (index <= high) {
            if (num[index] <= temp) {
                swap(num, low, index);
                index++;
                low++;
            } else {
                swap(num, high, index);
                index++;
                high--;
            }
        }
        return low;
    }

    private static void swap(int[] array, int i, int j) {
        if (i == j) {
            return;
        }
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] a1 = {1, 3, 4, 2, 2, 5};
        int[] a2 = {5, 4, 3, 2, 1};
        quickSort(a1);
        quickSort(a2);
        System.out.println(Arrays.toString(a1));
        System.out.println(Arrays.toString(a2));
    }

}

package exec.NumberArray;

import java.util.Arrays;

public class MergeSorted {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = m + n - 1;
        int l1 = m - 1, l2 = n - 1;
        while (l1 >= 0 || l2 >= 0) {
            Integer n1 = null, n2 = null;
            if (l1 >= 0) {
                n1 = nums1[l1];
            }
            if (l2 >= 0) {
                n2 = nums2[l2];
            }
            if (n1 == null) {
                nums1[index] = n2;
                l2--;
            } else if (n2 == null) {
                nums1[index] = n1;
                l1--;
            } else if (n1 > n2) {
                nums1[index] = n1;
                l1--;
            } else {
                nums1[index] = n2;
                l2--;
            }
            index--;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 0, 0, 0};
        merge(array, 3, new int[]{2, 5, 6}, 3);
        System.out.println(Arrays.toString(array));
    }

}

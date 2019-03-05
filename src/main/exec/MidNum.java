package exec;

/**
 * @author: yuki
 * @date: 2018/9/29
 */
public class MidNum {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //类归并排序法
        double result = 0;
        int sumLength = nums1.length + nums2.length;
        int indexFlag = sumLength / 2;
        boolean flag = (sumLength % 2) == 0;
        int[] array = new int[sumLength];
        int index1 = 0, index2 = 0, indexSum = 0;
        while (index1 < nums1.length && index2 < nums2.length) {
            int num;
            if (nums1[index1] < nums2[index2]) {
                num = nums1[index1++];
            } else {
                num = nums2[index2++];
            }

            if (!flag && indexSum == indexFlag) {
                //only one target num
                return num;
            } else if (flag && (indexSum == (indexFlag - 1))) {
                result += num;
            } else if (flag && (indexSum == indexFlag)) {
                result += num;
                return result / 2;
            }
            array[indexSum++] = num;
        }
        while (index1 < nums1.length) {
            int num = nums1[index1++];
            if (!flag && indexSum == indexFlag) {
                //only one target num
                return num;
            } else if (flag && (indexSum == (indexFlag - 1))) {
                result += num;
            } else if (flag && (indexSum == indexFlag)) {
                result += num;
                return result / 2;
            }
            array[indexSum++] = num;
        }
        while (index2 < nums2.length) {
            int num = nums2[index2++];
            if (!flag && indexSum == indexFlag) {
                //only one target num
                return num;
            } else if (flag && (indexSum == (indexFlag - 1))) {
                result += num;
            } else if (flag && (indexSum == indexFlag)) {
                result += num;
                return result / 2;
            }
            array[indexSum++] = num;
        }
        return 0;
    }

    public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int[] A = nums1.length < nums2.length ? nums1 : nums2;
        int[] B = nums1.length >= nums2.length ? nums1 : nums2;

        int m = A.length;
        int n = B.length;

        int iMin = 0, iMax = m, halfLen = (m + n) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j - 1] > A[i]) {
                // i is too small
                iMin = i + 1;
            } else if (i > iMin && A[i - 1] > B[j]) {
                // i is too big
                iMax = i - 1;
            } else { // i is perfect
                System.out.println("i:" + i);
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = B[j - 1];
                } else if (j == 0) {
                    maxLeft = A[i - 1];
                } else {
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                int minRight = 0;
                if (i == m) {
                    minRight = B[j];
                } else if (j == n) {
                    minRight = A[i];
                } else {
                    minRight = Math.min(B[j], A[i]);
                }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }

    public static void main(String[] args) {
        double r1 = findMedianSortedArrays1(new int[]{1, 3}, new int[]{2});
        System.out.println(r1);
        double r2 = findMedianSortedArrays1(new int[]{1, 3}, new int[]{2, 4});
        System.out.println(r2);
    }

}

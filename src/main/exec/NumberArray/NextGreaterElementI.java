package exec.NumberArray;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NextGreaterElementI {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = getNextGreater(nums1[i], nums2);
        }
        return result;
    }

    private int getNextGreater(int element, int[] array) {
        int result = -1;
        int index = array.length - 1;
        while (index >= 0 && array[index] != element) {
            if (array[index] > element) {
                result = array[index];
            } else if (array[index] == element) {
                break;
            }
            index--;
        }
        return result;
    }

    public int[] nextGreaterElementStack(int[] nums1, int[] nums2) {
        int resultLength = nums1.length;
        int[] result  = new int[resultLength];

        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = nums2.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() < nums2[i]) {
                stack.pop();
            }
            int nextGreater = stack.isEmpty() ? -1 : stack.peek();
            map.put(nums2[i], nextGreater);
            stack.push(nums2[i]);
        }
        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.get(nums1[i]);
        }
        return result;
    }

}

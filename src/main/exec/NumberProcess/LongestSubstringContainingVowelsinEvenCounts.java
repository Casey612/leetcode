package exec.NumberProcess;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * #1379
 */
public class LongestSubstringContainingVowelsinEvenCounts {

    static Map<Character, Integer> map = new HashMap<>();

    static {
        map.put('a', 1);
        map.put('e', 2);
        map.put('i', 4);
        map.put('o', 8);
        map.put('u', 16);
    }

    public int findTheLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] array = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            array[i] = map.getOrDefault(c, 0);
        }
        int left = 0, right = array.length - 1, result = 0;
        while (left < array.length) {
            while (right >= left) {
                if (evenCounts(array, left, right)) {
                    System.out.println(left + "," + right);
                    result = Math.max(result, right - left + 1);
                }
                right--;
            }
            right = array.length - 1;
            left++;
        }
        return result;
    }

    private boolean evenCounts(int[] array, int left, int right) {
        int total = 0;
        for (int i = left; i <= right; i++) {
            total ^= array[i];
        }
        return total == 0;
    }

    public int findTheLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] index = new int[32];
        Arrays.fill(index, -1);
        index[0] = 0;
        int result = 0, total = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int cur = map.getOrDefault(c, 0);
            total ^= cur;
            if (index[total] == -1) {
                index[total] = i;
            } else {
                if (total == 0) {
                    result = Math.max(result, i - index[total] + 1);
                } else {
                    result = Math.max(result, i - index[total]);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        LongestSubstringContainingVowelsinEvenCounts counts = new LongestSubstringContainingVowelsinEvenCounts();
        System.out.println(counts.findTheLongestSubstring("leetcodeisgreat"));
    }
}

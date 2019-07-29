package exec.StringProcess;

import java.util.*;

/**
 * @author: yuki
 * @date: 2018/9/29
 */
public class LongestSubstring {

    public static int lengthOfLongestSubstring1(String s) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (map.containsKey(c)) {
                int index = map.get(c);
                //覆盖map
                map = new HashMap<>();
                for (int j = ++index; j <= i; j++) {
                    map.put(s.charAt(j), j);
                }
            }
            map.put(c, i);
            result = map.size() > result ? map.size() : result;
//            System.out.println(map + " " + map.size() + " " + result);
        }
        return result;
    }


    public static int lengthOfLongestSubstring(String s) {
        int result = 0, start = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (map.containsKey(c)) {
                int index = map.get(c) + 1;
                start = index > start ? index : start;
            }
            result = Math.max(result, i - start + 1);
            map.put(c, i);
//            System.out.println(map + " " + start + " " + result);
        }
        return result;
    }

    public static int lengthOfLongestSubstring3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int ans = 1;
        boolean[] flag = new boolean[256];
        int begin = 0;
        flag[s.charAt(0)] = true;
        for (int i = 1; i < s.length(); i ++) {
            if (flag[s.charAt(i)]) {
                while (s.charAt(begin) != s.charAt(i)) {
                    flag[s.charAt(begin)] = false;
                    begin ++;
                }
                begin ++;
            }
            flag[s.charAt(i)] = true;
            ans = i - begin + 1 > ans ? i - begin + 1: ans;
        }
        return ans;
    }

    public static void main(String[] args) {
//        int r1 = lengthOfLongestSubstring1("advdf");
        int r1 = lengthOfLongestSubstring("advdf");
        System.out.println(r1);
    }

}

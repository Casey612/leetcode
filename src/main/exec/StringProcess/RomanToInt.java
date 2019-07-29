package exec.StringProcess;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author yuzhe
 * @since 3/5/19
 */
public class RomanToInt {

    public static int romanToInt(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int result = 0;
        int index = 0;
        while (index < s.length()) {
            Character cur = s.charAt(index);
            Character next = null;
            if ((index + 1) < s.length()) {
                next = s.charAt(index + 1);
            }
            if (!Objects.isNull(next) && map.get(cur) < map.get(next)) {
                int val = map.get(next) - map.get(cur);
                result += val;
                index += 2;
            } else {
                result += map.get(cur);
                index++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("III"));
        System.out.println(romanToInt("IV"));
        System.out.println(romanToInt("LVIII"));
        System.out.println(romanToInt("MCMXCIV"));
    }

}

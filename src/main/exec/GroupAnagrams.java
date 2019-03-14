package exec;

import java.util.*;

/**
 * @author yuzhe
 * @since 3/13/19
 */
public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return result;
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String key = getKey(str);
            if (map.containsKey(key)) {
                map.get(key).add(str);
            } else {
                List<String> array = new ArrayList<>();
                array.add(str);
                map.put(key, array);
            }
        }
        for (String key : map.keySet()) {
            result.add(map.get(key));
        }
        return result;
    }

    private String getKey(String str) {
        char[] array = str.toCharArray();
        Arrays.sort(array);
        return new String(array);
    }

}

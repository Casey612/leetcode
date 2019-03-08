package exec;

import java.util.*;

/**
 * @author yuzhe
 * @since 3/8/19
 */
public class SubstringWithConcatenation {

    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (words == null || words.length == 0) {
            return result;
        }
        int wordLength = words[0].length(), substrLength = wordLength * words.length;
        Map<String, Integer> map = buildMap(words);
        for (int startIndex = 0; startIndex <= (s.length() - substrLength); startIndex++) {
            String subString = s.substring(startIndex, startIndex + substrLength);
            int i;
            for (i = 0; i < subString.length(); i += wordLength) {
                String word = subString.substring(i, i + wordLength);
                if (!map.containsKey(word) || map.get(word) < 1) {
                    map = buildMap(words);
                    break;
                } else {
                    int count = map.get(word);
                    count-- ;
                    map.put(word, count);
                }
            }
            if (i == substrLength) {
                result.add(startIndex);
                map = buildMap(words);
            }
        }
        return result;
    }

    private static Map<String, Integer> buildMap(String[] words) {
        Map<String, Integer> map = new HashMap<>(words.length);
        for (String s : words) {
            Integer count = 1;
            if (map.containsKey(s)) {
                count = map.get(s);
                count++;
            }
            map.put(s, count);
        }
        return map;
    }

    public static void main(String[] args) {
//        System.out.println(findSubstring("barfoothefoobarman", new String[]{"foo","bar"}));
//        System.out.println(findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "word"}));
//        System.out.println(findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "good"}));
        System.out.println(findSubstring("aaa", new String[]{"a", "a"}));
    }

}

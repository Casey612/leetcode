package exec.StringProcess;

import java.util.List;
import java.util.Objects;

/**
 * # 127
 * @author yuzhe
 * Created on 2021-11-08
 */
public class LadderLength {


    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (Objects.isNull(beginWord) || Objects.isNull(endWord) || beginWord.length() != endWord.length()
                || wordList.isEmpty() || !wordList.contains(endWord)) {
            return 0;
        }




        return 0;
    }


    public int wordDiff(String word1, String word2) {
        if (Objects.isNull(word1) || Objects.isNull(word2) || word1.length() != word2.length()) {
            return -1;
        }
        if (word1.equals(word2)) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i < word1.length(); i++) {
            char c1 = word1.charAt(i);
            char c2 = word2.charAt(i);
            if (c1 != c2) {
                result++;
            }
        }
        return result;
    }

}

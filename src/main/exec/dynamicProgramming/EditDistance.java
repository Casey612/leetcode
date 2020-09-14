package exec.dynamicProgramming;

public class EditDistance {

    public int minDistance(String word1, String word2) {
        if (word2 == null || word2.length() == 0) {
            //delete all chars
            return word1 == null ? 0 : word1.length();
        }
        if (word1 == null || word1.length() == 0) {
            //add all letters
            return word2.length();
        }
        //word1 is not empty && word2 is not empty.
        int[][] dp = new int[word1.length()][word2.length()];

        //初始化 ， i == 0 || j == 0
        char start2 = word2.charAt(0);
        boolean containFlag1 = false;
        for (int i = 0; i < word1.length(); i++) {
            //匹配word2首字母。 多余删除
            containFlag1 = containFlag1 || word1.charAt(i) == start2;
            dp[i][0] = containFlag1 ? i : i + 1;
        }

        char start1 = word1.charAt(0);
        boolean containFlag2 = word1.charAt(0) == word2.charAt(0);
        for (int j = 1; j < word2.length(); j++) {
            //匹配word2首字母。 不足添加
            containFlag2 = containFlag2 || word2.charAt(j) == start1;
            dp[0][j] = containFlag2 ? j : j + 1;
        }

        for (int i = 1; i < word1.length(); i++) {
            for (int j = 1; j < word2.length(); j++) {

                int add = dp[i][j - 1] + 1;
                int alter = word1.charAt(i) == word2.charAt(j) ? dp[i - 1][j - 1] : dp[i - 1][j - 1] + 1;
                int drop = dp[i - 1][j] + 1;

                dp[i][j] = Math.min(add, Math.min(alter, drop));
            }
        }
        return dp[word1.length() - 1][word2.length() - 1];
    }


    public static void main(String[] args) {
        EditDistance editDistance = new EditDistance();
        System.out.println(editDistance.minDistance("horse", "ros"));
        System.out.println(editDistance.minDistance("intention", "execution"));
        System.out.println(editDistance.minDistance("a", "ab"));
    }


}

package exec.NumberProcess;

public class NumTree {

    public int numTrees(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            int left = numTrees(i);
            int right = numTrees(n - 1 - i);
            result += (left * right);
        }
        return result;
    }

}

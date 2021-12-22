package exec.Metrics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * # 51
 * @author yuki
 * Created on 2021-12-22
 */
public class NQueen {


    List<List<String>> result = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        List<String> cur = new ArrayList<>(n);
        backtrace(cur, 0, n);
        return result;
    }

    private void backtrace(List<String> metric, int row, int n) {
        if (row == n) {
            List<String> container = new ArrayList<>(metric);
            result.add(container);
            return;
        }

        for (int i = 0; i < n; i++) {
            //初始化当前行
            StringBuilder line = getInitRow(n);
            //放置一个Q
            line.replace(i, i + 1, "Q");
            String lineStr = line.toString();
            metric.add(lineStr);
            //确认当前矩阵是否合理 不合理剪枝
            if (isValid(metric, row, i, n)) {
                //合理 向下递归
                backtrace(metric, row + 1, n);
            }
            metric.remove(row);

        }
    }

    private StringBuilder getInitRow(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append('.');
        }
        return sb;
    }

    /**
     * @param metric 当前矩阵
     * @return 是否合法
     */
    private boolean isValid(List<String> metric, int row, int col, int n) {
        //同一行冲突
        String rowLine = metric.get(row);
        for(int i = 0; i < col; i++) {
            if (rowLine.charAt(i) == 'Q') {
                return false;
            }
        }
        //同一列冲突
        for (int i = 0; i < row; i++) {
            if (metric.get(i).charAt(col) == 'Q') {
                return false;
            }
        }
        //左斜线冲突
        int i = row - 1, j = col - 1;
        while (i >= 0  && j >= 0) {
            String str = metric.get(i);
            if (str.charAt(j) == 'Q') {
                return false;
            }
            i--;
            j--;
        }
        //右斜线冲突
        i = row - 1;
        j = col + 1;
        while (i >= 0  && j < n) {
            String str = metric.get(i);
            if (str.charAt(j) == 'Q') {
                return false;
            }
            i--;
            j++;
        }
       return true;
    }

    public static void main(String[] args) {
        NQueen nQueen = new NQueen();
//        List<List<String>> result1 = nQueen.solveNQueens(1);
//        System.out.println(Arrays.toString(result1.toArray()));
        List<List<String>> result2 = nQueen.solveNQueens(2);
        System.out.println(Arrays.toString(result2.toArray()));
//        List<List<String>> result4 = nQueen.solveNQueens(4);
//        System.out.println(Arrays.toString(result4.toArray()));
    }

}

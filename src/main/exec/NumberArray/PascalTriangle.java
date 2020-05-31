package exec.NumberArray;

import java.util.ArrayList;
import java.util.List;

/**
 * #118
 */
public class PascalTriangle {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows <= 0) {
            return result;
        }
        List<Integer> row = new ArrayList<>();
        row.add(1);
        if (numRows == 1) {
            result.add(row);
            return result;
        }
        List<List<Integer>> before = generate(numRows - 1);
        List<Integer> lastRow = before.get(before.size() - 1);
        for (int index = 0; index < lastRow.size() - 1; index++) {
            row.add(lastRow.get(index) + lastRow.get(index + 1));
        }
        row.add(1);
        result.addAll(before);
        result.add(row);
        return result;
    }

}

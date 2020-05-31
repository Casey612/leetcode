package exec.NumberArray;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle2 {

    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        if (rowIndex < 0) {
            return result;
        }
        List<Integer> temp;
        for (int i = 0; i <= rowIndex; i++) {
            if (i == 0) {
                result.add(1);
            } else {
                temp = result;
                result = new ArrayList<>();
                result.add(1);
                for (int index = 0; index < temp.size() - 1; index++) {
                    result.add(temp.get(index) + temp.get(index + 1));
                }
                result.add(1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        PascalTriangle2 triangle2 = new PascalTriangle2();
        System.out.println(triangle2.getRow(3));
    }

}

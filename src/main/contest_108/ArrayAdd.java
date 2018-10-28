package contest_108;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yuki
 * @date: 2018/10/28
 */
public class ArrayAdd {

    public int numSubarraysWithSum(int[] A, int S) {
        List<Integer> oneIndex = new ArrayList<>();
        for (int index = 0; index < A.length; index++) {
            if (A[index] == 1) {
                oneIndex.add(index);
            }
        }
        if (S == 0) {
            if (oneIndex.size() == 0) {
                return A.length * (A.length + 1) / 2;
            } else {
                int result = 0;
                for(int i = 0; i < oneIndex.size(); i++){
                    int start, end;
                    if(i == 0){
                        if(oneIndex.get(i) == 0){
                            continue;
                        }
                        start = 0;
                    }else {
                        start = oneIndex.get(i - 1) + 1;
                    }
                    end = oneIndex.get(i);
                    result += (end - start)*(end - start + 1) / 2;
                }
                int lastOne = oneIndex.get(oneIndex.size() - 1);
                result += (A.length - lastOne)*(A.length - lastOne -1) / 2;
                return result;
            }
        } else {
            //排列组合 闸板法
            int result = 0;
            for (int i = 0; i < oneIndex.size() - S + 1; i++) {
                int start = oneIndex.get(i);
                int end = oneIndex.get(i + S - 1);
                int left = (start == 0 || i == 0) ? (start + 1) : (start - oneIndex.get(i - 1));
                int right = end == (A.length - 1) ? 1 : (i + S >= oneIndex.size()) ? (A.length - end) : (oneIndex.get(i + S) - end);
                result += left * right;
            }
            return result;
        }
    }

    public static void main(String[] args) {
        ArrayAdd test = new ArrayAdd();
        System.out.println(test.numSubarraysWithSum(new int[]{0, 0, 0, 0, 0}, 0));
        System.out.println(test.numSubarraysWithSum(new int[]{1, 0, 1, 0, 1}, 2));
        System.out.println(test.numSubarraysWithSum(new int[]{0, 1, 1, 1, 1}, 3));
        //[0,0,0,0,0,0,1,0,0,0]
        //0
        System.out.println(test.numSubarraysWithSum(new int[]{0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, 0));
        //[1,0,0,0,0,0,0,0,0,0]
        //1
        System.out.println(test.numSubarraysWithSum(new int[]{1,0,0,0,0,0,0,0,0,0}, 1));
    }
}

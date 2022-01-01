package exec.dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/**
 * # 1884
 * @author yuki
 * Created on 2021-12-29
 */
public class TwoEggDrop {

    public int twoEggDrop(int n) {
        return eggDropHelper(2, n);
    }

    Map<String, Integer> map = new HashMap<>();
    private int eggDropHelper(int eggExist, int high) {
        if (high == 0) {
            return 0;
        }
        if (eggExist == 1) {
            return high;
        }

        String key = eggExist + "," + high;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        int num = Integer.MAX_VALUE;
        for (int i = 1; i <= high; i++) {
            //操作一次
            int broken = eggDropHelper(eggExist - 1,i - 1);
            int nowBroken = eggDropHelper(eggExist, high - i);
            num = Math.min(num, Math.max(broken, nowBroken) + 1);
        }
        map.put(key, num);
        return num;
    }

    public static void main(String[] args) {
        TwoEggDrop drop = new TwoEggDrop();
        System.out.println(drop.twoEggDrop(6));
    }
}

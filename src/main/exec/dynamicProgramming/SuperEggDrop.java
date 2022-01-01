package exec.dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuki
 * Created on 2021-12-30
 */
public class SuperEggDrop {

    Map<String, Integer> map = new HashMap<>();
    public int superEggDrop(int eggExist, int high) {
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
            int broken = superEggDrop(eggExist - 1,i - 1);
            int nowBroken = superEggDrop(eggExist, high - i);
            num = Math.min(num, Math.max(broken, nowBroken) + 1);
        }
        map.put(key, num);
        return num;
    }


    public int superEggDrop2(int k, int n) {
        if (n == 0) {
            return 0;
        }
        if (k == 1) {
            return n;
        }
        String key = k + "," + n;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        int num = Integer.MAX_VALUE;
        //确定i的取值
        int low = 1, high = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int broken = superEggDrop2(k - 1,mid - 1);
            int notBroken = superEggDrop2(k, n - mid);
            if (broken > notBroken) {
                high = mid - 1;
                num = Math.min(num, broken + 1);
            } else {
                //notBroken
                low = mid + 1;
                num = Math.min(num, notBroken + 1);
            }
        }
        map.put(key, num);
        return num;
    }

    public static void main(String[] args) {
        SuperEggDrop drop = new SuperEggDrop();
        System.out.println(drop.superEggDrop(2, 6));
    }

}

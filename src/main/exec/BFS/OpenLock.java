package exec.BFS;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * # 752 Open the Lock
 * @author yuki
 * Created on 2021-12-23
 */
public class OpenLock {

    public int openLock(String[] deadends, String target) {
        int result = 0;
        String start = "0000";
        if (start.equals(target)) {
            return result;
        }
        Set<String> deadSet = Arrays.stream(deadends).collect(Collectors.toSet());
        if (deadSet.contains(start)) {
            return -1;
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++){
                String cur = queue.poll();
                //当前值为目标值
                if (target.equals(cur)) {
                    return result;
                }
                for (int t = 0; t < 4; t++) {
                    String up = turn(cur,t, true);
                    if (!deadSet.contains(up) && !visited.contains(up)) {
                        queue.add(up);
                        visited.add(up);
                    }
                    String down = turn(cur, t, false);
                    if (!deadSet.contains(down) && !visited.contains(down)) {
                        queue.add(down);
                        visited.add(down);
                    }
                }
            }
            result++;
        }
        return -1;
    }

    public int openLock2(String[] deadends, String target) {
        int result = 0;
        String start = "0000";
        if (start.equals(target)) {
            return result;
        }
        Set<String> deadSet = Arrays.stream(deadends).collect(Collectors.toSet());
        if (deadSet.contains(start)) {
            //直接凉凉
            return -1;
        }

        Set<String> q1 = new HashSet<>();
        Set<String> q2 = new HashSet<>();

        q1.add(start);
        q2.add(target);

        while (!q1.isEmpty() && !q2.isEmpty()) {
            //当前层的扩展
            Set<String> temp = new HashSet<>();
            //遍历当前层
            for (String cur : q1) {
                //取得当前扩散层
                if (deadSet.contains(cur)) {
                    //不做扩展
                    continue;
                }
                if (q2.contains(cur)) {
                    return result;
                }
                //做扩散
                for (int t = 0; t < 4; t++) {
                    String up = turn(cur,t, true);
                    if (!deadSet.contains(up)) {
                        temp.add(up);
                    }
                    String down = turn(cur, t, false);
                    if (!deadSet.contains(down)) {
                        temp.add(down);
                    }
                }
            }
            result++;
            //首尾切换
            q1 = q2;
            q2 = temp;
        }
        return -1;
    }


    /**
     * 转动一次锁
     * @param s 原始数据
     * @param index 转动序列
     * @param up 是否向上
     * @return 转动后
     */
    public String turn(String s, int index, boolean up) {
        char[] chars = s.toCharArray();
        char origin = s.charAt(index);
        char after;
        if (origin == '0' && !up) {
            //原始数值为0时向上
            after = '9';
        } else if (origin == '9' && up) {
            after = '0';
        } else if (up) {
            after = (char) (origin + 1);
        } else {
            //trun down
            after = (char) (origin - 1);
        }

        chars[index] = after;
        return new String(chars);
    }

    public static void main(String[] args) {
        OpenLock lock = new OpenLock();
        System.out.println(lock.openLock2(new String[] {"0201","0101","0102","1212","2002"}, "0202"));
        System.out.println(lock
                .openLock2(new String[] {"8887","8889","8878","8898","8788","8988","7888","9888"}, "8888"));
    }

}

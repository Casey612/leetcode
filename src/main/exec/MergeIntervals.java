package exec;

import java.util.*;

/**
 * @author yuzhe
 * @since 3/15/19
 */
public class MergeIntervals {

    public static List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new ArrayList<>();
        if(intervals == null ||  intervals.size() == 0){
            return result;
        }
        intervals.sort(Comparator.comparingInt(o -> o.start));
        LinkedList<Interval> queue = new LinkedList<>();
        queue.add(intervals.get(0));
        for (int i = 1; i < intervals.size(); i++) {
            Interval cur = intervals.get(i);
            Interval temp = queue.pollLast();
            if (cur.start <= temp.end) {
                queue.add(new Interval(temp.start, Integer.max(cur.end, temp.end)));
            } else {
                queue.add(temp);
                queue.add(cur);
            }
        }
        while(!queue.isEmpty()){
            result.add(queue.pollFirst());
        }
        return result;
    }

    public static List<Interval> merge2(List<Interval> intervals) {
        intervals.sort(Comparator.comparingInt(o -> o.start));
        for (int i = 0; i <= intervals.size() - 2;) {
            Interval pre = intervals.get(i);
            Interval tail = intervals.get(i + 1);
            if (tail.start <= pre.end) {
                tail.start = pre.start;
                tail.end = Integer.max(pre.end, tail.end);
                intervals.remove(i);
            }else {
                i++;
            }
        }
        return intervals;
    }

    public static void main(String[] args) {
        List<Interval> arg = new ArrayList<>();
        arg.add(new Interval(1,3));
        arg.add(new Interval(2,6));
        arg.add(new Interval(8,10));
        arg.add(new Interval(15,18));
        System.out.println(merge(arg));
    }

}



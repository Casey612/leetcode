package exec.NumberProcess;

import java.util.*;

/**
 * @author yuzhe
 * @since 3/15/19
 */
public class InsertInterval {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        intervals.add(newInterval);
        intervals.sort(Comparator.comparingInt(o -> o.start));
        for (int i = 0; i <= intervals.size() - 2; ) {
            Interval pre = intervals.get(i);
            Interval tail = intervals.get(i + 1);
            if (tail.start <= pre.end) {
                tail.start = pre.start;
                tail.end = Integer.max(pre.end, tail.end);
                intervals.remove(i);
            } else {
                i++;
            }
        }
        return intervals;
    }

    public static List<Interval> insert2(List<Interval> intervals, Interval newInterval) {
        intervals.sort((o1, o2) ->
                Integer.compare(o1.start, o2.start) == 0 ? Integer.compare(o1.end, o2.end) : Integer.compare(o1.start, o2.start));
        boolean mergeFlag = false;
        for (int i = 0; i < intervals.size(); i++) {
            Interval cur = intervals.get(i);
            //要有交集
            if (newInterval != null && cur.end >= newInterval.start && cur.start <= newInterval.end) {
                if (cur.start > newInterval.start && cur.end < newInterval.end) {
                    cur.end = newInterval.end;
                    cur.start = newInterval.start;
                } else if (cur.start > newInterval.start && cur.end >= newInterval.end) {
                    cur.start = newInterval.start;
                } else if (cur.start <= newInterval.start && cur.end < newInterval.end) {
                    cur.end = newInterval.end;
                }
                mergeFlag = true;
                newInterval = null;
            } else if (newInterval != null && cur.start > newInterval.end) {
                intervals.add(i, newInterval);
                newInterval = null;
                break;
            }
            if (mergeFlag && i <= intervals.size() - 2) {
                Interval pre = intervals.get(i);
                Interval tail = intervals.get(i + 1);
                if (pre.end >= tail.start) {
                    //合并
                    tail.start = pre.start;
                    tail.end = Integer.max(pre.end, tail.end);
                    intervals.remove(i);
                    i--;
                    mergeFlag = true;
                } else {
                    mergeFlag = false;
                }
            }
        }
        if (newInterval != null) {
            intervals.add(newInterval);
        }
        return intervals;
    }

    public static List<Interval> insert3(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        for (Interval interval : intervals) {
            if (newInterval == null || interval.end < newInterval.start) {
                result.add(interval);
            } else if (interval.start > newInterval.end) {
                result.add(newInterval);
                result.add(interval);
                newInterval = null;
            } else {
                newInterval.start = Math.max(newInterval.start, interval.start);
                newInterval.end = Math.max(newInterval.end, interval.end);
            }
        }
        if (newInterval != null) {
            result.add(newInterval);
        }
        return result;
    }

    public static void main(String[] args) {
        List<Interval> arg = new ArrayList<>();
        arg.add(new Interval(1, 3));
        arg.add(new Interval(6, 9));
//        arg.add(new Interval(8,10));
//        arg.add(new Interval(15,18));
        System.out.println(insert2(arg, new Interval(2, 5)));
    }

}

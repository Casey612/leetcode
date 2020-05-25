package exec.map;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache {

    Map<Integer, Integer> valueMap;
    Map<Integer, Integer> timesMap;

    int minTimeKey = -1;
    int capacity = 0;

    public LRUCache(int capacity) {
        valueMap = new LinkedHashMap<>(capacity);
        timesMap = new HashMap<>(capacity);
        this.capacity = capacity;
    }

    public int get(int key) {
        if (valueMap.containsKey(key)) {
            int times = timesMap.getOrDefault(key, 0) + 1;
            timesMap.put(key, times);
            minTimeKey = findMinKey();
        }
        return valueMap.getOrDefault(key, -1);
    }

    private int findMinKey() {
        int times = Integer.MAX_VALUE, result = -1;
        for (int key : valueMap.keySet()) {
            int curTime = timesMap.get(key);
            if (curTime < times) {
                times = curTime;
                result = key;
            }
        }
        return result;
    }

    public void put(int key, int value) {
        if (valueMap.size() >= capacity && !valueMap.containsKey(key)) {
            //需要清除一个key
            valueMap.remove(minTimeKey);
            timesMap.remove(minTimeKey);
        }
        valueMap.put(key, value);
        timesMap.put(key, 0);
        minTimeKey = findMinKey();
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(2, 1);
        cache.put(2, 2);
        System.out.println(cache.get(2));
        cache.put(1, 1);
        cache.put(4, 1);
        System.out.println(cache.get(2));
    }

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

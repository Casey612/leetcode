package exec.map;

import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache {

    Map<Integer, Integer> valueMap;

    int minTimeKey = -1;
    int capacity = 0;

    public LRUCache(int capacity) {
        valueMap = new LinkedHashMap<>(capacity);
        this.capacity = capacity;
    }

    public int get(int key) {
       if (valueMap.containsKey(key)) {
           int value = valueMap.get(key);
           valueMap.remove(key);
           valueMap.put(key, value);
           minTimeKey = valueMap.keySet().iterator().next();
           return value;
       } else {
           return -1;
       }
    }

    public void put(int key, int value) {
        if (valueMap.size() >= capacity && !valueMap.containsKey(key)) {
            //容量不够, 删除节点
            valueMap.remove(minTimeKey);
        } else if (valueMap.containsKey(key)) {
            valueMap.remove(key);
        }
        valueMap.put(key, value);
        minTimeKey = valueMap.keySet().iterator().next();
    }

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

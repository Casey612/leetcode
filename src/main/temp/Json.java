package temp;

import java.util.*;

/**
 * JSON格式转换
 * 在某个特定应用场景中，我们有一个从JSON获取的内容，比如：
 * m = { "a": 1, "b": { "c": 2, "d": [3,4] } }
 * 现在需要把这个层级的结构做展开，只保留一层key/value结构。对于上述输入，需要得到的结构是：
 * o = {"a": 1, "b.c": 2, "b.d": [3,4] }
 * 也就是说，原来需要通过 m["b"]["c"] 访问的值，在展开后可以通过 o["b.c"] 访问。
 * 请实现这个“层级结构展开”的代码。
 * 输入：任意JSON（或者map/dict）
 * 输出：展开后的JSON（或者map/dict）
 */
public class Json {

    public Map parse(Map<String, Object> param) {
        return parseHelper(param, "");
    }

    public Map<String, Object> parseHelper(Map<String, Object> map, String preKey) {
        if (Objects.isNull(map)) {
            return Collections.emptyMap();
        }
        preKey = preKey == null ? "" : preKey;
        Map<String, Object> result = new HashMap<>();
        for (String key : map.keySet()) {
            Object obj = map.get(key);
            if (obj instanceof Map) {
                Map<String, Object> tempMap = (Map<String, Object>) obj;
                Map<String, Object> temp = parseHelper(tempMap, key);
                result.putAll(temp);
            } else {
                String newKey = "".equals(preKey) ? key : preKey + "." + key;
                result.put(newKey, obj);
            }
        }
        return result;
    }


    public Map<String, Object> parseHelper2(Map<String, Object> map) {
        if (Objects.isNull(map)) {
            return Collections.emptyMap();
        }
        Map<String, Object> result = new HashMap<>();
        Stack<Map.Entry<String, Object>> stack = new Stack<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            stack.push(entry);
        }
        while (!stack.isEmpty()) {
            Map.Entry<String, Object> cur = stack.pop();
            if (cur.getValue() instanceof Map) {
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    String newKey = cur.getKey() + "." + entry.getKey();
                    Map.Entry<String, Object> newEntry = new AbstractMap.SimpleEntry<String, Object>(newKey, cur.getValue());
                    stack.push(newEntry);
                }
            } else {
                result.put(cur.getKey(), cur.getValue());
                stack.pop();
            }
        }
        return result;
    }


}

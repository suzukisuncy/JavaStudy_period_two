package cn.tedu.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Map的遍历
 * Map的三种遍历方式
 * ①单独遍历key
 * ②单独遍历value(基本不用)
 * ③遍历每一组键值对
 */
public class MapDemo02 {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("语文", 90);
        map.put("数学", 99);
        map.put("物理", 80);
        map.put("化学", 85);
        System.out.println(map);
        System.out.println("------------------单独遍历key-------------------");
        //将map中所有的key值都存在Set集合中
        Set<String> keySet = map.keySet();
        for (String k : keySet) {
            System.out.println("k = " + k);
        }
        System.out.println("------------------单独遍历value-------------------");
        //将map中的所有value都存在Collection集合中
        Collection<Integer> values = map.values();
        for (Integer value : values) {
            System.out.println("value = " + value);
        }
        System.out.println("------------------遍历每一组键值对-------------------");
        //将map中的所有的entry存储在Set集合中 entry就是键值对实例对象
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + " = " + value);
        }
        System.out.println("------------------遍历每一组键值对-------------------");
    }
}

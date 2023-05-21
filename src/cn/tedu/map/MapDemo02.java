package cn.tedu.map;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Map的遍历
 * Map的三种遍历方式
 * ①单独遍历key
 * ②单独遍历value(基本不用)
 * ③遍历每一组键值对
 * <p>
 * JDK8之后,集合和Map都支持了基于Lambda表达式的遍历
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
        System.out.println("------------------Lambda表达式遍历Map-------------------");
        map.forEach(
                (k, v) -> System.out.println(k + " = " + v)
        );
        System.out.println("------------------Lambda表达式遍历集合-------------------");
        Collection<String> c = new ArrayList<>();
        c.add("A");
        c.add("B");
        c.add("C");
        c.add("D");
        c.forEach(
                (e) -> System.out.println(e)
        );
        //参数只有一个时,可以省略小括号
        c.forEach(
                e -> System.out.println(e)
        );
        //如果输出的参数和传入的参数是同一个时,就可以省略参数,替换::
        c.forEach(
                System.out::println
        );
    }
}

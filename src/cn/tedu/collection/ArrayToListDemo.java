package cn.tedu.collection;

import java.util.Arrays;
import java.util.List;

/**
 * 数组转换为集合
 */
public class ArrayToListDemo {
    public static void main(String[] args) {
        String[] array = {"A", "B", "C", "D", "E"};
        System.out.println("数组array = " + Arrays.toString(array));
        List<String> list = Arrays.asList(array);
        System.out.println("集合list = " + list);
    }
}

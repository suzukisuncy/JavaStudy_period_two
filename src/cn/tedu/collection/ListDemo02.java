package cn.tedu.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * 给指定的下标位置添加元素或者删除元素
 */
public class ListDemo02 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        System.out.println("list = " + list);
        //将元素添加到集合的最后面
        list.add("F");
        //将元素添加到指定的位置
        /*
         * void add(int index, E element);
         * 将给定元素element添加到指定的index位置,然后将原位置的元素整体后移
         */
        list.add(3, "★");
        System.out.println("list = " + list);
        String str = list.remove(3);
        System.out.println("删除了: " + str);
        System.out.println("list = " + list);
    }
}

package cn.tedu.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 学习List中定义的方法
 */
public class ListDemo01 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        System.out.println("list = " + list);
        //E get(int index) 取出指定下标位置的元素(下标起始是0)
        String str = list.get(2);
        System.out.println("str = " + str);
        //通过下标遍历集合
        for (int i = 0; i < list.size(); i++) {
            str = list.get(i);
            System.out.println("集合中的第" + (i + 1) + "个元素: " + str);
        }
        /*
         * E set(int index, E element);
         * 将给定的元素element设置到指定的index位置上,并将替换的元素返回
         */
        str = list.set(2, "★");
        System.out.println("被替换的元素 = " + str);
        System.out.println("list = " + list);
        //翻转集合 java.util.Collections 集合的工具类
        Collections.reverse(list);
        System.out.println("翻转后的集合: " + list);
        ArrayList<String> list1 = new ArrayList<>();
        Collections.addAll(list1, "1","2","3","4","5");
        System.out.println("list1 = " + list1);
    }
}

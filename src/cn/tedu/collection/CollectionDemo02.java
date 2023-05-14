package cn.tedu.collection;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionDemo02 {
    public static void main(String[] args) {
        Collection c = new ArrayList();
        c.add(new Point(1, 2));
        c.add(new Point(3, 4));
        c.add(new Point(5, 6));
        c.add(new Point(7, 8));
        c.add(new Point(9, 0));
        /*
         * 集合重写了toString方法
         * 格式:
         * [元素1.toString(),元素2.toString(),...]
         */
        System.out.println(c);
        Point p = new Point(3, 4);
        /*
         * boolean contains(Object o);
         * 判断当前集合中是否包含给定的元素
         * 集合是否包含给定的元素取决于给定的元素和集合中的元素通过equals比较的结果是否为true
         * Object中提供的equals方法,该方法作用是比较内存地址
         * 如果希望equals比较的是内容是否相同时,需要重写equals方法
         * alt+insert→equals and hashcode→Next→Next→Finish
         */
        boolean contains = c.contains(p);
        Point p2 = new Point(3, 4);
        System.out.println("集合c中是否包含给定的(3,4)点:" + contains);
    }
}

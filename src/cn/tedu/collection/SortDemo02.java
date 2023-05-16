package cn.tedu.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 集合的自定义排序
 */
public class SortDemo02 {
    public static void main(String[] args) {
        List<Point> list = new ArrayList<>();
        list.add(new Point(1, 4));
        list.add(new Point(3, 12));
        list.add(new Point(2, 5));
        list.add(new Point(0, 8));
        list.add(new Point(3, 3));
        list.add(new Point(9, 2));
        System.out.println("乱序: " + list);
        /*
         * Collections提供的sort方法,如果对集合进行排序,必要要求集合中的元素要实现Comparable接口,
         * 该接口中的compareTo方法定义排序规则
         * sort方法会自动将集合中的两个元素进行比较,而比较时,会通过重写的compareTo方法比较,
         * 格式
         * A.compareTo(B) A就是调用该方法的集合的参数 B就是和A比较的集合的参数
         * A 大于 B 返回正数
         * A 等于 B 返回0
         * A 小于 B 返回负数
         */
        Collections.sort(list);
        System.out.println("正序:" + list);
    }
}

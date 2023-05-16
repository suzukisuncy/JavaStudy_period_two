package cn.tedu.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * 集合转换为数组
 */
public class CollectionToArrayDemo {
    public static void main(String[] args) {
        Collection<String> c = new ArrayList<>();
        c.add("A");
        c.add("B");
        c.add("C");
        c.add("D");
        c.add("E");
        System.out.println("集合c = " + c);
        /*
         * Object[] toArray();
         * 将当前集合转换为一个Object类型的数组
         * 由于返回的是一个Object类型的数组,所以用的很少
         */
        Object[] array = c.toArray();
        /*
         * T[] toArray(T[] a);
         * 将当前集合转换为给定数组类型的数组
         * 其中参数声明的数组有一些要注意的事项:
         * ①定义的数组类型,要和待转换的集合的泛型最好一致
         * ②定义的数组长度,最好和待转换的集合的存储的元素个数一致
         */
        String[] array2 = c.toArray(new String[c.size()]);
        System.out.println("数组的长度:" + array2.length);
        System.out.println("数组array2 = " + Arrays.toString(array2));
    }
}

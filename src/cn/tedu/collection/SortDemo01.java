package cn.tedu.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 集合的排序
 * 集合的工具类Collections中提供了一个静态的sort方法,可以对集合中的元素进行自然排序
 */
public class SortDemo01 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            list.add(random.nextInt(100));
        }
        System.out.println("乱序list = " + list);
        Collections.sort(list);//自然排序,从小到大
        System.out.println("自然排序list = " + list);
    }
}

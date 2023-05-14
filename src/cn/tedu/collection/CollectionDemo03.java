package cn.tedu.collection;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 集合间的操作
 */
public class CollectionDemo03 {
    public static void main(String[] args) {
        Collection c1 = new ArrayList();
        c1.add("大娃");
        c1.add("二娃");
        c1.add("三娃");
        System.out.println("c1 = " + c1);
        Collection c2 = new ArrayList();
        c2.add("四娃");
        c2.add("五娃");
        //c2.add("大娃");
        System.out.println("c2 = " + c2);
        /*
         * boolean addAll(Collection c);
         * 将给定集合中的所有元素都添加到当前集合中(取并集)
         * 操作后,如果当前集合发生了改变,则返回true
         */
        c1.addAll(c2);
        System.out.println("c1 = " + c1);
        System.out.println("c2 = " + c2);
        Collection c3 = new ArrayList();
        c3.add("二娃");
        c3.add("大娃");
        c3.add("七娃");
        System.out.println("c3 = " + c3);
        /*
         * boolean containsAll(Collection c);
         * 判断当前集合中是否包含给定集合中的所有元素
         */
        boolean b = c1.containsAll(c3);
        System.out.println("c1集合是否包含c3集合:" + b);
        /*
         * boolean retainAll(Collection c);
         * 将当前集合中的元素保留和给定集合中的元素相同的部分
         */
        c1.retainAll(c3);
        System.out.println("c1 = " + c1);
        System.out.println("c3 = " + c3);
        c1.add("小明");
        /*
         * boolean removeAll(Collection c);
         * 将当前集合中和给定集合中共有的元素删除
         */
        c1.removeAll(c3);
        System.out.println("c1 = " + c1);
        System.out.println("c3 = " + c3);
    }
}

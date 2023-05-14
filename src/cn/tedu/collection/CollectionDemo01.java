package cn.tedu.collection;

import java.util.ArrayList;
import java.util.Collection;

/**
 * JAVA集合类
 * 集合和数组一样,都是用于保存一组数据,但是集合将元素的操作都封装成了方法,操作更加简便
 * 并且集合提供了很多不同的实现类供我们使用
 * <p>
 * java.util.Collection是所有集合的顶级接口,里面定义了所有集合都必须具备的功能方法
 * 集合的两类常用子类:
 * ①java.util.List: 可以重复的集合,且有序,通常我们称为叫做线性表
 * ②java.util.Set: 不可以重复的集合
 * 上述两个集合都是接口,而元素是否重复取决于equals方法
 */
public class CollectionDemo01 {
    public static void main(String[] args) {
        Collection c = new ArrayList();
        /*
         * boolean add(E e);
         * 添加指定元素到集合中,添加成功返回true,否则返回false
         * 此处的参数E其实是泛型,先理解为是Object类型
         * 集合存储的元素必须要是引用类型
         * 并且一般使用时,集合中存储的元素的类型都是一致的
         */
        c.add("one");
        c.add("two");
        c.add("three");
        c.add("four");
        c.add("five");
        System.out.println(c);
        c.add("six");
        c.add("seven");
        //1是基本类型,此处触发自动装箱特性 int→Integer
        c.add(1);
        c.add(true);
        c.add(new Object());
        System.out.println(c);
        /*
         * int size();
         * 返回当前集合中的元素个数
         * size要区别与数组的length
         * length表示的数组能存储多少个元素(如果数组中的元素不够长度那么长,但是长度是不会变化的)
         * size表示集合中现在有几个元素
         */
        System.out.println(c.size());
        /*
         * boolean isEmpty();
         * 判断集合是否为一个空集合,空集合等价于集合的size方法返回0
         */
        System.out.println("集合是否为空集:" + c.isEmpty());
        /*
         * 清空集合,将集合中存储的所有元素删除
         */
        System.out.println("开始清空集合!");
        c.clear();
        System.out.println("集合是否为空集:" + c.isEmpty());
        System.out.println(c.size());
    }
}

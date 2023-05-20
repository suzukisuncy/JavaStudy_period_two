package cn.tedu.collection;

import java.util.*;

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
         * 内部比较器
         * Collections提供的sort方法,如果对集合进行排序,必要要求集合中的元素要实现Comparable接口,
         * 该接口中的compareTo方法定义排序规则
         * sort方法会自动将集合中的两个元素进行比较,而比较时,会通过重写的compareTo方法比较,
         * 格式
         * A.compareTo(B) A就是调用该方法的集合的参数 B就是和A比较的集合的参数
         * A 大于 B 返回正数
         * A 等于 B 返回0
         * A 小于 B 返回负数
         * 但是上述的方式具有侵入性,为了调用该API,反而要求我们去修改其他的代码,导致代码结构出现混乱,
         * 就造成了侵入性,侵入性不利于后期维护,尽可能的避免
         */
        //Collections.sort(list);
        /*
         * 外部比较器
         * 创建Comparator比较器实例,然后重写compare方法,定义比较规则
         * 然后将比较器实例传入到sort方法的第二个参数中,sort方法就会自动根据该比较器定义的规则,进行排序
         * 格式
         * int compare(Point o1, Point o2)
         * o1 大于 o2 返回正数
         * o1 等于 o2 返回0
         * o1 小于 o2 返回负数
         */
        Comparator<Point> com = new Comparator<Point>() {
            /**
             * 定义比较规则
             */
            @Override
            public int compare(Point o1, Point o2) {
                //比较y坐标的大小,y坐标越大,该点就越大
                int y1 = o1.getY();
                int y2 = o2.getY();
                return y1 - y2;
            }
        };
        Collections.sort(list, com);
        System.out.println("正序:" + list);
    }
}

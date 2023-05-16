package cn.tedu.collection;

import java.util.Objects;

public class Point implements Comparable<Point> {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    //alt+insert→equals and hashcode→Next→Next→Finish
    @Override
    public boolean equals(Object o) {
        /*
         * p2.equals(p);
         * this: 指的调用当前方法的实例 就是p2
         * o: 指的是传递的参数 就是p
         */
        if (this == o) return true;
        /*
         * ①如果传入的对象为空,则没有可比性,直接返回false
         * ②如果p2和p不是同一个类的实例,则直接返回false
         */
        if (o == null || getClass() != o.getClass()) return false;
        //由于需要比较熟悉,而参数是Object,需要向下转换为原类型
        Point point = (Point) o;
        //开始进行两个对象的属性值的比较 p2的x和p的x是否相同,p2的y和p的y是否相同
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * 定义排序规则
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Point o) {
        //定义比教两个点的x坐标
        int x1 = this.getX();
        int x2 = o.getX();
        // //判断x1大于x2说明x1大
        // if (x1 - x2 > 0) {
        //     return 1;
        // } else if (x1 - x2 < 0) {//说明x2大
        //     return -1;
        // } else {//x1和x2相等
        //     return 0;
        // }
        return x1 - x2;
    }
}

package cn.tedu.collection;

import java.util.Objects;

public class Point {
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
}

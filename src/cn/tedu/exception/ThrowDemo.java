package cn.tedu.exception;

/**
 * 模拟异常的抛出
 */
public class ThrowDemo {
    public static void main(String[] args) {
        Person p = new Person();
        //满足语法,但是不满业务需求
        p.setAge(10000);
        System.out.println("此人年龄:" + p.getAge() + "岁");
    }
}

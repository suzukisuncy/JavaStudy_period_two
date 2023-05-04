package cn.tedu.exception;

/**
 * @author 老安
 * @data 2023-05-04 21:52
 */
public class Person {
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        //判断录入的年龄是否符合需求
        if (age < 0 || age > 100) {
            //此处可以抛异常
            throw new NullPointerException("您录入的年龄不合法!!");
        }
        this.age = age;
    }
}

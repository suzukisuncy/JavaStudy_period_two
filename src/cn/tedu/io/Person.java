package cn.tedu.io;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 使用该类的实例,测试对象流的内容
 */
public class Person implements Serializable {
    //固定当前类的版本号为42
    static final long serialVersionUID = 42L;
    private String name;
    private int age;
    private String gender;
    /*
     * transient可以将修饰的属性在进行序列化时,忽略该属性的值,
     * 当我们反序列化时,改属性的值将不会被读取,以此达到一个对象瘦身的目的,
     * 从而提高程序的响应速度,减少资源开销
     */
    private transient String[] otherInfo;
    private double salary;
    //生成全参构造
    public Person(String name, int age, String gender, String[] otherInfo) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.otherInfo = otherInfo;
    }

    //生成get和set方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String[] getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String[] otherInfo) {
        this.otherInfo = otherInfo;
    }

    //生成toString方法
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", otherInfo=" + Arrays.toString(otherInfo) +
                '}';
    }
}

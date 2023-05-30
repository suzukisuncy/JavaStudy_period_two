package cn.tedu.reflect.pojo;

import cn.tedu.reflect.annotation.AutoRunClass;
import cn.tedu.reflect.annotation.AutoRunMethod;

@AutoRunClass
public class Person {
    private String name = "张三";
    private int age = 18;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void eat() {
        System.out.println(name + ": 在吃饭!");
    }

    @AutoRunMethod
    public void sleep() {
        System.out.println(name + ": 在睡觉!");
    }

    public void say() {
        System.out.println(name + ": 在说话!");
    }

    @AutoRunMethod(2)
    public void sing() {
        System.out.println(name + ": 在唱歌!");
    }

    private void secret() {
        System.out.println(name + "的小秘密,不能被人知道");
    }

    public void dosome(String thing) {
        System.out.println(name + "正在" + thing);
    }

    public void dosome(String thing, int num) {
        for (int i = 1; i <= num; i++) {
            System.out.println(name + "正在第" + i + "次" + thing);
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

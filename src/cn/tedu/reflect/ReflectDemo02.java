package cn.tedu.reflect;

import cn.tedu.reflect.pojo.Person;
import cn.tedu.reflect.pojo.Student;

import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * 通过反射获取类中方法
 * cn.tedu.reflect.pojo.Person
 * cn.tedu.reflect.pojo.Student
 * java.lang.String
 */
public class ReflectDemo02 {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("请输入想获取的类的全路径名:");
        String line = new Scanner(System.in).nextLine();
        Class cls = Class.forName(line);
        //getDeclaredMethods() 获取当前类中的自身定义的所有的方法(包含私有的方法)
        Method[] methods = cls.getDeclaredMethods();
        System.out.println(cls.getSimpleName() + "类中共有" + methods.length + "个自定义的方法");
        for (Method method : methods) {
            System.out.println(method.getName());
        }
        System.out.println("※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※");
        //getMethods() 获取类中的所有的公开方法(包含父类中继承的)
        methods = cls.getMethods();
        System.out.println(cls.getSimpleName() + "类中共有" + methods.length + "个公开的方法");
        for (Method method : methods) {
            System.out.println(method.getName());
        }
    }
}

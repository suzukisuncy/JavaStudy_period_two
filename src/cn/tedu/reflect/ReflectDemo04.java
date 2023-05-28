package cn.tedu.reflect;

import java.lang.reflect.Constructor;
import java.util.Scanner;

/**
 * 通过反射创建指定参数的实例对象
 */
public class ReflectDemo04 {
    public static void main(String[] args) throws Exception {
        Class cls = Class.forName("cn.tedu.reflect.pojo.Person");
        //getConstructor 获取指定的构造器,传入对应的构造器的参数的Class实例
        Constructor c = cls.getConstructor(String.class, int.class);
        //newInstance 执行当前构造器,并且接收传入的参数,参数顺序需要和构造器保持一致
        Object o = c.newInstance("张飞", 34);
        System.out.println(o);
    }
}

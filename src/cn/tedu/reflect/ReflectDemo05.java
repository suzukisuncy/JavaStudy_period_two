package cn.tedu.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 通过反射调用方法
 */
public class ReflectDemo05 {
    public static void main(String[] args) throws Exception {
        Class cls = Class.forName("cn.tedu.reflect.pojo.Person");
        //创建实例对象,方便后面调用方法
        Object o = cls.newInstance();
        //getMethod 获取指定的方法
        Method say = cls.getMethod("say");
        //o.say() invoke执行当前方法,并将方法对象作为参数传入
        say.invoke(o);
        Method doSome = cls.getMethod("doSome", String.class);//获取doSome(String thing)
        //invoke执行当前方法,并将方法对象作为参数传入,且将方法所需参数传入
        doSome.invoke(o, "打豆豆");
        doSome = cls.getMethod("doSome", String.class, int.class);
        doSome.invoke(o, "喝水", 5);
    }
}

package cn.tedu.reflect;

import com.sun.org.apache.xpath.internal.operations.Mod;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 自动调用Person类中所有名字含有"s"的无参的公开的方法
 */
public class Test01 {
    public static void main(String[] args) throws Exception {
        Class cls = Class.forName("cn.tedu.reflect.pojo.Person");
        Object o = cls.newInstance();
        //获取Person类中的所有方法
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {//methods.for
            //筛选出名字含有"s"的方法
            if (method.getName().contains("s")) {
                //筛选出公开的方法 getModifiers 获取方法的权限
                if (method.getModifiers() == Modifier.PUBLIC) {
                    //筛选出无参的方法 getParameterCount 获取方法的参数个数
                    if (method.getParameterCount() == 0) {
                        System.out.println("自动执行" +
                                cls.getSimpleName() +
                                "类中方法:" +
                                method.getName());
                        method.invoke(o);
                    }
                }
            }
        }
    }
}

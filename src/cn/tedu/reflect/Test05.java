package cn.tedu.reflect;

import cn.tedu.reflect.annotation.AutoRunClass;
import cn.tedu.reflect.annotation.AutoRunMethod;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 自动调用pojo包下的被@AutoRunClass注解修饰的类中的被@AutoRunMethod注解修饰的方法
 */
public class Test05 {
    public static void main(String[] args) throws Exception {
        //定位Test02这个类所在的包
        File dir = new File(
                Test05.class.getResource("./pojo").toURI()
        );
        //获取包下的所有字节码文件
        File[] subs = dir.listFiles(f -> f.getName().endsWith(".class"));
        for (File sub : subs) {
            String fileName = sub.getName(); //获取文件名 Person.class
            //substring(a,b) 截取字符串中[a,b)范围的内容
            //indexOf("") 获取指定字符串的下标
            String className = fileName.substring(0, fileName.indexOf(".")); //获取类名
            String allName = Test05.class.getPackage().getName() +
                    ".pojo." + className; //获取全路径名
            Class cls = Class.forName(allName); //声明对应类的Class实例,方便后面的反射操作
            if (cls.isAnnotationPresent(AutoRunClass.class)) {//判断当前类是否被@AutoRunClass注解修饰
                Object o = cls.newInstance(); //创建这个类的实例对象
                Method[] methods = cls.getDeclaredMethods();//获取类中定义的所有方法
                for (Method method : methods) {
                    if (method.isAnnotationPresent(AutoRunMethod.class)) {
                        System.out.println("自动调用了" + className + "类中的" + method.getName() + "方法");
                        method.invoke(o);
                    }
                }
            }
        }
    }
}

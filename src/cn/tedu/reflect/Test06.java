package cn.tedu.reflect;

import cn.tedu.reflect.annotation.AutoRunClass;
import cn.tedu.reflect.annotation.AutoRunMethod;

import java.io.File;
import java.lang.reflect.Method;

/**
 * 自动调用pojo包下的被@AutoRunClass注解修饰的类中的被@AutoRunMethod注解修饰的方法,
 * 并且根据@AutoRunMethod注解传入的参数,来执行对应的次数
 */
public class Test06 {
    public static void main(String[] args) throws Exception {
        //定位Test02这个类所在的包
        File dir = new File(
                Test06.class.getResource("./pojo").toURI()
        );
        //获取包下的所有字节码文件
        File[] subs = dir.listFiles(f -> f.getName().endsWith(".class"));
        for (File sub : subs) {
            String fileName = sub.getName(); //获取文件名 Person.class
            //substring(a,b) 截取字符串中[a,b)范围的内容
            //indexOf("") 获取指定字符串的下标
            String className = fileName.substring(0, fileName.indexOf(".")); //获取类名
            String allName = Test06.class.getPackage().getName() +
                    ".pojo." + className; //获取全路径名
            Class cls = Class.forName(allName); //声明对应类的Class实例,方便后面的反射操作
            if (cls.isAnnotationPresent(AutoRunClass.class)) {//判断当前类是否被@AutoRunClass注解修饰
                Object o = cls.newInstance(); //创建这个类的实例对象
                Method[] methods = cls.getDeclaredMethods();//获取类中定义的所有方法
                for (Method method : methods) {
                    if (method.isAnnotationPresent(AutoRunMethod.class)) {
                        AutoRunMethod anno = method.getAnnotation(AutoRunMethod.class);//获取方法上指定的注解
                        int num = anno.value();//获取注解中传入的参数
                        System.out.println("自动调用了" + className + "类中的" + method.getName() + "方法" + num + "次!");
                        for (int i = 0; i < num; i++) {
                            method.invoke(o);
                        }
                    }
                }
            }
        }
    }
}
